package testJunit.implementations;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import junit.framework.TestCase;
import link.Search;

import org.junit.Before;
import org.junit.Test;

import plugin.PluginLoader;
import plugin.ResearchPlugin;
import testJunit.interfaces.ITestPluginLoader;

public class TestPluginLoader extends TestCase implements ITestPluginLoader {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
	}

	@Test
	@Override
	public void testWhenResearchPluginOk() {
		PluginLoader pluginLoader = new PluginLoader(
				new String[] { "src/testJunit/plugins/pluginTestOk.jar" });
		try {
			ArrayList<ResearchPlugin> rp = pluginLoader.loadResearchPlugin();
			assertEquals("Research Plugin not correctly loaded", rp.size(), 1);
			setUpStreams();
			rp.get(0).research();
			assertEquals("Methods research of plugin should dislay Plugin a and display "+outContent.toString(),outContent.toString(),"Plugin a"+System.getProperty("line.separator"));
		} catch (Exception e) {
			// TODO Bloc catch auto-généré
			e.printStackTrace();
		}
	}

	@Override
	public void testWhenFileResearchPluginNotExistant() {
		// TODO Module de remplacement de méthode auto-généré

	}

	@Override
	public void testWhenResearchPluginKO() {
		// TODO Module de remplacement de méthode auto-généré

	}

	@Override
	public void testWhenSeveralResearchPluginsOk() {
		// TODO Module de remplacement de méthode auto-généré

	}

	@Override
	public void testWhenSeveralResearchPluginsAndOneKo() {
		// TODO Module de remplacement de méthode auto-généré

	}

}
