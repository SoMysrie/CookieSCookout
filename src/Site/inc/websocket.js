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
				socket.emit( 'sendmail' , { status: 'success' } ) ;
			}
			catch( e )
			{
				// Signalement de l'échec à l'IHM
				socket.emit( 'sendmail' , { status: 'error' } ) ;
			}
		}) ;

		// Evenement qui se déclenche lors de l'envoi de la rercherche
		socket.on( 'sendsearch' , function( o ){
			if( !o.search )
				throw new Error("Le champ est vide ou incorrect !") ;

			try
			{	
				// Signalement du succès à l'IHM
				socket.emit( 'sendsearch' , [
					{
						'imgRecipe'		: 'https://teammangas.files.wordpress.com/2015/07/transforming_furikake_gohan_anime.png'         ,
						'titleRecipe' 	: 'Titre de la première recette' ,
						'nameIngredient': [ 'sel', 'sucre', 'poivre']	 ,
						'contentRecipe'	: "On the other hand, we denounce with righteous indignation and dislike men who are so beguiled and demoralized by the charms of pleasure of the moment, so blinded by desire, that they cannot foresee the pain and trouble that are bound to ensue; and equal blame belongs to those who fail in their duty through weakness of will, which is the same as saying through shrinking from toil and pain. These cases are perfectly simple and easy to distinguish. In a free hour, when our power of choice is untrammelled and when nothing prevents our being able to do what we like best, every pleasure is to be welcomed and every pain avoided. But in certain circumstances and owing to the claims of duty or the obligations of business it will frequently occur that pleasures have to be repudiated and annoyances accepted. The wise man therefore always holds in these matters to this principle of selection: he rejects pleasures to secure other greater pleasures, or else he endures pains to avoid worse pains." ,
					} , 
					{
						'imgRecipe'		: 'https://blog.manga.tv/wp-content/uploads/2015/06/food-wars-shokugeki-no-soma-episode-10-manga-tv-streaming-anime-online-legal-gratuit-screenshot-27.jpg'         ,
						'titleRecipe' 	: 'Titre de la deuxième recette' ,
						'nameIngredient': [ 'sel', 'sucre', 'poivre']	 ,
						'contentRecipe'	:  "On the other hand, we denounce with righteous indignation and dislike men who are so beguiled and demoralized by the charms of pleasure of the moment, so blinded by desire, that they cannot foresee the pain and trouble that are bound to ensue; and equal blame belongs to those who fail in their duty through weakness of will, which is the same as saying through shrinking from toil and pain. These cases are perfectly simple and easy to distinguish. In a free hour, when our power of choice is untrammelled and when nothing prevents our being able to do what we like best, every pleasure is to be welcomed and every pain avoided. But in certain circumstances and owing to the claims of duty or the obligations of business it will frequently occur that pleasures have to be repudiated and annoyances accepted. The wise man therefore always holds in these matters to this principle of selection: he rejects pleasures to secure other greater pleasures, or else he endures pains to avoid worse pains.",
					} , 
					{
						'imgRecipe'		: 'http://ekladata.com/RTqwob7JakraQf-kDrvJeVzPJEM.png'         ,
						'titleRecipe' 	: 'Titre de la troisième recette',
						'nameIngredient': [ 'sel', 'sucre', 'poivre']	 ,
						'contentRecipe'	:  "On the other hand, we denounce with righteous indignation and dislike men who are so beguiled and demoralized by the charms of pleasure of the moment, so blinded by desire, that they cannot foresee the pain and trouble that are bound to ensue; and equal blame belongs to those who fail in their duty through weakness of will, which is the same as saying through shrinking from toil and pain. These cases are perfectly simple and easy to distinguish. In a free hour, when our power of choice is untrammelled and when nothing prevents our being able to do what we like best, every pleasure is to be welcomed and every pain avoided. But in certain circumstances and owing to the claims of duty or the obligations of business it will frequently occur that pleasures have to be repudiated and annoyances accepted. The wise man therefore always holds in these matters to this principle of selection: he rejects pleasures to secure other greater pleasures, or else he endures pains to avoid worse pains.",
					} 
				] ) ;
			}
			catch( e )
			{
				// Signalement de l'échec à l'IHM
				socket.emit( 'sendsearch' , { status: 'error' } ) ;
			}
		}) ;
	}) ;
} ;