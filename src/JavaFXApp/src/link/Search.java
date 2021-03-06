package link;

import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import plugin.PluginLoader;
import plugin.ResearchPlugin;
import xmlLoader.XMLLoader;

public class Search {
	ArrayList<Site> sites = new ArrayList<Site>();
	String researcher = "";
	public static String confPath = "conf/link.xml";
	ArrayList<String> keyWords = new ArrayList<String>();
	ArrayList<String> exceptedIngredients = new ArrayList<String>();
	public ArrayList<String> plugins =new ArrayList<>();
	PluginLoader pluginLoader = new PluginLoader();
	ArrayList<ResearchPlugin> researchPlugins;
	ArrayList<NotedRecipes> resultSearch= new ArrayList<NotedRecipes>();

	public ArrayList<NotedRecipes> sql(String[] ingredient, String[] excepted)throws ClassNotFoundException, SQLException
	{
		if(ingredient.length==0)
			return null;
		ArrayList<NotedRecipes> res= new ArrayList<>();
		Class.forName( "com.mysql.jdbc.Driver" ) ;
		Connection cn = DriverManager.getConnection( "jdbc:mysql://localhost:3306/CookieSCookout?allowMultiQueries=true" , "root" , "root" ) ;

		Statement st = cn.createStatement() ;
		ArrayList<Integer> ingre= new ArrayList<>();
		ArrayList<Integer> ex= new ArrayList<>();
		for(String s: ingredient){
			String getIngret="Select idIngredient from Ingredient WHERE Ingredient.nameIngredient LIKE '%"+s+"%';";
			ResultSet rs = st.executeQuery( getIngret) ;
			while( rs.next() )
			{
				// System.out.println(rs.getInt("idIngredient") ) ;
				ingre.add(rs.getInt("idIngredient"));
			}
		}
		for(String s: excepted){
			String getEx="Select idIngredient from Ingredient WHERE Ingredient.nameIngredient LIKE '%"+s+"%';";
			ResultSet rs = st.executeQuery( getEx) ;
			while( rs.next() )
			{
				// System.out.println(rs.getInt("idIngredient") ) ;
				ex.add(rs.getInt("idIngredient"));
			}
		}
		ArrayList<Integer> rec = new ArrayList<>();
		String getRec="Select idRecipe from COMPOSE WHERE ";

		for(int i=0;i< ingre.size();i++){
			getRec+="Compose.idIngredient="+ingre.get(i)+" ";
			if(ingre.size()!=i+1)
				getRec+=" OR ";
		}
		if(ex.size()!=0) {
			getRec += " NOT (";
			int i;
			for (i = 0; i < ex.size() - 1; i++) {
				getRec += "Compose.idIngredient=" + ex.get(i) + " OR ";
			}
			getRec += "Compose.idIngredient=" + ex.get(i) + " );";
		}
		// System.out.println(getRec);
		ResultSet rs = st.executeQuery( getRec) ;
		while( rs.next() )
		{
			if(!rec.contains(rs.getInt("idRecipe")))
				rec.add(rs.getInt("idRecipe"));
		}

		int j;

		for(j=0;j<rec.size();j++) {
			ArrayList<String> arrayIn= new ArrayList<>();
			String q="Select nameIngredient from Ingredient natural join Recipe where Recipe.idRecipe="+rec.get(j)+";";
			rs = st.executeQuery(q);
			while (rs.next()) {
				if(!arrayIn.contains(rs.getString("nameIngredient")))
					arrayIn.add(rs.getString("nameIngredient"));
			}
			String[] resultIng=new String[arrayIn.size()];
			int k=0;
			for(String s : arrayIn) {
				resultIng[k] = s;
				// System.out.println(resultIng[k]);
				k++;
			}
			q="Select * from Recipe where Recipe.idRecipe="+rec.get(j)+";";
			rs = st.executeQuery(q);
			while (rs.next()) {
				res.add(new NotedRecipes(rs.getString("titleRecipe"),rs.getString("urlRecipe"),rs.getInt("pollRecipe"),rs.getInt("scoreRecipe"),resultIng,rs.getString("contentRecipe")));
			}
		}


		return res;

	}
	public void setPluginLoader(){
		String[] plug=new String[plugins.size()];
		int i=0;
		for(String s : plugins){
			plug[i]=s;
			i++;
		}
		pluginLoader=new PluginLoader(plug);
	}
	public ArrayList<Site> getSites() {
		return sites;
	}

	public void setSites(ArrayList<Site> sites) {
		this.sites = sites;
	}

	public String getResearcher() {
		return researcher;
	}

	public void setResearcher(String researcher) {
		this.researcher = researcher;
	}

