package plugin;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarFile;

/**
 * Created by Nicolas_Travail on 21/06/2016.
 */
public class PluginLoader {
    private String[] files;

    private ArrayList<Object> classResearchPlugin;


public PluginLoader(){
	this.classResearchPlugin=new ArrayList<Object>();
}

    public PluginLoader(String[] files){
    	this();
        this.files = files;
    }


    public void setFiles(String[] files ){
        this.files = files;
    }


    public ArrayList<ResearchPlugin> loadResearchPlugin() throws Exception {

        this.initializeLoader();
        ArrayList<ResearchPlugin> tmp = new ArrayList<ResearchPlugin>();
        for(int i=0;i<this.classResearchPlugin.size();i++)
            tmp.add((ResearchPlugin)((Class)this.classResearchPlugin.get(i)).newInstance());
        return tmp;
    }



    private void initializeLoader() throws Exception{
        if(this.files == null || this.files.length == 0 || this.classResearchPlugin.size()!=0){
            return;
        }

       
       
        File[] f = new File[this.files.length];
        URLClassLoader loader;
        String tmp = "";
        Enumeration enumeration;
        Class tmpClass = null;

        for(int index = 0 ; index < f.length ; index ++ ){
        	
            f[index] = new File(this.files[index]);

            if( !f[index].exists() ) {
                break;
            }

            URL u = f[index].toURI().toURL();
            
            loader = new URLClassLoader(new URL[] {u});

            JarFile jar = new JarFile(f[index].getAbsolutePath());

            enumeration = jar.entries();

            while(enumeration.hasMoreElements()){

                tmp = enumeration.nextElement().toString();

                if(tmp.length() > 6 && tmp.endsWith(".class")) {

                    tmp = tmp.substring(0,tmp.length()-6);
                    tmp = tmp.replaceAll("/",".");

                    tmpClass = Class.forName(tmp ,true,loader);

                    for(int i = 0 ; i < tmpClass.getInterfaces().length; i ++ ){
                        if(tmpClass.getInterfaces()[i].getName().equals("plugin.ResearchPlugin")) {
                            this.classResearchPlugin.add(tmpClass);
                            
                        }
                    }

                }
            }


        }

    }


}
