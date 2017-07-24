package com.restaurant.view.dialog;

import com.restaurant.ConnectionDB;
import com.restaurant.model.Table;
import com.restaurant.view.SystemTabPageController;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class TableEditDialogController extends EditDialogController<Table> {

	// table
	@FXML
	private TextField tableId_field;
	@FXML
	private TextField tableNo_field;
	@FXML
	private TextField tableStatus_field;
	@FXML
	private TextField tableInfo_field;
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
			Integer tableId = Integer.parseInt(tableId_field.getText());
			Integer tableNo = Integer.parseInt(tableNo_field.getText());
			String tableStatus = tableStatus_field.getText();
			String tableInfo = tableInfo_field.getText();

			if (SystemTabPageController.Modify_Add_flag == 1) {
				if (ConnectionDB.ModifyTable(tableId, tableId, tableStatus,tableInfo,tableNo)) {
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
				if (ConnectionDB.InsertTable(tableId, tableStatus,tableInfo,tableNo)) {
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
			
			this.getMainApp().tableData=ConnectionDB.ConnectionTable(0);
			this.getMainApp().getMainController().getSystemTabPageController()
					.getTableTable().setItems(this.getMainApp().getSysTableData());
		});
	}

	@Override
	public void transferData(Table data) {
		tableId_field.setText(data.getTableID().getValue().toString());
		tableNo_field.setText(data.getTableNo().getValue().toString());
		tableStatus_field.setText(data.getTableStatus().getValue());
		tableInfo_field.setText(data.getTableInfo().getValue());
	}
}
