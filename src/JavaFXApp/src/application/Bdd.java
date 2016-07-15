package application ;

import java.sql.Connection ;
import java.sql.DriverManager ;
import java.sql.ResultSet ;
import java.sql.SQLException ;
import java.sql.Statement ;

import javafx.collections.FXCollections ;
import javafx.collections.ObservableList ;
import javafx.scene.control.Label ;

/**
 * Final class because it must not be inherited
 */
public class Bdd
{
	Label label ;
	String string ;
	private ObservableList<Recipe> data ;
	
	public Connection getConnection() throws ClassNotFoundException, SQLException
	{       
        Class.forName( "com.mysql.jdbc.Driver" ) ;
        return DriverManager.getConnection( "jdbc:mysql://localhost:3306/CookieSCookout" , "root" , "root" ) ; 
	} ;
	
	/**
	 * 
	 * @param img
	 * @param title
	 * @param recipe
	 * @param ing
	 * @param score
	 * @param poll
	 * @param url
	 * @return
	 */
   public String saveInBDD( String img , String title , String recipe , String ing , int score , int poll , String url )
   {
 		Connection cn = null ;
 		Statement st = null ;
 		
 		try
 		{
 			cn = getConnection() ;
 			st = cn.createStatement() ;
 			String sql = "INSERT INTO SiteWeb ( img , title , recipe , ingredients , score , poll , url ) "
 					+ " VALUES (' " + img + "','" + title + "','" + recipe +  "','" + ing + "','" + score + "','" + poll + "','" + url +"');" ;
 			st.executeUpdate( sql ) ;
 			string = new String( "Successfuly saved in BDD!" ) ;
 		}
 		catch( SQLException e )
 		{
 			e.printStackTrace() ;
 			string = new String( "Error! Cannot saved in BDD!" ) ;
 		}
 		catch(ClassNotFoundException e)
 		{
 			e.printStackTrace() ;
 			string = new String( "Error! Cannot saved in BDD!" ) ;
 		}
 		finally
 		{
 			try
 			{
 				cn.close() ;
 				st.close() ;
 			}
 			catch( SQLException e )
 			{
 				e.printStackTrace() ;
 				string = new String( "Error! Cannot close!" ) ;
 			}
 		}
		return string ;
 		
 	} ;
	
	/**
	 * 
	 */
	public ObservableList<Recipe> getFromBDD()
	{
		data = FXCollections.observableArrayList() ;
		Connection cn = null ;
		Statement st = null ;
		ResultSet rs = null ;
		
		try
		{
			cn = getConnection() ;
			st = cn.createStatement() ;
			String sql = "SELECT * FROM SiteWeb" ;
			rs = st.executeQuery( sql ) ;
			
			while( rs.next() )
			{
				Recipe rec = new Recipe() ;
				rec.setImg( rs.getString( "img" ) ) ;
				rec.setTitle( rs.getString( "title" ) ) ;
				rec.setRecipe(  rs.getString( "recipe" ) ) ;
				rec.setIng( rs.getString( "ingredients" ) ) ;
				rec.setScore( rs.getString( "score" ) ) ;
				rec.setPoll( rs.getString( "poll" ) ) ;
				rec.setUrl( rs.getString( "url" ) ) ;
				data.add( rec ) ;
			}
		}
		catch( SQLException e )
		{
			e.printStackTrace() ;
			System.out.println( "Error on Building Data" ) ;
		}
		catch( ClassNotFoundException e )
		{
			e.printStackTrace() ;
			System.out.println( "Error on Building Data" ) ;
		}
		finally
		{
			try
			{
				cn.close() ;
				st.close() ;
			}
			catch(SQLException e)
			{
				e.printStackTrace() ;
				System.out.println( "Error! Cannot close!" ) ;
			}
		}
		
		return data ;
	} ;
	
	/**
	 * 
	 * @param toDelete
	 */
	public String deleteInBDD (String toDelete )
	{
		Connection cn = null ;
		Statement st = null ;
				
		try
		{
			cn = getConnection() ;
			st = cn.createStatement() ;
			String sql = "DELETE FROM `SiteWeb` WHERE title='" + toDelete + "';" ;
			st.executeUpdate( sql ) ;
			string = new String( "Successfuly deleted from BDD!" ) ;
		}
		catch( SQLException e )
		{
			e.printStackTrace() ;
			string = new String( "Error! Cannot delete!" ) ;
		}
		catch( ClassNotFoundException e )
		{
			e.printStackTrace() ;
			string = new String( "Error! Cannot delete!" ) ;
		}
		finally
		{
			try
			{
				cn.close() ;
				st.close() ;
			}
			catch( SQLException e )
			{
				e.printStackTrace() ;
				string = new String( "Error! Cannot delete!" ) ;
			}
		}
		
		return string ;
	} ;
	
	/**
	 * 
	 * @param toModify
	 */
	public void modifyInBDD( String recipeToModify , String toModify , String modification )
	{
		Connection cn = null ;
		Statement st = null ;
				
		try
		{
			cn = getConnection() ;
			st = cn.createStatement() ;
		}
		catch(SQLException e)
		{
			e.printStackTrace() ;
		}
		catch( ClassNotFoundException e )
		{
			e.printStackTrace() ;
		}
		finally
		{
			try
			{
				cn.close() ;
				st.close() ;
			}
			catch( SQLException e )
			{
				e.printStackTrace() ;
			}
		}
	} ;
	
} ;


