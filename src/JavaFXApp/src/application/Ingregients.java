package application;

import javafx.beans.property.SimpleStringProperty;

public class Ingregients 
{
	private SimpleStringProperty id ;
	private SimpleStringProperty ing ;
	private SimpleStringProperty qty ;
	private SimpleStringProperty unit ;
	
	public Ingregients( )
	{
		this.id 	= new SimpleStringProperty( ) ;
		this.ing 	= new SimpleStringProperty( ) ;
		this.qty 	= new SimpleStringProperty( ) ;
		this.unit 	= new SimpleStringProperty( ) ;
	} ;
	
	public Ingregients( String id , String ing , String qty , String unit )
	{
		this.id 	= new SimpleStringProperty( ) ;
		this.ing 	= new SimpleStringProperty( ) ;
		this.qty 	= new SimpleStringProperty( ) ;
		this.unit 	= new SimpleStringProperty( ) ;
	} ;
	
	public void setId( SimpleStringProperty id ) 
    {
        this.id.set( id.get() ) ;
    } ;
    
    public void setId( String id )
    {
    	this.id.set( id ) ;
    } ;
    
    public String getId()
    {
    	return id.get() ;
    } ;
    
    public void setIng( SimpleStringProperty ing ) 
    {
        this.ing.set( ing.get() ) ;
    } ;
    
    public void setIng( String ing )
    {
    	this.ing.set( ing ) ;
    } ;
    
    public String getIng()
    {
    	return ing.get() ;
    } ;
	
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
    
    public void setUnit( SimpleStringProperty unit ) 
    {
        this.unit.set( unit.get() ) ;
    } ;
    
    public void setUnit( String unit )
    {
    	this.unit.set( unit ) ;
    } ;
    
    public String getUnit()
    {
    	return unit.get() ;
    } ;
	
} ;
