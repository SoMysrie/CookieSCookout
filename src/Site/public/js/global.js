jQuery( document ).ready(function( $ ){

	// Rajout de la barre de progression pour les alertes
	toastr.options = {
		"progressBar": true
	} ;

	// Indicateut montrant si on a déjà été déconnecté ou pas
	var alreadyDisconnected = false ;

	// Connection au serveur et récupération du socket
	var socket = io('http://' + location.hostname + ( location.port == '' ? '' : ':' + location.port ) ) ;

	// Evenement qui se déclenche lors d'une connection
	socket.on('connect', function( data ) {
	    if( alreadyDisconnected )
		    toastr.success( "Vous êtes à nouveau connecté. Bonne visite !" ) ;
	}) ;

	// Evenement qui se déclenche lors d'une déconnection
	socket.on('disconnect', function( data ) {
		alreadyDisconnected = true ;
	    toastr.warning( "Il semblerait que vous n'avez plus internet ! Merci de bien vouloir vous reconnecter pour pouvoir naviguer sur le site internet." ) ;
	}) ;

	// Evenement qui se déclenche lors de l'envoi d'un email
	socket.on('sendmail', function( data ) {
		if( data.status == 'success' )
			// Retourne un message de succés et vide les champs du formulaire
			return toastr.success( "Votre message a bien été expédié !" ) && $( 'form#contactform button[type=reset]' ).click() ;
		
		toastr.error( "Une erreur est survenue lors de l'expédition de votre message ! Merci de bien vouloir réitérer ultérieurement." ) ;
	}) ;

	// Evenement qui se déclenche lors de l'envoi de la recherche
	socket.on('sendsearch', function( data ) {

		// Pour vider le résultat de la précédente recherche
		$( 'div#searchresult' ).html('') ;

		// Retourne un message d'erreur 
		if( data.status || typeof data.length == 'undefined' )
			return toastr.error( "Une erreur est survenue lors de votre recherche ! Merci de bien vouloir réitérer ultérieurement." ) ;
	
		// Affiche le nombre de recette trouvée
		toastr.info( data.length + " recette(s) trouvée(s)" ) ;

		// Block représentant la recette
		var recipe = "<div class='recipe list'>{{titleRecipe}}<div class='thumbs'></div><div>{{nameIngredient}}<br />{{contentRecipe}}</div></div>" ;

		// Parcoure toutes les recettes trouvées
		for( var i = 0 ; i < data.length ; i++ )
		{
			var ingr = "<ul>" ;

			// Parcoure la liste des ingrédients pour la mettre sous forme de liste
			for( var x = 0 ; x < data[i]['nameIngredient'].length ; x++ )
				ingr += "<li>" + ( $('input[type=radio][name=searchcdt]:checked').val() == 'recipe' ? data[i]['nameIngredient'][x] : data[i]['nameIngredient'][x].replace( $.trim( $( 'input#search' ).val() ) , '<i style="color:red">' + $.trim( $( 'input#search' ).val() ) + '</i>' ) ) + "</li>" ;

			ingr += "</ul>" ;

			// On ajoute la recette au résultat avec ses informations
			$( 'div#searchresult' )
				.append(
					recipe
						.replace( '{{titleRecipe}}'    , ( $('input[type=radio][name=searchcdt]:checked').val() == 'recipe' ? data[i]['titleRecipe'].replace( $.trim( $( 'input#search' ).val() ) , '<i style="color:red">' + $.trim( $( 'input#search' ).val() ) + '</i>' ) : data[i]['titleRecipe'] ) ) //
						.replace( '{{nameIngredient}}' , ingr ) //
						.replace( '{{contentRecipe}}'  , data[i]['contentRecipe'] ) //
				) ;

			// Spécifie l'image à afficher pour la recette courante
			$( 'div#searchresult div.recipe.list:last-child div.thumbs' ).css( 'background-image' , "url('" + data[i]['imgRecipe'] + "')" ) ;
		} ;
	}) ;

	// On boucle sur toutes les miniatures de recettes pour afficher la vignette 
	$('div.recipe').each(function(e){
		$(this).css( 'background-image' , 'url(\'' + $(this).attr('data-background') + '\')' ) ;
	});

	$('section#menu a').click(function(){
		$('section.block').hide() ;
		$('section.block#' + $(this).attr('data-link') ).show('slow');
	}) ;

	// Pour le formulaire de contact
	$('form#contactform').submit(function(event){

		// Empêche la validation du formulaire
		event.preventDefault() ; 

		// Vérifie que le champ texte n'est pas nul
		if( $.trim( $( 'input#nom' ).val() ).length < 2 )
			return toastr.error( "Merci de bien vouloir spécifier un nom valide !" ) ;

		// Vérifie que le champ email est conforme à une adresse email
		if( ! /^[a-z0-9._-]+@[a-z0-9._-]+\.[a-z]{2,6}$/.test( $( 'input#courriel' ).val() ) )
			return toastr.error( "Merci de bien vouloir spécifier une adresse email valide !" ) ;

		// Vérifie que le message n'est pas inférieur à 15 caractères
		if( $.trim( $( 'textarea#message' ).val() ) <= 15 )
			return toastr.error( "Message trop court !<br />Avez-vous réellement quelque chose d'intéressant à me dire ?" ) ;  

		// Envoi des données du message au serveur
		socket.emit( 'sendmail' , {
			name: $.trim( $( 'input#nom' ).val() ) ,
			mail: $.trim( $( 'input#courriel' ).val() ) ,
			msg : $.trim( $( 'textarea#message' ).val() )
		}) ;

		// Signale que le message est en cours d'expédition
		toastr.info( "Votre message est en cours d'expédition..." ) ;
	}) ;

	//
	$('form#searchform').submit( function(event){

		// Empêche la validation du formulaire
		event.preventDefault() ;

		// Vérifie que le champ recherche n'est pas nul
		if( $.trim( $( 'input#search' ).val() ).length < 3 )
			return toastr.error( "Merci de bien vouloir spécifier votre recherche !" ) ;

		// Envoie des données de la recherche au serveur
		socket.emit( 'sendsearch' , {
			search: $.trim( $( 'input#search' ).val() ) ,
			recipe: $('input[type=radio][name=searchcdt]:checked').val() == 'recipe'
		}) ;

		// Signale que la demande est en cours
		toastr.info( "Recherche en cours..." ) ;
	}) ;
}) ;