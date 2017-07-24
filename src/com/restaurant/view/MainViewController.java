package com.restaurant.view;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import com.restaurant.ConnectionDB;
import com.restaurant.MainApp;

public class MainViewController {

	private MainApp mainApp;

	@FXML
	private TabPane tabPane;
	@FXML
	private Tab orderTab;
	@FXML
	private OrderTabPageController orderTabPageController;
	@FXML
	private Tab checkoutTab;
	@FXML
	private CheckoutTabPageController checkoutTabPageController;
	@FXML
	private Tab reportTab;
	@FXML
	private ReportTabPageController reportTabPageController;
	@FXML
	private Tab systemTab;
	@FXML
	private SystemTabPageController systemTabPageController;
	@FXML
	private Button exit_btn;

	/**
	 * The constructor. The constructor is called before the initialize()
	 * method.
	 */
	public MainViewController() {
		
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
		setTabAction();
		exit_btn.setOnMouseClicked(e -> {
			Platform.exit();
		});
	}

	private void setTabAction() {
		// every time the tab is activated, refresh the data
		// only the first time you need to call .setItems()
		tabPane.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
			if (isNowFocused) {//input complete
				tabPane.getSelectionModel().selectedItemProperty().addListener((ov, oldTab, newTab) -> {
					if (newTab.getText().equals("Order") ) {
						mainApp.clearTableData();
						mainApp.clearOrderData();
						mainApp.tableData = ConnectionDB.ConnectionTable(0);
						orderTabPageController.getTableInfoTable().setItems(mainApp.getTableData());
					} else if (newTab.getText().equals("Checkout")) {
						//checkoutTabPageController.getCheckoutTable().setItems(mainApp.getCheckoutData());
					} else if (newTab.getText().equals("Report")) {
						//reportTabPageController.getOrderReportTable().setItems(mainApp.getReportCheckoutData());
						//reportTabPageController.getMenuReportTable().setItems(mainApp.getMenuStatData());
					} else if (newTab.getText().equals("System")) {
						//systemTabPageController.getCategoryTable().setItems(mainApp.getCategoryData());
						//systemTabPageController.getMenuTable().setItems(mainApp.getMenuData());
						//systemTabPageController.getStaffTable().setItems(mainApp.getStaffData());
						//systemTabPageController.getTableTable().setItems(mainApp.getTableData());
					}
				});
			}
		});
		
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	public OrderTabPageController getOrderTabPageController() {
		return orderTabPageController;
	}

	public CheckoutTabPageController getCheckoutTabPageController() {
		return checkoutTabPageController;
	}

	public ReportTabPageController getReportTabPageController() {
		return reportTabPageController;
	}

	public SystemTabPageController getSystemTabPageController() {
		return systemTabPageController;
	}

	public TabPane getTabPane() {
		return tabPane;
	}

}
