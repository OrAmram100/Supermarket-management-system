package application;
	
import controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Store;
import view.View;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application{

	public static void main(String[] args) {
		launch(args);
		//Store store = new Store("Shefa isashar");
		//Check check = new Check(s);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
	    Store store= new Store("Shefa isashar");
	    View view = new View(primaryStage,store);
	    store.readProductsFromBinaryFile(Store.FILE_NAME);
	    
	    
	    
	    Controller controller= new Controller(store,view);
		
	}

}