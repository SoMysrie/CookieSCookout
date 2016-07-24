package application ;

import java.awt.Desktop ;
import java.io.File ;
import java.sql.SQLException ;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import cookiescookout.ws.WsServer ;
import javafx.application.Application ;
import javafx.application.Platform ;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent ;
import javafx.event.EventHandler ;
import javafx.geometry.Insets ;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button ;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox ;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label ;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu ;
import javafx.scene.control.MenuBar ;
import javafx.scene.control.MenuItem ;
import javafx.scene.control.TableColumn ;
import javafx.scene.control.TableView ;
import javafx.scene.control.TextField ;
import javafx.scene.control.cell.PropertyValueFactory ;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox ;
import javafx.scene.layout.VBox ;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser ;
import javafx.stage.Stage ;
import link.Search;



public class Main extends Application 
{
	Search searcher = new Search();
	//controls needed for app:
    CheckBox chkmarmiton , chkcuisineaz , chkaufeminin , chkdbRecipe , chklocal ;
    Label label , lbltotal , lbllist , 
    	lblNameRecipe , lblIng ,
    	lblMenu , lblDB , lblPlugin  , lblHelp ;
    Button  btsearch , btclear , 
    	btaddImg , btadd , btdel , btedit , btsave ,
    	btsubmit ,
    	btopen ;
    TextField search , 
    	addImg , addTitle , addContent  , addIng , addScore  , addPoll , addUrl ,
    	addName ,
    	addQty , addNote ;
    Alert alert ;
    
    //VBoxes and hbaddoxes for the labels and checkboxes 
    VBox vbchecks , vblabels , vbbutton , vbtext , vball , vbRecipe , vbtable ;
    HBox hbadd , hball , hbbutton , hblist , hbRecipe ;
    
    //Desktop needed for searching a file
    private Desktop desktop = Desktop.getDesktop() ;
    FileChooser fileChooser , pictureChooser ;
    ImageView imageView ; 
    
    // URL
    Hyperlink link , link2 ;
    List<Hyperlink> links ;
    
    //TableView for dbRecipe
    private TableView<Recipe> tableRecipe ;
    private TableColumn<Recipe,String> imgCol 	  = new TableColumn<Recipe,String>( "IMAGE"  ) ;
    private TableColumn<Recipe,String> titleCol   = new TableColumn<Recipe,String>( "TITLE"  ) ;
    private TableColumn<Recipe,String> contentCol = new TableColumn<Recipe,String>( "CONTENT") ;
    private TableColumn<Recipe,String> scoreCol   = new TableColumn<Recipe,String>( "SCORE"  ) ;
    private TableColumn<Recipe,String> pollCol	  = new TableColumn<Recipe,String>( "POLL"   ) ;
    private TableColumn<Recipe,String> urlCol     = new TableColumn<Recipe,String>( "URL"    ) ;
    private ObservableList<Recipe> dataRecipe ;
    
    //TableView for dbIngredient
    private TableView<Ingredient> tableIngredient;
    private TableColumn<Ingredient, String> nameCol = new TableColumn<Ingredient, String>( "NAME" ) ;
    private ObservableList<Ingredient> dataIngredient ;
    
    //TableView for dbIngredient
    private TableView<Ingredient> tableIngredientComplet;
    private TableColumn<Ingredient, String> nameIngCol  = new TableColumn<Ingredient, String>( "NAME" 	  ) ;
    private TableColumn<Ingredient, String> qtyCol 		= new TableColumn<Ingredient, String>( "QUANTITY" ) ;
    private TableColumn<Ingredient, String> noteCol		= new TableColumn<Ingredient, String>( "NOTE" 	  ) ;
    private ObservableList<Ingredient> dataIngredientCompose ;
    
    //ComboBox
    ComboBox<Recipe> cbRecipe ;
    
    //ListView
    ListView<Ingredient> listIngredient ;
    ListView listView ;
    
    //Calling Methods
    MainContainer mainContainer = new MainContainer() ;
    DBRecipe dbRecipe = new DBRecipe() ;
    DBIng dbIngredient = new DBIng() ;
    
    /**
     * Main class
     * @param args
     * @throws ClassNotFoundException
     * @throws SQLException
     */
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
        		//Hyperlink
        		links = new ArrayList<>();
        		listView = new ListView();
 				
        		//vbox for checkboxes
        		vbchecks = new VBox() ;
                vbchecks.setSpacing( 10 ) ;
                vbchecks.setPadding (new Insets( 20 ) ) ;
                 
                //vbox for textfield
                vbtext = new VBox() ;
                vbtext.setSpacing(10) ;
                vbtext.setPadding( new Insets( 20 ) ) ;		
                
                //vbox for labels
                vblabels = new VBox() ;
                vblabels.setSpacing( 10 ) ;
                vblabels.setPadding( new Insets( 20 ) ) ;	
                 
                //vbox for buttons
                hbbutton = new HBox() ;
                hbbutton.setSpacing( 10 ) ;
     		    hbbutton.setPadding( new Insets( 20 ) ) ;
     		    
                //checkboxes
                chkmarmiton = new CheckBox( "Website Marmiton" ) ;
                chkcuisineaz = new CheckBox( "Website Cuisineaz" ) ;
                chkaufeminin = new CheckBox( "Website auFeminin" ) ;
                chkdbRecipe = new CheckBox( "Website Cookie's Cookout" ) ;
                chklocal = new CheckBox( "Local" ) ;
                
