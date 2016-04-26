jQuery( document ).ready(function( $ ){

	// On boucle sur toutes les miniatures de recettes pour afficher la vignette 
	$('div.recipe').each(function(e){
		$(this).css( 'background-image' , 'url(\'' + $(this).attr('data-background') + '\')' ) ;
	});

	$('section#menu a').click(function(){
		$('section.block').hide() ;
		$('section.block#' + $(this).attr('data-link') ).show('slow');
	});
}) ;