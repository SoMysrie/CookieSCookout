package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import link.NotedRecipes;

public class sqlQueries {
	public static Connection cn = null;
	public static Statement st = null;

	public void connect() throws SQLException, ClassNotFoundException  {
		if (cn == null && st == null) {
			String url = "jdbc:mysql://localhost:3306/CookieSCookout";
			String login = "root";
			String password = "root";
			Class.forName("com.mysql.jdbc.Driver");
			// Etape 2: recuperation de la connexion
			cn = DriverManager.getConnection(url, login, password);
			// Etape 3: creation d'un statement
			st = cn.createStatement();

		}

	}

	public ArrayList<NotedRecipes> getFromBDD(String[] keyWords, String[] exceptedIngredients) {
		ResultSet rs = null;
		ArrayList<NotedRecipes> result = new ArrayList<NotedRecipes>();
		
		try {
			this.connect();
			// Etape 1: chargement du driver
			String sqlLike="";
			for(int i=0; i<keyWords.length-1;i++)
				sqlLike+="("+"'"+"%"+keyWords[i]+"%"+"'"+") AND LIKE ";
			sqlLike+="("+"'"+"%"+keyWords[keyWords.length-1]+"%"+"'"+")";
			
			String sqlNotLike="AND NOT LIKE";
			for(int i=0; i<exceptedIngredients.length-1;i++)
				sqlNotLike+="("+"'"+"%"+exceptedIngredients[i]+"%"+"'"+") AND NOT LIKE ";
			sqlNotLike+="("+"'"+"%"+exceptedIngredients[exceptedIngredients.length-1]+"%"+"'"+"))";
			
			String sql = "SELECT * FROM SiteWeb WHERE ( ingredients LIKE "+sqlLike+sqlNotLike;
			System.out.println(sql);
			rs = (ResultSet) st.executeQuery(sql);
			// Etape 5: parcours (ResultSet)
			while (rs.next()) {
				result.add(new NotedRecipes(rs.getString("title"),rs.getString("url"),rs.getInt("vote"),rs.getInt("note"),rs.getString("ingredients").split("+"),rs.getString("recipe")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Bloc catch auto-généré
			e.printStackTrace();
		}finally {
			try {
				// Etape 6: libérer les ressources de la mémoire
				cn.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}