	public ArrayList<String> getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(ArrayList<String> keyWords) {
		this.keyWords = keyWords;
	}

	public void initSites(boolean marmiton, boolean cuisineaz, boolean feminin, boolean local) throws Exception {
		XMLLoader loader = new XMLLoader();
		ArrayList<Site> remove = new ArrayList<>();
		this.sites = loader.load(confPath);
		for(Site s : sites){
			//// System.out.println(s.getMainSite().contains("feminin"));
			if(s.getMainSite().contains("marmiton"))
				if(!marmiton)
					remove.add(s);
			if(s.getMainSite().contains("cuisineaz"))
				if(!cuisineaz)
					remove.add(s);
			if(s.getMainSite().contains("feminin")){
				if(!feminin) {
					remove.add(s);

				}}
		}
		for(Site removes: remove)
			sites.remove(removes);
	}

	public void initKeyWords(String input) {

		String[] tmpKeys = input.split(" ");
		for (String s : tmpKeys) {
			if (s.startsWith("^"))
				this.exceptedIngredients.add(s.substring(1));
			else
				this.keyWords.add(s);
		}
	}

	public void initLinks() {
		for (int i = 0; i < sites.size(); i++) {
			sites.get(i).replaceSearchSite(this.researcher);
		}
	}

	public void init(boolean marmiton, boolean cuisineaz, boolean feminin, boolean local, String input) throws Exception {
		initKeyWords(input);
		initSites( marmiton,cuisineaz,feminin,local);
		updateResearcher();
		initLinks();
	}

	public void updateResearcher() {
		if (keyWords.size() != 0) {
			this.researcher = "";
			int i;
			for (i = 0; i < keyWords.size() - 1; i++) {
				this.researcher += keyWords.get(i) + "-";
			}
			this.researcher += keyWords.get(i);
		}
	}

	public void researchWithPlugins() {
		if (researchPlugins.size() != 0) {
			for (ResearchPlugin rp : this.researchPlugins) {
				rp.research();
			}
		} else
			research();
	}

	public void research() {

		Document doc;
		for (int i = 0; i < this.sites.size(); i++) {
			try {
				doc = Jsoup.connect(sites.get(i).searchSite).get();
				for (Element file : doc.select(sites.get(i).getDivURLS())) {
					if(!file.attr("href").contains("youtube"))
						sites.get(i).addURL(file.attr("href"));
				}
			} catch (UnknownHostException e) {
				//// System.out.println("Impossible d'acc�der au site "+ sites.get(i).getSearchSite());
				//// System.out.println("V�rifiez que vous n'avez pas de proxy, ni de VPN activ�s");
			} catch (IOException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				//// System.out.println("Malformed URL :" + sites.get(i).searchSite);
			} finally {
				sites.get(i).reinitSearchSite(this.researcher);
			}
		}

	}

	public void addDatas() {
		Document doc;
		//ArrayList<Integer> indexToRemove = new ArrayList<Integer>();
		for (Site s : this.sites) {

			for (int i = 0; i < s.getUrls().size(); i++) {
				try {
					boolean present=false;
					doc = Jsoup.connect(s.getUrls().get(i).getUrl()).get();
					for (Element file : doc.select(s.divIngredients)) {

						present=setIngredients( s, i, file);
					}
					if (!present) {
						// System.out.println(s.getUrls().get(i).getUrl());
						setTitle(doc,s,i);
						setRecipe(doc, s, i);
						setVote(doc, s, i);
						setMark(doc,s,i);
						setImg(doc,s,i);
					}
					else{
						i--;
					}

				} catch (UnknownHostException e) {
					// System.out.println("Impossible d'accéder au site "+ sites.get(i).getSearchSite());
					//System.out.println("Vérifiez que vous n'avez pas de proxy, ni de VPN activés");
				} catch (IOException e) {
					// TODO Bloc catch auto-g�n�r�
					e.printStackTrace();
				}
			}
		}

	}

