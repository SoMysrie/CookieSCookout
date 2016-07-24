"use strict";

const express    = require('express')     ; // Web Middleware
const http       = require('http')        ; // Http web server module
const vhost      = require('vhost')       ; // Virtual domain hosting
const socketio   = require('socket.io')   ; // Realtime web socket framework 
const bodyparser = require('body-parser') ; // Body parsing middleware
const fs         = require('fs')          ; // File I/O
const path       = require('path')        ; // Utilities for handling and transforming file paths
const mysql      = require('mysql')       ; // Mysql driver for node.js
const nodemailer = require('nodemailer')  ; // E-mail sending from your Node.js applications

const app      = express()        ; // Instance of express
const server   = http.Server(app) ; // Using express server 3/4
var   settings = {}               ; // Server settings

// create reusable transporter object using the default SMTP transport 
//const transporter = nodemailer.createTransport('smtps://heltestergo%40gmail.com:testergonomique@smtp.gmail.com');
const transporter = nodemailer.createTransport({
	service: 'Gmail',
	auth: {
	    user: 'heltestergo@gmail.com',
	    pass: 'testergonomique'
	}
	});


// Specify virtual host
const createVirtualHost = function ( domainName , dirPath ) 
{
	return vhost( domainName , express.static( dirPath ) ) ; 
} ;

// Read settings file and launch server
fs.readFile( __dirname + '/settings.json', 'utf8', function ( err ,setting ) {
    if( err )
        return console.error( err ) ;

    try
    {
    	// Parse setting file
    	settings = JSON.parse( setting ) ;

    	// Check minimum settings
    	if( !settings.HOST || !settings.PORT || !settings.PUBDIR )
    		throw new Error( 'Incomplete configuration file !' ) ;

		app.use( bodyparser.json() );      // to support JSON-encoded bodies
		app.use( bodyparser.urlencoded({   // to support URL-encoded bodies
			extended: true
		}) ) ; 

		// Virtual host template
		app.use( createVirtualHost( settings.HOST , settings.PUBDIR ) ) ;

		// We start web server
		const server = app.listen( process.env.PORT || setting.PORT || 8080 , function () {
				const host = server.address().address ;
				const port = server.address().port    ;

				// SocketIO instance
				const io = (socketio)(server) ;

				// Web-Service & Web-Socket
				( require( __dirname + '/inc/webservice.js' ) )({ app: app }) ;
				( require( __dirname + '/inc/websocket.js'  ) )({ io: io , mysql: mysql , mailer: nodemailer, transporter: transporter }) ;

				console.log( 'Server are running at %s:%s', host, port) ;
		}) ;
    }
    catch( e )
    {
    	console.log( e.message ) ;
    	process.exit(1) ;
    }
}) ;