package application ;

import java.sql.Connection ;
import java.sql.DriverManager ;
import java.sql.ResultSet ;
import java.sql.SQLException ;
import java.sql.Statement ;

import javafx.collections.FXCollections ;
import javafx.collections.ObservableList ;
import javafx.scene.control.Label ;

public class DBRecipe
{
	Label label ;
	String string ;
	private ObservableList<Recipe> dataRecipe ;
	
	public Connection getConnection() throws ClassNotFoundException, SQLException
	{       
        Class.forName( "com.mysql.jdbc.Driver" ) ;
        return DriverManager.getConnection( "jdbc:mysql://localhost:3306/CookieSCookout" , "root" , "root" ) ; 
	} ;
	
	/**
	 * Save in DB Recipe
	 * @param imgRecipe
	 * @param titleRecipe
	 * @param contentRecipe
	 * @param scoreRecipe
	 * @param pollRecipe
	 * @param urlRecipe
	 * @return
	 */
	public String saveInDBRecipe
   		( 
		   String imgRecipe , String titleRecipe  , String contentRecipe , 
		   int scoreRecipe , int pollRecipe , String urlRecipe 
		)
	{
		Connection cn = null ;
 		Statement st = null ;
 		
 		try
 		{
 			cn = getConnection() ;
 			st = cn.createStatement() ;
 			String sql = "INSERT INTO Recipe ( imgRecipe , titleRecipe , contentRecipe , scoreRecipe , pollRecipe , urlRecipe ) "
 					+ " VALUES (\" " + imgRecipe + "\",\"" + titleRecipe + "\",\"" + contentRecipe +  "\",\"" 
 					+ scoreRecipe + "\",\""  + pollRecipe + "\",\""  + urlRecipe +"\");" ;
 			st.executeUpdate( sql ) ;
 			string = new String( titleRecipe + " was successfuly saved in BDD!" ) ;
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
 	 * Update in DB Recipe
 	 * @param idRecipe
 	 * @param imgRecipe
 	 * @param titleRecipe
 	 * @param contentRecipe
 	 * @param scoreRecipe
 	 * @param pollRecipe
 	 * @param urlRecipe
 	 * @return
 	 */
 	public String updateInDBRecipe
 		(
 			int idRecipe , String imgRecipe , String titleRecipe , 
 			String contentRecipe , int scoreRecipe , int pollRecipe , String urlRecipe 
 		)
 	{
 		Connection cn = null ;
 		Statement st = null ;
 		
 		try
 		{
 			cn = getConnection() ;
 			st = cn.createStatement() ;
 			String sql = "UPDATE Recipe SET "
 					+ " imgRecipe = \"" + imgRecipe + "\" , "
 					+ "titleRecipe = \"" + titleRecipe + "\" , "
 					+ "contentRecipe = \"" + contentRecipe + "\" , "
 					+ "scoreRecipe = " + scoreRecipe + ", "
 					+ "pollRecipe = " + pollRecipe + ", "
 					+ "urlRecipe = \"" + urlRecipe + "\" "
 					+ " where idRecipe = " + idRecipe + ";" ;
 			st.executeUpdate( sql ) ;
 			string = new String( titleRecipe + " was successfuly updated in BDD!" ) ;
 		}
 		catch( SQLException e )
 		{
 			e.printStackTrace() ;
 			string = new String( "Error! Cannot updated in BDD!" ) ;
 		}
 		catch(ClassNotFoundException e)
 		{
 			e.printStackTrace() ;
 			string = new String( "Error! Cannot updated in BDD!" ) ;
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
	 * Get From DB Recipe
	 */
	public ObservableList<Recipe> getFromDBRecipe()
	{
		dataRecipe = FXCollections.observableArrayList() ;
		Connection cn = null ;
		Statement st = null ;
		ResultSet rs = null ;
		
		try
		{
			cn = getConnection() ;
			st = cn.createStatement() ;
			String sql = "SELECT * FROM Recipe" ;
			rs = st.executeQuery( sql ) ;
			
			while( rs.next() )
			{
				Recipe rec = new Recipe() ;
				rec.setIdRecipe(rs.getString( "idRecipe" ) ) ;
				rec.setImgRecipe( rs.getString( "imgRecipe" ) ) ;
				rec.setTitleRecipe( rs.getString( "titleRecipe" ) ) ;
				rec.setContentRecipe(  rs.getString( "contentRecipe" ) ) ;
				rec.setScoreRecipe( rs.getString( "scoreRecipe" ) ) ;
				rec.setPollRecipe( rs.getString( "pollRecipe" ) ) ;
				rec.setUrlRecipe( rs.getString( "urlRecipe" ) ) ;
				dataRecipe.add( rec ) ;
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
		
		return dataRecipe ;
	} ;
	
	/**
	 * Delete in DB Recipe
	 * @param toDelete
	 * @param titleToDelete
	 * @return
	 */
	public String deleteInDBRecipe (int toDelete , String titleToDelete)
	{
		Connection cn = null ;
		Statement st = null ;
				
		try
		{
			cn = getConnection() ;
			st = cn.createStatement() ;
			String sql = "DELETE FROM COMPOSE WHERE idRecipe = \"" + toDelete + "\";" 
					   + "DELETE FROM Recipe  WHERE idRecipe = \"" + toDelete + "\";" ;
			st.executeUpdate( sql ) ;
			string = new String( titleToDelete + " was successfuly deleted from BDD!" ) ;
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
	
} ;


