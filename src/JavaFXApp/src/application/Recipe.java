package application ;

import javafx.beans.property.SimpleStringProperty ;

public class Recipe 
{
	private SimpleStringProperty img 	;
	private SimpleStringProperty title	;
	private SimpleStringProperty recipe	;
	private SimpleStringProperty ing	;
	private SimpleStringProperty score	;
	private SimpleStringProperty poll	;
	private SimpleStringProperty url	;
 
    
    public Recipe( ) 
    {
        this.img 	= new SimpleStringProperty( ) ;
        this.title 	= new SimpleStringProperty( ) ;
        this.recipe = new SimpleStringProperty( ) ;
        this.ing 	= new SimpleStringProperty( ) ;
        this.score 	= new SimpleStringProperty( ) ;
        this.poll 	= new SimpleStringProperty( ) ;
        this.url 	= new SimpleStringProperty( ) ;
    } ;
    
    public Recipe( String img , String title , String recipe , String ing , String score , String poll , String url ) 
    {
        this.img 	= new SimpleStringProperty( ) ;
        this.title 	= new SimpleStringProperty( ) ;
        this.recipe = new SimpleStringProperty( ) ;
        this.ing 	= new SimpleStringProperty( ) ;
        this.score 	= new SimpleStringProperty( ) ;
        this.poll 	= new SimpleStringProperty( ) ;
        this.url 	= new SimpleStringProperty( ) ;
    } ;
 
    public void setImg( SimpleStringProperty img ) 
    {
        this.img.set( img.get() ) ;
    } ;
    
    public void setImg( String img )
    {
    	this.img.set( img ) ;
    } ;
    
    public String getImg() 
    {
        return img.get() ;
    } ;
    
	public void setTitle( SimpleStringProperty title ) 
	{
	    this.title.set( title.get() ) ;
	} ;
	
	public void setTitle( String title ) 
	{
		this.title.set( title ) ;
	} ;
    
    public String getTitle() 
    {
        return title.get() ;
    } ;
    
    public void setRecipe( SimpleStringProperty recipe ) 
    {
        this.recipe.set( recipe.get() ) ;
    } ;
    
    public void setRecipe( String recipe ) 
    {
        this.recipe.set( recipe ) ;
    } ;
    
    public String getRecipe() 
    {
        return recipe.get() ;
    } ;
    
    public void setIng( SimpleStringProperty ing ) 
    {
        this.ing.set( ing.get() ) ;
    } ;
    
    public void setIng(String ing) 
    {
        this.ing.set( ing ) ;
    } ;
    
    public String getIng() 
    {
        return ing.get() ;
    } ;
    
    public void setScore( SimpleStringProperty score ) 
    {
        this.score.set( score.get() ) ;
    } ;
    
    public void setScore( String score) 
    {
        this.score.set( score ) ;
    } ;
    
    public String getScore() 
    {
        return score.get() ;
    } ;
    
	public void setPoll( SimpleStringProperty poll ) 
	{
	    this.poll.set( poll.get() ) ;
	}
	
	public void setPoll( String poll ) 
	{
		this.poll.set( poll ) ;
	} ;
    
    public String getPoll() 
    {
        return poll.get() ;
    } ;
    
    public void setUrl( SimpleStringProperty url ) 
    {
        this.url.set( url.get() ) ;
    } ;
    
    public void setUrl( String url ) 
    {
        this.url.set( url ) ;
    } ;
    
    public String getUrl() 
    {
        return url.get() ;
    } ;
    
} ;
