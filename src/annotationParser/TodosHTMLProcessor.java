package annotationParser;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

//Permet de spécifier les annotations à traiter
@SupportedAnnotationTypes(value = { 
               "annotationParser.Todos",
               "annotationParser.Todo"
              }
)

//Définit quelle version de source gérer, ici je code en Java 8
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class TodosHTMLProcessor extends AbstractProcessor {
   
  List<Todo> list = new ArrayList<>();;
  FileOutputStream fw = null;
  
  @Override
  public boolean process(
      Set<? extends TypeElement> annotations,
      RoundEnvironment roundEnv) {
      
    System.out.println("Début du traitement HTML !");
    
    //tout ceci est identique à l'autre processeur
    for (TypeElement te : annotations) {
 
      for (Element element : roundEnv.getElementsAnnotatedWith(te)) {
        
         Todos todos = element.getAnnotation(Todos.class);
         
        //Si l'annotation est de type Todos
        if(todos != null){
           
           System.out.println("Annotation Todos trouvée.");
           //On récupère l'annotation et on traite son contenu
           //en utilisant une boucle
           for(Todo todo : todos.value())
              this.addToList(todo);
        }
        else{
           System.out.println("Annotation Todo simple trouvée.");
           //sinon, on traite normalement
           Todo todo = element.getAnnotation(Todo.class);
           this.addToList(todo);
        }
      }
    }
    System.out.println("Fin du traitement HTML");
    
    //Génération du fichier HTML
    genererHTML(list);
    return true;
  }
  
  private void addToList(Todo todo){
     if(todo != null)
        this.list.add(todo);
  }
  
  private void genererHTML(List<Todo> list){
     
     StringBuilder html = new StringBuilder();
     html.append("<html>");     
     html.append("<body>");
     html.append("<table>");
     
     html.append("<tr>");
     html.append("<td style=\"border:1px solid black\">Criticité</td>");
     html.append("<td style=\"border:1px solid black\">Auteur</td>");
     html.append("<td style=\"border:1px solid black\">Destinataire</td>");
     html.append("<td style=\"border:1px solid black\">Commentaire</td>");
     html.append("</tr>");
     
     Iterator<Todo>  it = list.iterator();
     
     if(list.isEmpty())return;
     
     File htmlFile = new File("Todo.html");
     
     try {
        fw = new FileOutputStream(htmlFile);
     } catch (IOException e) {
       e.printStackTrace();
     }
     
     while(it.hasNext()){
        
        Todo todo = it.next();
        html.append("<tr>");
        String style = "";
        
        //Voilà à quoi sert le nouveau champ de mon énumération
        switch(todo.niveau().getLevel()){
           case 0 : 
              style = "style=\"color:green;border:1px solid black\"";
           break;
           case 1:
              style = "style=\"color:purple;border:1px solid black\"";
           break;
           case 2:
              style = "style=\"color:orange;border:1px solid black\"";
           break;
           case 3:
              style = "style=\"color:red;border:1px solid black\"";
           break;
        }
        
        html.append("<td " + style + ">" + todo.niveau() + "</td>");
        html.append("<td " + style + ">" + todo.auteur() + "</td>");
        html.append("<td " + style + ">" + todo.destinataire() + "</td>");
        html.append("<td " + style + ">" + todo.commentaire() + "</td>");
        html.append("</tr>");
     }
     
     html.append("</table>");
     html.append("</body>");     
     html.append("</html>");

     //On écrit dans le fichier et voilà !
     try {
      fw.write(html.toString().getBytes());
     } catch (IOException e) { 
      e.printStackTrace();
     }finally{
        try{
           fw.close();
        } catch (IOException ex) { 
           ex.printStackTrace();
           
        }
     }
  }  
}