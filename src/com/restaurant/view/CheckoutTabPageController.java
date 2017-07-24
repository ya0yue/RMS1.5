package com.restaurant.view;

import java.time.LocalDate;
import java.time.LocalTime;

import com.restaurant.ConnectionDB;
import com.restaurant.MainApp;
import com.restaurant.model.Checkout;
import com.restaurant.model.MenuItem;
import com.restaurant.model.OrderItem;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class CheckoutTabPageController {
	
	private MainApp mainApp;

	@FXML
	private TableView<Checkout> checkoutTable;
	@FXML
	private TableColumn<Checkout, Number> checkoutID_col;
	@FXML
	private TableColumn<Checkout, Number> payment_col;
	@FXML
	private TableColumn<Checkout, Number> discount_col;
	@FXML
	private TableColumn<Checkout, Number> amount_col;
	@FXML
	private TableColumn<Checkout, String> time_col;
	@FXML
	private TableColumn<Checkout, String> date_col;
	@FXML
	private TableColumn<Checkout, String> info_col;

	@FXML
	private TextField tableNO_field;
	@FXML
	private TextField orderSum_field;
	@FXML
	private TextField pay_field;
	@FXML
	private TextField discount_field;
	@FXML
	private TextField info_field;
	@FXML
	private TextField change_field;
	@FXML
	private TextField orderid_field;
	@FXML
	private ComboBox<String> paymentType_cbox;
	@FXML
	private Button checkout_btn;

	private void setOrderInfoTableView() {
		checkoutID_col.setCellValueFactory(cellData -> cellData.getValue().getCheckoutID());
		payment_col.setCellValueFactory(cellData -> cellData.getValue().getPayment());
		discount_col.setCellValueFactory(cellData -> cellData.getValue().getDiscount());
		amount_col.setCellValueFactory(cellData -> cellData.getValue().getAmount());
		time_col.setCellValueFactory(cellData -> cellData.getValue().getCheckoutTime());
		date_col.setCellValueFactory(cellData -> cellData.getValue().getCheckoutDate());
		info_col.setCellValueFactory(cellData -> cellData.getValue().getCheckoutInfo());
	}
	
	private void setTableSelectionEvent(){
		
		checkoutTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			tableNO_field.setText("tableNO");
			orderSum_field.setText("need data model");
		});
	}
	
	private void setTableSearch() {
		tableNO_field.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
			if (!isNowFocused) {//input complete
				Double TotalAmount = 0.00;
				int tableno = Integer.parseInt(tableNO_field.getText());
				ConnectionDB.ConnectionBelong(0, tableno);
				mainApp.orderData = ConnectionDB.ConnectionOrder(
						ConnectionDB.ReturnBelongData_Rorderid, 0, "", "");
				if (mainApp.orderData.size() > 1) {
					final Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("warning!");
					alert.setContentText("more than one order");
					alert.showAndWait();
					return;
				}
				if (mainApp.orderData.size() == 1) {
					mainApp.containData = ConnectionDB.ConnectionContain(
							mainApp.orderData.get(0).getOrderID().getValue());
					ObservableList<MenuItem> menuData = ConnectionDB
							.ConnectionMenuItem(mainApp.containData); // I change this
																		// place
					// count and display the order item no "i"
					int i = 0;
					if (!menuData.isEmpty()) {
						while (i < menuData.size()) {
							double Amount = menuData.get(i).getItemPrice().doubleValue()
									* mainApp.containData.get(i).getQuantity();

							mainApp.getOrderItemData().add(new OrderItem(i + 1,
									menuData.get(i).getItemID().intValue(),
									menuData.get(i).getItemName().getValue(),
									mainApp.containData.get(i).getQuantity().intValue(),
									menuData.get(i).getItemPrice().doubleValue(),
									Amount));
							TotalAmount = TotalAmount + Amount;
							i++;
						}
					}
					mainApp.tableData = ConnectionDB.ConnectionTable(0);
					orderSum_field.setText(Double.toString(TotalAmount));
					orderid_field.setText(Integer.toString(mainApp.orderData.get(0).getOrderID().intValue()));
				}else{
				    final Alert alert = new Alert(AlertType.INFORMATION);
			    	alert.setTitle("Message");
			    	alert.setContentText("No available order");		    		
			    	alert.showAndWait();
			    	return;
				}

			}
		});
	}

	@FXML
	private void initialize() {
		setOrderInfoTableView();
		paymentType_cbox.getItems().addAll("cash","credit card");
		paymentType_cbox.setValue("cash");
		setChange();
		setTableSearch();
	}

	public TableView<Checkout> getCheckoutTable() {
		return checkoutTable;
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	public void CheckoutAction() {
    	mainApp.clearCheckoutData();
    	String info = null;
    	int tableno = Integer.parseInt(tableNO_field.getText());
    	int orderid = Integer.parseInt(orderid_field.getText());
    	Double ordersum =0.00;
    	Double pay =0.00;
    	Double discount =0.00;
    	Double change =0.00;
    	try{
       		discount = Double.valueOf(discount_field.getText()).doubleValue();
        	ordersum = Double.valueOf(orderSum_field.getText()).doubleValue();
        	pay = Double.valueOf(pay_field.getText()).doubleValue();
        	change = Double.valueOf(change_field.getText()).doubleValue();
        	info = info_field.getText();
			}catch(Exception ee)
			{
			    final Alert alert = new Alert(AlertType.INFORMATION);
		    	alert.setTitle("Message");
		    	alert.setContentText("input fail");		    		
		    	alert.showAndWait();
		    	return;
			}
    	
    	String paymentType = paymentType_cbox.getValue();
    	int paymentT = 0;
    	if (paymentType.equals("cash")){
    		paymentT = 0;
    	}else{
    		paymentT = 1;
    	}
    	
    	if (ConnectionDB.InsertCheckoutTable(ordersum,pay,paymentT,change,discount,LocalDate.now().toString(),LocalTime.now().toString(),info,
    			orderid,1)){
		    final Alert alert = new Alert(AlertType.INFORMATION);
	    	alert.setTitle("Message");
	    	alert.setContentText("insert success");	    		
	    	alert.showAndWait();
    	}else{
		    final Alert alert = new Alert(AlertType.INFORMATION);
	    	alert.setTitle("Message");
	    	alert.setContentText("insert fail");		    		
	    	alert.showAndWait();
    	}
    	mainApp.checkoutData = ConnectionDB.TableCheckout(tableno);
    	checkoutTable.setItems(mainApp.getCheckoutData());
	}
	
	private void setChange() {
		pay_field.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
			if (!isNowFocused) {//input complete
				double pay=0.00;
				double orderSum=0.00;
				double discount=0.00;
				try{
					pay=Double.valueOf(pay_field.getText()).doubleValue();
					orderSum=Double.valueOf(orderSum_field.getText()).doubleValue();
					discount=Double.valueOf(discount_field.getText()).doubleValue();
				}catch(Exception ee)
				{
				    final Alert alert = new Alert(AlertType.INFORMATION);
			    	alert.setTitle("Message");
			    	alert.setContentText("input fail");		    		
			    	alert.showAndWait();
			    	return;
				}
				Double Change = pay-orderSum+discount;
				change_field.setText(String .format("%.2f",Change));
			}
		});
	}
}
