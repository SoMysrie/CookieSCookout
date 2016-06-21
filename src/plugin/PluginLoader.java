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



    /**
     * Constucteur initialisant le tableau de fichier à charger.
     * @param files Tableau de String contenant la liste des fichiers à charger.
     */
    public PluginLoader(String[] files){
        this.files = files;
    }

    /**
     * Défini l'ensemble des fichiers à charger
     * @param files
     */
    public void setFiles(String[] files ){
        this.files = files;
    }


    public ResearchPlugin loadResearchPlugin() throws Exception {

        this.initializeLoader();


            //On créer une nouvelle instance de l'objet contenu dans la liste grâce à newInstance()
            //et on le cast en StringPlugins. Vu que la classe implémente StringPlugins, le cast est toujours correct
            ResearchPlugin tmp = (ResearchPlugin)((Class)this.classResearchPlugin).newInstance();
        return tmp;
    }



    private void initializeLoader() throws Exception{
        //On vérifie que la liste des plugins à charger à été initialisé
        if(this.files == null || this.files.length == 0 ){
            throw new Exception("Pas de fichier spécifié");
        }

        //Pour eviter le double chargement des plugins
        if(this.classResearchPlugin != null ){
            return ;
        }

        File[] f = new File[this.files.length];
//		Pour charger le .jar en memoire
        URLClassLoader loader;
        //Pour la comparaison de chaines
        String tmp = "";
        //Pour le contenu de l'archive jar
        Enumeration enumeration;
        //Pour déterminé quels sont les interfaces implémentées
        Class tmpClass = null;

        for(int index = 0 ; index < f.length ; index ++ ){

            f[index] = new File(this.files[index]);

            if( !f[index].exists() ) {
                break;
            }

            URI uri = f[index].toURI();
            URL u=uri.toURL();
            //On créer un nouveau URLClassLoader pour charger le jar qui se trouve ne dehors du CLASSPATH
            loader = new URLClassLoader(new URL[] {u});

            //On charge le jar en mémoire
            JarFile jar = new JarFile(f[index].getAbsolutePath());

            //On récupére le contenu du jar
            enumeration = jar.entries();

            while(enumeration.hasMoreElements()){

                tmp = enumeration.nextElement().toString();

                //On vérifie que le fichier courant est un .class (et pas un fichier d'informations du jar )
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
