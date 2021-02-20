package view;

import java.util.Iterator;
import java.util.Optional;

import javax.swing.JOptionPane;

import command.AddProductCommand;
import command.FindProductCommand;
import command.RemoveAllProducts;
import command.RemoveLastProductCommand;
import command.RemoveProductCommand;
import command.SortMapAccordingTypeCommand;
import command.UpdateCommand;
import command.printObserversCommand;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Customer;
import model.Product;
import model.Store;
import model.Store.SortType;
import model.storeDetailesTable;

public class View {
	private Label product;
	private Label customer;
	private Stage MainWindow;
	private Text serialTxt;
	private TextField serial;
	private Text serialTxt2;
	private TextField serial2;
	private Text productNameTxt;
	private TextField productName;
	private Text storeCostTxt;
	private TextField storeCost;
	private Text sellingPriceTxt;
	private TextField sellingPrice;
	private Text customerNameTxt;
	private TextField customerName;
	private Text CustomerPhoneTxt;
	private TextField CustomerPhone;
	private Text CustomerIdTxt;
	private TextField CustomerId;
	private Text productKeyTxt;
	private TextField productKey;
	private RadioButton r1;
	private Button sortButton;
	private Button buttonSearch;
	private Button buttonAddProduct;
	private Button showDetails;
	private Button removeProduct;
	private Button cancelProductAddition;
	private Button removeAllProduct;
	private Button okButton;
	private Button makeNewSale;
	private Button updateObserversAboutSale;
	private Button startSendMessages;
	private Scene mainScene, showDetailsScene, sortScene, findProductScene, addProductScene , makeSale, printObservers;
	private String function;
	private ComboBox<String> sortComboBox;


