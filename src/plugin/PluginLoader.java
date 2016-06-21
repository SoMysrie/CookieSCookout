package plugin;

import link.Search;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarFile;

/**
 * Created by Nicolas_Travail on 21/06/2016.
 */
public class PluginLoader {
    private String[] files;

    private Object classResearchPlugin=null;




    public PluginLoader(String[] files){
        this.files = files;
    }


    public void setFiles(String[] files ){
        this.files = files;
    }


    public ResearchPlugin loadResearchPlugin() throws Exception {

        this.initializeLoader();


            ResearchPlugin tmp = (ResearchPlugin)((Class)this.classResearchPlugin).newInstance();
        return tmp;
    }



    private void initializeLoader() throws Exception{
        //On vérifie que la liste des plugins à charger à été initialisé
        if(this.files == null || this.files.length == 0 ){
            throw new Exception("Pas de fichier spécifié");
        }

        if(this.classResearchPlugin != null ){
            return ;
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

            URI uri = f[index].toURI();
            URL u=uri.toURL();
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

                        if(tmpClass.getInterfaces()[i].getName().toString().equals("plugin.ResearchPlugin") && this.classResearchPlugin==null) {
                            this.classResearchPlugin=tmpClass;
                        }
                    }

                }
            }


        }

    }


}
