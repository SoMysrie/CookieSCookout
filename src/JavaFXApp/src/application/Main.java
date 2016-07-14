package application;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import cookiescookout.ws.WsServer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Main extends Application 
{
	//controls needed for app:
    CheckBox chkmarmiton, chkcuisineaz, chkaufeminin, chkbdd, chklocal;
    Label lbltotal, lbllist, lblMenu, lblFile, lblPlugin, lblHelp;
    Button btsubmit, btclear, btsearch;
    
    //VBoxes for the labels and checkboxes 
    VBox vbchecks, vblabels, vbplugin, vbhelp, vbbutton, vbtext, vball;
    
    //Desktop needed for searching a file
    private Desktop desktop = Desktop.getDesktop();
    
    //TableView for Bdd
    private TableView<Recipe> table = new TableView<Recipe>();
    private TableColumn<Recipe, String> imgCol = new TableColumn<Recipe, String>();
    private TableColumn<Recipe, String> titleCol = new TableColumn<Recipe, String>();
    private TableColumn<Recipe, String> recipeCol = new TableColumn<Recipe, String>();
    private TableColumn<Recipe, String> ingCol = new TableColumn<Recipe, String>();
    private TableColumn<Recipe, String> scoreCol = new TableColumn<Recipe, String>();
    private TableColumn<Recipe, String> pollCol = new TableColumn<Recipe, String>();
    private TableColumn<Recipe, String> urlCol = new TableColumn<Recipe, String>();
    
    //Calling Methods
    MainContainer mainContainer = new MainContainer();
    Bdd bdd = new Bdd();
    
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
    	
    	// --- Menu Home
        Menu menuHome = new Menu("Home");
        MenuItem menuItemHome = new MenuItem("Home");
        menuItemHome.setOnAction(new EventHandler<ActionEvent>() 
        {
        	@Override
            public void handle(ActionEvent event) 
        	{
        		//vbox for all
        		vball = new VBox();
        		
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
     		    search.setMaxWidth(195);
                
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
                 
                
                vball.getChildren().addAll(menuBar, vbchecks, vblabels, vbtext, vbbutton);
                mainContainer.createMainContainer(stage, menuBar, vball);
            }
        });
        menuHome.getItems().add(menuItemHome);
        
        /*
        // --- Menu Recipes
        Menu menuRecipe = new Menu("Recipes");
        MenuItem menuItemRecipe = new MenuItem("Recipes");
        menuItemRecipe.setOnAction(new EventHandler<ActionEvent>() 
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
    		   title.setMaxWidth(250);
    		   title.setMaxHeight(8);
    		   //Defining the picture text field
    		   final TextField img = new TextField();
    		   img.setPromptText("Enter your recipe's image.");
    		   GridPane.setConstraints(img, 0, 1);
    		   grid.getChildren().add(img);
    		   img.setMaxWidth(250);
    		   img.setMaxHeight(8);
    		   //Defining the ingredients text field
    		   final TextArea ingredients = new TextArea();
    		   ingredients.setPrefColumnCount(15);
    		   ingredients.setPromptText("Enter your recipe's ingredients.");
    		   GridPane.setConstraints(ingredients, 0, 2);
    		   grid.getChildren().add(ingredients);
    		   ingredients.setMaxWidth(250);
    		   ingredients.setMaxHeight(250);
    		   //Defining the  text field
    		   final TextArea recipe = new TextArea();
    		   recipe.setPrefColumnCount(15);
    		   recipe.setPromptText("Enter your recipe's steps.");
    		   GridPane.setConstraints(recipe, 0, 3);
    		   grid.getChildren().add(recipe);
    		   recipe.setMaxWidth(250);
    		   recipe.setMaxHeight(500);
    		   
    		   //Defining the Submit button
    		   Button submit = new Button("Submit");
    		   GridPane.setConstraints(submit, 2, 0);
    		   grid.getChildren().add(submit);
    		   //Defining the Clear button
    		   Button clear = new Button("Clear");
    		   GridPane.setConstraints(clear, 2, 1);
    		   grid.getChildren().add(clear);
            	 
    		   //Adding a Label
    		   final Label label = new Label();
    		   GridPane.setConstraints(label, 0, 4);
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
    					   label.setText(bdd.saveInBDD(img.getText(), title.getText(), recipe.getText(), ingredients.getText()));
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
            	 
               mainContainer.createMainContainer(stage, menuBar, vbtext, vbbutton);
    	    }
        });
        menuRecipe.getItems().add(menuItemRecipe);
        */
        
     // --- Menu Recipes
        Menu menuRecipe = new Menu("Recipes");
        MenuItem menuItemRecipe = new MenuItem("Recipes");
        menuItemRecipe.setOnAction(new EventHandler<ActionEvent>() 
        {
        	@Override
        	@FXML
            public void handle(ActionEvent event) 
    	    {
        		// On démarre un autre fils d'execution pour ne pas ralentir la mise à jour de l'interface graphique
        		new Thread(new Runnable(){
        			@Override
        			public void run()
        			{
        				table.setItems(bdd.getFromBDD());
        				
                		table.setEditable(true);
                		table.setMaxSize(600, 600);
                		
                		//assert table != null : "fx:id=\"tableview\" was not injected: check your FXML file 'UserMaster.fxml'.";

                		imgCol.setCellValueFactory(new PropertyValueFactory<Recipe,String>("img"));	
                        imgCol.setMinWidth(100);
                        titleCol.setCellValueFactory(new PropertyValueFactory<Recipe,String>("title"));   
                        titleCol.setMinWidth(75);
                        recipeCol.setCellValueFactory(new PropertyValueFactory<Recipe,String>("recipe"));   
                        recipeCol.setMinWidth(50);
                        ingCol.setCellValueFactory(new PropertyValueFactory<Recipe,String>("ing"));   
                        ingCol.setMinWidth(50);
                        scoreCol.setCellValueFactory(new PropertyValueFactory<Recipe,String>("score"));   
                        scoreCol.setMinWidth(25);
                        pollCol.setCellValueFactory(new PropertyValueFactory<Recipe,String>("poll"));   
                        pollCol.setMinWidth(25);
                        urlCol.setCellValueFactory(new PropertyValueFactory<Recipe,String>("url"));   
                        urlCol.setMinWidth(100);
                        
                       Platform.runLater(new Runnable() {
        				    @SuppressWarnings("unchecked")
							@Override public void run()
        				    {
                                table.getColumns().addAll(imgCol, titleCol, recipeCol, ingCol, scoreCol, pollCol, urlCol);
                                
                                final VBox vbox = new VBox();
                                vbox.setSpacing(5);
                                vbox.setPadding(new Insets(10, 0, 0, 10));
                                vbox.getChildren().add(table);
                            	 
                               mainContainer.createMainContainer(stage, menuBar, vbox);
        					} ;
                       });
        			} ;
        		}).start();
    	    }
        });
        menuRecipe.getItems().add(menuItemRecipe);
        
        // --- Menu Plugin
        Menu menuPlugin = new Menu("Plugin");
        MenuItem menuItemPlugin = new MenuItem("Plugin");
        menuItemPlugin.setOnAction(new EventHandler<ActionEvent>() 
        {
             @Override
             public void handle(ActionEvent event) 
             {
            	 final FileChooser fileChooser = new FileChooser();
                 final Button openButton = new Button("Choose a File...");
          
                 openButton.setOnAction(new EventHandler<ActionEvent>()
                     {
                         @Override
                         public void handle(final ActionEvent e) 
                         {
                             File file = fileChooser.showOpenDialog(stage);
                             if (file != null) 
                             {
                                 openFile(file);
                             }
                         }
                     }
                 );
                 
                 mainContainer.createMainContainer(stage, menuBar, openButton);
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
                 mainContainer.createMainContainer(stage, menuBar, vblabels);
             }
        });
        menuHelp.getItems().add(menuItemHelp);
    	
        menuBar.getMenus().addAll(menuHome, menuRecipe, menuPlugin, menuHelp);
        mainContainer.createMainContainer(stage, menuBar);
    };
    
    /**
     * 
     * @param e
     */
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

     /*
     void initialize()
     {
    	 table.setEditable(true);
    	 table.setMaxSize(600, 600);
         assert table != null : "fx:id=\"tableview\" was not injected: check your FXML file 'UserMaster.fxml'.";
         TableColumn imgCol = new TableColumn("Image's Path");
         imgCol.setMinWidth(100);
         TableColumn titleCol = new TableColumn("Title");
         titleCol.setMinWidth(75);
         TableColumn recipeCol = new TableColumn("Recipe");
         recipeCol.setMinWidth(50);
         TableColumn ingCol = new TableColumn("Ingredients");
         ingCol.setMinWidth(50);
         TableColumn scoreCol = new TableColumn("Score");
         scoreCol.setMinWidth(25);
         TableColumn pollCol = new TableColumn("Poll");
         pollCol.setMinWidth(25);
         TableColumn urlCol = new TableColumn("Recipe's url");
         urlCol.setMinWidth(100);
             
         
        
        try{
            con = bdd.getConnection();
            buildData();
        }
        catch(ClassNotFoundException ce){
            logger.info(ce.toString());
        }
        catch(SQLException ce){
            logger.info(ce.toString());
        }
    }
     

     private ObservableList<Recipe> data;

	public void buildData(){        
    data = FXCollections.observableArrayList();
    try{      
        String SQL = "Select * from usermaster Order By UserName";            
        ResultSet rs = con.createStatement().executeQuery(SQL);  
        while(rs.next()){
        	Recipe cm = new Recipe();
            cm.userId.set(rs.getInt("UserId"));                       
            Image img = new Image("tailoring/UserPhoto/User"+cm.getUserId().toString()+".jpg");                

            ImageView mv = new ImageView();
            mv.setImage(img);
            mv.setFitWidth(70);
            mv.setFitHeight(80);
            cm.userPhoto.set(mv);
            cm.userName.set(rs.getString("UserName"));
            cm.userPassword.set(rs.getString("UserPassword"));
            cm.userType.set(rs.getString("UserType"));
            data.add(cm);                  
        }
        tableview.setItems(data);
    }
    catch(Exception e){
          e.printStackTrace();
          System.out.println("Error on Building Data");            
    }
	}*/
     
  	/**
  	 * Open a file
  	 * @param file
  	 */
  	private void openFile(File file) 
  	{
        try 
        {
            desktop.open(file);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger
            (
                Main.class.getName()).log(
                    Level.SEVERE, null, ex
                );
        }
    };

  	
}; 