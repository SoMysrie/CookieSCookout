package testJunit.implementations;

import java.util.ArrayList;

import junit.framework.Assert;
import junit.framework.TestCase;
import link.Site;

import org.junit.Test;

import testJunit.interfaces.ITestLoader;
import xmlLoader.XMLLoader;

public class TestLoader extends TestCase implements ITestLoader {
	@Test
	public void testLoadWhenOk() throws Exception  {
		ArrayList<Site> sites;
		XMLLoader loader = new XMLLoader();
		sites = loader.load("src/testJunit/xmlFiles/testOK.xml");
		assertFalse("Not enough sites returned by XMLLoader", sites.size() < 3);
		assertFalse("Too many sites returned by XMLLoader", sites.size() > 3);
		int i = 1;
		for (Site site : sites) {
			assertTrue(
					"Main site has not been correctly initialized. Should be : mainSite"
							+ i + " instead of : " + site.getMainSite(), site
							.getMainSite().equals("mainSite" + i));
			assertTrue(
					"Search site has not been correctly initialized. Should be : REPLACEWITHKEYWORDS"
							+ i + " instead of : " + site.getSearchSite(), site
							.getSearchSite().equals("REPLACEWITHKEYWORDS" + i));
			assertTrue(
					"Div URLS has not been correctly initialized. Should be : divURLS"
							+ i + " instead of : " + site.getDivURLS(), site
							.getDivURLS().equals("divURLS" + i));
			assertTrue(
					"Div vote has not been correctly initialized. Should be : divVote"
							+ i + " instead of : " + site.getDivVote(), site
							.getDivVote().equals("divVote" + i));
			assertTrue(
					"Div mark has not been correctly initialized. Should be : divMark"
							+ i + " instead of : " + site.getDivMark(), site
							.getDivMark().equals("divMark" + i));
			assertTrue(
					"Div Ingredients has not been correctly initialized. Should be : divIngredients"
							+ i + " instead of : " + site.getDivIngredients(), site
							.getDivIngredients().equals("divIngredients" + i));
			assertTrue(
					"titleVote has not been correctly initialized. Should be : titleVote"
							+ i + " instead of : " + site.getTitleVote(), site
							.getTitleVote().equals("titleVote" + i));
			assertTrue(
					"titleMark has not been correctly initialized. Should be : titleMark"
							+ i + " instead of : " + site.getTitleMark(), site
							.getTitleMark().equals("titleMark" + i));
			assertTrue(
					"divRecipe has not been correctly initialized. Should be : divRecipe"
							+ i + " instead of : " + site.getDivRecipe(), site
							.getDivRecipe().equals("divRecipe" + i));
			assertTrue(
					"divTitle has not been correctly initialized. Should be : divTitle"
							+ i + " instead of : " + site.getDivTitle(), site
							.getDivTitle().equals("divTitle" + i));
			assertTrue(
					"divImg has not been correctly initialized. Should be : divImg"
							+ i + " instead of : " + site.getDivImg(), site
							.getDivImg().equals("divImg" + i));
			i++;
		}
	}

	@Test
	public void testLoadWhenSmtNull() {
		XMLLoader loader = new XMLLoader();
		try {
			loader.load("src/testJunit/xmlFiles/okButSmtNull.xml");
			fail("Must catch exception");

		}
		catch(Exception e){

		}


	}

	@Test
	public void testLoadWhenXMLIncomplete() {
		
		XMLLoader loader = new XMLLoader();
		try {
			loader.load("src/testJunit/xmlFiles/xmlIncomplete.xml");
			fail("Exception hasn't been thrown");
		} catch (Exception e) {
		}
	}
}
