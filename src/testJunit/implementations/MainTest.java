package testJunit;


import junit.extensions.ActiveTestSuite;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class MainTest extends TestCase {
	
	public static TestSuite suite() {
		
		TestSuite suite = new ActiveTestSuite();
		
		suite.addTest(new TestSuite(testJunit.testSearch.implementations.TestSearch.class));
		suite.addTest(new TestSuite(testJunit.testXML.implementations.TestLoader.class));
		
	
		return suite;
	
		
	}
		public static void main(String[] args) {
	
		junit.textui.TestRunner.run(suite());
		
		}
	}
	
	
