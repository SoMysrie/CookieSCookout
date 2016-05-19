package testJunit.testXML;

import link.Site;
import java.util.ArrayList;
import xmlLoader.XMLLoader;

import junit.framework.*;


public class testLoader  extends TestCase{
		public void testLoadWhenOk(){
			ArrayList<Site> sites;
			XMLLoader loader = new XMLLoader();
			sites=loader.load("src/testJunit/testXML/testOK.xml");
			assertFalse("Not enough sites returned by XMLLoader", sites.size()< 3);
			assertFalse("Too much sites returned by XMLLoader", sites.size()> 3);
			int i=1;
			for(Site site : sites){
				assertTrue("Main site has not been correctly initialized. Should be : mainSite"+i+" instead of : "+site.getMainSite(), site.getMainSite().equals("mainSite"+i));
				assertTrue("Search site has not been correctly initialized. Should be : searchSite"+i+" instead of : "+site.getSearchSite(), site.getSearchSite().equals("searchSite"+i));
				assertTrue("Div URLS has not been correctly initialized. Should be : divURLS"+i+" instead of : "+site.getDivURLS(), site.getDivURLS().equals("divURLS"+i));
				assertTrue("Div vote has not been correctly initialized. Should be : divVote"+i+" instead of : "+site.getDivVote(), site.getDivVote().equals("divVote"+i));
				assertTrue("Div mark has not been correctly initialized. Should be : divMark"+i+" instead of : "+site.getDivMark(), site.getDivMark().equals("divMark"+i));
				i++;
			}
		}
}