	public boolean containIngredients(String ingredients, String reg){
		boolean result =ingredients.toLowerCase().contains(
				reg.toLowerCase())||ingredients.toLowerCase().contains(
				reg.toLowerCase()+"s")||ingredients.toLowerCase().contains(
				reg.toLowerCase()+"x");
		if(reg.endsWith("s")){
			result=ingredients.toLowerCase().contains(
					reg.toLowerCase().substring(0,reg.lastIndexOf("s")));
		}
		else if(reg.endsWith("x")) {
			result = ingredients.toLowerCase().contains(
					reg.toLowerCase().substring(0, reg.lastIndexOf("x")));
		}
		return(result);
	}
	public boolean setIngredients( Site s,
								   int i, Element file) {
		boolean ret= false;
		String ingredients = file.outerHtml()
				.replaceAll("<.*?>", "")
				.replaceAll("[\r\n]", "");
		for (String str : exceptedIngredients) {
			if (this.containIngredients(ingredients,str)) {
				// System.out.println("REMOVE : "+ s.getUrls().get(i).getUrl());
				s.getUrls().remove(i);
				ret=true;
			} else {
				s.getUrls()
						.get(i)
						.setIngredients(
								ingredients.split("-"));
				for (int j = 0; j < s.getUrls().get(i)
						.getIngredients().length; j++) {

					s.getUrls().get(i).getIngredients()[j] = s
							.getUrls().get(i).getIngredients()[j]
							.replaceAll(" +", " ");
					// System.out.println(s.getUrls().get(i).getIngredients()[j]);
				}
			}
		}
		return ret;
	}

	private void setImg(Document doc, Site s, int i){
		// System.out.println("TEST : "+s.getDivImg());
		for (Element file : doc.select(s.getDivImg())) {
			s.getUrls().get(i).setImage(file.attr("src"));
		}
	}
	private void setVote(Document doc, Site s, int i) {
		for (Element file : doc.select(s.getDivVote())) {
			s.getUrls()
					.get(i)
					.setVote(
							Integer.valueOf(extractVote(file.toString()
									,s)));



		}
		if (doc.select(s.getDivVote()).size() == 0) {
			s.getUrls().get(i).setVote(0);
		}
	}
	private void setMark(Document doc, Site s, int i) {
		for (Element file : doc.select(s.getDivMark())) {
			s.getUrls()
					.get(i)
					.setMark(
							Float.valueOf(extractMark(file.toString().replace(",",".")
									,s)));



		}

	}
	private void setRecipe(Document doc, Site s, int i) {
		for (Element file : doc.select(s.getDivRecipe())) {

			String recipe =file.toString().replaceAll("<.*?>", "")
					.replaceAll("[\r\n]", "");
			s.getUrls()
					.get(i)
					.setRecipe(
							recipe);




		}

	}
	private void setTitle(Document doc, Site s, int i) {
		for (Element file : doc.select(s.getDivTitle())) {

			String title =file.toString().replaceAll("<.*?>", "")
					.replaceAll("[\r\n]", "");
			// System.out.println("Titre : "+ title);
			s.getUrls()
					.get(i)
					.setTitle(
							title);




		}

	}
	public String extractVote(String s,Site site) {
		String[] split=site.getTitleVote().split("value");
		String res=s.substring(s.indexOf(split[0])+split[0].length(),s.indexOf(split[1],s.indexOf(split[0])+split[0].length()));

		return res;
	}
	public String extractMark(String s,Site site) {
		String[] split=site.getTitleMark().split("value");
		String res=s.substring(s.indexOf(split[0])+split[0].length(),s.indexOf(split[1],s.indexOf(split[0])+split[0].length()));
		return res;
	}



	public void run(boolean marmiton, boolean cuisineaz, boolean feminin, boolean local, String input) {
		try {
			setPluginLoader();
			this.researchPlugins = this.pluginLoader.loadResearchPlugin();

			this.init(marmiton,cuisineaz, feminin,local,input);
			this.researchWithPlugins();
			this.addDatas();
			this.setResultMap(local);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public void setResultMap(boolean local){

		if(local){
			String[] ing= new String[keyWords.size()];
			String[] ex = new String[exceptedIngredients.size()];
			int k=0;
			for(String s : keyWords) {
				ing[k]=s;
				k++;
			}
			k=0;
			for(String s : exceptedIngredients){
				ex[k]=s;
				k++;
			}
			try {
				resultSearch.addAll(sql(ing,ex));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		for(Site s : this.getSites()){
			for(NotedRecipes recipe : s.getUrls()){
				resultSearch.add((recipe));
			}
		}

		Collections.sort(resultSearch,Collections.reverseOrder());
		/*
		for( NotedRecipes recipe : resultSearch) {
			// System.out.println(recipe.getMark() + "   " + recipe.getVote() + "   " + recipe.getUrl());
		}*/
	}

	public List<String> getResult( final int count )
	{
		for( final Site s : this.getSites() )
			for( final NotedRecipes recipe : s.getUrls() )
				resultSearch.add( recipe ) ;

		Collections.sort( resultSearch , Collections.reverseOrder() ) ;

		final List<String> links = new ArrayList<String>() ;

		int i = 0 ;

		for( final NotedRecipes recipe : resultSearch )
		{
			i++ ;
			links.add( recipe.getUrl() ) ;

			if( count > 0 && i == count )
				break ;
		}

		return links ;
	} ;
}

