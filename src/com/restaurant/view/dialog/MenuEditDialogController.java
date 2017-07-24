package com.restaurant.view.dialog;

import com.restaurant.ConnectionDB;
import com.restaurant.model.MenuItem;
import com.restaurant.view.SystemTabPageController;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class MenuEditDialogController extends EditDialogController<MenuItem> {

	// menu
	@FXML
	private TextField menuId_field;
	@FXML
	private TextField menuName_field;
	@FXML
	private TextField menuQuantity_field;
	@FXML
	private TextField menuPrice_field;
	@FXML
	private TextField menuCode_field;
	@FXML
	private TextField menuInfo_field;
	@FXML
	private ComboBox<Integer> menuCategoryId_combo;
	@FXML
	private Button save_btn;
	@FXML
	private Button cancel_btn;

	@FXML
	private void initialize() {
		cancel_btn.setOnMouseClicked(e -> {
			getDialogStage().close();
		});

		save_btn.setOnMouseClicked(e -> {
			Integer menuId = Integer.parseInt(menuId_field.getText());
			String menuName = menuName_field.getText();
			Integer menuQuantity = Integer.parseInt(menuQuantity_field.getText());
			Double menuPrice = Double.parseDouble(menuPrice_field.getText());
			String menuCode = menuCode_field.getText();
			String menuInfo = menuInfo_field.getText();

			if (SystemTabPageController.Modify_Add_flag == 1) {
				if (ConnectionDB.ModifyMenuTable(menuId, menuId, menuName, menuQuantity, menuPrice, menuCode, menuInfo)) {
					final Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Message");
					alert.setContentText("Modify success");
					alert.showAndWait();
				} else {
					final Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Message");
					alert.setContentText("Modify fail");
					alert.showAndWait();
					return;
				}
				getDialogStage().close();
			} else {
				if (ConnectionDB.InsertMenuTable(menuId, menuName, menuQuantity, menuPrice, menuCode, menuInfo,1)) {
					final Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Message");
					alert.setContentText("insert success");
					alert.showAndWait();
				} else {
					final Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Message");
					alert.setContentText("insert fail");
					alert.showAndWait();
					return;
				}
				getDialogStage().close();
			}
			
			this.getMainApp().setMenuData(ConnectionDB.ConnectionMenu());
			this.getMainApp().getMainController().getSystemTabPageController()
					.getMenuTable().setItems(this.getMainApp().getMenuData());
		});
	}

	private MenuItem buildMenuItem() {
		return new MenuItem(Integer.parseInt(menuId_field.getText()), menuName_field.getText(),
				Integer.parseInt(menuQuantity_field.getText()), Double.parseDouble(menuPrice_field.getText()),
				menuCode_field.getText(), menuInfo_field.getText(),
				Integer.parseInt(menuCategoryId_combo.getValue().toString()));
	}

	@Override
	public void transferData(MenuItem data) {
		menuId_field.setText(data.getItemID().getValue().toString());
		menuName_field.setText(data.getItemName().getValue());
		menuPrice_field.setText(data.getItemPrice().getValue().toString());
		menuQuantity_field.setText(data.getItemQuantity().getValue().toString());
		menuCode_field.setText(data.getItemCode().getValue());
		menuInfo_field.setText(data.getItemInfo().getValue());
	}
}
