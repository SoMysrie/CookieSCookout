"use strict";

module.exports = function( obj )
{
	if( !obj.app || !obj.mysql )
		throw new Error( "Missing argument !" ) ;

	// 
	obj.app.get( '/recipe/:id/*' , function(req,res,next){

		//
		if( !req.params['id'] || parseInt( req.params['id'] ) < 1 || isNaN( parseInt( req.params['id'] ) ) )
			return res.end( 'Bad id !' ) ; 

		//
		res.set({ 'content-type': 'text/html; charset=utf-8' })

		// 1 connection à la bdd
		// 2 récupération des informations de la recette ayant l'id req.params['id']
		// 3 Affichage de la page ci dessous en remplacant les valeurs par les informations de la recettes

		try
		{	
			var mySqlClient = obj.mysql.createConnection({
			  host     : "localhost",
			  user     : "root",
			  password : "root",
			  database : "CookieSCookout"
			});

			mySqlClient.connect();

			mySqlClient.query(
				"SELECT * FROM Ingredient, COMPOSE, Recipe WHERE COMPOSE.idRecipe = Recipe.idRecipe and Ingredient.idIngredient = COMPOSE.idIngredient and Recipe.idRecipe = " + req.params['id'] + ";" ,
				function select( error, results, fields ) {
					if( error ) 
					{
					    mySqlClient.end();
					    res.end('Internal error !') ;
					    throw error ;
					}

				 	if ( results.length > 0 ) 
				 	{ 
				 		var recipe = null ;

				    	for( var i = 0 ; i < results.length ; i++)
				    	{
				
				    		if( recipe == null )
				    		{
				    			recipe = {
				    				'idRecipe'      : results[i]['idRecipe'] ,
									'imgRecipe'		: results[i]['imgRecipe'] ,
									'titleRecipe' 	: results[i]['titleRecipe'] ,
									'nameIngredient': [ ( results[i]['qty'] != null ? results[i]['qty'] + " -> " : "" ) + results[i]['nameIngredient'] + ( results[i]['note'] != null ? " -> " + results[i]['note'] : "" ) ] ,
									'contentRecipe'	: results[i]['contentRecipe']
								} ;

								continue ; // saute la suite si recipe a bien été null
							}

							recipe.nameIngredient.push(
								 ( results[i]['qty'] != null ? results[i]['qty'] + " -> " : "" ) + results[i]['nameIngredient'] + ( results[i]['note'] != null ? " -> " + results[i]['note'] : "" ) 
							) ;
						} ;
				    
						var ingr = "<ul>" ;

						// Parcoure la liste des ingrédients pour la mettre sous forme de liste
						for( var x = 0 ; x < recipe['nameIngredient'].length ; x++ )
							ingr += "<li>" + recipe['nameIngredient'][x] + "</li>" ;

						ingr += "</ul>" ;

						res.write( "<html>" ) ;
						res.write( "	<head>" ) ;
						res.write( "		<title>CookieSCookout</title>" ) ;
						res.write( "		<link rel='stylesheet' type='text/css' href='/css/fonts.css'>" ) ;
						res.write( "		<link rel='stylesheet' type='text/css' href='/css/global.css'>" ) ;
						res.write( "		<link rel='stylesheet' type='text/css' href='/css/toastr.min.css'>" ) ;
						res.write( "	</head>" ) ;
						res.write( "	<body>" ) ;
						res.write( "		<header><img src='/img/LogoCookieSCookout.png' /></header>" ) ;
						res.write( "		<section id='therecipe'>" ) ;
						res.write( "      <div class='recipe list'>" + recipe.titleRecipe + "<div class='thumbs' style=\"background-image: url('" + recipe.imgRecipe + "');\"></div><div> " + ingr + " <br /> " + recipe.contentRecipe + "</div></div>" ) ;
						res.write( "		</section>" ) ;
						res.write( "		<footer>CookieSCookout &copy; 2016 — Tous droits réservés.</footer>" ) ;
						res.write( "		<script src='/socket.io/socket.io.js'></script>" ) ;
						res.write( "		<script src='/js/jquery-2.2.3.min.js'></script>" ) ;
						res.write( "	</body>" ) ;
						res.write( "</html>" ) ;
						res.end() ;
				    }
				    else 
				    	socket.emit( 'sendsearch' , [] ) ;
				    
					mySqlClient.end() ;
				}) ;
				// function select(error, results, fields) {
				// 	if( error ) 
				// 	{
				// 	    mySqlClient.end();
				// 	    throw new Error( "Erreur de connection !" ) ;
				// 	    throw error ;
				// 	}
			 
				//     if ( results.length > 0 )  
				//     { 
				//     	var recipe = {
				//     		'idRecipe'      : results[0]['idRecipe'] ,
				// 			'imgRecipe'		: results[0]['imgRecipe'] ,
				// 			'titleRecipe' 	: results[0]['titleRecipe'] ,
				// 			'nameIngredient': [ ( results[0]['qty'] != null ? results[0]['qty'] + " -> " : "" ) + results[0]['nameIngredient'] + ( results[0]['note'] != null ? " -> " + results[0]['note'] : "" ) ] ,
				// 			'contentRecipe'	: results[0]['contentRecipe']
				// 		} ;

				// 		recipe.nameIngredient.push(
				// 			( results[0]['qty'] != null ? results[0]['qty'] + " -> " : "" ) + results[0]['nameIngredient'] + ( results[0]['note'] != null ? " -> " + results[0]['note'] : "" ) 
				// 		) ;

				// 		var ingr = "<ul>" ;

				// 		// Parcoure la liste des ingrédients pour la mettre sous forme de liste
				// 		for( var x = 0 ; x < results[0]['nameIngredient'].length ; x++ )
				// 			ingr += "<li>" + results[0]['nameIngredient'] + "</li>" ;

				// 		ingr += "</ul>" ;

				// 		res.write( "<html>" ) ;
				// 		res.write( "	<head>" ) ;
				// 		res.write( "		<title>CookieSCookout</title>" ) ;
				// 		res.write( "		<link rel='stylesheet' type='text/css' href='/css/fonts.css'>" ) ;
				// 		res.write( "		<link rel='stylesheet' type='text/css' href='/css/global.css'>" ) ;
				// 		res.write( "		<link rel='stylesheet' type='text/css' href='/css/toastr.min.css'>" ) ;
				// 		res.write( "	</head>" ) ;
				// 		res.write( "	<body>" ) ;
				// 		res.write( "		<header><img src='/img/LogoCookieSCookout.png' /></header>" ) ;
				// 		res.write( "		<section id='therecipe'>" ) ;
				// 		res.write( "      <div class='recipe list'>" + recipe.titleRecipe + "<div class='thumbs' style=\"background-image: url('" + recipe.imgRecipe + "');\"></div><div> " + ingr + " <br /> " + recipe.contentRecipe + "</div></div>" ) ;
				// 		res.write( "		</section>" ) ;
				// 		res.write( "		<footer>CookieSCookout &copy; 2016 — Tous droits réservés.</footer>" ) ;
				// 		res.write( "		<script src='/socket.io/socket.io.js'></script>" ) ;
				// 		res.write( "		<script src='/js/jquery-2.2.3.min.js'></script>" ) ;
				// 		res.write( "	</body>" ) ;
				// 		res.write( "</html>" ) ;
				// 		res.end() ;
				//     } 
				//     else 
				//     {
				// 		throw new Error ("Pas de données ! ") ;
				//     }

				//     mySqlClient.end();
				// });
		}
		catch( e )
		{
			res.end('Internal error !') ;
			throw e ;
		}
	}) ;
} ;