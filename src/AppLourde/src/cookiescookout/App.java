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
 		
 		getFromBDD() ;
 		
 		System.out.println("Quelle recette voulez-vous modifier? ") ;
 		String recipeToModify = sc.nextLine() ;
 		System.out.println("Vous avez saisi : " + recipeToModify) ;
 		int choice = 0 ;
 		while ( choice < 1 || choice > 3 )
 		{
 			System.out.println("Que voulez-vous modifier?") ;
 			System.out.println("1- L'image de la recette") ;
 			System.out.println("2- Le titre de la recette" );
 			System.out.println("3- Le contenu de la recette") ;
 			choice = sc.nextInt() ;
 			
 			if ( choice < 1 || choice > 3 )
 			{
 				System.out.println("Impossible de modifier ce qu'il n'y a pas dans la bdd.") ;
 				System.out.println("Veuillez recommencer.") ;
 			}
 			else
 			{
 				System.out.println("Vous avez saisi : " + choice) ;
 			}
 		}
 		String toModify = null ;
 		switch ( choice ) 
 		{
 			case 1:
 				toModify = "img" ;
 			break;
 			case 2:
 				toModify = "title" ;
 			break;
 			case 3:
 				toModify = "contents" ;
 			break;
 			default:
 				System.out.println("Impossible de modifier.") ;
 			break;
 		}
 		
 		System.out.println("Par quoi voulez-vous modifier?") ;
 		String modification = sc.nextLine() ;
 		System.out.println("Vous avez saisi : " + modification) ;
 		
 		modifyInBDD(recipeToModify, toModify, modification) ;
 		
 		getFromBDD() ;
 		
 		sc.close();
 	} ;
 	
 	/**
 	 * 
 	 * @param img
 	 * @param title
 	 * @param contents
 	 */
 	public static void saveInBDD(String img, String title, String contents)
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
 				// Etape 5: liberer les ressources de la memoire
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
 	public static void deleteInBDD(String toDelete)
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
 	public static void modifyInBDD(String recipeToModify, String toModify, String modification)
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
 
