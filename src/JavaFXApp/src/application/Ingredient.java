package application;

import javafx.beans.property.SimpleStringProperty;

public class Ingredient
{
	private SimpleStringProperty idIngredient 	;
	private SimpleStringProperty nameIngredient ;
	private SimpleStringProperty qty 			;
	private SimpleStringProperty note 			;
	
	public Ingredient( )
	{
		this.idIngredient 	= new SimpleStringProperty( ) ;
		this.nameIngredient = new SimpleStringProperty( ) ;
		this.qty			= new SimpleStringProperty( ) ;
		this.note			= new SimpleStringProperty( ) ;
	} ;
	
	public Ingredient( String idIngredient , String nameIngredient )
	{
		this.idIngredient 	= new SimpleStringProperty( ) ;
		this.nameIngredient = new SimpleStringProperty( ) ;
	} ;
	
	public Ingredient( String idIngredient , String nameIngredient , String qty , String note)
	{
		this.idIngredient 	= new SimpleStringProperty( ) ;
		this.nameIngredient = new SimpleStringProperty( ) ;
		this.qty 			= new SimpleStringProperty( ) ;
		this.note 			= new SimpleStringProperty( ) ;
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
    
    //QTY
    public void setQty( SimpleStringProperty qty ) 
    {
        this.qty.set( qty.get() ) ;
    } ;
    
    public void setQty( String qty )
    {
    	this.qty.set( qty ) ;
    } ;
    
    public String getQty()
    {
    	return qty.get() ;
    } ;
    
    //NOTE
    public void setNote( SimpleStringProperty note ) 
    {
        this.note.set( note.get() ) ;
    } ;
    
    public void setNote( String note )
    {
    	this.note.set( note ) ;
    } ;
    
    public String getNote()
    {
    	return note.get() ;
    } ;
    
    /**
     * On écrase la méthode héritée de la classe Object qui nous renvoie la référence et on retourne à la place le nom de la recette
     * */
    @Override
    public String toString()
    {
    	return this.getNameIngredient() ;
    } ;
} ;
