package com.restaurant;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.restaurant.model.Belong;
import com.restaurant.model.Category;
import com.restaurant.model.Checkout;
import com.restaurant.model.Contain;
import com.restaurant.model.MenuItem;
import com.restaurant.model.MenuStat;
import com.restaurant.model.Order;
import com.restaurant.model.OrderItem;
import com.restaurant.model.Staff;
import com.restaurant.model.Table;
import com.restaurant.view.LoginViewController;
import com.restaurant.view.MainViewController;
import com.restaurant.view.OrderTabPageController;

public class MainApp extends Application {

	private Stage primaryStage;

	private Scene loginScene;
	private Scene mainScene;

	private LoginViewController loginController;
	private MainViewController mainController;

	// order tab
	public ObservableList<OrderItem> orderItemData = FXCollections.observableArrayList();
	public ObservableList<Table> tableData = FXCollections.observableArrayList();
	public ObservableList<Order> orderData = FXCollections.observableArrayList();

	// checkout tab
	public ObservableList<Checkout> checkoutData = FXCollections.observableArrayList();

	// report tab
	private ObservableList<Checkout> reportCheckoutData = FXCollections.observableArrayList();
	public ObservableList<MenuStat> menuStatData = FXCollections.observableArrayList();

	// system tab
	public ObservableList<Staff> staffData = FXCollections.observableArrayList();
	public ObservableList<Category> categoryData = FXCollections.observableArrayList();
	private ObservableList<Table> sysTableData = FXCollections.observableArrayList();
	private ObservableList<MenuItem> menuData = FXCollections.observableArrayList();

	// mediate tab
	public ObservableList<Belong> belongData = FXCollections.observableArrayList();
	public ObservableList<Contain> containData = FXCollections.observableArrayList();

	// indicate the user role
	private List<Integer> userAuthorityList;
	public Text dateTime_Text;
	public String language = Locale.getDefault().getLanguage();
	public String country = Locale.getDefault().getCountry();
	public Locale currentLocale = new Locale(language, country);
	/**
	 * Constructor
	 */
	public MainApp() {
		// for test
//		menuStatData.add(new MenuStat(1, "b", 2));
		categoryData.add(new Category(1, "b", "c"));
	}

	public Scene getMainScene() {
		return mainScene;
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public ObservableList<OrderItem> getOrderItemData() {
		return orderItemData;
	}

	public void setOrderItemData(OrderItem p) {
		orderItemData.add(p);
	}

	public ObservableList<Table> getTableData() {
		return tableData;
	}

	public ObservableList<Order> getOrderData() {
		return orderData;
	}

	public ObservableList<Checkout> getCheckoutData() {
		return checkoutData;
	}

	public ObservableList<Checkout> getReportCheckoutData() {
		return reportCheckoutData;
	}

	public ObservableList<MenuStat> getMenuStatData() {
		return menuStatData;
	}

	public ObservableList<Staff> getStaffData() {
		return staffData;
	}

	public ObservableList<Category> getCategoryData() {
		return categoryData;
	}

	public ObservableList<Table> getSysTableData() {
		return sysTableData;
	}

	public ObservableList<MenuItem> getMenuData() {
		return menuData;
	}

	public void clearTableData() {
		tableData.clear();
	}

	public void clearStaffData() {
		staffData.clear();
	}

	public void clearOrderData() {
		orderData.clear();
	}

	public void clearCheckoutData() {
		checkoutData.clear();
	}

	public void clearMenuData() {
		menuData.clear();
	}

	public void clearCategoryData() {
		categoryData.clear();
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Restaurant Management System");

		// load the fxml
		setLoginView();
		setMainView();

		// Show the scene containing the login view.
		primaryStage.setScene(loginScene);
		primaryStage.show();
	}

	public void setLoginView() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("view/Login.fxml"));
			Pane loginView = (Pane) loader.load();
			loginController = loader.getController();
			loginController.setMainApp(this);
			this.loginScene = new Scene(loginView);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setMainView() {
		try {
			// Load search view.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("view/MainInterface.fxml"));
			AnchorPane mainView = (AnchorPane) loader.load();

			// Give the controller access to the main app.
			mainController = loader.getController();
			mainController.setMainApp(this);
			this.mainScene = new Scene(mainView);

			mainController.getOrderTabPageController().setMainApp(this);
			mainController.getCheckoutTabPageController().setMainApp(this);
			mainController.getReportTabPageController().setMainApp(this);
			mainController.getSystemTabPageController().setMainApp(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loginSuccess() {
		// load test data

		mainController.getCheckoutTabPageController().getCheckoutTable().setItems(checkoutData);
		mainController.getReportTabPageController().getOrderReportTable().setItems(reportCheckoutData);
		mainController.getReportTabPageController().getMenuReportTable().setItems(menuStatData);
		mainController.getSystemTabPageController().getCategoryTable().setItems(categoryData);
		mainController.getSystemTabPageController().getStaffTable().setItems(staffData);
		mainController.getSystemTabPageController().getTableTable().setItems(sysTableData);

		menuData = ConnectionDB.ConnectionMenu();

		//tableData = ConnectionDB.ConnectionTable(0);
		//mainController.getOrderTabPageController().getTableInfoTable().setItems(tableData);

		// set component visibility according to the user authorities
		for (Tab tab : mainController.getTabPane().getTabs()) {
			// 1 - waiter
			// 2 - cashier
			// 3 - finance
			// 4 - manager
			// 5 - ceo
			if (tab.getText().equals("Order")) {
				if (userAuthorityList.contains(1) || userAuthorityList.contains(4)) {
					tab.setDisable(false);
				}
			} else if (tab.getText().equals("Checkout")) {
				if (userAuthorityList.contains(2) || userAuthorityList.contains(4)) {
					tab.setDisable(false);
				}
			} else if (tab.getText().equals("Report")) {
				if (userAuthorityList.contains(3) || userAuthorityList.contains(4)) {
					tab.setDisable(false);
				}
			} else if (tab.getText().equals("System")) {
				if (userAuthorityList.contains(5) || userAuthorityList.contains(4)) {
					tab.setDisable(false);
				}
			}
		}

		primaryStage.setScene(mainScene);
	}

	public static void main(String[] args) {
		launch(args);
	}

	public List<Integer> getUserAuthorityList() {
		return userAuthorityList;
	}

	public void setUserAuthorityList(List<Integer> userAuthorityList) {
		this.userAuthorityList = userAuthorityList;
	}
    public void setTime() {
    	dateTime_Text.setText(getLocaleTime(currentLocale));
    }
    public String getLocaleTime(Locale locale) {
    	DateFormat f = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.SHORT, locale);
    	return f.format(new Date());
    }

	public void setMenuData(ObservableList<MenuItem> menuData) {
		this.menuData = menuData;
	}

	public MainViewController getMainController() {
		return mainController;
	}
}
