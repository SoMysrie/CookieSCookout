package application;

import cookiescookout.ws.WsServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class Main extends Application 
{
	//controls needed for app:
    CheckBox chkmarmiton, chkcuisineaz, chkaufeminin, chkbdd, chklocal;
    Label lbltotal, lbllist, lblMenu, lblFile, lblPlugin, lblHelp;
    Button btsubmit, btclear, btsearch;
    
    //VBoxes for the labels and checkboxes 
    VBox vbchecks, vblabels, vbplugin, vbhelp, vbbutton, vbtext;
    
    //
    private Desktop desktop = Desktop.getDesktop();
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException
    {
    	System.out.println("CookieSCookout app...") ;
 		WsServer ws = new WsServer() ;
 		ws.start() ;
 		
        launch(args) ;
    } ;
 
    @Override
    public void start(Stage stage) 
    {
    	MenuBar menuBar = new MenuBar() ;
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Open Resource File");
    	fileChooser.showOpenDialog(stage);
    	
    	// --- Menu Home
        Menu menuHome = new Menu("Home");
        MenuItem menuItemHome = new MenuItem("Home");
        menuItemHome.setOnAction(new EventHandler<ActionEvent>() 
        {
        	@Override
            public void handle(ActionEvent event) 
        	{
        		//vbox for checkboxes     
                vbchecks = new VBox();
                vbchecks.setSpacing(10);
                vbchecks.setPadding(new Insets(20));
                 
                //vbox for textfield
                vbtext = new VBox();
                vbtext.setSpacing(10);
                vbtext.setPadding(new Insets(20));		
                
                //vbox for labels
                vblabels = new VBox();
                vblabels.setSpacing(10);
                vblabels.setPadding(new Insets(20));
                 
                //vbox for buttons
                vbbutton = new VBox();
                vbbutton.setSpacing(10);
                vbbutton.setPadding(new Insets(20));
                 
                //make 5 checkboxes
                chkmarmiton = new CheckBox("Website Marmiton");
                chkcuisineaz = new CheckBox("Website Cuisineaz");
                chkaufeminin = new CheckBox("Website auFeminin");
                chkbdd = new CheckBox("Website Cookie's Cookout");
                chklocal = new CheckBox("Local");
     		   
                //setSelected on true for all 5 checkboxes
                chkmarmiton.setSelected(true);
                chkcuisineaz.setSelected(true);
                chkaufeminin.setSelected(true);
                chkbdd.setSelected(true);
                chklocal.setSelected(true);
                 
                //make 2 labels
                lbltotal = new Label("Websites chosen: 5");
                lbllist = new Label("- Website Marmiton \n"
                		+ "- Website Cuisineaz \n"
                		+ "- Website auFeminin \n"
                 		+ "- Website Cookie's Cookout \n"
                		+ "- Local \n");
                 
                //textfield
                final TextField search = new TextField();
     		    search.setPromptText("What do you want to search?");
     		    search.setPrefWidth(200);
                
                //Defining the Submit button
                btsearch = new Button("Search");
            	//Defining the Clear button
                btclear = new Button("Clear");

                //add all things to vboxes
                vbchecks.getChildren().addAll(chkmarmiton, chkcuisineaz, chkaufeminin, chkbdd, chklocal);
                vblabels.getChildren().addAll(lbltotal, lbllist);
                vbtext.getChildren().add(search);
                vbbutton.getChildren().addAll(btsearch, btclear);
                 
                //attach click-method to all 5 checkboxes
                chkmarmiton.setOnAction(e -> handleButtonAction(e));
                chkcuisineaz.setOnAction(e -> handleButtonAction(e));
                chkaufeminin.setOnAction(e -> handleButtonAction(e));
                chkbdd.setOnAction(e -> handleButtonAction(e));
                chklocal.setOnAction(e -> handleButtonAction(e));
                
                //attach click-method to all 2 buttons
                btsearch.setOnAction(new EventHandler<ActionEvent>() 
                {
                	@Override
            	    public void handle(ActionEvent e)
                	{
            	         //TO DO
            	     }
            	});
                btclear.setOnAction(new EventHandler<ActionEvent>() 
                {
                	@Override
            	    public void handle(ActionEvent e) 
            		{
                		chkmarmiton.setSelected(false);
                        chkcuisineaz.setSelected(false);
                        chkaufeminin.setSelected(false);
                        chkbdd.setSelected(false);
                        chklocal.setSelected(false);
                        lbltotal.setText("Website chosen: 0");
                        lbllist.setText("None");
                        search.clear();
            	    }
            	});
                 
                //create main container
                FlowPane root = new FlowPane();
                root.setHgap(20);
                root.getChildren().addAll(menuBar, vbchecks, vblabels, vbtext, vbbutton);
                String image = Main.class.getResource(("/application/static/img/LogoCookieSCookout.png").toString()).toExternalForm();
                root.setStyle("-fx-background-image: url('" + image + "'); " 
                		 + "-fx-background-position: center center; " 
                		 + "-fx-background-repeat: stretch;"
                		 + "-fx-background-color : transparent");    
                Scene scene = new Scene(root, 750, 750);
                stage.setScene(scene);
                stage.show();
            }
       });
        menuHome.getItems().add(menuItemHome);
        
        // --- Menu File
        Menu menuFile = new Menu("File");
        MenuItem menuItemFile = new MenuItem("File");
        menuItemFile.setOnAction(new EventHandler<ActionEvent>() 
        {
        	@Override
            public void handle(ActionEvent event) 
    	    {
    		   //vbbox for text field
    		   vbtext = new VBox();
    		   vbtext.setSpacing(10);
    		   vbtext.setPadding(new Insets(20));
            	 
    		   //vbbox for buttons
    		   vbbutton = new VBox();
    		   vbbutton.setSpacing(10);
    		   vbbutton.setPadding(new Insets(20));
            	 
    		   //Creating a GridPane container
    		   GridPane grid = new GridPane();
    		   grid.setPadding(new Insets(10, 10, 10, 10));
    		   grid.setVgap(5);
    		   grid.setHgap(5);
    		   //Defining the Title text field
    		   final TextField title = new TextField();
    		   title.setPromptText("Enter your recipe's title");
    		   title.setPrefColumnCount(10);
    		   title.getText();
    		   GridPane.setConstraints(title, 0, 0);
    		   grid.getChildren().add(title);
    		   //Defining the picture text field
    		   final TextField img = new TextField();
    		   img.setPromptText("Enter your recipe's image.");
    		   GridPane.setConstraints(img, 0, 1);
    		   grid.getChildren().add(img);
    		   //Defining the ingredients text field
    		   final TextArea ingredients = new TextArea();
    		   ingredients.setPrefColumnCount(15);
    		   ingredients.setPromptText("Enter your recipe's ingredients.");
    		   GridPane.setConstraints(ingredients, 0, 2);
    		   grid.getChildren().add(ingredients);
    		   ingredients.setMaxWidth(250);
    		   ingredients.setMaxHeight(8);
    		   //Defining the  text field
    		   final TextArea recipe = new TextArea();
    		   recipe.setPrefColumnCount(15);
    		   recipe.setPromptText("Enter your recipe's steps.");
    		   GridPane.setConstraints(recipe, 0, 2);
    		   grid.getChildren().add(recipe);
    		   recipe.setPrefWidth(250);
    		   recipe.setPrefWidth(50);
    		   
    		   //Defining the Submit button
    		   Button submit = new Button("Submit");
    		   GridPane.setConstraints(submit, 1, 0);
    		   grid.getChildren().add(submit);
    		   //Defining the Clear button
    		   Button clear = new Button("Clear");
    		   GridPane.setConstraints(clear, 1, 1);
    		   grid.getChildren().add(clear);
            	 
    		   //Adding a Label
    		   final Label label = new Label();
    		   GridPane.setConstraints(label, 0, 3);
    		   GridPane.setColumnSpan(label, 2);
    		   grid.getChildren().add(label);
            	 
    		   //Setting an action for the Submit button
    		   submit.setOnAction(new EventHandler<ActionEvent>() 
    		   {
    			   @Override
    			   public void handle(ActionEvent e) 
    			   {
    				   if ((title.getText() != null && !title.getText().isEmpty()) 
    						   && (img.getText() != null && !img.getText().isEmpty()) 
    						   && (ingredients.getText() != null && !ingredients.getText().isEmpty()) 
    						   && (recipe.getText() != null && !recipe.getText().isEmpty())) 
    				   {
    					   label.setText("Yes!");
    					   saveInBDD(img.getText(), title.getText(), recipe.getText(), ingredients.getText());
            	       } 
    				   else
    				   {
    					   label.setText("You have to complete everything!");
    				   }
    			   }
    		   });
    		   
    		   //Setting an action for the Clear button
    		   clear.setOnAction(new EventHandler<ActionEvent>() 
    		   {
    			   @Override
            	   public void handle(ActionEvent e) 
    			   {
    				   title.clear();
    				   img.clear();
    				   ingredients.clear();
    				   recipe.clear();
            	       label.setText(null);
            	   }
    		   });
    		   
    		   vbtext.getChildren().addAll(title, img, ingredients, recipe);
               vbbutton.getChildren().addAll(submit, clear, label);
            	 
               //create main container
               FlowPane root = new FlowPane();
               root.setHgap(20);
               root.getChildren().addAll(menuBar, vbtext, vbbutton);
               String image = Main.class.getResource(("/application/static/img/LogoCookieSCookout.png").toString()).toExternalForm();
               root.setStyle("-fx-background-image: url('" + image + "'); " 
                 		+ "-fx-background-position: center center; " 
                 		+ "-fx-background-repeat: stretch;"
                 		+ "-fx-background-color : transparent");   
               Scene scene = new Scene(root, 750, 750);
               stage.setScene(scene);
               stage.show();
    	    }
        });
        menuFile.getItems().add(menuItemFile);
        
        // --- Menu Plugin
        Menu menuPlugin = new Menu("Plugin");
        MenuItem menuItemPlugin = new MenuItem("Plugin");
        menuItemPlugin.setOnAction(new EventHandler<ActionEvent>() 
        {
             @Override
             public void handle(ActionEvent event) 
             {
            	 final FileChooser fileChooser = new FileChooser();
            	 
                 final Button openButton = new Button("Open a Picture...");
                 final Button openMultipleButton = new Button("Open Pictures...");
          
                 openButton.setOnAction(
                     new EventHandler<ActionEvent>() {
                         @Override
                         public void handle(final ActionEvent e) {
                             File file = fileChooser.showOpenDialog(stage);
                             if (file != null) {
                                 openFile(file);
                             }
                         }
                     }
                 );
          
                 openMultipleButton.setOnAction(
                     new EventHandler<ActionEvent>() {
                         @Override
                         public void handle(final ActionEvent e) {
                             List<File> list =
                                 fileChooser.showOpenMultipleDialog(stage);
                             if (list != null) {
                                 for (File file : list) {
                                     openFile(file);
                                 }
                             }
                         }
                     }
                 );
                 
                 final GridPane inputGridPane = new GridPane();
                 
                 GridPane.setConstraints(openButton, 0, 0);
                 GridPane.setConstraints(openMultipleButton, 1, 0);
                 inputGridPane.setHgap(6);
                 inputGridPane.setVgap(6);
                 inputGridPane.getChildren().addAll(openButton, openMultipleButton);
          
                 final Pane rootGroup = new VBox(12);
                 rootGroup.getChildren().addAll(inputGridPane);
                 rootGroup.setPadding(new Insets(12, 12, 12, 12));
                 
            	 //create main container
                 FlowPane root = new FlowPane();
                 root.setHgap(20);
                 root.getChildren().addAll(menuBar, rootGroup);
                 String image = Main.class.getResource(("/application/static/img/LogoCookieSCookout.png").toString()).toExternalForm();
                 root.setStyle("-fx-background-image: url('" + image + "'); " 
                 		+ "-fx-background-position: center center; " 
                 		+ "-fx-background-repeat: stretch;"
                 		+ "-fx-background-color : transparent");  
                 Scene scene = new Scene(root, 750, 750);
                 stage.setScene(scene);
                 stage.show();
             }
        });
        menuPlugin.getItems().add(menuItemPlugin);
        
        // --- Menu Help
        Menu menuHelp = new Menu("Help");
        MenuItem menuItemHelp = new MenuItem("Help");
        menuItemHelp.setOnAction(new EventHandler<ActionEvent>()
        {       
             @Override
             public void handle(ActionEvent event)
             {
                 //vbox for labels
                 vblabels = new VBox();
                 vblabels.setSpacing(10);
                 vblabels.setPadding(new Insets(20));
                 
                 //make 2 labels
                 lblMenu = new Label("Menu \n"
                 		+ "	You can search through our different support to find any recipe. \n"
                 		+ "		Websites: Marmiton, Cuisineaz, auFeminin \n"
                 		+ "		Even on our website Cookie's Cookout \n"
                 		+ "		Or locally. \n");
                 lblFile = new Label("File \n"
                 		+ "	You can add any new recipe with these following information: \n"
                 		+ "		- Title\n"
                 		+ "		- Picture\n"
                 		+ "		- Ingredients\n"
                 		+ "		- Steps\n");
                 lblPlugin = new Label("Plugin \n"
                 		+ "	You can add any new plugin.\n");
                 lblHelp = new Label("Help \n"
                 		+ "	You can see this list of information.\n");
                 
                 vblabels.getChildren().addAll(lblMenu, lblFile, lblPlugin, lblHelp);
                 
                 //create main container
                 FlowPane root = new FlowPane();
                 root.setHgap(20);
                 root.getChildren().addAll(menuBar, vblabels);
                 String image = Main.class.getResource(("/application/static/img/LogoCookieSCookout.png").toString()).toExternalForm();
                 root.setStyle("-fx-background-image: url('" + image + "'); " 
                 		+ "-fx-background-position: center center; " 
                 		+ "-fx-background-repeat: stretch;"
                 		+ "-fx-background-color : transparent");  
                 Scene scene = new Scene(root, 750, 750);
                 stage.setScene(scene);
                 stage.show();
             }
        });
        menuHelp.getItems().add(menuItemHelp);
    	
        menuBar.getMenus().addAll(menuHome, menuFile, menuPlugin, menuHelp);
        
        //create main container
        FlowPane root = new FlowPane();
        root.setHgap(20);
        root.getChildren().add(menuBar);
        menuBar.prefWidthProperty().bind(stage.widthProperty());
        String image = Main.class.getResource(("/application/static/img/LogoCookieSCookout.png").toString()).toExternalForm();
        root.setStyle("-fx-background-image: url('" + image + "'); " 
        		+ "-fx-background-position: center center; " 
        		+ "-fx-background-repeat: stretch;"
        		+ "-fx-background-color : transparent");
        Scene scene = new Scene(root, 750, 750);
        stage.setTitle("CookiesCookout");
        stage.setScene(scene);
        stage.show();
    };
    
    private void handleButtonAction(ActionEvent e) 
    {
        int count = 0;
        String choices = "";
        if(chkmarmiton.isSelected())
        {
            count++;
            choices += "- " + chkmarmiton.getText() + "\n";
        }
        if(chkcuisineaz.isSelected())
        {
            count++;
            choices += "- " + chkcuisineaz.getText() + "\n";
        }
        if(chkaufeminin.isSelected())
        {
            count++;
            choices += "- " + chkaufeminin.getText() + "\n";
        }
        if(chkbdd.isSelected())
        {
            count++;
            choices += "- " + chkbdd.getText() + "\n";
        }
        if(chklocal.isSelected())
        {
            count++;
            choices += "- " + chklocal.getText() + "\n";
        }
        if(count == 0)
        {
        	lbltotal.setText("Website chosen: 0");
            lbllist.setText("None");
        }
        lbltotal.setText("Websites chosen: " + count);
        lbllist.setText(choices);
     };

    public static void saveInBDD(String img, String title, String recipe, String ingredients)
  	{
  		// Information d'acces a la BDD
  		String url = "jdbc:mysql://localhost:3306/CookieSCookout" ;
  		String login = "root" ;
  		String password = "root" ;
  		Connection cn = null ;
  		Statement st = null ;
  		
  		try
  		{
  			// Etape 1: chargement du driver
  			Class.forName("com.mysql.jdbc.Driver") ;
  			// Etape 2: recuperation de la connexion
  			cn = DriverManager.getConnection(url, login, password) ;
  			// Etape 3: creation d'un statement
  			st = cn.createStatement() ;
  			String sql = "INSERT INTO SiteWeb (img, title, recipe, ingredients, score, poll, url) "
  					+ "VALUES ('"+ img + "','" + title + "','" + recipe +  "','" + ingredients + "','" + 1 + "','" + 1 + "','" + null +"');" ;
  			// Etape 4: execution requete
  			st.executeUpdate(sql) ;
  			System.out.println("Successfuly saved in BDD!");
  		}
  		catch(SQLException e)
  		{
  			e.printStackTrace() ;
  		}
  		catch(ClassNotFoundException e)
  		{
  			e.printStackTrace() ;
  		}
  		finally
  		{
  			try
  			{
  				// Etape 5: liberer les ressources de la memoire
  				cn.close() ;
  				st.close() ;
  			}
  			catch(SQLException e)
  			{
  				e.printStackTrace() ;
  			}
  		}
  		
  	} ;

  	private void openFile(File file) {
        try {
            desktop.open(file);
        } catch (IOException ex) {
            Logger.getLogger(
                Main.class.getName()).log(
                    Level.SEVERE, null, ex
                );
        }
    }
}; 
