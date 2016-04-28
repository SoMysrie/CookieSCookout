package cookiescookout;

import cookiescookout.ws.WsServer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import com.mysql.jdbc.ResultSet;

/**
 * @author SoMysrie
 * 
 * Main application class
 * 
 * Final class because it must not be inherited
 */
public final class App
{
	/**
	 * Main application entry
	 * @param args
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void main( final String[] args ) throws ClassNotFoundException, SQLException
	{
		System.out.println("CookieSCookout app...") ;
		WsServer ws = new WsServer() ;
		ws.start() ;
		
		Scanner sc = new Scanner(System.in) ;
		
		System.out.println("Entrez le lien de l'image de votre recette: ") ;
		String img = sc.nextLine() ;
		System.out.println("Vous avez saisi : " + img) ;
		System.out.println("Entrez le titre de votre recette: ") ;
		String title = sc.nextLine() ;
		System.out.println("Vous avez saisi : " + title) ;
		System.out.println("Entrez le contenu de votre recette: ") ;
		String contents = sc.nextLine() ;
		System.out.println("Vous avez saisi : " + contents) ;
			
		saveInBDD(img, title, contents) ;
		
		getFromBDD() ;
		
		System.out.println("Quelle recette voulez-vous supprimer? ") ;
		String toDelete = sc.nextLine() ;
		System.out.println("Vous avez saisi : " + toDelete) ;
		deleteInBDD(toDelete) ;
	} ;
	
	/**
	 * 
	 * @param img
	 * @param title
	 * @param contents
	 */
	public static void saveInBDD(String img, String title, String contents)
	{
		// Information d'accès à la BDD
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
			String sql = "INSERT INTO SiteWeb (img, title, contents) VALUES ('"+ img + "','" + title + "','" + contents + "');" ;
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
				// Etape 5: libérer les ressources de la mémoire
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
	 */
	public static void getFromBDD()
	{
		// Information d'accès à la BDD
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
				// Etape 6: libérer les ressources de la mémoire
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
	public static void deleteInBDD(String toDelete)
	{
		// Information d'accès à la BDD
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
				// Etape 6: libérer les ressources de la mémoire
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
	public static void modifyInBDD(String toModify)
	{
		// Information d'accès à la BDD
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
				// Etape 6: libérer les ressources de la mémoire
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

