package view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
import javafx.application.Platform;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
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
	private Scene mainScene, showDetailsScene, sortScene, findProductScene, addProductScene , makeSale, printObservers;
	private String function;
	private int numOfPrdoucts=0;
	private ComboBox<SortType> sortComboBox;
	private List<Product> allProducts = new ArrayList<Product>();
	private VBox print;
	private VBox addNewSale;
	private ScrollPane sp;



	public View(Stage primaryStage, Store store) throws Exception {

		MainWindow = primaryStage;
		primaryStage.setTitle(store.getNameStore());
		okButton = new Button("confirm");
		showDetails = new Button("show Details");
		sortButton = new Button("sort details");
		buttonSearch = new Button("find a product");
		removeAllProduct = new Button("remove all products");
		buttonAddProduct = new Button("add product");
		removeProduct = new Button("remove a product");
		makeNewSale = new Button("make new sale"); 
		updateObserversAboutSale = new Button("update observers");
		cancelProductAddition = new Button("Cancel last product");
		sortButton.setFont(new Font("David", 20));
		buttonSearch.setFont(new Font("David", 20));
		buttonAddProduct.setFont(new Font("David", 20));
		removeProduct.setFont(new Font("David", 20));
		buttonAddProduct.setFont(new Font("David", 20));
		cancelProductAddition.setFont(new Font("David", 20));
		makeNewSale.setFont(new Font("David", 20));
		showDetails.setFont(new Font("David", 20));
		okButton.setFont(new Font("David", 20));
		Label title = new Label("Welcome to Management system");
		title.setFont(new Font(40));

		VBox layoutInMainWindow = new VBox(20);
		layoutInMainWindow.setAlignment(Pos.CENTER);
		layoutInMainWindow.getChildren().addAll(title, showDetails, sortButton, buttonSearch, buttonAddProduct,
				makeNewSale);
		layoutInMainWindow.setAlignment(Pos.CENTER);
		mainScene = new Scene(layoutInMainWindow, 950, 760);
		primaryStage.setScene(mainScene);
		primaryStage.show();
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
		sp = new ScrollPane();
		sp.setVisible(true);
		Label title = new Label("create sale");
		title.setFont(Font.font("Calibri", FontWeight.BOLD, 36));
		Button previous = new Button("Previous");
		updateObserversAboutSale.setFont(Font.font("Calibri", FontWeight.BOLD, 40));
		updateObserversAboutSale.setVisible(false);
		okButton.setVisible(true);
		productKey = new TextField();
		productKey.setVisible(true);
		productKeyTxt = new Text("product serial (up to 9 chars)");
		sellingPrice = new TextField();
		sellingPrice.setVisible(true);
		sellingPriceTxt = new Text("new price:");
		 addNewSale = new VBox(10);
		previous.setOnAction(e -> getMainWindow().setScene(mainScene));
		addNewSale.getChildren().addAll(title,sp, productKeyTxt, productKey,sellingPriceTxt, sellingPrice,  okButton,updateObserversAboutSale, previous);	
		makeSale = new Scene(addNewSale, 1000, 800);
		getMainWindow().setScene(makeSale);
		numOfPrdoucts=0;
		getMainWindow().show();


	}


