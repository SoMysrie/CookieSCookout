package application ;

import javafx.beans.property.SimpleStringProperty ;

public class Recipe 
{
	private SimpleStringProperty idRecipe	   ;
	private SimpleStringProperty imgRecipe 	   ;
	private SimpleStringProperty titleRecipe   ;
	private SimpleStringProperty contentRecipe ;
	private SimpleStringProperty scoreRecipe   ;
	private SimpleStringProperty pollRecipe	   ;
	private SimpleStringProperty urlRecipe	   ;
 
    
    public Recipe( ) 
    {
    	this.idRecipe 		= new SimpleStringProperty( ) ;
        this.imgRecipe 		= new SimpleStringProperty( ) ;
        this.titleRecipe 	= new SimpleStringProperty( ) ;
        this.contentRecipe	= new SimpleStringProperty( ) ;
        this.scoreRecipe 	= new SimpleStringProperty( ) ;
        this.pollRecipe 	= new SimpleStringProperty( ) ;
        this.urlRecipe 		= new SimpleStringProperty( ) ;
    } ;
    
    public Recipe
    	( 
    		String idRecipe , String imgRecipe , String titleRecipe , String contentRecipe , 
    		String scoreRecipe , String pollRecipe , String urlRecipe ) 
    {
    	this.idRecipe 		= new SimpleStringProperty( ) ;
        this.imgRecipe 		= new SimpleStringProperty( ) ;
        this.titleRecipe 	= new SimpleStringProperty( ) ;
        this.contentRecipe	= new SimpleStringProperty( ) ;
        this.scoreRecipe 	= new SimpleStringProperty( ) ;
        this.pollRecipe 	= new SimpleStringProperty( ) ;
        this.urlRecipe 		= new SimpleStringProperty( ) ;
    } ;
    
    //ID
    public void setIdRecipe( SimpleStringProperty idRecipe ) 
    {
        this.idRecipe.set( idRecipe.get() ) ;
    } ;
    
    public void setIdRecipe( String idRecipe )
    {
    	this.idRecipe.set( idRecipe ) ;
    } ;
    
    public String getIdRecipe()
    {
    	return idRecipe.get() ;
    } ;
    
    //IMG
    public void setImgRecipe( SimpleStringProperty imgRecipe ) 
    {
        this.imgRecipe.set( imgRecipe.get() ) ;
    } ;
    
    public void setImgRecipe( String imgRecipe )
    {
    	this.imgRecipe.set( imgRecipe ) ;
    } ;
    
    public String getImgRecipe() 
    {
        return imgRecipe.get() ;
    } ;
    
	//TITLE
    public void setTitleRecipe( SimpleStringProperty titleRecipe ) 
	{
	    this.titleRecipe.set( titleRecipe.get() ) ;
	} ;
	
	public void setTitleRecipe( String titleRecipe ) 
	{
		this.titleRecipe.set( titleRecipe ) ;
	} ;
    
    public String getTitleRecipe() 
    {
        return titleRecipe.get() ;
    } ;
    
    //CONTENT
    public void setContentRecipe( SimpleStringProperty contentRecipe ) 
    {
        this.contentRecipe.set( contentRecipe.get() ) ;
    } ;
    
    public void setContentRecipe( String contentRecipe ) 
    {
        this.contentRecipe.set( contentRecipe ) ;
    } ;
    
    public String getContentRecipe() 
    {
        return contentRecipe.get() ;
    } ;
    
    //SCORE
    public void setScoreRecipe( SimpleStringProperty scoreRecipe ) 
    {
        this.scoreRecipe.set( scoreRecipe.get() ) ;
    } ;
    
    public void setScoreRecipe( String scoreRecipe) 
    {
        this.scoreRecipe.set( scoreRecipe ) ;
    } ;
    
    public String getScoreRecipe() 
    {
        return scoreRecipe.get() ;
    } ;
    
    //POLL
	public void setPollRecipe( SimpleStringProperty pollRecipe ) 
	{
	    this.pollRecipe.set( pollRecipe.get() ) ;
	}
	
	public void setPollRecipe( String pollRecipe ) 
	{
		this.pollRecipe.set( pollRecipe ) ;
	} ;
    
    public String getPollRecipe() 
    {
        return pollRecipe.get() ;
    } ;

    //URL
    public void setUrlRecipe( SimpleStringProperty urlRecipe ) 
    {
        this.urlRecipe.set( urlRecipe.get() ) ;
    } ;
    
    public void setUrlRecipe( String urlRecipe ) 
    {
        this.urlRecipe.set( urlRecipe ) ;
    } ;
    
    public String getUrlRecipe() 
    {
        return urlRecipe.get() ;
    } ;
    
} ;
