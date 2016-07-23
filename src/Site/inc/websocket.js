"use strict";

module.exports = function( obj )
{
	if( !obj.io )
		throw new Error( "Aucun websocket défini ! " ) ;

	// Evenement qui se déclenche lors de la connection
	obj.io.on( 'connection' , function( socket ) {
	  	
	  	// Evenement qui se déclenche lors de l'envoi du message
		socket.on( 'sendmail' , function( o ){
			if( !o.name || !o.mail || !o.msg )
				throw new Error("Un des champs est vide ou incorrect !") ;

			try
			{	
				console.log( o ) ;
				
				// Signalement du succès à l'IHM
				socket.emit( 'sendemail' , { status: 'success' } ) ;
			}
			catch( e )
			{
				// Signalement de l'échec à l'IHM
				socket.emit( 'sendemail' , { status: 'error' } ) ;
			}
		}) ;
	}) ;
} ;