package testJunit.interfaces;

public interface ITestPluginLoader {
	public void testWhenResearchPluginOk();
	public void testWhenFileResearchPluginNotExistant();
	public void testWhenResearchPluginKO();
	public void testWhenSeveralResearchPluginsOk();
	public void testWhenSeveralResearchPluginsAndOneKo();

}
