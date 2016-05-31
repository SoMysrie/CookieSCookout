package testJunit.testSearch.implementations;

import junit.framework.TestCase;
import link.Search;
import link.Site;

import org.junit.Before;
import org.junit.Test;

import testJunit.testSearch.interfaces.ITestSearch;

public class TestSearch extends TestCase implements ITestSearch {
	public Search searcher = new Search();

	@Test
	public void testInitSite() {
		Search.confPath = "src/testJunit/testSearch/xmlFiles/testOK.xml";
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
				i++;
			}

		} catch (Exception e) {
			// TODO Bloc catch auto-généré
			e.printStackTrace();
		}

	}

	@Test
	@Before
	@Override
	public void testUpdateResearcherFirstTime() {
		String[] keyWords1 = { "oignon", "citron" };
		searcher.setKeyWords(keyWords1);
		searcher.updateResearcher();
		assertEquals("Researcher not properly updated for the first time",
				"oignon-citron", searcher.getResearcher());
	}

	@Test
	@Override
	public void testUpdateResearcherWithOneKeyWord() {
		String[] keyWords1 = { "citron" };
		searcher.setKeyWords(keyWords1);
		searcher.updateResearcher();
		assertEquals("Researcher not properly updated with one key word",
				"citron", searcher.getResearcher());
	}

	@Test
	@Override
	public void testUpdateResearcherTwice() {
		String[] keyWords1 = { "champignon", "bacon" };
		String[] keyWords2 = { "oignon", "citron" };

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
			searcher.initSites();
			String[] keyWords = { "oignon", "citron" };
			searcher.setKeyWords(keyWords);
			searcher.updateResearcher();
			searcher.initLinks();
			int i = 1;
			for (Site site : searcher.getSites()) {
				assertEquals("Link not properly initialized",
						site.getSearchSite(), "oignon-citron" + i);
				i++;
			}
		} catch (Exception e) {
			// TODO Bloc catch auto-généré
			e.printStackTrace();
		}

	}

	@Override
	@Test
	public void testSearchLinkAfterSearch() {
		try {
			String[] keyWords = { "oignon", "citron" };
			searcher.setKeyWords(keyWords);
			searcher.init();
			try {
				searcher.research();
			} catch (Exception e) {
				// TODO Bloc catch auto-généré
				e.printStackTrace();
			} finally {
				int i = 1;
				for (Site site : searcher.getSites()) {
					assertEquals("Search site not properly reset",
							site.getSearchSite(), "REPLACEWITHKEYWORDS"+i);
					i++;
				}
			}
		} catch (Exception e) {
			// TODO Bloc catch auto-généré
			e.printStackTrace();
		}

	}

}
