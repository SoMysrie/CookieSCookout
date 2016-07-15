package application ;

import java.awt.Desktop ;
import java.io.File ;
import java.io.IOException ;
import java.sql.SQLException ;
import java.util.logging.Level ;
import java.util.logging.Logger ;

import cookiescookout.ws.WsServer ;
import javafx.application.Application ;
import javafx.application.Platform ;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent ;
import javafx.event.EventHandler ;
import javafx.geometry.Insets ;
import javafx.scene.control.Button ;
import javafx.scene.control.CheckBox ;
import javafx.scene.control.Label ;
import javafx.scene.control.Menu ;
import javafx.scene.control.MenuBar ;
import javafx.scene.control.MenuItem ;
import javafx.scene.control.TableColumn ;
import javafx.scene.control.TableView ;
import javafx.scene.control.TextField ;
import javafx.scene.control.cell.PropertyValueFactory ;
import javafx.scene.layout.HBox ;
import javafx.scene.layout.VBox ;
import javafx.stage.FileChooser ;
import javafx.stage.Stage ;

public class Main extends Application 
{
	//controls needed for app:
    CheckBox chkmarmiton , chkcuisineaz , chkaufeminin , chkbdd , chklocal ;
    Label lbltotal , lbllist , lblMenu , lblFile , lblPlugin , lblHelp ;
    Button btsubmit , btclear , btsearch ;
    
    //VBoxes for the labels and checkboxes 
    VBox vbchecks , vblabels , vbbutton , vbtext , vball, vbbox ;
    
    //HBoxes for group of labels and buttons
    HBox hb ;
    
    //Desktop needed for searching a file
    private Desktop desktop = Desktop.getDesktop() ;
    
    //TableView for Bdd
    private TableView<Recipe> table = new TableView<Recipe>() ;
    private TableColumn<Recipe, String> imgCol = new TableColumn<Recipe, String>( "Image" ) ;
    private TableColumn<Recipe, String> titleCol = new TableColumn<Recipe, String>( "Title" ) ;
    private TableColumn<Recipe, String> recipeCol = new TableColumn<Recipe, String>( "Recipe" ) ;
    private TableColumn<Recipe, String> ingCol = new TableColumn<Recipe, String>( "Ingredients" ) ;
    private TableColumn<Recipe, String> scoreCol = new TableColumn<Recipe, String>( "Score" ) ;
    private TableColumn<Recipe, String> pollCol = new TableColumn<Recipe, String>( "Poll" ) ;
    private TableColumn<Recipe, String> urlCol = new TableColumn<Recipe, String>( "URL" ) ;
    private ObservableList<Recipe> data ;
    
    //Calling Methods
    MainContainer mainContainer = new MainContainer() ;
    Bdd bdd = new Bdd() ;
    
    public static void main( String[] args ) throws ClassNotFoundException, SQLException
    {
    	System.out.println( "CookieSCookout app..." ) ;
 		WsServer ws = new WsServer() ;
 		ws.start() ;
 		
        launch( args ) ;
    } ;
 
