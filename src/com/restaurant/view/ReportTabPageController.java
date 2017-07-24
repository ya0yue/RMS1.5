package com.restaurant.view;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.restaurant.ConnectionDB;
import com.restaurant.MainApp;
import com.restaurant.model.Checkout;
import com.restaurant.model.MenuStat;
import com.restaurant.model.OrderItem;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class ReportTabPageController {
	
	private MainApp mainApp;

	@FXML
	private TableView<Checkout> orderReportTable;
	@FXML
	private TableColumn<Checkout, Number> checkoutID_col;
	@FXML
	private TableColumn<Checkout, Number> amount_col;
	@FXML
	private TableColumn<Checkout, Number> discount_col;
	@FXML
	private TableColumn<Checkout, Number> paymentType_col;
	@FXML
	private TableColumn<Checkout, String> time_col;
	@FXML
	private TableColumn<Checkout, String> date_col;
	@FXML
	private TableColumn<Checkout, String> info_col;
	@FXML
	private TableColumn<Checkout, Number> payment_col;
	@FXML
	private TableColumn<Checkout, Number> change_col;
	
	@FXML
	private TableView<MenuStat> menuReportTable;
	@FXML
	private TableColumn<MenuStat, Number> menuID_col;
	@FXML
	private TableColumn<MenuStat, String> menuName_col;
	@FXML
	private TableColumn<MenuStat, Number> menuQuantity_col;
	@FXML
	private TableColumn<MenuStat, Number> menuIncome_col;
	@FXML
	private TableColumn<MenuStat, Number> menuOrderID_col;
	
	@FXML
	private TextField billNum_field;
	@FXML
	private TextField totalIncome_field;
	@FXML
	private DatePicker to_date;
	@FXML
	private DatePicker from_date;
	@FXML
	private Button search_btn;
	private int SelectMenuID;
	
	private void setMenuReportTableView() {
		menuID_col.setCellValueFactory(cellData -> cellData.getValue().getMenuID());
		menuName_col.setCellValueFactory(cellData -> cellData.getValue().getMenuName());
		menuQuantity_col.setCellValueFactory(cellData -> cellData.getValue().getQuantity());
		menuIncome_col.setCellValueFactory(cellData -> cellData.getValue().getIncome());
		menuOrderID_col.setCellValueFactory(cellData -> cellData.getValue().getOrderID());
	}
	
	private void setOrderReportTableView(){
		checkoutID_col.setCellValueFactory(cellData -> cellData.getValue().getCheckoutID());
		amount_col.setCellValueFactory(cellData -> cellData.getValue().getPayment());
		payment_col.setCellValueFactory(cellData -> cellData.getValue().getPayment());
		paymentType_col.setCellValueFactory(cellData -> cellData.getValue().getPaymentType());
		change_col.setCellValueFactory(cellData -> cellData.getValue().getChangeMoney());
		discount_col.setCellValueFactory(cellData -> cellData.getValue().getDiscount());
		date_col.setCellValueFactory(cellData -> cellData.getValue().getCheckoutDate());
		time_col.setCellValueFactory(cellData -> cellData.getValue().getCheckoutTime());
		info_col.setCellValueFactory(cellData -> cellData.getValue().getCheckoutInfo());
	}
	
	public void setDefaultDate() {
		from_date.setValue(LocalDate.now().minusDays(30));
		to_date.setValue(LocalDate.now());
	}

	
	private void setButtonEvent(){
		search_btn.setOnMouseClicked(e -> {
			Double totalincome = 0.00;
			int totalorderno = 0;
			int x = 0; //counter
			int y = 0; //counter
			if (null != from_date.getValue().toString() && null != to_date.getValue().toString()){
				mainApp.menuStatData = ConnectionDB.ConnectionReportMenu(from_date.getValue().toString(),to_date.getValue().toString());
				menuReportTable.setItems(mainApp.getMenuStatData());
				while(x<mainApp.menuStatData.size()){
					totalincome = totalincome + mainApp.menuStatData.get(x).getIncome().doubleValue();
					x++;
				}
				while(y<mainApp.menuStatData.size()){
					totalorderno = totalorderno + mainApp.menuStatData.get(y).getOrderID().intValue();
					y++;
				}
				
				mainApp.checkoutData = ConnectionDB.ReportCheckout(from_date.getValue().toString(),to_date.getValue().toString());
				orderReportTable.setItems(mainApp.getCheckoutData());
			}
			billNum_field.setText(Integer.toString(totalorderno));
			totalIncome_field.setText(Double.toString(totalincome));;
		});
	}
	
//	private void setTableSelectionEvent() {
//		menuReportTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
//			// auto show order details in the left side
//			mainApp.getReportCheckoutData().clear();
//			SelectMenuID = newSelection.getMenuID().getValue();
//			mainApp.checkoutData = ConnectionDB.ReportCheckout(SelectMenuID);
//
//			orderReportTable.setItems(mainApp.getCheckoutData());
//		});
//	}
	
	@FXML
	private void initialize(){
		setMenuReportTableView();
		setOrderReportTableView();
		setDefaultDate();
		setButtonEvent();
//		setTableSelectionEvent();
	}

	public TableView<Checkout> getOrderReportTable() {
		return orderReportTable;
	}

	public TableView<MenuStat> getMenuReportTable() {
		return menuReportTable;
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
}
