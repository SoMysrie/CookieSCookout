package testJunit.implementations;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import plugin.PluginLoader;
import plugin.ResearchPlugin;
import testJunit.interfaces.ITestPluginLoader;

public class TestPluginLoader extends TestCase implements ITestPluginLoader {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Before
	public void setUpStream() {
		System.setOut(new PrintStream(outContent));
	}

	@After
	public void cleanUpStream() {
		System.setOut(null);
	}

	@Test
	@Override
	public void testWhenResearchPluginOk() {
		PluginLoader pluginLoader = new PluginLoader(
				new String[]{"src/testJunit/plugins/pluginTestOk.jar"});
		try {
			ArrayList<ResearchPlugin> rp = pluginLoader.loadResearchPlugin();
			assertEquals("Research Plugin not correctly loaded", 1, rp.size());
			setUpStream();
			rp.get(0).research();
			assertEquals(
					"Methods research of plugin should dislay Plugin a and display "
							+ outContent.toString(),
					"Plugin a" + System.getProperty("line.separator"),
					outContent.toString());
			cleanUpStream();
		} catch (Exception e) {
			Assert.fail("Mustn't throw Exception");
		}
	}

	@Override
	public void testWhenFileResearchPluginNotExistant() {
		PluginLoader pluginLoader = new PluginLoader(
				new String[]{"src/testJunit/plugins/pluginNonExistant.jar"});
		try {
			ArrayList<ResearchPlugin> rp = pluginLoader.loadResearchPlugin();
			assertEquals("Research Plugin loaded", 0, rp.size());

		} catch (Exception e) {
			Assert.fail("Try to load a non existant Plugin must not be a probleme, the plugin must not just be loaded");
		}

	}


	@Test
	@Override
	public void testWhenSeveralResearchPluginsOk() {
		PluginLoader pluginLoader = new PluginLoader(new String[]{
				"src/testJunit/plugins/pluginTestOk.jar",
				"src/testJunit/plugins/pluginTestOk2.jar"});
		try {
			ArrayList<ResearchPlugin> rp = pluginLoader.loadResearchPlugin();
			assertEquals("Research Plugin not correctly loaded", 2, rp.size());
			setUpStream();
			for (ResearchPlugin r : rp) {
				r.research();
			}
			assertEquals("Methods research of plugin should dislay Plugin a"
							+ System.getProperty("line.separator")
							+ "Plugin b and display " + outContent.toString(),
					"Plugin a" + System.getProperty("line.separator") + "Plugin b" + System.getProperty("line.separator"),
					outContent.toString());
			cleanUpStream();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Mustn't throw exception");

		}
	}
}



