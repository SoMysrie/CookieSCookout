package testJunit.testXML;

import java.util.ArrayList;

import junit.framework.Assert;
import junit.framework.TestCase;
import link.Site;

import org.junit.Test;

import xmlLoader.XMLLoader;

public class testLoader extends TestCase  {
	public void testLoadWhenOk() throws Exception {
		ArrayList<Site> sites;
		XMLLoader loader = new XMLLoader();
		sites = loader.load("src/testJunit/testXML/testOK.xml");
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

	public void testLoadWhenSmtNull() throws Exception {
		ArrayList<Site> sites;
		XMLLoader loader = new XMLLoader();
		sites = loader.load("src/testJunit/testXML/okButSmtNull.xml");
		assertFalse("Not enough sites returned by XMLLoader", sites.size() < 3);
		assertFalse("Too many sites returned by XMLLoader", sites.size() > 3);
		int i = 1;
		for (Site site : sites) {
			assertTrue(
					"Main site has not been correctly initialized. Should be :   instead of : "
							+ site.getMainSite(), site.getMainSite().equals(""));
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


	@Test
	public void testLoadWhenXMLIncomplete() {
			ArrayList<Site> sites= new ArrayList<Site>();
			XMLLoader loader = new XMLLoader();
			
			try {
				sites=loader.load("src/testJunit/testXML/xmlIncomplete.xml");
				Assert.fail("Exception hasn't been thrown");
			} catch (Exception e) {
				assertTrue("Sites must be empty", sites.size()==0);
			}
			
			 
			
			
		}
}
