"use strict";

module.exports = function( obj )
{
	if( !obj.io || !obj.mysql || !obj.mailer || !obj.transporter )
		throw new Error( "Module manquant ! " ) ;

	// Evenement qui se déclenche lors de la connection
	obj.io.on( 'connection' , function( socket ) {
	  	
	  	// Evenement qui se déclenche lors de l'envoi du message
		socket.on( 'sendmail' , function( o ){
			if( !o.name || !o.mail || !o.msg )
				throw new Error("Un des champs est vide ou incorrect !") ;
			try
			{	
				// setup e-mail data with unicode symbols 
				var mailOptions = {
				    to: '"Helena de CookieSCookout 👥" <heltestergo@gmail.com>', // sender address 
				    from: o.mail, // list of receivers 
				    subject: 'Nouveau message', // Subject line 
				    text: o.msg, // plaintext body 
				    html: '<br>' + o.msg + '</br><br>' + o.name + '</br><br>'+ o.mail + '</br>'// html body 
				};

				// send mail with defined transport object 
				obj.transporter.sendMail( mailOptions , function( error, info ){
				    if(error)
				    	throw error ;
				        
				    // Signalement du succès à l'IHM
					socket.emit( 'sendmail' , { status: 'success' } ) ;
				});
			}
			catch( e )
			{
				// Signalement de l'échec à l'IHM
				socket.emit( 'sendmail' , { status: 'error' } ) ;
			}
		}) ;
		
		
		// Evenement qui se déclenche lors du scrolling pour récupérer les recettes de la bdd
		socket.on( 'needrecipe' , function(data){

			// On vérifie que le nombre de recettes est un bien un nombre et non inférieur à 0
			if( typeof data.recipeSize != 'number' || data.recipeSize < 0 )
				return ;

			// On fixe à 12 le nombre de recette par défaut à retourner
			var count = 12 ;

			// On vérifie que le nombre de recettes qu'on veut récupérer est bien un nombre et qu'il n'est pas inférieur à 1
			if( typeof data.count == 'number' && data.count >= 1 )
				count = data.count ;

			try
			{
				// Permet de se connecter à la bdd
				var mySqlClient = obj.mysql.createConnection({
					host     : "localhost",
					user     : "root",
					password : "root",
					database : "CookieSCookout"
				});

				// Connection à la bdd
				mySqlClient.connect(); 
				
				// Execution de la requete et constitution d'une liste avec les recettes récupérées
				mySqlClient.query( "SELECT idRecipe AS id, titleRecipe AS title, imgRecipe AS thumbs FROM Recipe LIMIT " + data.recipeSize + ", " + data.count + ";" , function select( error, results, fields ) {
					if( error ) 
					{
					    mySqlClient.end() ;
						socket.emit( 'needrecipe' , { status: 'error' } ) ;
						return ;
					}					 
					
					// création d'une liste vide
					var recipes = [] ;

					// remplissage de la liste
					for( var i = 0 ; i < results.length ; i++ )
						recipes.push({
							id    : results[i]['id'] ,
							title : results[i]['title'] ,
							thumbs: results[i]['thumbs'] 
						}) ;

					// Envoi de la liste des recettes constituées
					socket.emit('needrecipe',recipes) ;

					// Arrêt de la connection à la bdd
					mySqlClient.end() ;
				});
			}
			catch( e )
			{
				socket.emit( 'needrecipe' , { status: 'error' } ) ;
			}
		}) ;
	
		// Evenement qui se déclenche lors de l'envoi de la recherche
		socket.on( 'sendsearch' , function( o ){
			if( !o.search )
				throw new Error("Le champ est vide ou incorrect !") ;

			try
			{	
				var mySqlClient = obj.mysql.createConnection({
				  host     : "localhost",
				  user     : "root",
				  password : "root",
				  database : "CookieSCookout"
				});

				mySqlClient.connect();
				
				var sqlQuery ;

				if( o.recipe )
					// Requête pour récupérer les recettes en fonction du mot demandé pour la recette
					sqlQuery = "SELECT * FROM Ingredient, COMPOSE, Recipe " 
							 + "WHERE COMPOSE.idRecipe = Recipe.idRecipe "
							 + "and Ingredient.idIngredient = COMPOSE.idIngredient "
							 + "and Recipe.titleRecipe LIKE '%" + o.search + "%';" ; 	
				else
					// Requête pour récupérer l'id des recettes contenant l'ingrédient
					sqlQuery = "SELECT * FROM Ingredient, COMPOSE, Recipe WHERE Ingredient.nameIngredient LIKE '%" + o.search +  "%' AND Ingredient.idIngredient = COMPOSE.idIngredient AND COMPOSE.idRecipe = Recipe.idRecipe ;" ; 	
	
				mySqlClient.query(
					sqlQuery,
					o.recipe
					  ?
						function select( error, results, fields ) {
							if( error ) 
							{
							    mySqlClient.end();
							    socket.emit( 'sendsearch' , { status: 'error' } ) ;
							    throw error ;
							}
						 	
							var recipes = [] ;

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

									// permet de récupérer tous les ingrédients d'une recette
									if( recipe.idRecipe == results[i]['idRecipe'] )
									{
										recipe.nameIngredient.push(
											 ( results[i]['qty'] != null ? results[i]['qty'] + " -> " : "" ) + results[i]['nameIngredient'] + ( results[i]['note'] != null ? " -> " + results[i]['note'] : "" ) 
										) ;

										continue ; // saute la suite si l'id est bien correspondant
									}

									// ajoute une nouvelle recette à la liste
									recipes.push( recipe ) ;

									// met la recette courante en ligne une fois que tout est fini
									recipe = null ;
								} ;
						    
						    	// ajoute une nouvelle recette à la liste
								recipes.push( recipe ) ;

								// Envoi le résultat de la recherche à l'IHM
								socket.emit( 'sendsearch' , recipes ) ;
						    }
						    else 
						    	socket.emit( 'sendsearch' , [] ) ;
						    
							mySqlClient.end();
						}
					  :
					    function select( error, results, fields ) {
							if( error ) 
							{
							    mySqlClient.end();
							    socket.emit( 'sendsearch' , { status: 'error' } ) ;
							    throw error ;
							}

						 	if ( results.length > 0 ) 
						 	{ 
						 		var ids_query = "" ;
						    	
						    	// Ajoute chaque id de la recette trouvée avec le nom de l'ingrédient
						    	for( var i = 0 ; i < results.length ; i++)
						    	{
						    		console.log(results[i]['idRecipe']);
								    ids_query += "Recipe.idRecipe = " + results[i]['idRecipe'] ;

				    				if( i+1 < results.length )
				    					ids_query += " OR " ;
								} ;

								console.log("SELECT * FROM Ingredient, COMPOSE, Recipe " 
								  + "WHERE COMPOSE.idRecipe = Recipe.idRecipe "
								  + "and Ingredient.idIngredient = COMPOSE.idIngredient "
								  + "and ( " + ids_query + " ) ;" );

						    	mySqlClient.query(
									"SELECT * FROM Ingredient, COMPOSE, Recipe " 
								  + "WHERE COMPOSE.idRecipe = Recipe.idRecipe "
								  + "and Ingredient.idIngredient = COMPOSE.idIngredient "
								  + "and ( " + ids_query + " ) ;" ,
									function select( error, results, fields ) {
										if( error ) 
										{
										    mySqlClient.end();
										    socket.emit( 'sendsearch' , { status: 'error' } ) ;
										    throw error ;
										}
							
										var recipes = [] ;

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

													continue ;
												}

												if( recipe.idRecipe == results[i]['idRecipe'] )
												{
													recipe.nameIngredient.push(
														 ( results[i]['qty'] != null ? results[i]['qty'] + " -> " : "" ) + results[i]['nameIngredient'] + ( results[i]['note'] != null ? " -> " + results[i]['note'] : "" ) 
													) ;

													continue ;
												}

												recipes.push( recipe ) ;

												recipe = null ;
											} ;
									    
											recipes.push( recipe ) ;

											// Envoi du résultat de la recherche
											socket.emit( 'sendsearch' , recipes ) ;
									    }
									    else 
									    	socket.emit( 'sendsearch' , [] ) ;
									}
								) ;
						    }
						    else 
						    	socket.emit( 'sendsearch' , [] ) ;
						    
							mySqlClient.end();

					    }
				) ;
			}
			catch( e )
			{
				// Signalement de l'échec à l'IHM
				socket.emit( 'sendsearch' , { status: 'error' } ) ;
			}
		}) ;
	}) ;
} ;