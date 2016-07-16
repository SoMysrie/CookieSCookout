package application;

import java.sql.Connection ;
import java.sql.DriverManager ;
import java.sql.ResultSet ;
import java.sql.SQLException ;
import java.sql.Statement ;

import javafx.collections.FXCollections ;
import javafx.collections.ObservableList ;
import javafx.scene.control.Label ;

public class BddIng 
{
	Label label ;
	String string ;
	private ObservableList<Ingregients> data ;
	
	public Connection getConnection() throws ClassNotFoundException, SQLException
	{       
        Class.forName( "com.mysql.jdbc.Driver" ) ;
        return DriverManager.getConnection( "jdbc:mysql://localhost:3306/CookieSCookout" , "root" , "root" ) ; 
	} ;
	
	/**
	 * 
	 * @param ing
	 * @param qty
	 * @param unit
	 * @return
	 */
   public String saveInBDD( String ing , String qty , String unit )
   {
 		Connection cn = null ;
 		Statement st = null ;
 		
 		try
 		{
 			cn = getConnection() ;
 			st = cn.createStatement() ;
 			String sql = "INSERT INTO Ingredients ( ing , qty , unit ) "
 					+ " VALUES (\" " + ing + "\",\"" + qty + "\",\"" + unit +  "\");" ;
 			st.executeUpdate( sql ) ;
 			string = new String( ing + " was successfuly saved in BDD!" ) ;
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
 	 * @param id
 	 * @param ing
 	 * @param qty
 	 * @param unit
 	 * @return
 	 */
 	public String updateInBDD( int id , String ing , int qty , String unit )
 	{
 		Connection cn = null ;
 		Statement st = null ;
 		
 		try
 		{
 			cn = getConnection() ;
 			st = cn.createStatement() ;
 			String sql = "UPDATE Ingredients SET "
 					+ " ing = \"" + ing + "\" , "
 					+ " qty = \"" + qty + "\" , "
 					+ " unit = \"" + unit + "\" , "
 					+ " where id = " + id + ";" ;
 			st.executeUpdate( sql ) ;
 			string = new String( ing + " was successfuly updated in BDD!" ) ;
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
	 * 
	 */
	public ObservableList<Ingredients> getFromBDD()
	{
		data = FXCollections.observableArrayList() ;
		Connection cn = null ;
		Statement st = null ;
		ResultSet rs = null ;
		
		try
		{
			cn = getConnection() ;
			st = cn.createStatement() ;
			String sql = "SELECT * FROM Ingredients" ;
			rs = st.executeQuery( sql ) ;
			
			while( rs.next() )
			{
				Ingredients ingr = new Ingredients() ;
				ingr.setId(rs.getString( "id" ) ) ;
				ingr.setIng( rs.getString( "ing" ) ) ;
				ingr.setQty( rs.getString( "qty" ) ) ;
				ingr.setUnit(  rs.getString( "unit" ) ) ;
				data.add( ingr ) ;
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
	public String deleteInBDD (int toDelete , String titleToDelete)
	{
		Connection cn = null ;
		Statement st = null ;
				
		try
		{
			cn = getConnection() ;
			st = cn.createStatement() ;
			String sql = "DELETE FROM `SiteWeb` WHERE id='" + toDelete + "';" ;
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
