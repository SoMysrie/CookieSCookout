package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import com.mysql.jdbc.ResultSet;

import javafx.scene.control.Label;

/**
 * Final class because it must not be inherited
 */
public class Bdd
{
	Label label;
	String string;
	
	/**
     * Save a recipe in the bdd
     * @param img
     * @param title
     * @param recipe
     * @param ingredients
     */
   public String saveInBDD(String img, String title, String recipe, String ingredients)
 	{
 		// Information d'acces a la BDD
 		String url = "jdbc:mysql://localhost:3306/CookieSCookout" ;
 		String login = "root" ;
 		String password = "root" ;
 		Connection cn = null ;
 		Statement st = null ;
 		
 		try
 		{
 			// Etape 1: chargement du driver
 			Class.forName("com.mysql.jdbc.Driver") ;
 			// Etape 2: recuperation de la connexion
 			cn = DriverManager.getConnection(url, login, password) ;
 			// Etape 3: creation d'un statement
 			st = cn.createStatement() ;
 			String sql = "INSERT INTO SiteWeb (img, title, recipe, ingredients, score, poll, url) "
 					+ "VALUES ('"+ img + "','" + title + "','" + recipe +  "','" + ingredients + "','" + 1 + "','" + 1 + "','" + null +"');" ;
 			// Etape 4: execution requete
 			st.executeUpdate(sql) ;
 			string = new String("Successfuly saved in BDD!");
 		}
 		catch(SQLException e)
 		{
 			e.printStackTrace() ;
 			string = new String("Error! Cannot saved in BDD!");
 		}
 		catch(ClassNotFoundException e)
 		{
 			e.printStackTrace() ;
 			string = new String("Error! Cannot saved in BDD!");
 		}
 		finally
 		{
 			try
 			{
 				// Etape 5: liberer les ressources de la memoire
 				cn.close() ;
 				st.close() ;
 			}
 			catch(SQLException e)
 			{
 				e.printStackTrace() ;
 			}
 		}
		return string;
 		
 	} ;
	
	/**
	 * 
	 */
	public void getFromBDD()
	{
		// Information d'acces a la BDD
		String url = "jdbc:mysql://localhost:3306/CookieSCookout" ;
		String login = "root" ;
		String password = "root" ;
		Connection cn = null ;
		Statement st = null ;
		ResultSet rs = null ;
		
		try
		{
			// Etape 1: chargement du driver
			Class.forName("com.mysql.jdbc.Driver") ;
			// Etape 2: recuperation de la connexion
			cn = DriverManager.getConnection(url, login, password) ;
			// Etape 3: creation d'un statement
			st = cn.createStatement() ;
			String sql = "SELECT * FROM SiteWeb" ;
			// Etape 4: execution requete
			rs = (ResultSet) st.executeQuery(sql) ;
			// Etape 5: parcours (ResultSet)
			while(rs.next())
			{
				System.out.println(rs.getString("img"));
				System.out.println(rs.getString("title"));
				System.out.println(rs.getString("contents"));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace() ;
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace() ;
		}
		finally
		{
			try
			{
				// Etape 6: liberer les ressources de la memoire
				cn.close() ;
				st.close() ;
			}
			catch(SQLException e)
			{
				e.printStackTrace() ;
			}
		}
	} ;
	
	/**
	 * 
	 * @param toDelete
	 */
	public void deleteInBDD(String toDelete)
	{
		// Information d'acces a la BDD
		String url = "jdbc:mysql://localhost:3306/CookieSCookout" ;
		String login = "root" ;
		String password = "root" ;
		Connection cn = null ;
		Statement st = null ;
				
		try
		{
			// Etape 1: chargement du driver
			Class.forName("com.mysql.jdbc.Driver") ;
			// Etape 2: recuperation de la connexion
			cn = DriverManager.getConnection(url, login, password) ;
			// Etape 3: creation d'un statement
			st = cn.createStatement() ;
			String sql = "DELETE FROM `SiteWeb` WHERE title='" + toDelete + "';" ;
			// Etape 4: execution requete
			st.executeUpdate(sql) ;
		}
		catch(SQLException e)
		{
			e.printStackTrace() ;
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace() ;
		}
		finally
		{
			try
			{
				// Etape 6: liberer les ressources de la memoire
				cn.close() ;
				st.close() ;
			}
			catch(SQLException e)
			{
				e.printStackTrace() ;
			}
		}
	} ;
	
	/**
	 * 
	 * @param toModify
	 */
	public void modifyInBDD(String recipeToModify, String toModify, String modification)
	{
		// Information d'acc�s � la BDD
		String url = "jdbc:mysql://localhost:3306/CookieSCookout" ;
		String login = "root" ;
		String password = "root" ;
		Connection cn = null ;
		Statement st = null ;
				
		try
		{
			// Etape 1: chargement du driver
			Class.forName("com.mysql.jdbc.Driver") ;
			// Etape 2: recuperation de la connexion
			cn = DriverManager.getConnection(url, login, password) ;
			// Etape 3: creation d'un statement
			st = cn.createStatement() ;
			
			// Etape 4: execution requete
		}
		catch(SQLException e)
		{
			e.printStackTrace() ;
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace() ;
		}
		finally
		{
			try
			{
				// Etape 6: liberer les ressources de la memoire
				cn.close() ;
				st.close() ;
			}
			catch(SQLException e)
			{
				e.printStackTrace() ;
			}
		}
	} ;
} ;


