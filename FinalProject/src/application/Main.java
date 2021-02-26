package application;

import controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Store;
import view.View;

public class Main extends Application {
	Store store;
	View view;
	Controller controller;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		Store store = Store.getInstance("Shefa isashar");
		View view = new View(primaryStage, store);
		Controller controller = new Controller(store, view);

	}
}