package testJunit.testXML.implementations;

import java.util.ArrayList;

import junit.framework.Assert;
import junit.framework.TestCase;
import link.Site;

import org.junit.Test;

import testJunit.testXML.interfaces.ITestLoader;
import xmlLoader.XMLLoader;

public class TestLoader extends TestCase implements ITestLoader {
	
	@Override
	@Test
	public void testLoadWhenOk() throws Exception  {
		ArrayList<Site> sites;
		XMLLoader loader = new XMLLoader();
		sites = loader.load("src/testJunit/testXML/xmlFiles/testOK.xml");
		assertFalse("Not enough sites returned by XMLLoader", sites.size() < 3);
		assertFalse("Too many sites returned by XMLLoader", sites.size() > 3);
		int i = 1;
		for (Site site : sites) {
			assertTrue(
					"Main site has not been correctly initialized. Should be : mainSite"
							+ i + " instead of : " + site.getMainSite(), site
							.getMainSite().equals("mainSite" + i));
			assertTrue(
					"Search site has not been correctly initialized. Should be : searchSite"
							+ i + " instead of : " + site.getSearchSite(), site
							.getSearchSite().equals("searchSite" + i));
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
			i++;
		}
	}
	@Override
	@Test
	public void testLoadWhenSmtNull() throws Exception {
		XMLLoader loader = new XMLLoader();
		try {
			loader.load("src/testJunit/testXML/xmlFiles/okButFieldNull.xml");
			Assert.fail("Exception hasn't been thrown");
		} catch (Exception e) {
		}
	}

	@Override
	@Test
	public void testLoadWhenXMLIncomplete() {
		
		XMLLoader loader = new XMLLoader();
		try {
			loader.load("src/testJunit/testXML/xmlFiles/xmlIncomplete.xml");
			Assert.fail("Exception hasn't been thrown");
		} catch (Exception e) {
		}
	}
}