    @Override
    public void start( Stage stage ) 
    {
    	MenuBar menuBar = new MenuBar() ;
    	
    	// --- Menu Home
        Menu menuHome = new Menu( "Home" ) ;
        MenuItem menuItemHome = new MenuItem( "Home" ) ;
        menuItemHome.setOnAction( new EventHandler<ActionEvent>() 
        {
        	@Override
            public void handle( ActionEvent event ) 
        	{
        		//vbox for all
        		vball = new VBox() ;
        		
        		//vbox for checkboxes     
                vbchecks = new VBox() ;
                vbchecks.setSpacing(10) ;
                vbchecks.setPadding(new Insets(20)) ;
                 
                //vbox for textfield
                vbtext = new VBox() ;
                vbtext.setSpacing(10) ;
                vbtext.setPadding(new Insets(20)) ;		
                
                //vbox for labels
                vblabels = new VBox() ;
                vblabels.setSpacing(10) ;
                vblabels.setPadding(new Insets(20)) ;
                 
                //vbox for buttons
                vbbutton = new VBox() ;
                vbbutton.setSpacing(10) ;
                vbbutton.setPadding(new Insets(20)) ;
                 
                //make 5 checkboxes
                chkmarmiton = new CheckBox( "Website Marmiton" );
                chkcuisineaz = new CheckBox( "Website Cuisineaz" ) ;
                chkaufeminin = new CheckBox( "Website auFeminin" ) ;
                chkbdd = new CheckBox( "Website Cookie's Cookout" ) ;
                chklocal = new CheckBox( "Local" ) ;
     		   
                //setSelected on true for all 5 checkboxes
                chkmarmiton.setSelected( true ) ;
                chkcuisineaz.setSelected (true ) ;
                chkaufeminin.setSelected( true ) ;
                chkbdd.setSelected( true ) ;
                chklocal.setSelected( true ) ;
                 
                //make 2 labels
                lbltotal = new Label( "Websites chosen: 5" ) ;
                lbllist = new Label( "- Website Marmiton \n"
                		+ "- Website Cuisineaz \n"
                		+ "- Website auFeminin \n"
                 		+ "- Website Cookie's Cookout \n"
                		+ "- Local \n" ) ;
                 
                //textfield
                final TextField search = new TextField() ;
     		    search.setPromptText( "What do you want to search?" ) ;
     		    search.setMaxWidth( 195 ) ;
                
                //Defining the Submit button
                btsearch = new Button( "Search" ) ;
            	//Defining the Clear button
                btclear = new Button( "Clear" ) ;

                //add all things to vboxes
                vbchecks.getChildren().addAll( chkmarmiton , chkcuisineaz , chkaufeminin , chkbdd , chklocal ) ;
                vblabels.getChildren().addAll( lbltotal , lbllist ) ;
                vbtext.getChildren().add( search ) ;
                vbbutton.getChildren().addAll( btsearch , btclear ) ;
                 
                //attach click-method to all 5 checkboxes
                chkmarmiton.setOnAction( e -> handleButtonAction( e ) ) ;
                chkcuisineaz.setOnAction( e -> handleButtonAction( e ) ) ;
                chkaufeminin.setOnAction( e -> handleButtonAction( e ) ) ;
                chkbdd.setOnAction( e -> handleButtonAction( e ) ) ;
                chklocal.setOnAction( e -> handleButtonAction( e ) ) ;
                
                //attach click-method to all 2 buttons
                btsearch.setOnAction( new EventHandler<ActionEvent>() 
                {
                	@Override
            	    public void handle( ActionEvent e )
                	{
            	         //TO DO
            	     } ;
            	} ) ;
                btclear.setOnAction( new EventHandler<ActionEvent>() 
                {
                	@Override
            	    public void handle( ActionEvent e ) 
            		{
                		chkmarmiton.setSelected( false ) ;
                        chkcuisineaz.setSelected( false ) ;
                        chkaufeminin.setSelected( false ) ;
                        chkbdd.setSelected( false ) ;
                        chklocal.setSelected( false ) ;
                        lbltotal.setText( "Website chosen: 0" ) ;
                        lbllist.setText( "None" ) ;
                        search.clear( ) ;
            	    } ;
            	} ) ;
                
                vball.getChildren().addAll( menuBar , vbchecks , vblabels , vbtext , vbbutton ) ;
                mainContainer.createMainContainer( stage , menuBar , vball ) ;
            } ;
        } ) ;
        menuHome.getItems().add( menuItemHome ) ;
        
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
        Menu menuRecipe = new Menu( "Recipes" ) ;
        MenuItem menuItemRecipe = new MenuItem( "Recipes" ) ;
        menuItemRecipe.setOnAction( new EventHandler<ActionEvent>() 
        {
        	@Override
            public void handle( ActionEvent event ) 
    	    {
        		// On démarre un autre fil d'execution pour ne pas ralentir la mise à jour de l'interface graphique
        		new Thread( new Runnable()
        		{
        			@Override
        			public void run()
        			{
        				data = bdd.getFromBDD() ;
        				table.setItems( data ) ;
        				
                		table.setEditable( true ) ;
                		table.setMaxSize( 700 , 600 ) ;
                		
                		imgCol.setCellValueFactory( new PropertyValueFactory<Recipe,String>( "img" ) ) ;
                        titleCol.setCellValueFactory( new PropertyValueFactory<Recipe,String>( "title" ) ) ;
                        recipeCol.setCellValueFactory( new PropertyValueFactory<Recipe,String>( "recipe" ) ) ;
                        ingCol.setCellValueFactory(new PropertyValueFactory<Recipe,String>( "ing" ) ) ;
                        scoreCol.setCellValueFactory(new PropertyValueFactory<Recipe,String>( "score" ) ) ;
                        pollCol.setCellValueFactory(new PropertyValueFactory<Recipe,String>( "poll" ) ) ;
                        urlCol.setCellValueFactory(new PropertyValueFactory<Recipe,String>( "url" ) ) ;
                        
                        Platform.runLater( new Runnable() 
                        {
        				    @SuppressWarnings( "unchecked" )
							@Override public void run()
        				    {
        				    	table.getColumns().addAll( imgCol , titleCol , recipeCol , ingCol , scoreCol , pollCol , urlCol ) ;
                                
        				    	final TextField addImg = new TextField() ;
        		        		addImg.setPromptText( "Image" ) ;
        		        		addImg.setMaxWidth( imgCol.getPrefWidth( ) ) ;
        		     			final TextField addTitle = new TextField() ;
        		        		addTitle.setMaxWidth( titleCol.getPrefWidth( ) ) ;
        		        		addTitle.setPromptText( "Title" ) ;
        		        		final TextField addRecipe = new TextField() ;
        		        		addRecipe.setMaxWidth( recipeCol.getPrefWidth( ) ) ;
        		  				addRecipe.setPromptText( "Recipe" ) ;
        		  				final TextField addIng = new TextField() ;
        		        		addIng.setPromptText( "Ingredient" ) ;
        		        		addIng.setMaxWidth( ingCol.getPrefWidth( ) ) ;
        		  				final TextField addScore = new TextField() ;
        	      				addScore.setMaxWidth( scoreCol.getPrefWidth( ) ) ;
       	        				addScore.setPromptText( "Score");
        		        		final TextField addPoll = new TextField() ;
        		        		addPoll.setMaxWidth( pollCol.getPrefWidth( ) ) ;
        		        		addPoll.setPromptText( "Poll" ) ;
        		        		final TextField addUrl = new TextField() ;
        		        		addUrl.setMaxWidth( urlCol.getPrefWidth( ) ) ;
        		        		addUrl.setPromptText( "Url" ) ;
        				        final Button addButton = new Button( "Add" ) ;
        				        final Label label = new Label() ;
        				        
        				        addButton.setOnAction( new EventHandler<ActionEvent>()
        				        {
        				        	@Override
        				        	public void handle( ActionEvent e )
        				        	{
        				        		data.add( new Recipe(
        				        				addImg.getText() ,
        				        				addTitle.getText() ,
        				        				addRecipe.getText() ,
        				        				addIng.getText() ,
        				        				addScore.getText() ,
        				        				addPoll.getText() ,
        				        				addUrl.getText()
        				        				) ) ;
        				        		table.setItems( data ) ;
        				        		
        				        		if ( ( addImg.getText() != null && !addImg.getText().isEmpty() ) 
        			    				  && ( addTitle.getText() != null && !addTitle.getText().isEmpty() )  
        			    				  && ( addRecipe.getText() != null && !addRecipe.getText().isEmpty() ) 
        			    				  && ( addIng.getText() != null && !addIng.getText().isEmpty() )
        			    				  && ( addScore.getText() != null && !addScore.getText().isEmpty() )
        			    				  && ( addPoll.getText() != null && !addPoll.getText().isEmpty() )
        			    				  && ( addUrl.getText() != null && !addUrl.getText().isEmpty() )
        				        		   )
        				        		{
        				        			label.setText( bdd.saveInBDD( 
        				        					addImg.getText() , 
        				        					addTitle.getText() , 
        				        					addRecipe.getText() , 
        				        					addIng.getText() , 
        				        					Integer.parseInt(addScore.getText() ) , 
        				        					Integer.parseInt(addPoll.getText() ) , 
        				        					addUrl.getText() 
        				        					) 
            				        			) ;
        				        			
        				        				addImg.clear() ;
            		        					addTitle.clear() ;
            		        					addRecipe.clear() ;
            		        					addIng.clear() ;
            		        					addScore.clear() ;
            		        					addPoll.clear() ;
            		        					addUrl.clear() ;
        			            	       } 
        			    				   else
        			    				   {
        			    					   label.setText("You have to complete everything!");
        			    				   }
        				        		
        				        		
        		        			}
        				        } ) ;
        				        
        				        hb = new HBox() ;
        				        hb.getChildren().addAll( addImg , addTitle , addRecipe , addIng , addScore , addPoll , addUrl , addButton ) ;
                                hb.setSpacing( 3 ) ;
                                vbbox = new VBox() ;
                                vbbox.setSpacing( 5 ) ;
                                vbbox.setPadding( new Insets( 10 , 0 , 0 , 10 ) ) ;
                                vbbox.getChildren().addAll( table , hb , label ) ;
        		        		
                                mainContainer.createMainContainer( stage , menuBar , vbbox ) ;
        				    } ;
                       } ) ;
        			} ;
        		} ).start() ;
    	    } ;
        } ) ;
        menuRecipe.getItems().add( menuItemRecipe ) ;
        
        // --- Menu Plugin
        Menu menuPlugin = new Menu( "Plugin" ) ;
        MenuItem menuItemPlugin = new MenuItem( "Plugin" ) ;
        menuItemPlugin.setOnAction( new EventHandler<ActionEvent>() 
        {
             @Override
             public void handle( ActionEvent event ) 
             {
            	 final FileChooser fileChooser = new FileChooser() ;
                 final Button openButton = new Button( "Choose a File..." ) ;
          
                 openButton.setOnAction(new EventHandler<ActionEvent>()
                 {
                	 @Override
                     public void handle( final ActionEvent e )
                	 {
                		 File file = fileChooser.showOpenDialog( stage ) ;
                		 if ( file != null )
                		 {
                			 openFile( file ) ;
                         }
                     } ;
                 } ) ;
                 
                 mainContainer.createMainContainer( stage , menuBar , openButton ) ;
             } ;
        } ) ;
        menuPlugin.getItems().add( menuItemPlugin ) ;
        
        // --- Menu Help
        Menu menuHelp = new Menu( "Help" ) ;
        MenuItem menuItemHelp = new MenuItem( "Help" ) ;
        menuItemHelp.setOnAction( new EventHandler<ActionEvent>()
        {       
             @Override
             public void handle( ActionEvent event )
             {
                 //vbox for labels
                 vblabels = new VBox() ;
                 vblabels.setSpacing( 10 ) ;
                 vblabels.setPadding( new Insets( 20 ) ) ;
                 
                 //make 2 labels
                 lblMenu = new Label( "Menu \n"
                 		+ "	You can search through our different support to find any recipe. \n"
                 		+ "		Websites: Marmiton, Cuisineaz, auFeminin \n"
                 		+ "		Even on our website Cookie's Cookout \n"
                 		+ "		Or locally. \n" ) ;
                 lblFile = new Label( "File \n"
                 		+ "	You can add any new recipe with these following information: \n"
                 		+ "		- Title\n"
                 		+ "		- Picture\n"
                 		+ "		- Ingredients\n"
                 		+ "		- Steps\n" ) ;
                 lblPlugin = new Label( "Plugin \n"
                 		+ "	You can add any new plugin.\n" ) ;
                 lblHelp = new Label( "Help \n"
                 		+ "	You can see this list of information.\n" ) ;
                 
                 vblabels.getChildren().addAll( lblMenu , lblFile , lblPlugin , lblHelp ) ;
                 mainContainer.createMainContainer( stage , menuBar , vblabels ) ;
             } ;
        } ) ;
        menuHelp.getItems().add( menuItemHelp ) ;
    	
        menuBar.getMenus().addAll( menuHome , menuRecipe , menuPlugin , menuHelp ) ;
        mainContainer.createMainContainer( stage , menuBar ) ;
    } ;
    
