package application;

import javafx.beans.property.SimpleStringProperty;

public class Recipe 
{
	private SimpleStringProperty img;
	private SimpleStringProperty title;
	private SimpleStringProperty recipe;
	private SimpleStringProperty ing;
	private SimpleStringProperty score;
	private SimpleStringProperty poll;
	private SimpleStringProperty url;
 
    
    public Recipe() 
    {
        this.img = new SimpleStringProperty();
        this.title = new SimpleStringProperty();
        this.recipe = new SimpleStringProperty();
        this.ing = new SimpleStringProperty();
        this.score = new SimpleStringProperty();
        this.poll = new SimpleStringProperty();
        this.url = new SimpleStringProperty();
    }
 
    public void setImg( SimpleStringProperty img ) 
    {
        this.img.set( img.get() );
    }
    
    public void setImg( String img )
    {
    	this.img.set( img ) ;
    } ;
    
    public String getImg() 
    {
        return img.get() ;
    } ;
 
    public void setimg( SimpleStringProperty img ) 
    {
        this.img = img;
    }
    
    public void setimg( String img )
    {
    	this.img = new SimpleStringProperty( img ) ;
    } ;
    
    public String getimg() 
    {
        return img.get() ;
    } ;
    
	public void setTitle( SimpleStringProperty title) 
	{
	    this.title = title;
	}
	
	public void setTitle( String title ) 
	{
		this.title = new SimpleStringProperty( title );
	}
    
    public String getTitle() 
    {
        return title.get();
    }
    
    public void setRecipe(SimpleStringProperty recipe) 
    {
        this.recipe = recipe;
    }
    
    public void setRecipe( String recipe ) 
    {
        this.recipe = new SimpleStringProperty( recipe );
    }
    
    public String getRecipe() 
    {
        return recipe.get();
    }
    
    public void setIng(SimpleStringProperty ing) 
    {
        this.ing = ing;
    }
    
    public void setIng(String ing) 
    {
        this.ing = new SimpleStringProperty(ing);
    }
    
    public String getIng() 
    {
        return ing.get();
    }
    
    public void setScore(SimpleStringProperty score) 
    {
        this.score = score;
    }
    
    public void setScore( String score) 
    {
        this.score = new SimpleStringProperty( score ) ;
    }
    
    public String getScore() 
    {
        return score.get();
    }
    
	public void setPoll( SimpleStringProperty poll) 
	{
	    this.poll = poll;
	}
	
	public void setPoll( String poll) 
	{
		this.poll = new SimpleStringProperty( poll );
	}
    
    public String getPoll() 
    {
        return poll.get();
    }
    
    public void setUrl(SimpleStringProperty url) 
    {
        this.url = url;
    }
    
    public void setUrl( String url) 
    {
        this.url = new SimpleStringProperty( url ) ;
    }
    
    public String getUrl() 
    {
        return url.get();
    }
    
}
