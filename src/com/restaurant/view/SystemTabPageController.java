package com.restaurant.view;

import java.io.IOException;

import com.restaurant.ConnectionDB;
import com.restaurant.MainApp;
import com.restaurant.model.Category;
import com.restaurant.model.MenuItem;
import com.restaurant.model.Staff;
import com.restaurant.model.Table;
import com.restaurant.view.dialog.EditDialogController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SystemTabPageController {

	private MainApp mainApp;

	@FXML
	private ToggleButton staff_btn;
	@FXML
	private ToggleButton table_btn;
	@FXML
	private ToggleButton category_btn;
	@FXML
	private ToggleButton menu_btn;
	@FXML
	private Button add_btn;
	@FXML
	private Button edit_btn;
	@FXML
	private Button delete_btn;

	// Staff
	@FXML
	public TableView<Staff> staffTable;
	@FXML
	private TableColumn<Staff, Number> staffID_col;
	@FXML
	private TableColumn<Staff, String> staffName_col;
	@FXML
	private TableColumn<Staff, Number> staffAge_col;
	@FXML
	private TableColumn<Staff, Number> staffContact_col;
	@FXML
	private TableColumn<Staff, String> staffRole_col;
	@FXML
	private TableColumn<Staff, Number> staffAccountNO_col;
	@FXML
	private TableColumn<Staff, Number> staffAuthority_col;

	// Category
	@FXML
	private TableView<Category> categoryTable;
	@FXML
	private TableColumn<Category, Number> categoryID_col;
	@FXML
	private TableColumn<Category, String> categoryName_col;
	@FXML
	private TableColumn<Category, String> cegegoryInfo_col;

	// Table
	@FXML
	private TableView<Table> tableTable;
	@FXML
	private TableColumn<Table, Number> tableID_col;
	@FXML
	private TableColumn<Table, Number> tableNO_col;
	@FXML
	private TableColumn<Table, String> tableStatus_col;
	@FXML
	private TableColumn<Table, String> tableInfo_col;

	// Menu
	@FXML
	private TableView<MenuItem> menuTable;
	@FXML
	private TableColumn<MenuItem, Number> menuID_col;
	@FXML
	private TableColumn<MenuItem, String> menuName_col;
	@FXML
	private TableColumn<MenuItem, Number> menuQuantity_col;
	@FXML
	private TableColumn<MenuItem, Number> menuPrice_col;
	@FXML
	private TableColumn<MenuItem, String> menuCode_col;
	@FXML
	private TableColumn<MenuItem, String> menuInfo_col;

	private int table_signal = 3;// 0-menu; 1-category; 2-table; 3-staff
	public static int Modify_Add_flag = 0;// 1-Modify; 2-Add

	private void setStaffTableView() {
		staffID_col.setCellValueFactory(cellData -> cellData.getValue().getStaffID());
		staffName_col.setCellValueFactory(cellData -> cellData.getValue().getStaffName());
		staffAge_col.setCellValueFactory(cellData -> cellData.getValue().getAge());
		staffContact_col.setCellValueFactory(cellData -> cellData.getValue().getContactNumber());
		staffRole_col.setCellValueFactory(cellData -> cellData.getValue().getRole());
		staffAccountNO_col.setCellValueFactory(cellData -> cellData.getValue().getAccountNo());
		staffAuthority_col.setCellValueFactory(cellData -> cellData.getValue().getAuthority());
	}

	private void setCategoryTableView() {
		categoryID_col.setCellValueFactory(cellData -> cellData.getValue().getCategoryID());
		categoryName_col.setCellValueFactory(cellData -> cellData.getValue().getCategoryName());
		cegegoryInfo_col.setCellValueFactory(cellData -> cellData.getValue().getCategoryInfo());
	}

	private void setTableTableView() {
		tableID_col.setCellValueFactory(cellData -> cellData.getValue().getTableID());
		tableNO_col.setCellValueFactory(cellData -> cellData.getValue().getTableNo());
		tableStatus_col.setCellValueFactory(cellData -> cellData.getValue().getTableStatus());
		tableInfo_col.setCellValueFactory(cellData -> cellData.getValue().getTableInfo());
	}

	private void setMenuTableView() {
		menuID_col.setCellValueFactory(cellData -> cellData.getValue().getItemID());
		menuName_col.setCellValueFactory(cellData -> cellData.getValue().getItemName());
		menuQuantity_col.setCellValueFactory(cellData -> cellData.getValue().getItemQuantity());
		menuPrice_col.setCellValueFactory(cellData -> cellData.getValue().getItemPrice());
		menuCode_col.setCellValueFactory(cellData -> cellData.getValue().getItemCode());
		menuInfo_col.setCellValueFactory(cellData -> cellData.getValue().getItemInfo());
	}

	/**
	 * show staff at first time
	 */
	private void setInitialViewVisibility() {
		tableTable.setVisible(false);
		categoryTable.setVisible(false);
		menuTable.setVisible(false);
		staffTable.setVisible(true);
		staff_btn.setSelected(true);
		staff_btn.setDisable(true);
	}

	private void clickToggleButton(ToggleButton clickedBtn) {
		if (clickedBtn.getText().equals("Staff")) {
			if (!staffTable.isVisible()) {
				categoryTable.setVisible(false);
				tableTable.setVisible(false);
				menuTable.setVisible(false);
				staffTable.setVisible(true);

				staff_btn.setSelected(true);
				staff_btn.setDisable(true);
				if (table_btn.isSelected()) {
					table_btn.setSelected(false);
					table_btn.setDisable(false);
				}
				if (category_btn.isSelected()) {
					category_btn.setSelected(false);
					category_btn.setDisable(false);
				}
				if (menu_btn.isSelected()) {
					menu_btn.setSelected(false);
					menu_btn.setDisable(false);
				}
				table_signal = 3;
				showTableData();
			}
		} else if (clickedBtn.getText().equals("Table")) {
			if (!tableTable.isVisible()) {
				categoryTable.setVisible(false);
				staffTable.setVisible(false);
				menuTable.setVisible(false);
				tableTable.setVisible(true);

				table_btn.setSelected(true);
				table_btn.setDisable(true);
				if (staff_btn.isSelected()) {
					staff_btn.setSelected(false);
					staff_btn.setDisable(false);
				}
				if (category_btn.isSelected()) {
					category_btn.setSelected(false);
					category_btn.setDisable(false);
				}
				if (menu_btn.isSelected()) {
					menu_btn.setSelected(false);
					menu_btn.setDisable(false);
				}
				table_signal = 2;
				showTableData();
			}
		} else if (clickedBtn.getText().equals("Category")) {
			if (!categoryTable.isVisible()) {
				staffTable.setVisible(false);
				tableTable.setVisible(false);
				menuTable.setVisible(false);
				categoryTable.setVisible(true);

				category_btn.setSelected(true);
				category_btn.setDisable(true);
				if (table_btn.isSelected()) {
					table_btn.setSelected(false);
					table_btn.setDisable(false);
				}
				if (staff_btn.isSelected()) {
					staff_btn.setSelected(false);
					staff_btn.setDisable(false);
				}
				if (menu_btn.isSelected()) {
					menu_btn.setSelected(false);
					menu_btn.setDisable(false);
				}
				table_signal = 1;
				showTableData();
			}
		} else if (clickedBtn.getText().equals("Menu")) {
			if (!menuTable.isVisible()) {
				categoryTable.setVisible(false);
				tableTable.setVisible(false);
				staffTable.setVisible(false);
				menuTable.setVisible(true);

				menu_btn.setSelected(true);
				menu_btn.setDisable(true);
				if (table_btn.isSelected()) {
					table_btn.setSelected(false);
					table_btn.setDisable(false);
				}
				if (category_btn.isSelected()) {
					category_btn.setSelected(false);
					category_btn.setDisable(false);
				}
				if (staff_btn.isSelected()) {
					staff_btn.setSelected(false);
					staff_btn.setDisable(false);
				}
				table_signal = 0;
				showTableData();
			}
		}
	}

	private void setButtonEvent() {
		staff_btn.setOnMouseClicked(e -> {
			clickToggleButton(staff_btn);
		});

		table_btn.setOnMouseClicked(e -> {
			clickToggleButton(table_btn);
		});

		category_btn.setOnMouseClicked(e -> {
			clickToggleButton(category_btn);
		});

		menu_btn.setOnMouseClicked(e -> {
			clickToggleButton(menu_btn);
		});

		add_btn.setOnMouseClicked(e -> {
			addAction();
		});

		edit_btn.setOnMouseClicked(e -> {
			editAction();
		});
		delete_btn.setOnMouseClicked(e -> {
			deleteAction();
		});

	}

	@FXML
	private void initialize() {
		setStaffTableView();
		setCategoryTableView();
		setTableTableView();
		setMenuTableView();

		setInitialViewVisibility();
		setButtonEvent();
	}

	public TableView<Staff> getStaffTable() {
		return staffTable;
	}

	public TableView<Category> getCategoryTable() {
		return categoryTable;
	}

	public TableView<Table> getTableTable() {
		return tableTable;
	}

	public TableView<MenuItem> getMenuTable() {
		return menuTable;
	}

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	public void showTableData() {
		if (table_signal == 0) {// menu
			mainApp.clearMenuData();
			mainApp.setMenuData(ConnectionDB.ConnectionMenu());
 			menuTable.setItems(mainApp.getMenuData());
		} else if (table_signal == 1) {// category
			mainApp.clearCategoryData();
			mainApp.categoryData = ConnectionDB.ConnectionCategory();
			categoryTable.setItems(mainApp.getCategoryData());
		} else if (table_signal == 2) {// table
			mainApp.clearTableData();
			mainApp.tableData = ConnectionDB.ConnectionTable(0);
			tableTable.setItems(mainApp.getTableData());
		} else if (table_signal == 3) {// staff
			mainApp.clearStaffData();
			mainApp.staffData = ConnectionDB.ConnectionStaff(0);
			staffTable.setItems(mainApp.getStaffData());
		}
	}

	private void addAction() {
		Modify_Add_flag = 2;
		if (table_signal == 0) {// menu
			openEditDialog();
		}
		if (table_signal == 1) {// category
			openEditDialog();
		}
		if (table_signal == 2) {// table
			openEditDialog();
		}
		if (table_signal == 3) {// staff
			openEditDialog();
		}
	}

	private void editAction() {
		Modify_Add_flag = 1;
		if (table_signal == 0) {// menu
			openEditDialog();
		} else if (table_signal == 1) {// category
			openEditDialog();
		} else if (table_signal == 2) {// table
			openEditDialog();
		} else if (table_signal == 3) {// staff
			openEditDialog();
		}
	}

	private void deleteAction() {
		if (table_signal == 0) {// menu
			MenuItem selected = menuTable.getSelectionModel().getSelectedItem();
			if (selected == null) {
				noSelectionAlert();
			}

			Integer ItemID = menuTable.getSelectionModel().getSelectedItem().getItemID().intValue();
			if (ConnectionDB.DeleteMenu(ItemID)) {
				final Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Message");
				alert.setContentText("delete success");
				alert.showAndWait();
			} else {
				final Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Message");
				alert.setContentText("delete fail");
				alert.showAndWait();
				return;
			}
			mainApp.getMenuData().remove(selected);
			menuTable.refresh();
		} else if (table_signal == 1) {// category
			Category selected = categoryTable.getSelectionModel().getSelectedItem();
			if (selected == null) {
				noSelectionAlert();
			}
			
			Integer CategoryID = categoryTable.getSelectionModel().getSelectedItem().getCategoryID().intValue();
			if (ConnectionDB.DeleteCategory(CategoryID)) {
				final Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Message");
				alert.setContentText("delete success");
				alert.showAndWait();
			} else {
				final Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Message");
				alert.setContentText("delete fail");
				alert.showAndWait();
				return;
			}
			mainApp.getCategoryData().remove(selected);
			categoryTable.refresh();
		} else if (table_signal == 2) {// table
			Table selected = tableTable.getSelectionModel().getSelectedItem();
			if (selected == null) {
				noSelectionAlert();
			}

			Integer TableID = tableTable.getSelectionModel().getSelectedItem().getTableID().intValue();
			if (ConnectionDB.DeleteTable(TableID)) {
				final Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Message");
				alert.setContentText("delete success");
				alert.showAndWait();
			} else {
				final Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Message");
				alert.setContentText("delete fail");
				alert.showAndWait();
				return;
			}
			mainApp.getTableData().remove(selected);
			tableTable.refresh();
		} else if (table_signal == 3) {// staff
			Staff selected = staffTable.getSelectionModel().getSelectedItem();
			if (selected == null) {
				noSelectionAlert();
			}
			
			Integer StaffID = staffTable.getSelectionModel().getSelectedItem().getStaffID().intValue();
			if (ConnectionDB.DeleteStaff(StaffID)) {
				final Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Message");
				alert.setContentText("delete success");
				alert.showAndWait();
			} else {
				final Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Message");
				alert.setContentText("delete fail");
				alert.showAndWait();
				return;
			}
			mainApp.getStaffData().remove(selected);
			staffTable.refresh();
		}
	}

	private void openEditDialog() {
		try {
			FXMLLoader loader = new FXMLLoader();
			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(mainApp.getPrimaryStage());

			if (table_signal == 0) {// menu
				MenuItem selected=menuTable.getSelectionModel().getSelectedItem();
				if (Modify_Add_flag == 1){
					if(selected==null){
						noSelectionAlert();
						return;
					}
				}
				dialogStage.setTitle("Edit Menu");
				loader.setLocation(getClass().getResource("dialog/SystemTab_EditMenu.fxml"));
				Pane page = (Pane) loader.load();
				Scene scene = new Scene(page);
				dialogStage.setScene(scene);
				EditDialogController<MenuItem> controller = loader.getController();
				controller.setMainApp(mainApp);
				controller.setDialogStage(dialogStage);
				if (Modify_Add_flag == 1){
					controller.transferData(selected);
				}				
			} else if (table_signal == 1) {// category
				Category selected=categoryTable.getSelectionModel().getSelectedItem();
				if(selected==null && Modify_Add_flag == 1){
					noSelectionAlert();
					return;
				}
				dialogStage.setTitle("Edit Category");
				loader.setLocation(getClass().getResource("dialog/SystemTab_EditCategory.fxml"));
				Pane page = (Pane) loader.load();
				Scene scene = new Scene(page);
				dialogStage.setScene(scene);
				EditDialogController<Category> controller = loader.getController();
				controller.setDialogStage(dialogStage);
				controller.setMainApp(mainApp);
				if (Modify_Add_flag == 1){
					controller.transferData(selected);
				}				
			} else if (table_signal == 2) {// table
				Table selected=tableTable.getSelectionModel().getSelectedItem();
				if(selected==null && Modify_Add_flag == 1){
					noSelectionAlert();
					return;
				}
				dialogStage.setTitle("Edit Table");
				loader.setLocation(getClass().getResource("dialog/SystemTab_EditTable.fxml"));
				Pane page = (Pane) loader.load();
				Scene scene = new Scene(page);
				dialogStage.setScene(scene);
				EditDialogController<Table> controller = loader.getController();
				controller.setDialogStage(dialogStage);
				controller.setMainApp(mainApp);
				if (Modify_Add_flag == 1){
					controller.transferData(selected);	
				}				
			} else if (table_signal == 3) {// staff
				Staff selected=staffTable.getSelectionModel().getSelectedItem();
				if (Modify_Add_flag == 1){
					if(selected==null){
						noSelectionAlert();
						return;
					}
				}
				dialogStage.setTitle("Edit Staff");
				loader.setLocation(getClass().getResource("dialog/SystemTab_EditStaff.fxml"));
				Pane page = (Pane) loader.load();
				Scene scene = new Scene(page);
				dialogStage.setScene(scene);
				EditDialogController<Staff> controller = loader.getController();
				controller.setDialogStage(dialogStage);
				controller.setMainApp(mainApp);
				if (Modify_Add_flag == 1){
					controller.transferData(selected);	
				}
			}
			dialogStage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void noSelectionAlert(){
		Alert a = new Alert(AlertType.INFORMATION);
		a.setTitle("Message");
		a.setHeaderText(null);
		a.setContentText("You have not select a row. Please select one of the records.");
		a.showAndWait();
	}
}