                //setSelected on true for all 5 checkboxes
                chkmarmiton.setSelected( true ) ;
                chkcuisineaz.setSelected (true ) ;
                chkaufeminin.setSelected( true ) ;
                chkdbRecipe.setSelected( true ) ;
                chklocal.setSelected( true ) ;
                 
                //make 2 labels
                lbltotal = new Label( "Websites chosen: 5" ) ;
                lbllist = new Label( "- Website Marmiton \n"
                		+ "- Website Cuisineaz \n"
                		+ "- Website auFeminin \n"
                 		+ "- Website Cookie's Cookout \n"
                		+ "- Local \n" ) ;
                 
                //textfield
                search = new TextField() ;
     		    search.setPromptText( "What do you want to search?" ) ;
     		    search.setMinWidth( 195 ) ;
     		    
     		    //buttons
     		    btsearch = new Button( "Search" ) ;
     		    btclear = new Button( "Clear" ) ;

                //add all things to vboxes
                vbchecks.getChildren().addAll( chkmarmiton , chkcuisineaz , chkaufeminin , chkdbRecipe , chklocal ) ;
                vblabels.getChildren().addAll( lbltotal , lbllist ) ;
                vbtext.getChildren().add( search ) ;
                hbbutton.getChildren().addAll( btsearch , btclear ) ;
                
                //attach click-method to all 5 checkboxes
                chkmarmiton.setOnAction( e -> handleButtonAction( e ) ) ;
                chkcuisineaz.setOnAction( e -> handleButtonAction( e ) ) ;
                chkaufeminin.setOnAction( e -> handleButtonAction( e ) ) ;
                chkdbRecipe.setOnAction( e -> handleButtonAction( e ) ) ;
                chklocal.setOnAction( e -> handleButtonAction( e ) ) ;
                
                //attach click-method to all 2 buttons
                btsearch.setOnAction( e -> handleButtonSearch( e ) ) ;
                btclear.setOnAction( e -> handleButtonClear( e ) ) ;
                
                hball = new HBox() ;
                hball.getChildren().addAll( vbchecks , vblabels , vbtext ) ;
                
