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
	socket.on('sendemail', function( data ) {
		if( data.status == 'success' )
			// Retourne un message de succés et vide les champs du formulaire
			return toastr.success( "Votre message à bien été expédié !" ) && $( 'form#contactform button[type=reset]' ).click() ;
		
		toastr.error( "Une erreur est survenue lors de l'expédition de votre message ! Merci de bien vouloir réitéré ultérieurement." ) ;
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
}) ;