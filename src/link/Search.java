package link;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.*;

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
	String[] plugins = {};//"plugin.jar","plugin2.jar","plugin3.jar"};
	PluginLoader pluginLoader = new PluginLoader(plugins);
	ArrayList<ResearchPlugin> researchPlugins;
    ArrayList<NotedRecipes> resultSearch= new ArrayList<NotedRecipes>();

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

	public void initSites() throws Exception {
		XMLLoader loader = new XMLLoader();
		this.sites = loader.load(confPath);
	}

	public void initKeyWords() {
		String input = "citron oignon ^poulet";
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

	public void init() throws Exception {
		initKeyWords();
		initSites();
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
					sites.get(i).addURL(file.attr("href"));
				}
			} catch (UnknownHostException e) {
				System.out.println("Impossible d'acc�der au site "
						+ sites.get(i).getSearchSite());
				System.out
						.println("V�rifiez que vous n'avez pas de proxy, ni de VPN activ�s");
			} catch (IOException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				System.out.println("Malformed URL :" + sites.get(i).searchSite);
			} finally {

				sites.get(i).reinitSearchSite(this.researcher);
			}
		}

	}

	public void addDatas() {
		Document doc;
		ArrayList<Integer> indexToRemove = new ArrayList<Integer>();
		for (Site s : this.sites) {
			int size = s.getUrls().size();
			for (int i = 0; i < size; i++) {
				try {
					doc = Jsoup.connect(s.getUrls().get(i).getUrl()).get();
					for (Element file : doc.select(s.divIngredients)) {
						setIngredients(indexToRemove, s, i, file);
					}
					if (!indexToRemove.contains(i)) {
                        System.out.println(s.getUrls().get(i).getUrl());
						setVote(doc, s, i);
						setMark(doc,s,i);
					}

				} catch (UnknownHostException e) {
					System.out.println("Impossible d'accéder au site "
							+ sites.get(i).getSearchSite());
					System.out
							.println("Vérifiez que vous n'avez pas de proxy, ni de VPN activés");
				} catch (IOException e) {
					// TODO Bloc catch auto-g�n�r�
					e.printStackTrace();
				}
			}
			this.updateLinks(s, indexToRemove);
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
	public void setIngredients(ArrayList<Integer> indexToRemove, Site s,
			int i, Element file) {
		String ingredients = file.outerHtml()
				.replaceAll("<.*?>", "")
				.replaceAll("[\r\n]", " ");
		for (String str : exceptedIngredients) {
			if (this.containIngredients(ingredients,str)) {
				indexToRemove.add(i);
				System.out.println("REMOVE : "
						+ s.getUrls().get(i).getUrl());
			} else {
				s.getUrls()
						.get(i)
						.setIngredients(
								ingredients.split(" - "));
				for (int j = 0; j < s.getUrls().get(i)
						.getIngredients().length; j++) {

					s.getUrls().get(i).getIngredients()[j] = s
							.getUrls().get(i).getIngredients()[j]
							.replaceAll(" +", " ");
		/*			System.out.println(s.getUrls().get(i)
							.getIngredients()[j]);*/
				}
			}
		}
	}

	private void setVote(Document doc, Site s, int i) {
		for (Element file : doc.select(s.getDivVote())) {
            System.out.println(s.getMainSite()+"    "+file);
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
            System.out.println( s.getUrls()
                    .get(i).getVote()+"     "+s.getUrls().get(i).getMark());



        }
        if (doc.select(s.getDivVote()).size() == 0) {
            s.getUrls().get(i).setVote(0);
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

	void updateLinks(Site s, ArrayList<Integer> indexToRemove) {
		for (Integer i : indexToRemove) {
			s.getUrls().remove(i);
		}
	}

	public void run() {
		try {
			this.initSites();
			this.researchPlugins = this.pluginLoader.loadResearchPlugin();
			System.out.println(researchPlugins.size());
			this.init();
			this.researchWithPlugins();
			this.addDatas();
            this.setResultMap();
		} catch (Exception e) {
			// TODO Bloc catch auto-g�n�r�
			e.printStackTrace();
		}

	}
    public void setResultMap(){

        for(Site s : this.getSites()){
            for(NotedRecipes recipe : s.getUrls()){
               resultSearch.add((recipe));
            }
        }
        Collections.sort(resultSearch,Collections.reverseOrder()
        );
        for(NotedRecipes recipe : resultSearch) {
            System.out.println(recipe.getMark() + "   " + recipe.getVote() + "   " + recipe.getUrl());
        }
    }
}

