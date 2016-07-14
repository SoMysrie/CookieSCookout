package application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * 
 * @author SophieLand
 *
 */
public class MainContainer 
{

    /**
     * 
     * @param stage
     * @param menuBar
     */
    public void createMainContainer(Stage stage, MenuBar menuBar)
    {
    	//create main container
        VBox root = new VBox();
        root.getChildren().addAll(menuBar);
        menuBar.prefWidthProperty().bind(stage.widthProperty());
        Scene scene = new Scene(root, 750, 750);
        scene.getStylesheets().add("/application/static/css/style.css");
        stage.setTitle("CookiesCookout");
        stage.setScene(scene);
        stage.show();
    }
    
    /**
    * @param stage
    * @param menuBar
    * @param vblabels
    */
   public void createMainContainer(Stage stage, MenuBar menuBar, VBox vbox)
   {
   	//create main container
       VBox root = new VBox();
       root.getChildren().addAll(menuBar, vbox);
       menuBar.prefWidthProperty().bind(stage.widthProperty());
       Scene scene = new Scene(root, 750, 750);
       scene.getStylesheets().add("/application/static/css/style.css");
       stage.setTitle("CookiesCookout");
       stage.setScene(scene);
       stage.show();
   }
   
    
    /**
     * 
     * @param stage
     * @param menuBar
     * @param vbox1
     * @param vbox2
     */
    public void createMainContainer(Stage stage, MenuBar menuBar, VBox vbox1, VBox vbox2)
    {
    	//create main container
        VBox root = new VBox();
        root.getChildren().addAll(menuBar, vbox1, vbox2);
        menuBar.prefWidthProperty().bind(stage.widthProperty());
        Scene scene = new Scene(root, 750, 750);
        scene.getStylesheets().add("/application/static/css/style.css");
        stage.setTitle("CookiesCookout");
        stage.setScene(scene);
        stage.show();
    }
    
    /**
     * 
     * @param stage
     * @param menuBar
     * @param button
     */
    public void createMainContainer(Stage stage, MenuBar menuBar, Button button)
    {
    	//create main container
        VBox root = new VBox();
        root.getChildren().addAll(menuBar, button);
        menuBar.prefWidthProperty().bind(stage.widthProperty());
        Scene scene = new Scene(root, 750, 750);
        scene.getStylesheets().add("/application/static/css/style.css");
        stage.setTitle("CookiesCookout");
        stage.setScene(scene);
        stage.show();
    }
}
