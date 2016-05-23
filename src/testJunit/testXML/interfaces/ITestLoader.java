package testJunit.testXML.interfaces;

public interface ITestLoader {
	public void testLoadWhenOk() throws Exception;
	public void testLoadWhenSmtNull() throws Exception;
	public void testLoadWhenXMLIncomplete();
}