	public View(Stage primaryStage, Store store) throws Exception {

		MainWindow = primaryStage;
		primaryStage.setTitle(store.getName());
		showDetails = new Button("show Details");
		sortButton = new Button("sort details");
		buttonSearch = new Button("find a product");
		buttonAddProduct = new Button("add product");
		removeProduct = new Button("remove a product");
		makeNewSale = new Button("make new sale"); 
		updateObserversAboutSale = new Button("update observers");
		sortButton.setFont(new Font("David", 20));
		buttonSearch.setFont(new Font("David", 20));
		buttonAddProduct.setFont(new Font("David", 20));
		removeProduct.setFont(new Font("David", 20));
		buttonAddProduct.setFont(new Font("David", 20));
		cancelProductAddition.setFont(new Font("David", 20));
		makeNewSale.setFont(new Font("David", 20));
		updateObserversAboutSale.setFont(new Font("David", 20));
		Label title = new Label(" Welcome to Management system");
		title.setFont(new Font(40));

		VBox layoutInMainWindow = new VBox(180);
		layoutInMainWindow.setAlignment(Pos.CENTER);
		layoutInMainWindow.getChildren().addAll(title, showDetails, sortButton, buttonSearch, buttonAddProduct,
				makeNewSale, updateObserversAboutSale);
		layoutInMainWindow.setAlignment(Pos.CENTER);
		mainScene = new Scene(layoutInMainWindow, 950, 760);
		primaryStage.setScene(mainScene);
		primaryStage.setOnCloseRequest(e -> {
			e.consume();
			try {
				CloseProgram();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		});
	}

	public void makeASale(Store store) {
		function = "makeSale";
		Label title = new Label("new Sale");
		Button previous = new Button("Previous");
		okButton = new Button("confirm");
		productKey = new TextField();
		productKey.setVisible(true);
		productKeyTxt = new Text("product serial (up to 9 chars)");
		sellingPrice = new TextField();
		sellingPrice.setVisible(true);
		sellingPriceTxt = new Text("new price:");
		VBox addNewSale = new VBox(10);
		addNewSale.getChildren().addAll(title, productKey, productKeyTxt, sellingPrice, sellingPriceTxt, okButton, previous);
		makeSale = new Scene(addNewSale, 1000, 800);
		getMainWindow().setScene(makeSale);

	}

	public void printObservers(Store store) {
		function = "printObservers";
		Label title = new Label("send messages");
		startSendMessages = new Button("send");
		Button previous = new Button("Previous");
		VBox print = new VBox(10);

		print.getChildren().addAll(title,startSendMessages,  previous);
		printObservers = new Scene(print, 1000, 800);
		getMainWindow().setScene(printObservers);


	}

	public void sortDetails(Store store) {
		function = "Sorting";
		Button previous = new Button("Previous");
		Button okButton = new Button("confirm");
		sortComboBox = new ComboBox<String>();
		sortComboBox.getItems().addAll(Store.SortType.values().toString());
		VBox sortVbox = new VBox(10);
		Label title = new Label("Sorting details");
		title.setFont(Font.font("Calibri", FontWeight.BOLD, 36));
		sortVbox.getChildren().addAll(title, sortComboBox, okButton, previous);
		sortScene = new Scene(sortVbox, 1000, 800);
		previous.setOnAction(e -> getMainWindow().setScene(mainScene));
		getMainWindow().setScene(sortScene);
	}

	public void findAProduct(Store store) {
		function = "findAProduct";
		okButton = new Button("confirm");
		Button previous = new Button("Previous");
		serial = new TextField();
		previous.setOnAction(e -> getMainWindow().setScene(mainScene));
		serialTxt = new Text("Please enter product serial");
		Label title = new Label("Find a product");
		title.setFont(Font.font("Calibri", FontWeight.BOLD, 36));
		serial.setVisible(true);
		VBox findVBox = new VBox(10);
		findVBox.getChildren().addAll(title, serialTxt, serial, okButton, previous);
		findProductScene = new Scene(findVBox, 1000, 800);
		getMainWindow().setScene(findProductScene);
	}


	public void addProduct(Store store) {
		function = "addProduct";
		okButton = new Button("confirm");
		Button previous = new Button("Previous");
		previous.setOnAction(e -> getMainWindow().setScene(mainScene));
		Label title = new Label("add a product");
		title.setFont(Font.font("Calibri", FontWeight.BOLD, 36));
		product = new Label("Product Deteails");
		serial2 = new TextField();
		serial2.setVisible(true);
		serialTxt2 = new Text("product serial (up to 9 chars)");
		productName = new TextField();
		productName.setVisible(true);
		productNameTxt = new Text("product name:");
		storeCost = new TextField();
		storeCost.setVisible(true);
		storeCostTxt = new Text("please enter price to store:");
		sellingPrice = new TextField();
		sellingPrice.setVisible(true);
		sellingPriceTxt = new Text("please enter selling price:");
		customer = new Label("Customer Deteails");
		customerName = new TextField();
		customerName.setVisible(true);
		customerNameTxt = new Text("please enter name:");
		CustomerPhone = new TextField();
		CustomerPhone.setVisible(true);
		CustomerIdTxt = new Text("Enter id of the customer ");
		CustomerId = new TextField();
		CustomerPhoneTxt = new Text("please enter phone number:");
		Text wantsUpdates = new Text("the customer wants updates?");
		r1 = new RadioButton();
		VBox addProductBox = new VBox(10);
		addProductBox.getChildren().addAll(title, product, serial2, serialTxt2, productName, productNameTxt, storeCost,
				storeCostTxt, sellingPrice, sellingPriceTxt, customer, customerName, customerNameTxt, CustomerPhone,
				CustomerPhoneTxt, CustomerId, CustomerIdTxt, wantsUpdates, r1, okButton, previous);
		addProductScene = new Scene(addProductBox, 1000, 800);
		getMainWindow().setScene(addProductScene);

	}

	public void showDeteails(Store store) {
		function = "showDeteails";
		removeAllProduct = new Button("remove all products");
		cancelProductAddition = new Button("cancel product addition");
		Label title = new Label("show details");
		title.setTextFill(Color.DARKBLUE);
		title.setFont(Font.font("Calibri", FontWeight.BOLD, 36));
		Label profit = new Label("The profit is:"+store.calculateProfit());

		TableView table = new TableView();
		TableColumn<Product, String> col1 = new TableColumn<>("serial");
		col1.setCellValueFactory(new PropertyValueFactory<>("serial"));
		TableColumn<Product, String> col2 = new TableColumn<>("productName");
		col2.setCellValueFactory(new PropertyValueFactory<>("productName"));
		TableColumn<Product, String> col3 = new TableColumn<>("priceToStore");
		col3.setCellValueFactory(new PropertyValueFactory<>("priceToStore"));
		TableColumn<Product, String> col4 = new TableColumn<>("sellPrice");
		col4.setCellValueFactory(new PropertyValueFactory<>("sellPrice"));
		TableColumn<Product, String> col5 = new TableColumn<>("customerName");
		col5.setCellValueFactory(new PropertyValueFactory<>("customerName"));
		TableColumn<Product, String> col6 = new TableColumn<>("Id");
		col6.setCellValueFactory(new PropertyValueFactory<>("id"));
		TableColumn<Product, String> col7 = new TableColumn<>("customerPhone");
		col7.setCellValueFactory(new PropertyValueFactory<>("customerPhone"));
		TableColumn<Product, String> col8 = new TableColumn<>("wantsUpdate");
		col8.setCellValueFactory(new PropertyValueFactory<>("wantsUpdate"));
		TableColumn<Product, String> col9 = new TableColumn<>("Profit");
		col9.setCellValueFactory(new PropertyValueFactory<>("profit"));
		col1.setPrefWidth(160);
		col2.setPrefWidth(160);
		col3.setPrefWidth(160);
		col4.setPrefWidth(160);
		col5.setPrefWidth(160);
		col6.setPrefWidth(160);
		col7.setPrefWidth(160);
		col8.setPrefWidth(160);
		col9.setPrefWidth(160);

		ObservableList<storeDetailesTable> data = FXCollections.observableArrayList();
		table.getColumns().addAll(col1, col2, col3, col4, col5, col6, col7, col8,col9);
		VBox vbox = new VBox(10);
		Iterator<String> itr = store.getProducts().keySet().iterator();
		storeDetailesTable t;
		while (itr.hasNext()) {
			String key = itr.next();
			Product p = (Product) store.getProducts().get(key);
			t = new storeDetailesTable(key, p.getProductName(), "" + p.getPriceToStore(), p.getPriceToCostumer() + "",
					p.getCustomer().getCustomerName(), p.getCustomer().getId(), p.getCustomer().getPhoneNumber(),
					p.getCustomer().isWantUpdates() + "",(p.getPriceToCostumer()-p.getPriceToStore()));
			data.add(t);
		}
		table.setItems(data);
		Button previous = new Button("Previous");
		previous.setOnAction(e -> getMainWindow().setScene(mainScene));
		vbox.getChildren().addAll(title, table,profit, removeProduct, removeAllProduct, cancelProductAddition, previous);
		removeProduct.setVisible(true);

		showDetailsScene = new Scene(vbox, 1000, 800);
		getMainWindow().setScene(showDetailsScene);

	}

	public Stage getMainWindow() {
		return MainWindow;
	}


	public void removeProduct(Store store)
	{
		TextInputDialog dialog = new TextInputDialog("Remove product");
		dialog.setTitle("Remove product");
		dialog.setHeaderText(null);
		dialog.setContentText("Please enter a serial num :");

		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
			int check = new RemoveProductCommand(store, result.toString()).execute();
			if(check==0)
			{
				Alert art = new Alert(Alert.AlertType.ERROR);
				art.setContentText("there is no product like this");
				art.show();
			}
			else
			{
				Alert msg = new Alert(Alert.AlertType.CONFIRMATION);
				msg.setContentText("removed successfully!");
				msg.show();
			}


		}


	}
	public void deleteAllProducts(Store store)
	{
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Delete all products");
		alert.setHeaderText("Look, a Confirmation Dialog");
		alert.setContentText("Are you ok with this?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			new RemoveAllProducts(store).execute();
			alert.setContentText("deleted sucessfully");
		} else {
			getMainWindow().setScene(showDetailsScene);
		}
	}


