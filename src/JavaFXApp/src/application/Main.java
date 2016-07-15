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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
    CheckBox chkmarmiton = new CheckBox( "Website Marmiton" ) , chkcuisineaz = new CheckBox( "Website Cuisineaz" ) , 
    		chkaufeminin = new CheckBox( "Website auFeminin" ) , chkbdd = new CheckBox( "Website Cookie's Cookout" ) , 
    		chklocal = new CheckBox( "Local" ) ;
    Label label = new Label(), lbltotal = new Label() , 
    		lbllist = new Label() , lblMenu = new Label() , 
    		lblFile = new Label() , lblPlugin = new Label() , 
    		lblHelp = new Label() ;
    Button  btsearch = new Button( "Search" ) , btclear = new Button( "Clear" ) , 
    		btadd = new Button( "Add" ) , btdel = new Button( "Delete" ) , 
    		btopen = new Button( "Choose a File..." ) ;
    TextField search = new TextField() , addImg = new TextField() , 
    		addTitle = new TextField() , addRecipe = new TextField() , 
    		addIng = new TextField() , addScore = new TextField() , 
    		addPoll = new TextField() , addUrl = new TextField() ;
    
    //VBoxes and Hboxes for the labels and checkboxes 
    VBox vbchecks = new VBox() , vblabels = new VBox() , 
    		vbbutton = new VBox() , vbtext = new VBox() , 
    		vball = new VBox() , vbbox = new VBox() ;
    HBox hb = new HBox() ;
    
    //Desktop needed for searching a file
    private Desktop desktop = Desktop.getDesktop() ;
    final FileChooser fileChooser = new FileChooser() ;
    
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
        		//vbox for checkboxes
                vbchecks.setSpacing(10) ;
                vbchecks.setPadding(new Insets(20)) ;
                 
                //vbox for textfield
                vbtext.setSpacing(10) ;
                vbtext.setPadding(new Insets(20)) ;		
                
                //vbox for labels
                vblabels.setSpacing(10) ;
                vblabels.setPadding(new Insets(20)) ;
                 
                //vbox for buttons
                vbbutton.setSpacing(10) ;
                vbbutton.setPadding(new Insets(20)) ;
     		   
                //setSelected on true for all 5 checkboxes
                chkmarmiton.setSelected( true ) ;
                chkcuisineaz.setSelected (true ) ;
                chkaufeminin.setSelected( true ) ;
                chkbdd.setSelected( true ) ;
                chklocal.setSelected( true ) ;
                 
                //make 2 labels
                lbltotal.setText( "Websites chosen: 5" ) ;
                lbllist.setText( "- Website Marmiton \n"
                		+ "- Website Cuisineaz \n"
                		+ "- Website auFeminin \n"
                 		+ "- Website Cookie's Cookout \n"
                		+ "- Local \n" ) ;
                 
                //textfield
     		    search.setPromptText( "What do you want to search?" ) ;
     		    search.setMaxWidth( 195 ) ;

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
                btsearch.setOnAction( e -> handleButtonSearch( e ) ) ;
                
                btclear.setOnAction( e -> handleButtonClear( e ) ) ;
                
                vball.getChildren().addAll( menuBar , vbchecks , vblabels , vbtext , vbbutton ) ;
                mainContainer.createMainContainer( stage , menuBar , vball ) ;
            } ;
        } ) ;
        menuHome.getItems().add( menuItemHome ) ;
        
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
        				/*
        				System.out.println(data == null);
        				if ( data != null )
        				{
        					data.removeAll( data ) ;
        					data.clear() ;
        				}*/
        				
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
                                
        		        		addImg.setMaxWidth( imgCol.getPrefWidth( ) ) ;
        		        		addTitle.setMaxWidth( titleCol.getPrefWidth( ) ) ;
        		        		addRecipe.setMaxWidth( recipeCol.getPrefWidth( ) ) ;
        		        		addIng.setMaxWidth( ingCol.getPrefWidth( ) ) ;
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
        		        		addUrl.setMaxWidth( urlCol.getPrefWidth( ) ) ;
        				        
        		        		//Button action on click
        				        btadd.setOnAction( e -> handleButtonAddBdd( e ) ) ;
        				        btdel.setOnAction( e -> handleButtonDelBdd( e ) ) ;
        				        
        				        hb.getChildren().addAll( addImg , addTitle , addRecipe , addIng , addScore , addPoll , addUrl , btadd , btdel ) ;
                                hb.setSpacing( 3 ) ;
                                
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
                 btopen.setOnAction( e -> handleButtonOpenFile( e , stage ) ) ;
                 mainContainer.createMainContainer( stage , menuBar , btopen ) ;
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
                 vblabels.setSpacing( 10 ) ;
                 vblabels.setPadding( new Insets( 20 ) ) ;
                 
                 //make 2 labels
                 lblMenu.setText( "Menu \n"
                 		+ "	You can search through our different support to find any recipe. \n"
                 		+ "		Websites: Marmiton, Cuisineaz, auFeminin \n"
                 		+ "		Even on our website Cookie's Cookout \n"
                 		+ "		Or locally. \n" ) ;
                 lblFile.setText( "File \n"
                 		+ "	You can add any new recipe with these following information: \n"
                 		+ "		- Title\n"
                 		+ "		- Picture\n"
                 		+ "		- Ingredients\n"
                 		+ "		- Steps\n" ) ;
                 lblPlugin.setText( "Plugin \n"
                 		+ "	You can add any new plugin.\n" ) ;
                 lblHelp.setText( "Help \n"
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
     
    private void handleButtonSearch( ActionEvent e )
    {
    	//TO DO
    }
     
    /**
     * 
     * @param e
     */
    private void handleButtonAddBdd( ActionEvent e )
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
 			
 			data = bdd.getFromBDD() ;
				table.setItems( data ) ;
 			
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
 		
	} ;
     
	/**
	 * 
	 * @param e
	 */
    private void handleButtonDelBdd( ActionEvent e )
	{
		Recipe recipe2 = table.getSelectionModel().getSelectedItem() ;
		
		if ( recipe2 != null )
		{
			label.setText( bdd.deleteInBDD( recipe2.getTitle() ) ) ; 
			data = bdd.getFromBDD() ;
			table.setItems( data ) ;
		}
		else
		{
			label.setText( "You have to select a row in order to delete it!" ) ;
		}
	} ;
     
	/**
	 * 
	 * @param e
	 */
    private void handleButtonClear( ActionEvent e )
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
    
    /**
     * 
     * @param e
     * @param stage
     */
    private void handleButtonOpenFile( ActionEvent e , Stage stage)
	{
		 File file = fileChooser.showOpenDialog( stage ) ;
		 if ( file != null )
		 {
			 openFile( file ) ;
        }
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
    
} ; 