    /**
     * 
     * @param e
     */
    private void handleButtonAction( ActionEvent e ) 
    {
        int count = 0 ;
        String choices = "" ;
        if( chkmarmiton.isSelected() )
        {
            count++ ;
            choices += "- " + chkmarmiton.getText() + "\n" ;
        }
        if( chkcuisineaz.isSelected( ) )
        {
            count++;
            choices += "- " + chkcuisineaz.getText() + "\n" ;
        }
        if( chkaufeminin.isSelected( ) )
        {
            count++;
            choices += "- " + chkaufeminin.getText() + "\n" ;
        }
        if( chkbdd.isSelected( ) )
        {
            count++;
            choices += "- " + chkbdd.getText() + "\n" ;
        }
        if( chklocal.isSelected( ) )
        {
            count++;
            choices += "- " + chklocal.getText() + "\n" ;
        }
        if (count == 0 )
        {
        	lbltotal.setText( "Website chosen: 0" ) ;
            lbllist.setText( "None" ) ;
        }
        lbltotal.setText( "Websites chosen: " + count ) ;
        lbllist.setText( choices ) ;
     } ;
     
  	/**
  	 * Open a file
  	 * @param file
  	 */
  	private void openFile( File file ) 
  	{
        try 
        {
            desktop.open( file ) ;
        } 
        catch (IOException ex) 
        {
            Logger.getLogger
            (
            	Main.class.getName() ).log(
            		Level.SEVERE, null, ex
                ) ;
        }
    };

}; 