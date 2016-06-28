import link.Search;
import plugin.ResearchPlugin;


public class pluginTestKo implements ResearchPlugin{


	@Override
	public void research() {
		Search s = new Search();
		try {
			s.initSites();
			s.init();
			s.research();
			s.addDatas();
		} catch (Exception e) {
			// TODO Bloc catch auto-généré
			e.printStackTrace();
		}
		
		System.out.println("Plugin c");
		
	}

	@Override
	public String getName() {
		
		return null;
	}

}