	public void undoLastProduct(Store store)
	{
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("remove last product");
		alert.setHeaderText(null);
		alert.setContentText("Are you want to remove the last product?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			int res = new RemoveLastProductCommand(store).execute();
			if(res==0)
			{
				Alert a = new Alert(AlertType.ERROR);
				a.setTitle("Error !!!");
				a.setHeaderText("Look, an Error Dialog");
				a.setContentText("Ooops, there was an error!you cant remove again");
				alert.showAndWait();
			}
			else
			{
				Alert a2 = new Alert(AlertType.INFORMATION);
				a2.setTitle("sucessful deletion");
				a2.setHeaderText("Look, an Information Dialog");
				a2.setContentText("the last product is deleted!");
			}
		} else {
			getMainWindow().setScene(showDetailsScene);
		}
	}

	public void updateModel(Store model) throws Exception {
		if (function == "findAProduct") {
			if (serial.getText().isEmpty()) {
				Alert art = new Alert(Alert.AlertType.ERROR);
				art.setContentText("Please fill all the details");
				art.show();
			}
			try {
				String serialNum = serial.getText().toString();
				findProduct(new FindProductCommand(model, serialNum).execute());
			}

			catch (Exception exception) {
				Alert art = new Alert(Alert.AlertType.ERROR);
				art.setContentText(exception.getMessage());
				art.show();
			}

			if (function == "Sorting") {
				try {
					new SortMapAccordingTypeCommand(model, (SortType)sortComboBox.getUserData()).execute();
					Alert msg = new Alert(Alert.AlertType.CONFIRMATION);
					msg.setContentText("sorted successfully!");
					msg.show();
				} catch (Exception c) {
					Alert art = new Alert(Alert.AlertType.ERROR);
					art.setContentText(c.getMessage());
					art.show();
				}
			}
		}
		else if (function == "addProduct") {
			if (serial2.getText().isEmpty() || productName.getText().isEmpty() || storeCost.getText().isEmpty() || sellingPrice.getText().isEmpty()
					||customerName.getText().isEmpty()|| CustomerId.getText().isEmpty()||CustomerPhone.getText().isEmpty()) {
				Alert art = new Alert(Alert.AlertType.ERROR);
				art.setContentText("Please fill all the details");
				art.show();
			} else {
				try {
					String key =serial2.getText();
					String name = productName.getText();
					int Cost = Integer.parseInt(storeCost.getText());
					int price = Integer.parseInt(sellingPrice.getText());
					String nameCustomer = customerName.getText();
					String id = CustomerId.getText();
					String phone = CustomerPhone.getText();
					Customer customer;
					if(r1.isArmed())
					{
						customer = new Customer(nameCustomer, id, phone, true);

					}
					else
						customer = new Customer(nameCustomer, id, phone, false);

					Product p = new Product(name, Cost, price, customer);
					new AddProductCommand(model, key, p).execute();						
					Alert msg 
					= new Alert(Alert.AlertType.CONFIRMATION);
					msg.setContentText(" added successfully!");
					msg.show();
				}
				catch (Exception c) {
					Alert art = new Alert(Alert.AlertType.ERROR);
					art.setContentText(c.getMessage());
					art.show();

				}
			}
		}
		else if(function=="makeSale")
		{
			if (productKey.getText().isEmpty()|| sellingPrice.getText().isEmpty())
			{
				Alert art = new Alert(Alert.AlertType.ERROR);
				art.setContentText("Please fill all the details");
				art.show();
			}
			else
			{
				try {
					String key=productKey.getText();
					int price = Integer.parseInt(sellingPrice.getText());
					new UpdateCommand(model, key, price).execute();
					Alert msg 
					= new Alert(Alert.AlertType.CONFIRMATION);
					msg.setContentText(" updated successfully!");
					msg.show();
				}
				catch (Exception c) {
					Alert art = new Alert(Alert.AlertType.ERROR);
					art.setContentText(c.getMessage());
					art.show();

				}
			}
		}

		else if(function=="printObservers")
		{
			try {
				new printObserversCommand(model).execute();
			}
			catch (Exception c) {
				Alert art = new Alert(Alert.AlertType.ERROR);
				art.setContentText(c.getMessage());
				art.show();

			}

		}
	}

