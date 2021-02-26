package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import model.Store;
import view.View;

public class Controller {
	private Store theModel;
	private View theView;

	public Controller(Store model, View view) {
		theModel = model;
		theView = view;

		try {
			theModel.readProductsFromBinaryFile("products.txt");
		} catch (Exception c) {
			Label l = new Label("There are no products inside the file! ");
			l.setTextFill(Color.RED);
			theView.alertStage(l);
		}

		EventHandler<ActionEvent> confirm = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					theView.updateModel(theModel);
				} catch (Exception c) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setContentText(c.getMessage());
				}
			}
		};
		theView.addEventToConfirmButton(confirm);

		EventHandler<ActionEvent> showDetails = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					theView.showDeteails(model);
				} catch (Exception c) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setContentText(c.getMessage());
				}
			}
		};
		theView.addEventToShowdDetails(showDetails);

		EventHandler<ActionEvent> sortButton = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					theView.sortDetails(model);
				} catch (Exception c) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setContentText(c.getMessage());
				}
			}
		};
		theView.addEventToSortMap(sortButton);

		EventHandler<ActionEvent> searchByKey = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					theView.findAProduct(model);
				} catch (Exception c) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setContentText(c.getMessage());
				}
			}
		};
		theView.addEventToSearchProduct(searchByKey);

		EventHandler<ActionEvent> addProduct = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {

				try {
					theView.addProduct(model);
				} catch (Exception c) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setContentText(c.getMessage());
				}
			}
		};
		theView.addEventToAddProduct(addProduct);

		EventHandler<ActionEvent> removeProduct = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					theView.removeProduct(model);
				} catch (Exception c) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setContentText(c.getMessage());
				}
			}
		};
		theView.addEventToRemoveProduct(removeProduct);

		EventHandler<ActionEvent> removeAllProducts = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					theView.deleteAllProducts(model);
				} catch (Exception c) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setContentText(c.getMessage());
				}
			}
		};
		theView.addEventToRemoveAllProduct(removeAllProducts);

		EventHandler<ActionEvent> undoLastProduct = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					theView.undoLastProduct(model);
				} catch (Exception c) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setContentText(c.getMessage());
				}
			}
		};
		theView.addEventToCancelProductAddition(undoLastProduct);

		EventHandler<ActionEvent> sell = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					theView.makeASale(model);
				} catch (Exception c) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setContentText(c.getMessage());
				}
			}
		};
		theView.addEventToSell(sell);

	}

}
