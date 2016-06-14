package link;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Properties;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import xmlLoader.XMLLoader;

public class Search {
    ArrayList<Site> sites = new ArrayList<Site>();
    String researcher = "";
    public static String confPath = "conf/link.xml";
    Properties conf;
    String[] keyWords = {"oignon", "citron"};

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

    public String[] getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String[] keyWords) {
        this.keyWords = keyWords;
    }

    public void initSites() throws Exception {
        XMLLoader loader = new XMLLoader();
        this.sites = loader.load(confPath);
    }

    public void initLinks() {
        for (int i = 0; i < sites.size(); i++) {
            sites.get(i).replaceSearchSite(this.researcher);
        }
    }

    public void init() throws Exception {
        initSites();

        updateResearcher();
        initLinks();
    }

    public void updateResearcher() {
        this.researcher = "";
        int i;
        for (i = 0; i < keyWords.length - 1; i++) {
            this.researcher += keyWords[i] + "-";
        }
        this.researcher += keyWords[i];
    }

    public void research() {

        Document doc;
        for (int i = 0; i < this.sites.size(); i++) {
            try {
                doc = Jsoup.connect(sites.get(i).searchSite).get();
                for (Element file : doc.select(sites.get(i).getDivURLS())) {
                    sites.get(i).addURL(file.attr("href"));
                    // System.out.println(sites.get(i).getMainSite()+file.attr("href"));
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
        for (Site s : this.sites) {
            for (int i = 0; i < s.getUrls().size(); i++) {
                try {
                    doc = Jsoup.connect(s.getUrls().get(i).getUrl()).get();
                    for (Element file : doc.select(s.getDivVote())) {
                        // System.out.println(Integer.valueOf(file.ownText()));
                        s.getUrls()
                                .get(i)
                                .setVote(
                                        Integer.valueOf(extractVote(file
                                                .ownText())));


                    }
                    if (doc.select(s.getDivVote()).size() == 0) {
                        s.getUrls().get(i).setVote(0);
                    }
                    for (Element file : doc.select(s.divIngredients)) {
                        System.out.println();
                        s.getUrls()
                                .get(i)
                                .setIngredients(
                                        file.outerHtml().replaceAll("<.*?>", "").replaceAll("[\r\n]", " ").split(" - "));
                        for (int j = 0; j < s.getUrls().get(i).getIngredients().length; j++) {

                            s.getUrls().get(i).getIngredients()[j] = s.getUrls().get(i).getIngredients()[j].replaceAll(" +", " ");

                        }


                    }
                    System.out.println(s.getUrls().get(i).getVote());
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
        }
    }

    public String extractVote(String s) {
        return s.replace("(", "").replace(" ", "").replace(")", "")
                .replace("votes", "");

    }

    public void run() {
        try {
            this.initSites();
        } catch (Exception e) {
            // TODO Bloc catch auto-g�n�r�
            e.printStackTrace();
        }

        try {
            this.init();
            this.research();
            this.addDatas();
        } catch (Exception e) {
            // TODO Bloc catch auto-g�n�r�
            e.printStackTrace();
        }

    }
}
