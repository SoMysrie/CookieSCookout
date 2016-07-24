package testJunit.interfaces;

public interface ITestLoader {
	public void testLoadWhenOk() throws Exception;
	public void testLoadWhenSmtNull() ;
	public void testLoadWhenXMLIncomplete();
}
