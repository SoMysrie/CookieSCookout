package testJunit.implementations;

import junit.framework.TestCase;
import link.Search;
import link.Site;

import org.junit.Test;

import testJunit.interfaces.ITestSearch;

import java.util.ArrayList;

public class TestSearch extends TestCase implements ITestSearch {
    public Search searcher = new Search();

    @Test
    public void testInitSite() {
        Search.confPath = "src/testJunit/xmlFiles/testOK.xml";
        try {
            searcher.initSites();
            int i = 1;
            for (Site site : searcher.getSites()) {
                assertEquals("Main site not properly initialized",
                        site.getMainSite(), "mainSite" + i);
                assertEquals("Search site not properly initialized",
                        site.getSearchSite(), "REPLACEWITHKEYWORDS" + i);
                assertEquals("Div URLS not properly initialized",
                        site.getDivURLS(), "divURLS" + i);
                assertEquals("Div Vote not properly initialized",
                        site.getDivVote(), "divVote" + i);
                assertEquals("Div Mark not properly initialized",
                        site.getDivMark(), "divMark" + i);
                assertEquals("Div Ingredients not properly initialized",
                        site.getDivIngredients(), "divIngredients" + i);
                i++;
            }

        } catch (Exception e) {
            // TODO Bloc catch auto-g�n�r�
            e.printStackTrace();
        }

    }

    @Test
    @Override
    public void testUpdateResearcherFirstTime() {
        ArrayList<String> keyWords1 = new ArrayList<String>();
        keyWords1.add("oignon");
        keyWords1.add("citron");
        searcher.setKeyWords(keyWords1);
        searcher.updateResearcher();
        assertEquals("Researcher not properly updated for the first time",
                "oignon-citron", searcher.getResearcher());
    }

    @Test
    @Override
    public void testUpdateResearcherWithOneKeyWord() {
        ArrayList<String> keyWords1 = new ArrayList<String>();
        keyWords1.add("citron");
        searcher.setKeyWords(keyWords1);
        searcher.updateResearcher();
        assertEquals("Researcher not properly updated with one key word",
                "citron", searcher.getResearcher());
    }

    @Test
    @Override
    public void testUpdateResearcherTwice() {
        ArrayList<String> keyWords1 = new ArrayList<String>();
        ArrayList<String> keyWords2 = new ArrayList<String>();
        keyWords1.add("champignon");
        keyWords1.add("bacon");
        keyWords2.add("oignon");
        keyWords2.add("citron");

        searcher.setKeyWords(keyWords1);
        searcher.updateResearcher();
       assertEquals("Researcher not properly updated", "champignon-bacon",
                searcher.getResearcher());
        searcher.setKeyWords(keyWords2);
        searcher.updateResearcher();
        assertEquals("Researcher not properly updated for the second update",
                "oignon-citron", searcher.getResearcher());
    }

    @Override
    @Test
    public void testInitLinks() {
        try {

            ArrayList<String> keyWords= new ArrayList<String>();
            keyWords.add("oignon");
            keyWords.add("citron");
            searcher.setKeyWords(keyWords);
            searcher.updateResearcher();
            int i = 1;
            for (Site site : searcher.getSites()) {
                assertEquals("Link not properly initialized",
                        site.getSearchSite(), "oignon-citron" + i);
                i++;
            }
        } catch (Exception e) {
            // TODO Bloc catch auto-g�n�r�
            e.printStackTrace();
        }

    }

    @Override
    @Test
    public void testSearchLinkAfterSearch() {
        try {
            ArrayList<String> keyWords= new ArrayList<String>();
            keyWords.add("oignon");
            keyWords.add("citron");
            searcher.setKeyWords(keyWords);
            try {
                searcher.research();
            } catch (Exception e) {
                // TODO Bloc catch auto-g�n�r�
                e.printStackTrace();
            } finally {
                int i = 1;
                for (Site site : searcher.getSites()) {
                    assertEquals("Search site not properly reset",
                            site.getSearchSite(), "REPLACEWITHKEYWORDS" + i);
                    i++;
                }
            }
        } catch (Exception e) {
            // TODO Bloc catch auto-g�n�r�
            e.printStackTrace();
        }

    }

}
