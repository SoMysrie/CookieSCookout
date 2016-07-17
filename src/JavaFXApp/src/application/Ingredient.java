package application;

import javafx.beans.property.SimpleStringProperty;

public class Ingredient
{
	private SimpleStringProperty idIngredient 	;
	private SimpleStringProperty nameIngredient ;
	
	public Ingredient( )
	{
		this.idIngredient 	= new SimpleStringProperty( ) ;
		this.nameIngredient = new SimpleStringProperty( ) ;
	} ;
	
	public Ingredient( String idIngredient , String nameIngredient )
	{
		this.idIngredient 	= new SimpleStringProperty( ) ;
		this.nameIngredient= new SimpleStringProperty( ) ;
	} ;
	
	//ID
	public void setIdIngredient( SimpleStringProperty idIngredient ) 
    {
        this.idIngredient.set( idIngredient.get() ) ;
    } ;
    
    public void setIdIngredient( String idIngredient )
    {
    	this.idIngredient.set( idIngredient ) ;
    } ;
    
    public String getIdIngredient()
    {
    	return idIngredient.get() ;
    } ;
    
    //NAME
    public void setNameIngredient( SimpleStringProperty nameIngredient ) 
    {
        this.nameIngredient.set( nameIngredient.get() ) ;
    } ;
    
    public void setNameIngredient( String nameIngredient )
    {
    	this.nameIngredient.set( nameIngredient ) ;
    } ;
    
    public String getNameIngredient()
    {
    	return nameIngredient.get() ;
    } ;
} ;
