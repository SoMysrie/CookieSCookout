package application;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
 
public class Main extends Application 
{
	//controls needed for app:
    CheckBox chkmarmiton, chkcuisineaz, chkaufeminin, chkbdd, chklocal;
    Label lbltotal, lbllist, lblMenu, lblFile, lblPlugin;
    Button btsubmit, btclear;
    
    //VBoxes for the labels and checkboxes 
    VBox vbchecks, vblabels, vbhome, vbfile, vbfile2, vbplugin, vbhelp, vbbutton;
    
    public static void main(String[] args) 
    {
        launch(args) ;
    } ;
 
    @Override
    public void start(Stage stage) 
    {
    	MenuBar menuBar = new MenuBar() ;
    	
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
                chkcuisineaz = new CheckBox("Website cuisineaz");
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
                		+ "- Website cuisineaz \n"
                		+ "- Website auFeminin \n"
                 		+ "- Website Cookie's Cookout \n"
                		+ "- Local \n");
                 
                //Defining the Submit button
                btsubmit = new Button("Submit");
            	//Defining the Clear button
                btclear = new Button("Clear");
                 
                //add all things to vboxes
                vbchecks.getChildren().addAll(chkmarmiton, chkcuisineaz, chkaufeminin, chkbdd, chklocal);
                vblabels.getChildren().addAll(lbltotal, lbllist);
                vbbutton.getChildren().addAll(btsubmit, btclear);
                 
                //attach click-method to all 5 checkboxes
                chkmarmiton.setOnAction(e -> handleButtonAction(e));
                chkcuisineaz.setOnAction(e -> handleButtonAction(e));
                chkaufeminin.setOnAction(e -> handleButtonAction(e));
                chkbdd.setOnAction(e -> handleButtonAction(e));
                chklocal.setOnAction(e -> handleButtonAction(e));
                
                //attach click-method to all 2 buttons
                btsubmit.setOnAction(new EventHandler<ActionEvent>() 
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
            	    }
            	});
                 
                //create main container
                FlowPane root = new FlowPane();
                root.setHgap(20);
                root.getChildren().addAll(menuBar, vbchecks, vblabels, vbbutton);
                String image = Main.class.getResource(("/application/static/img/LogoCookieSCookout.png").toString()).toExternalForm();
                root.setStyle("-fx-background-image: url('" + image + "'); " 
                		 + "-fx-background-position: center center; " 
                		 + "-fx-background-repeat: stretch;");    
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
    		   vbfile = new VBox();
    		   vbfile.setSpacing(10);
    		   vbfile.setPadding(new Insets(20));
            	 
    		   //vbbox for buttons
    		   vbfile2 = new VBox();
    		   vbfile2.setSpacing(10);
    		   vbfile2.setPadding(new Insets(20));
            	 
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
    		   final TextField picture = new TextField();
    		   picture.setPromptText("Enter your recipe's picture.");
    		   GridPane.setConstraints(picture, 0, 1);
    		   grid.getChildren().add(picture);
    		   //Defining the  text field
    		   final TextField steps = new TextField();
    		   steps.setPrefColumnCount(15);
    		   steps.setPromptText("Enter your recipe's steps.");
    		   GridPane.setConstraints(steps, 0, 2);
    		   grid.getChildren().add(steps);
    		   //Defining the ingredients text field
    		   final TextField ingredients = new TextField();
    		   ingredients.setPrefColumnCount(15);
    		   ingredients.setPromptText("Enter your recipe's ingredients.");
    		   GridPane.setConstraints(ingredients, 0, 2);
    		   grid.getChildren().add(ingredients);
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
    				   if ((title.getText() != null && !title.getText().isEmpty())) 
    				   {
    					   label.setText(title.getText() + ", " + "thank you for your recipe!");
            	       } else {
            	    	   label.setText("You have not left a title for you recipe.");
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
    				   picture.clear();
            	       steps.clear();
            	       ingredients.clear();
            	       label.setText(null);
            	   }
    		   });
    		   
    		   vbfile.getChildren().addAll(title, picture, steps, ingredients);
               vbfile2.getChildren().addAll(submit, clear, label);
            	 
               //create main container
               FlowPane root = new FlowPane();
               root.setHgap(20);
               root.getChildren().addAll(menuBar, vbfile, vbfile2);
               String image = Main.class.getResource(("/application/static/img/LogoCookieSCookout.png").toString()).toExternalForm();
               root.setStyle("-fx-background-image: url('" + image + "'); " 
                 		+ "-fx-background-position: center center; " 
                 		+ "-fx-background-repeat: stretch;");   
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
               //create main container
                 FlowPane root = new FlowPane();
                 root.setHgap(20);
                 root.getChildren().addAll(menuBar);
                 String image = Main.class.getResource(("/application/static/img/LogoCookieSCookout.png").toString()).toExternalForm();
                 root.setStyle("-fx-background-image: url('" + image + "'); " 
                 		+ "-fx-background-position: center center; " 
                 		+ "-fx-background-repeat: stretch;");  
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
                 lblMenu = new Label("Menu");
                 lblFile = new Label("File");
                 lblPlugin = new Label("Plugin");
                 
                 vblabels.getChildren().addAll(lblMenu, lblFile, lblPlugin);
                 
                 
                 //create main container
                 FlowPane root = new FlowPane();
                 root.setHgap(20);
                 root.getChildren().addAll(menuBar, vblabels);
                 String image = Main.class.getResource(("/application/static/img/LogoCookieSCookout.png").toString()).toExternalForm();
                 root.setStyle("-fx-background-image: url('" + image + "'); " 
                 		+ "-fx-background-position: center center; " 
                 		+ "-fx-background-repeat: stretch;");  
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
        		+ "-fx-background-repeat: stretch;");
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
}; 
