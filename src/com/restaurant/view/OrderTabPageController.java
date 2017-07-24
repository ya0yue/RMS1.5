package com.restaurant.view;

import com.restaurant.ConnectionDB;
import com.restaurant.MainApp;
import com.restaurant.model.MenuItem;
import com.restaurant.model.OrderItem;
import com.restaurant.model.Table;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class OrderTabPageController {

	private MainApp mainApp;

	private OrderTabPageController ordertabpageController;
	private Scene orderScene;

	// left part (order) of the view
	@FXML
	private TableView<OrderItem> orderInfoTable;
	@FXML
	private TableColumn<OrderItem, Number> no_col;
	@FXML
	private TableColumn<OrderItem, Number> menuID_col;
	@FXML
	private TableColumn<OrderItem, String> name_col;
	@FXML
	private TableColumn<OrderItem, Number> quantity_col;
	@FXML
	private TableColumn<OrderItem, Number> price_col;
	@FXML
	private TableColumn<OrderItem, Number> amount_col;

	// right part (table) of the view
	@FXML
	private TableView<Table> tableInfoTable;
	@FXML
	private TableColumn<Table, Number> tableNO_col;
	@FXML
	private TableColumn<Table, String> status_col;

	// bottom of this view
	@FXML
	private TextField tableNO_field;
	@FXML
	private TextField menuCode_field;
	@FXML
	private TextField menuItemQuantity_field;
	@FXML
	private TextField orderItemSum_field;
	@FXML
	private TextField menuName_field;
	@FXML
	private TextField orderid_field;
	@FXML
	private Button addOrderItem_btn;

	private int SelectTableNo;

	/**
	 * map data to table view
	 */
	private void setTableInfoTableView() {
		tableNO_col.setCellValueFactory(
				cellData -> cellData.getValue().getTableNo());
		status_col.setCellValueFactory(
				cellData -> cellData.getValue().getTableStatus());
	}

	/**
	 * map data to order table
	 */
	private void setOrderInfoTableView() {
		no_col.setCellValueFactory(
				cellData -> cellData.getValue().getSequenID());
		menuID_col.setCellValueFactory(
				cellData -> cellData.getValue().getMenuID());
		name_col.setCellValueFactory(
				cellData -> cellData.getValue().getMenuName());
		quantity_col.setCellValueFactory(
				cellData -> cellData.getValue().getQuantity());
		price_col.setCellValueFactory(
				cellData -> cellData.getValue().getPrice());
		amount_col.setCellValueFactory(
				cellData -> cellData.getValue().getAmount());
	}

	private void setTableSelectionEvent() {
		tableInfoTable.getSelectionModel().selectedItemProperty().addListener((obs1, oldSelection, newSelection) -> {
			refreshAndKeepSelection(
					newSelection.getTableNo().getValue());
		});
	}

	// if you want to refresh both table and order in the OrderTab, and keep the
	// table selected status, use this
	private void refreshAndKeepSelection(Integer selectTableNo) {
		// clear previous data
		tableNO_field.clear();
		menuCode_field.clear();
		menuItemQuantity_field.clear();
		orderItemSum_field.clear();
		menuName_field.clear();

		mainApp.getOrderItemData().clear();
		SelectTableNo = selectTableNo;
		ConnectionDB.ConnectionBelong(0, SelectTableNo);
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
			Double TotalAmount = 0.00;
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
			tableInfoTable.setItems(mainApp.tableData);
			orderInfoTable.setItems(mainApp.getOrderItemData());
			orderItemSum_field.setText(Double.toString(TotalAmount));
		}
		tableNO_field.setText(Integer.toString(SelectTableNo));
	}

	private void setMenuCodeSearch() {
		System.out.println("key typed invoke");
		menuCode_field.textProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue.isEmpty()) {
				menuName_field.clear();
				return;
			}
			for (MenuItem item : mainApp.getMenuData()) {
				if (item.getItemCode().getValue().toLowerCase()
						.indexOf(newValue.toLowerCase()) == 0) {
					System.out.println(item.getItemCode().getValue());
					menuName_field.setText(item.getItemName().getValue());
					return;
				}
			}
			menuName_field.clear();
		});
	}

	@FXML
	private void initialize() {
		setTableInfoTableView();
		setTableSelectionEvent();
		setOrderInfoTableView();

		setMenuCodeSearch();
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	public TableView<OrderItem> getOrderInfoTable() {
		return orderInfoTable;
	}

	public TableView<Table> getTableInfoTable() {
		return tableInfoTable;
	}

	@FXML
	private void InsertOrderAction() {
		// test
		String ItemCode = menuCode_field.getText();
		String ItemName = menuName_field.getText();
		Integer ItemQuantity = 0;
		if (menuItemQuantity_field.getText().equals("")) {
			final Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Message");
			alert.setContentText("input fail");
			alert.showAndWait();
			return;
		} else {
			ItemQuantity = Integer.parseInt(menuItemQuantity_field.getText());
		}

		System.out.println("selected table no is " + SelectTableNo);
		if (ConnectionDB.InsertOrderTable(SelectTableNo, ItemCode, ItemName,
				ItemQuantity)) {
			final Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Message");
			alert.setContentText("insert success");
			alert.showAndWait();
		} else {
			final Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Message");
			alert.setContentText("insert fail");
			alert.showAndWait();
		}
		refreshAndKeepSelection(SelectTableNo);
	}
}