                vball = new VBox() ;
                vball.getChildren().addAll( menuBar , hball , hbbutton , listView ) ;
                mainContainer.createMainContainer( stage , menuBar , vball ) ;
            } ;
        } ) ;
        menuHome.getItems().add( menuItemHome ) ;
        
        // --- Menu DB
        Menu menuDB = new Menu( "DataBase" ) ;
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
        				dataRecipe = dbRecipe.getFromDBRecipe() ;
            			
                		imgCol.setCellValueFactory( new PropertyValueFactory<Recipe,String>( "imgRecipe" ) ) ;
                        titleCol.setCellValueFactory( new PropertyValueFactory<Recipe,String>( "titleRecipe" ) ) ;
                        contentCol.setCellValueFactory( new PropertyValueFactory<Recipe,String>( "contentRecipe" ) ) ;
                        scoreCol.setCellValueFactory(new PropertyValueFactory<Recipe,String>( "scoreRecipe" ) ) ;
                        pollCol.setCellValueFactory(new PropertyValueFactory<Recipe,String>( "pollRecipe" ) ) ;
                        urlCol.setCellValueFactory(new PropertyValueFactory<Recipe,String>( "urlRecipe" ) ) ;
                        
                        tableRecipe = new TableView<Recipe>() ;
                        tableRecipe.setItems( dataRecipe ) ;
                		tableRecipe.setEditable( true ) ;
                		tableRecipe.setMaxSize( 700 , 600 ) ;
                        
                        Platform.runLater( new Runnable() 
                        {
        				    @SuppressWarnings( "unchecked" )
							@Override public void run()
        				    {
        				    	tableRecipe.getColumns().addAll( imgCol , titleCol , contentCol , scoreCol , pollCol , urlCol ) ;
                                
        				    	//IMG
        				    	addImg = new TextField() ;
        				    	addImg.setPromptText( "Click on the button below" ) ;
        				    	addImg.setMinWidth( 170 ) ;
        				    	fileChooser = new FileChooser() ;
        				    	imageView = new ImageView(); 
        		        		
        		            	 //Title
        		        		addTitle = new TextField() ;
        		        		addTitle.setPromptText( "Title" ) ;
        		        		addTitle.setMaxWidth( titleCol.getPrefWidth( ) ) ;
        		        		
        		        		//RECIPE
        		        		addContent = new TextField() ;
        		        		addContent.setPromptText( "Recipe" ) ;
        		        		addContent.setMaxWidth( contentCol.getPrefWidth( ) ) ;
        		        		
        		        		//SCORE
        		        		addScore = new TextField() ;
        		        		addScore.setPromptText( "Score");
        		        		addScore.setMaxWidth( scoreCol.getPrefWidth( ) ) ;
        		        		// force the field to be numeric only
       	        				addScore.textProperty().addListener(new ChangeListener<String>() 
       	        				{
       	        			        @Override
       	        			        public void changed(ObservableValue<? extends String> observable , String oldValue , String newValue ) 
       	        			        {
       	        			            if ( !newValue.matches( "\\d*") )
       	        			            {
       	        			            	addScore.setText( newValue.replaceAll( "[^\\d]" , "1234" ) ) ;
       	        			            }
       	        			        }
       	        			    } ) ;
       	        				
       	        				//POLL
        		        		addPoll = new TextField() ;
       	        				addPoll.setPromptText( "Poll");
       	        				addPoll.setMaxWidth( pollCol.getPrefWidth( ) ) ;
        		        		// force the field to be numeric only
        		        		addPoll.textProperty().addListener( new ChangeListener<String>() 
        		        		{
       	        			        @Override
       	        			        public void changed( ObservableValue<? extends String> observable , String oldValue , String newValue ) 
       	        			        {
       	        			            if ( !newValue.matches( "\\d*") )
       	        			            {
       	        			            	addPoll.setText(newValue.replaceAll( "[^\\d]" , "1234" ) ) ;
       	        			            }
       	        			        }
       	        			    } ) ;
        		        		
        		        		//URL
        		        		addUrl = new TextField() ;
        		        		addUrl.setPromptText( "URL" ) ;
        		        		addUrl.setMaxWidth( urlCol.getPrefWidth( ) ) ;

        		        		//label
        		        		label = new Label( "" ) ;
        		        		
        		        		//buttons
        		        		btaddImg = new Button( "Choose a Picture..." ) ;
        		        		btadd = new Button( "Add" ) ;
        		        		btdel = new Button( "Delete" ) ;
        		        		btedit = new Button( "Edit" ) ;
        		        		btsave = new Button( "Save" ) ;
        		        		
        		        		btsave.setDisable( true ) ;
        		        		
        		        		//Button action on click
        		        		btaddImg.setOnAction( e -> handleButtonAddImgdbRecipe( e , stage ) ) ;
        				        btadd.setOnAction( e -> handleButtonAddDBRecipe( e ) ) ;
        				        btdel.setOnAction( e -> handleButtonDeldbRecipe( e ) ) ;
        				        btedit.setOnAction( e -> handleButtonEditdbRecipe( e ) ) ;
        				        btsave.setOnAction( e -> handleButtonSavedbRecipe( e ) ) ;
        				        
        				        hbadd = new HBox() ;
        				        hbadd.getChildren().addAll( addImg , addTitle , addContent , addScore , addPoll , addUrl ) ;
                                hbadd.setSpacing( 3 ) ;
                                
                                hbbutton = new HBox() ;
                                hbbutton.setSpacing( 3 ) ;
                                hbbutton.getChildren().addAll( btaddImg , btadd , btdel , btedit , btsave ) ;
                                
                                vball = new VBox() ;
                                vball.setSpacing( 5 ) ;
                                vball.setPadding( new Insets( 10 , 0 , 0 , 10 ) ) ;
                                vball.getChildren().addAll( tableRecipe , hbadd , hbbutton , label ) ;
        		        		
                                mainContainer.createMainContainer( stage , menuBar , vball ) ;
        				    } ;
                       } ) ;
        			} ;
        		} ).start() ;
    	    } ;
        } ) ;
        
        // --- Menu Ingredients
        MenuItem menuItemIng = new MenuItem( "Ingredients" ) ;
        menuItemIng.setOnAction( new EventHandler<ActionEvent>()
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
        				dataIngredient = dbIngredient.getFromDBIngredient() ;
            			
        				nameCol.setCellValueFactory( new PropertyValueFactory<Ingredient,String>( "nameIngredient" ) ) ;
                        
                        tableIngredient = new TableView<Ingredient>() ;
                        tableIngredient.setItems( dataIngredient ) ;
                		tableIngredient.setEditable( true ) ;
                		tableIngredient.setMaxSize( 700 , 600 ) ;
                        
                        Platform.runLater( new Runnable() 
                        {
        				    @SuppressWarnings( "unchecked" )
							@Override public void run()
        				    {
        				    	tableIngredient.getColumns().addAll( nameCol ) ;
        		        		
        		        		//Name
        		        		addName = new TextField() ;
        		        		addName.setPromptText( "Name");
        		        		addName.setMinWidth( 200 ) ;

        		        		//label
        		        		label = new Label( "" ) ;
        		        		
        		        		//buttons
        		        		btadd = new Button( "Add" ) ;
        		        		btedit = new Button( "Edit" ) ;
        		        		btsave = new Button( "Save" ) ;
        		        		
        		        		btsave.setDisable( true ) ;
        		        		
        		        		//Button action on click
        				        btadd.setOnAction( e -> handleButtonAddDBIngredient( e ) ) ;
        				        btedit.setOnAction( e -> handleButtonEditDBIngredient( e ) ) ;
        				        btsave.setOnAction( e -> handleButtonSaveDBIngredient( e ) ) ;
        				        
        				        hbadd = new HBox() ;
        				        hbadd.getChildren().add( addName ) ;
                                hbadd.setSpacing( 3 ) ;
                                
                                hbbutton = new HBox() ;
                                hbbutton.setSpacing( 3 ) ;
                                hbbutton.getChildren().addAll( btadd , btedit , btsave ) ;
                                
                                vball = new VBox() ;
                                vball.setSpacing( 5 ) ;
                                vball.setPadding( new Insets( 10 , 0 , 0 , 10 ) ) ;
                                vball.getChildren().addAll( tableIngredient , hbadd , hbbutton , label ) ;
        		        		
                                mainContainer.createMainContainer( stage , menuBar , vball ) ;
        				    } ;
                       } ) ;
        			} ;
        		} ).start() ;
            }
        } ) ;
        menuDB.getItems().addAll( menuItemRecipe , menuItemIng ) ;
        
        // --- Menu Form
        MenuItem menuItemForm = new MenuItem( "Form" ) ;
        menuItemForm.setOnAction( new EventHandler<ActionEvent>() 
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
         				//Labels
         				lblNameRecipe = new Label( "Please, select the recipe you wish to add some ingrdients.\n\n" ) ;
                   	 	lblIng = new Label( "\nSelect the ingredient on the left you want to add on the list on the right.\n"
                    	 		+ "Or select the ingredient on the right you want to delete.\n\n" ) ;
		        		label = new Label( "" ) ;
		        		
                   	 	//Recipe
                   	 	dataRecipe = dbRecipe.getFromDBRecipe() ;
                   	 	cbRecipe = new ComboBox<Recipe>() ;
                   	 	cbRecipe.setMinWidth( 200 ) ;
                   	 
                   	 	for( int i = 0 ; i < dataRecipe.size() ; i++ )
                   	 	{
                   	 		cbRecipe.getItems().add( dataRecipe.get( i ) ) ;
                   	 	}
                   	 	
                   	 	btsubmit = new Button( "Submit" ) ;
                   	 	btsubmit.setMinWidth( 75 ) ;
                   	 	
                   	 	hbRecipe = new HBox() ;
                   	 	hbRecipe.setSpacing( 3 ) ;
                   	 	hbRecipe.getChildren().addAll( cbRecipe , btsubmit ) ;
                   	 	
                   	 	vbRecipe = new VBox() ;
                   	 	vbRecipe.setSpacing( 5 ) ;
                   	 	vbRecipe.setPadding( new Insets( 10 , 10 , 10 , 10 ) ) ;
                   	 	vbRecipe.getChildren().addAll( lblNameRecipe , hbRecipe , lblIng ) ;
                   	 
                   	 	//buttons
                   	 	btadd = new Button( "Add" ) ;
                   	 	btadd.setMinWidth( 75 ) ;
                   	 	btadd.setDisable( true ) ;
                   	 	btdel = new Button( "Delete" ) ;
                   	 	btdel.setMinWidth( 75 ) ;
                   	 	btdel.setDisable( true ) ;
                   	 	
                   	 	//table
                   	 	nameIngCol.setCellValueFactory( new PropertyValueFactory<Ingredient,String>( "nameIngredient" ) ) ;
                	 	qtyCol.setCellValueFactory( new PropertyValueFactory<Ingredient,String>( "qty" ) ) ;
                	 	noteCol.setCellValueFactory( new PropertyValueFactory<Ingredient,String>( "note" ) ) ;
                   	 	tableIngredientComplet = new TableView<Ingredient>() ;
                   	 	
                   	 	//list
                   	 	dataIngredient = dbIngredient.getFromDBIngredient() ;
                   	 	listIngredient = new ListView<Ingredient>();
                   	 
                   	 	for( int i = 0 ; i < dataIngredient.size() ; i++ )
                   	 	{
                   		 	//listIngredient.getItems().add( dataIngredient.get( i ).getNameIngredient() ) ;
                   	 		listIngredient.getItems().add(dataIngredient.get( i )) ;
                   	 	}
                   	 	
                   	 	listIngredient.setOnMouseClicked(new EventHandler<MouseEvent>() 
                   	 	{
                   	 		@Override
                   	 		public void handle(MouseEvent event) 
                   	 		{
                             	btadd.setDisable(false);
                             	btdel.setDisable(true);
                         	}
                   	 	} ) ;
                   	 	
                   	 	vbbutton = new VBox() ;
                   	 	vbbutton.setSpacing( 5 ) ;
                   	 	vbbutton.setPadding( new Insets( 175 , 10 , 10 , 10 ) ) ;
                   	 	vbbutton.getChildren().addAll( btadd , btdel ) ;
                   	 	
                   	 	listIngredient.setDisable( true ) ;
                	 	tableIngredientComplet.setDisable( true ) ;
                   	 	
                   	 	//Button action on click
                   	 	btadd.setOnAction( e -> handleButtonAddToRecipe( e ) ) ;
                   	 	btdel.setOnAction( e -> handleButtonDelFromRecipe( e ) ) ;
                   	 	btsubmit.setOnAction(e -> handleButtonSelectedRecipe( e ) ) ;
                   	 	
                   	 	tableIngredientComplet.setOnMouseClicked(new EventHandler<MouseEvent>() 
                   	 	{
                   	 		@Override
                   	 		public void handle(MouseEvent event) 
                   	 		{
                             	btdel.setDisable(false);
                             	btadd.setDisable(true);
                         	}
                   	 	} ) ;
                   	 	
                   	 	Platform.runLater( new Runnable() 
                   	 	{
                   	 		@SuppressWarnings( "unchecked" )
							@Override public void run()
                   	 		{
                   	 			tableIngredientComplet.getColumns().addAll( nameIngCol , qtyCol , noteCol ) ;
                   	 			
                   	 			//Qty
        		        		addQty = new TextField() ;
        		        		addQty.setPromptText( "Quantity");
        		        		addQty.setMaxWidth( 75 ) ;
        		        		
        		        		//Note
        		        		addNote = new TextField() ;
        		        		addNote.setPromptText( "Note");
        		        		addNote.setMaxWidth( 75 ) ;
        		        		
        		        		//Button
        		        		btedit = new Button( "Edit") ;
        		        		btsave = new Button( "Save" ) ;
        		        		btsave.setDisable( true ) ;
        		        		
        		        		btedit.setOnAction( e -> handleButtonEditDBIngredientComplet( e ) ) ;
        		        		btsave.setOnAction( e -> handleButtonSaveDBIngredientComplet( e ) ) ;
                   	 			
                   	 			hbadd = new HBox() ;
                   	 			hbadd.setSpacing( 3 ) ;
                   	 			hbadd.getChildren().addAll( addQty , addNote , btedit , btsave ) ;
                   	 			
                   	 			vbtable = new VBox() ;
                   	 			vbtable.setSpacing( 5 ) ;
                   	 			vbtable.setPadding( new Insets( 10 , 10 , 10 , 10 ) ) ;
                   	 			vbtable.getChildren().addAll( tableIngredientComplet , hbadd , label ) ;
                   	 			
                   	 			hbbutton = new HBox() ;
               	 				hbbutton.setSpacing( 3 ) ;
               	 				hbbutton.getChildren().addAll( vbbutton ) ;
                   	 			
               	 				hblist = new HBox() ;
                   	 			hblist.setSpacing( 3 ) ;
                   	 			hblist.getChildren().addAll( listIngredient , vbbutton , vbtable ) ;
                   	 			
                   	 			vball = new VBox();
                   	 			vball.setSpacing( 5 ) ;
                   	 			vball.setPadding( new Insets( 10 , 10 , 10 , 10 ) ) ;
                   	 			vball.getChildren().addAll( vbRecipe , hblist ) ;	
                       	 
                   	 			mainContainer.createMainContainer( stage , menuBar , vball ) ;
                   	 		}
                   	 	} ) ;
         			}
         		} ).start() ;
             } ;
        } ) ;
        menuDB.getItems().add( menuItemForm ) ;
        
        // --- Menu Plugin
        Menu menuPlugin = new Menu( "Plugin" ) ;
        MenuItem menuItemPlugin = new MenuItem( "Plugin" ) ;
        menuItemPlugin.setOnAction( new EventHandler<ActionEvent>() 
        {
             @Override
             public void handle( ActionEvent event ) 
             {
            	 fileChooser = new FileChooser() ; 
            	 
            	 label = new Label( "Please a file ending by .java!" ) ;
            	 btopen = new Button( "Choose a File..." ) ;
            	 
            	 vball = new VBox() ;
            	 vball.setSpacing( 10 ) ;
            	 vball.setPadding( new Insets( 20 ) ) ;
                 vball.getChildren().addAll( label , btopen ) ;
                 
                 btopen.setOnAction( e -> handleButtonOpenFile( e , stage ) ) ;
                 
                 mainContainer.createMainContainer( stage , menuBar , vball ) ;
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
                 lblDB = new Label( "DataBase \n"
                 		+ "		Recipes\n"
                 		+ "			You can add, delete, edit and a recipe \n"
                 		+ "			and any changes will be saved in the database.\n\n" 
                 		+ "		Ingredients\n"
                 		+ "			You can add, delete, edit and save an ingredient \n"
                 		+ "			and any changes will be saved in the database.\n\n" 
                 		+ "		Form\n"
                 		+ "			You can add, delete an ingredient into a recipe. \n "
                 		+ "			You also can edit and save changes for an ingredient' informations. \n"
                 		+ "			Any changes will be saved in the database.\n\n" ) ;
                 lblPlugin = new Label( "Plugin \n"
                 		+ "	You can add any new JavaFXApp.src.plugin.\n" ) ;
                 lblHelp = new Label( "Help \n"
                 		+ "	You can see this list of information.\n" ) ;
                 
                 vblabels.getChildren().addAll( lblMenu , lblDB , lblPlugin , lblHelp ) ;
                 mainContainer.createMainContainer( stage , menuBar , vblabels ) ;
             } ;
        } ) ;
        menuHelp.getItems().add( menuItemHelp ) ;
    	
        menuBar.getMenus().addAll( menuHome , menuDB , menuPlugin , menuHelp ) ;
        mainContainer.createMainContainer( stage , menuBar ) ;
    } ;
    
    /**
     * Button for the checkboxes in the Home Tab
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
        if( chkdbRecipe.isSelected( ) )
        {
            count++;
            choices += "- " + chkdbRecipe.getText() + "\n" ;
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
     * Button search for the Home Tab
     * @param e
     */
    private void handleButtonSearch( ActionEvent e )
    {
		//searcher.run(chkmarmiton.isSelected(),chkcuisineaz.isSelected(),chkaufeminin.isSelected(),chklocal.isSelected(),search.getText());
		// On démarre un autre fil d'execution pour ne pas ralentir la mise à jour de l'interface graphique
 		new Thread( new Runnable()
 		{
 			@Override
 			public void run()
 			{
 				Platform.runLater( new Runnable() 
           	 	{
					@Override 
					public void run()
           	 		{
						Alert alert = new Alert( AlertType.WARNING ) ;
						alert.setTitle( "Warning Dialog") ;
						alert.setContentText( "You have to inter some ingredient(s) !" ) ;
						
						if ( search.getText() != null && !search.getText().isEmpty() )
						{
							final WebView browser = new WebView();
							final WebEngine webEngine = browser.getEngine();
			 		        link = new Hyperlink("http://blog.professional-webworkx.de");
			 		        link2= new Hyperlink("http://www.stackoverflow.com");
			 		        
			 		        links.add(link);
			 		        links.add(link2);
			 		        
			 		        for(final Hyperlink hyperlink : links) 
			 		        {
			 		            hyperlink.setOnAction(new EventHandler<ActionEvent>() 
			 		            {
			 		            	@Override
			 		                public void handle(ActionEvent t) 
			 		            	{
			 		                    getHostServices().showDocument(hyperlink.getText());
			 		                }
			 		            });
			 		        }

			 		        listView.getItems().addAll(links);
						}
						else
				 		{
							Optional<ButtonType> result = alert.showAndWait() ;
						}
           	 		}
           	 	});
 			}
 		}).start() ;
    }
     
    /**
     * Button clear for the Home Tab
     * @param e
	 */
   private void handleButtonClear( ActionEvent e )
   {
   		chkmarmiton.setSelected( false ) ;
   		chkcuisineaz.setSelected( false ) ;
   		chkaufeminin.setSelected( false ) ;
   		chkdbRecipe.setSelected( false ) ;
   		chklocal.setSelected( false ) ;
   		lbltotal.setText( "Website chosen: 0" ) ;
   		lbllist.setText( "None" ) ;
   		search.clear( ) ;
   } ;
   
   /**
    * Button select picture
    * @param e
    * @param stage
    */
   private void handleButtonAddImgdbRecipe( ActionEvent e , Stage stage)
   {
	   alert = new Alert( AlertType.INFORMATION ) ;
	   alert.setTitle( "Information Dialog" ) ;
	   alert.setHeaderText( null ) ;
	   alert.setContentText( "Please, choose a picture ending either by \n.PNG \n.JPG"
	   		+ "\n\n Thank you! " ) ;
	   alert.showAndWait();
       
       //Set extension filter
       FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter( "JPG files (*.jpg)" , "*.JPG" ) ; 
       FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter( "PNG files (*.png)" , "*.PNG" ) ;
       fileChooser.getExtensionFilters().addAll( extFilterJPG , extFilterPNG ) ;
         
       //Show open file dialog
       File file = fileChooser.showOpenDialog( null ) ;
                  
       if ( file != null )
    	   addImg.setText( file.getAbsolutePath() ) ;
   } ;
   
    /**
     * Button add in the database for the Recipes Tab
     * @param e
     */
    private void handleButtonAddDBRecipe( ActionEvent e )
 	{
 		if ( ( addImg.getText() != null && !addImg.getText().isEmpty() ) 
			  && ( addTitle.getText() != null && !addTitle.getText().isEmpty() )  
			  && ( addContent.getText() != null && !addContent.getText().isEmpty() ) 
			  && ( addScore.getText() != null && !addScore.getText().isEmpty() )
			  && ( addPoll.getText() != null && !addPoll.getText().isEmpty() )
			  && ( addUrl.getText() != null && !addUrl.getText().isEmpty() )
 		   )
 		{
 			dataRecipe.add( new Recipe
 				(
 					null, 
 					addImg.getText() ,
 	 				addTitle.getText() ,
 	 				addContent.getText() ,
 	 				addScore.getText() ,
 	 				addPoll.getText() ,
 	 				addUrl.getText()
 	 			) 
 			) ;
 			
 			label.setText( dbRecipe.saveInDBRecipe
 				(
 					addImg.getText() , 
 					addTitle.getText() , 
 					addContent.getText() ,
 					Integer.parseInt(addScore.getText() ) , 
 					Integer.parseInt(addPoll.getText() ) , 
 					addUrl.getText() 
 				) 
     		) ;
 			
 			dataRecipe = dbRecipe.getFromDBRecipe() ;
			tableRecipe.setItems( dataRecipe ) ;
			
			addImg.clear() ;
			addImg.clear( ) ;
			addTitle.clear() ;
			addContent.clear() ;
			addScore.clear() ;
			addPoll.clear() ;
			addUrl.clear() ;
 	    } 
 		else
 		{
 			label.setText("You have to complete everything!");
		}
	} ;
     
	/**
	 * Button delete from the database for the Recipes Tab
	 * @param e
	 */
    private void handleButtonDeldbRecipe( ActionEvent e )
	{
		Recipe recipe2 = tableRecipe.getSelectionModel().getSelectedItem() ;
		
		Alert alert = new Alert( AlertType.CONFIRMATION ) ;
		alert.setTitle( "Confirmation Dialog") ;
		alert.setHeaderText( "Are you sure with this?" ) ;
		alert.setContentText( "If you delete this recipe, there are no turning back." ) ;

		if ( recipe2 != null )
		{
			Optional<ButtonType> result = alert.showAndWait() ;
			if ( result.get() == ButtonType.OK )
			{
				label.setText( dbRecipe.deleteInDBRecipe( Integer.parseInt( recipe2.getIdRecipe() ) , recipe2.getTitleRecipe() ) ) ; 
				dataRecipe = dbRecipe.getFromDBRecipe() ;
				tableRecipe.setItems( dataRecipe ) ;
			} 
			else 
			{
				label.setText( "Nothing was deleted!" ) ;
			}
		}
		else
		{
			label.setText( "You have to select a row in order to delete it!" ) ;
		}
	} ;
    
	/**
	 * Button edit from the database for the Recipes Tab
	 * @param e
	 */
	private void handleButtonEditdbRecipe( ActionEvent e )
	{
		Recipe recipe2 = tableRecipe.getSelectionModel().getSelectedItem() ;
		
		if ( recipe2 != null )
		{
			addImg.setText( recipe2.getImgRecipe() ) ;
			addTitle.setText( recipe2.getTitleRecipe() ) ;
			addContent.setText( recipe2.getContentRecipe() ) ;
			addScore.setText( recipe2.getScoreRecipe() ) ;
			addPoll.setText( recipe2.getPollRecipe() ) ;
			addUrl.setText( recipe2.getUrlRecipe() ) ;
			label.setText( "Once you are done with your editing click on the button save!" ) ;
			
			btsave.setDisable( false ) ;
			btadd.setDisable( true ) ;
			btdel.setDisable( true ) ;
			btedit.setDisable( true ) ;
		}
		else
		{
			label.setText( "You have to select a row in order to edit it!" ) ;
		}
	} ;

	/**
	 * Button save in the database for the Recipes Tab
	 * @param e
	 */
	private void handleButtonSavedbRecipe( ActionEvent e )
	{
		Recipe recipe2 = tableRecipe.getSelectionModel().getSelectedItem() ;
		
		if ( ( addImg.getText() != null && !addImg.getText().isEmpty() ) 
				  && ( addTitle.getText() != null && !addTitle.getText().isEmpty() )  
				  && ( addContent.getText() != null && !addContent.getText().isEmpty() ) 
				  && ( addScore.getText() != null && !addScore.getText().isEmpty() )
				  && ( addPoll.getText() != null && !addPoll.getText().isEmpty() )
				  && ( addUrl.getText() != null && !addUrl.getText().isEmpty() )
	 		   )
	 		{
	 			dataRecipe.add( new Recipe(
	 					null, 
	 	 				addImg.getText() ,
	 	 				addTitle.getText() ,
	 	 				addContent.getText() ,
	 	 				addScore.getText() ,
	 	 				addPoll.getText() ,
	 	 				addUrl.getText()
	 	 				) ) ;
	 			
	 			label.setText( dbRecipe.updateInDBRecipe(
	 					Integer.parseInt( recipe2.getIdRecipe() ) ,
	 					addImg.getText() , 
	 					addTitle.getText() , 
	 					addContent.getText() ,
	 					Integer.parseInt(addScore.getText() ) , 
	 					Integer.parseInt(addPoll.getText() ) , 
	 					addUrl.getText() 
	 					) 
	     			) ;
	 			
	 			dataRecipe = dbRecipe.getFromDBRecipe() ;
				tableRecipe.setItems( dataRecipe ) ;
				
				addImg.clear() ;
				addImg.clear() ;
				addTitle.clear() ;
				addContent.clear() ;
				addScore.clear() ;
				addPoll.clear() ;
				addUrl.clear() ;
				
				btsave.setDisable( true ) ;
				btadd.setDisable( false ) ;
				btdel.setDisable( false ) ;
				btedit.setDisable( false ) ;
	 	    } 
	 		else
	 		{
	 			label.setText("You have to complete everything!");
			}
	} ;
	
	/**
	 * Button add ingredient
	 * @param e
	 */
	private void handleButtonAddDBIngredient( ActionEvent e )
	{
		if ( addName.getText() != null && !addName.getText().isEmpty() )
		{
			dataIngredient.add( new Ingredient( null , addName.getText() ) ) ;
			
			label.setText( dbIngredient.saveInDBIngredient( addName.getText() ) ) ;
			
			dataIngredient = dbIngredient.getFromDBIngredient() ;
			tableIngredient.setItems( dataIngredient ) ;
						
			addName.clear() ;
		}
		else
		{
			label.setText("You have to add a name!");
		}
	} ;
	
	/**
	 * Button edit from the database for the Ingredient Tab
	 * @param e
	 */
    private void handleButtonEditDBIngredient( ActionEvent e )
    {
    	Ingredient ingredient2 = tableIngredient.getSelectionModel().getSelectedItem() ;
		
		if ( ingredient2 != null )
		{
			addName.setText( ingredient2.getNameIngredient() ) ;
			label.setText( "Once you are done with your editing click on the button save!" ) ;
			
			btsave.setDisable( false ) ;
			btadd.setDisable( true ) ;
			btedit.setDisable( true ) ;
		}
		else
		{
			label.setText( "You have to select a row in order to edit it!" ) ;
		}
    } ;
    
    /**
     * Save in the DB Ingredient and Compose
     * @param e
     */
    private void handleButtonSaveDBIngredient( ActionEvent e )
    {
    	Ingredient ingredient2 = tableIngredient.getSelectionModel().getSelectedItem() ;
		
		if ( addName.getText() != null && !addName.getText().isEmpty() )
	 	{
			dataIngredient.add( new Ingredient(null , addName.getText() ) ) ;
	 		
			label.setText( dbIngredient.updateInDIngredient(
	 				Integer.parseInt( ingredient2.getIdIngredient() ) ,
	 				addName.getText()
	 		) ) ;
	 		
			dataIngredient = dbIngredient.getFromDBIngredient() ;
			tableIngredient.setItems( dataIngredient ) ;
				
			addName.clear() ;
			btsave.setDisable( true ) ;
			btadd.setDisable( false ) ;
			btedit.setDisable( false ) ;
	 	} 
		else
	 	{
			label.setText("You cannot let the name null!");
		}
    } ;
	
    /**
     * Button add ingredient into recipe
     * @param e
     */
    private void handleButtonAddToRecipe( ActionEvent e )
    {
    	Alert alert = new Alert( AlertType.ERROR ) ;
		alert.setTitle( "Error Dialog") ;
		alert.setContentText( "Nothing is selected!" ) ;
		
		if( listIngredient.getSelectionModel().getSelectedItem() != null )
		{
			dbIngredient.saveInComposeDB(Integer.valueOf( cbRecipe.getSelectionModel().getSelectedItem().getIdRecipe() ) ,
					Integer.valueOf( listIngredient.getSelectionModel().getSelectedItem().getIdIngredient() ) ) ;
			dataIngredientCompose = dbIngredient.getFromDBIngredientAndCompose(Integer.valueOf( cbRecipe.getSelectionModel().getSelectedItem().getIdRecipe() ) ) ;
			tableIngredientComplet.setItems( dataIngredientCompose ) ;
		}
		else
		{
			Optional<ButtonType> result = alert.showAndWait() ;
		}
    }
    
    /**
     * Button delete ingredient in recipe
     * @param e
     */
    private void handleButtonDelFromRecipe( ActionEvent e )
    {
    	Alert alert = new Alert( AlertType.ERROR ) ;
		alert.setTitle( "Error Dialog") ;
		alert.setContentText( "Nothing is selected!" ) ;
		
		if( tableIngredientComplet.getSelectionModel().getSelectedItem() != null)
		{
			label.setText( dbIngredient.deleteFromDBIngredientAndCompose(
					Integer.valueOf( cbRecipe.getSelectionModel().getSelectedItem().getIdRecipe() ) ,
					Integer.valueOf( tableIngredientComplet.getSelectionModel().getSelectedItem().getIdIngredient() ) ) ) ;
			dataIngredientCompose = dbIngredient.getFromDBIngredientAndCompose( Integer.valueOf( cbRecipe.getSelectionModel().getSelectedItem().getIdRecipe() ) ) ;
			tableIngredientComplet.setItems( dataIngredientCompose ) ;
		}
		else
		{
			Optional<ButtonType> result = alert.showAndWait() ;
		}
    }
    
    /**
	 * Button edit from the database for the Ingredient Tab
	 * @param e
	 */
    private void handleButtonEditDBIngredientComplet( ActionEvent e )
    {
    	Ingredient ingredient2 = tableIngredientComplet.getSelectionModel().getSelectedItem() ;
		
		if ( ingredient2 != null )
		{
			addQty.setText( ingredient2.getQty() ) ;
			addNote.setText( ingredient2.getNote() ) ;
			label.setText( "Once you are done with your editing click on the button save!" ) ;
			
			btsave.setDisable( false ) ;
			btedit.setDisable( true ) ;
		}
		else
		{
			label.setText( "You have to select a row in order to edit it!" ) ;
		}
    } ;
    
    /**
     * Save in the DB Ingredient and Compose
     * @param e
     */
    private void handleButtonSaveDBIngredientComplet( ActionEvent e )
    {
    	Ingredient ingredient2 = tableIngredientComplet.getSelectionModel().getSelectedItem() ;
    	int idRecipe = Integer.valueOf( cbRecipe.getSelectionModel().getSelectedItem().getIdRecipe() ) ;
		
		label.setText( dbIngredient.updateInDIngredientComplet(
	 				idRecipe ,
	 				Integer.parseInt( ingredient2.getIdIngredient() ) ,
	 				addQty.getText() ,
	 				addNote.getText()
	 		) ) ;
	 		
		dataIngredientCompose = dbIngredient.getFromDBIngredientAndCompose( idRecipe ) ;
		tableIngredientComplet.setItems( dataIngredientCompose ) ;
				
		addQty.clear() ;
		addNote.clear() ;
		btsave.setDisable( true ) ;
		btedit.setDisable( false ) ;
    } ;
    
    /**
     * Button submit a recipe to the tableView
     * @param e
     */
    private void handleButtonSelectedRecipe( ActionEvent e )
    {
    	Alert alert = new Alert( AlertType.ERROR ) ;
		alert.setTitle( "Error Dialog") ;
		alert.setContentText( "Nothing is selected!" ) ;
		
    	if (cbRecipe.getSelectionModel().getSelectedItem() != null)
    	{
    		dataIngredientCompose = dbIngredient.getFromDBIngredientAndCompose( Integer.valueOf( cbRecipe.getSelectionModel().getSelectedItem().getIdRecipe() ) ) ;
    		tableIngredientComplet.setItems( dataIngredientCompose ) ;
       	 	tableIngredientComplet.setEditable( true ) ;
       	 	tableIngredientComplet.setMaxSize( 700 , 400 ) ;
       	 	
       	 	listIngredient.setDisable( false ) ;
       	 	tableIngredientComplet.setDisable( false ) ;
    	}
    	else
    	{
    		Optional<ButtonType> result = alert.showAndWait() ;
    	}
    	
    }
    
    /**
     * Button open file for the Plugin Tab
     * @param e
     * @param stage
     */
    private void handleButtonOpenFile( ActionEvent e , Stage stage)
	{
		 File file = fileChooser.showOpenDialog( stage ) ;
		 if ( file != null )
		 {
				searcher.plugins.add(file.getName());
		 }
    } ;
    
} ; 