	public void addEventToConfirmButton(EventHandler<ActionEvent> confirm) {
		okButton.setOnAction(confirm);
	}

	public void addEventToShowdDetails(EventHandler<ActionEvent> details) {
		showDetails.setOnAction(details);
	}

	public void addEventToSortMap(EventHandler<ActionEvent> sort) {
		sortButton.setOnAction(sort);
	}

	public void addEventToSearchProduct(EventHandler<ActionEvent> SearchProduct) {
		buttonSearch.setOnAction(SearchProduct);
	}

	public void addEventToAddProduct(EventHandler<ActionEvent> add) {
		buttonAddProduct.setOnAction(add);
	}

	public void addEventToRemoveProduct(EventHandler<ActionEvent> removeP) {
		removeProduct.setOnAction(removeP);
	}

	public void addEventToRemoveAllProduct(EventHandler<ActionEvent> deleteAll) {
		removeAllProduct.setOnAction(deleteAll);
	}

	public void addEventToCancelProductAddition(EventHandler<ActionEvent> undo) {
		cancelProductAddition.setOnAction(undo);
	}
	public void addEventToSell(EventHandler<ActionEvent> makeSell) {
		makeNewSale.setOnAction(makeSell);
	}
	public void addEventToUpdateObservers(EventHandler<ActionEvent> update) {
		updateObserversAboutSale.setOnAction(update);
	}
	public void addEventToSendUpdate(EventHandler<ActionEvent> send) {
		startSendMessages.setOnAction(send);
	}
	
	
	private void CloseProgram() throws Exception {

		int answer = JOptionPane.showConfirmDialog(null, "Exit", "Exit? ", JOptionPane.YES_NO_OPTION);

		if (answer == JOptionPane.YES_OPTION) {

			System.out.print("Closing");
			MainWindow.close();

		}
	}

	public void findProduct(Product product) {
		if (product == null) {
			Alert l = new Alert(Alert.AlertType.ERROR);
			l.setContentText("Please fill all the details");
			l.show();
			return;
		}
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Product found!!!");
		alert.setHeaderText(null);
		alert.setContentText(product.toString());

		alert.showAndWait();
	}
}
