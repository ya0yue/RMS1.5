package com.restaurant.view.dialog;

import com.restaurant.ConnectionDB;
import com.restaurant.model.Category;
import com.restaurant.view.SystemTabPageController;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class CategoryEditDialogController
		extends
			EditDialogController<Category> {

	// category
	@FXML
	private TextField categoryId_field;
	@FXML
	private TextField categoryName_field;
	@FXML
	private TextField categoryInfo_field;
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
			Integer categoryId = Integer.parseInt(categoryId_field.getText());
			String categoryName = categoryName_field.getText();
			String categoryInfo = categoryInfo_field.getText();

			if (SystemTabPageController.Modify_Add_flag == 1) {
				if (ConnectionDB.ModifyCategory(categoryId, categoryId, categoryName, categoryInfo)) {
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
				if (ConnectionDB.InsertCategory(categoryId, categoryName, categoryInfo)) {
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
			
			this.getMainApp().categoryData = ConnectionDB.ConnectionCategory();
			this.getMainApp().getMainController().getSystemTabPageController()
					.getCategoryTable()
					.setItems(this.getMainApp().getCategoryData());
		});
	}

	@Override
	public void transferData(Category data) {
		categoryId_field.setText(data.getCategoryID().getValue().toString());
		categoryName_field.setText(data.getCategoryName().getValue());
		categoryInfo_field.setText(data.getCategoryInfo().getValue());
	}

}