//	public void printObservers(Store store,List<Product> products) {
//		function = "printObservers";
//		Label title = new Label("print Observers");
//		title.setFont(Font.font("Calibri", FontWeight.BOLD, 36));
//		Button previous = new Button("Previous");
//		print = new VBox(10);
//		previous.setOnAction(e -> getMainWindow().setScene(mainScene));	
//		new printObserversCommand(store,products,this).execute();
//		print.getChildren().addAll(title, previous);
//		printObservers = new Scene(print, 1000, 800);
//		getMainWindow().setScene(printObservers);
//		products.clear();
//
//
//	}

	public void sortDetails(Store store) {
		function = "Sorting";
		Button previous = new Button("Previous");
		okButton.setVisible(true);
		sortComboBox = new ComboBox<SortType>();
		sortComboBox.getItems().addAll(Store.SortType.values());
		VBox sortVbox = new VBox(10);
		sortVbox.setAlignment(Pos.CENTER);
		Label title = new Label("Sorting details");
		Label choose = new Label("please choose the way to sorf from the following options:");
		choose.setFont(Font.font("Calibri", FontWeight.BLACK, 15));
		title.setFont(Font.font("Calibri", FontWeight.BOLD, 36));
		sortVbox.getChildren().addAll(title,choose, sortComboBox, okButton, previous);
		sortScene = new Scene(sortVbox, 1000, 800);
		previous.setOnAction(e -> getMainWindow().setScene(mainScene));
		getMainWindow().setScene(sortScene);
	}

	public void findAProduct(Store store) {
		function = "findAProduct";
		okButton.setVisible(true);;
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
		okButton.setVisible(true);
		Button previous = new Button("Previous");
		previous.setOnAction(e -> getMainWindow().setScene(mainScene));
		Label title = new Label("add a product");
		title.setFont(Font.font("Calibri", FontWeight.BOLD, 36));
		product = new Label("Product Deteails:");
		product.setFont(Font.font("Calibri", FontWeight.BOLD, 25));
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
		customer = new Label("Customer Deteails:");
		customer.setFont(Font.font("Calibri", FontWeight.BOLD, 25));
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
		addProductBox.getChildren().addAll(title, product, serialTxt2, serial2, productNameTxt, productName,storeCostTxt, storeCost, sellingPriceTxt,
				sellingPrice, customer,customerNameTxt, customerName,CustomerPhoneTxt, CustomerPhone,
				CustomerIdTxt, CustomerId, wantsUpdates, r1, okButton, previous);
		addProductScene = new Scene(addProductBox, 1000, 800);
		getMainWindow().setScene(addProductScene);

	}

	public void showDeteails(Store store) {
		function = "showDeteails";
		removeAllProduct.setVisible(true);
		removeAllProduct.setFont(new Font("David", 20));
		cancelProductAddition.setVisible(true);
		cancelProductAddition.setFont(new Font("David", 20));
		Label title = new Label("show details");
		title.setFont(Font.font("Calibri", FontWeight.BOLD, 30));
		Label profit = new Label("The profit is:"+store.calculateProfit());
		profit.setFont(Font.font("Calibri", FontWeight.BOLD, 36));
		TableView table = new TableView();
		TableColumn<Product, String> col1 = new TableColumn<>("Serial");
		col1.setCellValueFactory(new PropertyValueFactory<>("serial"));
		TableColumn<Product, String> col2 = new TableColumn<>("Product name");
		col2.setCellValueFactory(new PropertyValueFactory<>("productName"));
		TableColumn<Product, String> col3 = new TableColumn<>("Buying price");
		col3.setCellValueFactory(new PropertyValueFactory<>("priceToStore"));
		TableColumn<Product, String> col4 = new TableColumn<>("Selling price");
		col4.setCellValueFactory(new PropertyValueFactory<>("sellPrice"));
		TableColumn<Product, String> col5 = new TableColumn<>("Customer name");
		col5.setCellValueFactory(new PropertyValueFactory<>("customerName"));
		TableColumn<Product, String> col6 = new TableColumn<>("Customer ID");
		col6.setCellValueFactory(new PropertyValueFactory<>("id"));
		TableColumn<Product, String> col7 = new TableColumn<>("Customer Phone");
		col7.setCellValueFactory(new PropertyValueFactory<>("customerPhone"));
		TableColumn<Product, String> col8 = new TableColumn<>("Want updates");
		col8.setCellValueFactory(new PropertyValueFactory<>("wantUpdates"));
		TableColumn<Product, String> col9 = new TableColumn<>("Profit");
		col9.setCellValueFactory(new PropertyValueFactory<>("profit"));
		col1.setPrefWidth(111);
		col2.setPrefWidth(111);
		col3.setPrefWidth(111);
		col4.setPrefWidth(111);
		col5.setPrefWidth(111);
		col6.setPrefWidth(111);
		col7.setPrefWidth(111);
		col8.setPrefWidth(111);
		col9.setPrefWidth(111);

		ObservableList<storeDetailesTable> data = FXCollections.observableArrayList();
		table.getColumns().addAll(col1, col2, col3, col4, col5, col6, col7, col8,col9);
		VBox vbox = new VBox(10);
		Iterator<String> itr = store.getProducts().keySet().iterator();
		storeDetailesTable t;
		while (itr.hasNext()) {
			String key = itr.next();
			Product p = (Product) store.getProducts().get(key);
			t = new storeDetailesTable(key, p.getProductName(), "" + p.getPriceToStore(), p.getPriceToCostumer() + "",
					p.getCustomer().getCustomerName(), p.getCustomer().getCustomerId(), p.getCustomer().getPhoneNumber(),""
							+ "" +p.getCustomer().isWantUpdates()+ "",(p.getPriceToCostumer()-p.getPriceToStore()));
			data.add(t);
		}
		table.setItems(data);
		Button previous = new Button("Previous");
		previous.setOnAction(e -> getMainWindow().setScene(mainScene));
		vbox.getChildren().addAll(title, table,profit, removeProduct, removeAllProduct, cancelProductAddition, previous);
		removeProduct.setFont(new Font("David", 20));
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
			int check = new RemoveProductCommand(store, result.get()).execute();
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
			getMainWindow().setScene(mainScene);


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
			alert.show();
		} else {
			getMainWindow().setScene(showDetailsScene);
		}
		getMainWindow().setScene(mainScene);
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
				a.setContentText("Ooops, there was an error!you cant remove ");
				a.showAndWait();
			}
			else
			{
				Alert a2 = new Alert(AlertType.INFORMATION);
				a2.setTitle("sucessful deletion");
				a2.setHeaderText("Look, an Information Dialog");
				a2.setContentText("the last product is deleted!");
				a2.showAndWait();

			}
			getMainWindow().setScene(mainScene);
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
		}
		else if (function == "Sorting") {
			try {
				new SortMapAccordingTypeCommand(model, (SortType)sortComboBox.getValue()).execute();
				Alert msg = new Alert(Alert.AlertType.CONFIRMATION);
				msg.setContentText("sorted successfully!");
				msg.show();
			} catch (Exception c) {
				Alert art = new Alert(Alert.AlertType.ERROR);
				art.setContentText(c.getMessage());
				art.show();
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
					if(r1.isSelected())
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
				getMainWindow().setScene(mainScene);

			}
		}
		else if(function=="makeSale")
		{
			if (productKey.getText().isEmpty()|| sellingPrice.getText().isEmpty())
			{
				Alert art = new Alert(Alert.AlertType.ERROR);
				art.setContentText("Please fill all the details");
				art.show();
				getMainWindow().setScene(mainScene);
			}
			else
			{
				try {
					String key=productKey.getText();
					int price = Integer.parseInt(sellingPrice.getText());
					numOfPrdoucts++;
					boolean check=true;
					if(allProducts.size()==0)
						allProducts.add(new UpdateCommand(model, key, price).execute());
					for(int i=0;i<numOfPrdoucts-1;i++)
					{
						allProducts.add(new UpdateCommand(model, key, price).execute());
					}
					for(int i=0;i<allProducts.size();i++)
					{
						if(allProducts.get(i)==null)
						{
							Alert art = new Alert(Alert.AlertType.ERROR);
							art.setContentText("there is no product like this");
							numOfPrdoucts=0;
							art.show();
							check=false;
							break;
						}
					}
					if(check)
					{
						Alert msg 
						= new Alert(Alert.AlertType.CONFIRMATION);
						msg.setContentText(" updated successfully!");
						updateObserversAboutSale.setVisible(true);
						msg.show();
						updateObserversAboutSale.setOnAction(e->new printObserversCommand(model,allProducts,this).execute());
						numOfPrdoucts=0;
					}
				}
				catch (Exception c) {
					Alert art = new Alert(Alert.AlertType.ERROR);
					art.setContentText(c.getMessage());
					art.show();

				}
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




	private void CloseProgram() throws Exception {

		int answer = JOptionPane.showConfirmDialog(null, "Exit", "Exit? ", JOptionPane.YES_NO_OPTION);

		if (answer == JOptionPane.YES_OPTION) {

			System.out.print("Closing");
			MainWindow.close();

		}
	}
	public void alertStage(Label l) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText("note");
		alert.setContentText("There is no file to read!\nadding product will create file");

		alert.showAndWait();
	}


	public void findProduct(Product product) {
		if (product == null) {
			Alert l = new Alert(Alert.AlertType.ERROR);
			l.setContentText("Please fill all the details");
			l.show();
			return;
		}
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Product found!");
		alert.setResizable(true);
		alert.setHeaderText(null);
		String content = String.format(product.toString());
		alert.setContentText(content);
		alert.showAndWait();
	}

	public void printLabels(String string)
	{
		// getMainWindow().setScene(printObservers);
		Label label = new Label(string);
		label.setVisible(true);
		label.setLayoutX(20);
		label.setLayoutY(20);
		label.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
		label.setMaxWidth(1000);
		label.setWrapText(true);
		label.wrapTextProperty();
		label.setAlignment(Pos.TOP_CENTER);
		Platform.runLater(
				() -> {
					
					 addNewSale.getChildren().add(label);
				});
	}
}
