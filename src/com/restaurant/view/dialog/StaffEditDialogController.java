package com.restaurant.view.dialog;

import com.restaurant.ConnectionDB;
import com.restaurant.model.Staff;
import com.restaurant.view.SystemTabPageController;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class StaffEditDialogController extends EditDialogController<Staff> {

	// staff
	@FXML
	private TextField staffId_field;
	@FXML
	private TextField staffName_field;
	@FXML
	private TextField staffAge_field;
	@FXML
	private TextField staffContact_field;
	@FXML
	private TextField staffRole_field;
	@FXML
	private TextField staffAccountNo_field;
	@FXML
	private PasswordField staffPassword_field;
	@FXML
	private TextField staffAuthority_field;
	@FXML
	private CheckBox system_check;
	@FXML
	private CheckBox report_check;
	@FXML
	private CheckBox checkout_check;
	@FXML
	private CheckBox order_check;
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
			Integer StaffID = Integer.parseInt(staffId_field.getText());
			String StaffName = staffName_field.getText();
			Integer Age = Integer.parseInt(staffAge_field.getText());
			Integer ContactNumber = Integer
					.parseInt(staffContact_field.getText());
			String Role = staffRole_field.getText();
			Integer AccountNo = Integer
					.parseInt(staffAccountNo_field.getText());
			String Password = staffPassword_field.getText();
			Integer AuthorityID = Integer
					.parseInt(staffAuthority_field.getText());
			// String ManagerID = staffPassword_field.getText(); TODO
			if (SystemTabPageController.Modify_Add_flag == 1) {
				if (ConnectionDB.ModifyStaff(StaffID, StaffID, StaffName, Age,
						ContactNumber, Role, AccountNo, Password, 2,
						AuthorityID)) {
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
				if (ConnectionDB.InsertStaff(StaffID, StaffName, Age,
						ContactNumber, Role, AccountNo, Password, 2, 4)) {
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
			this.getMainApp().staffData = ConnectionDB.ConnectionStaff(0);
			this.getMainApp().getMainController().getSystemTabPageController()
					.getStaffTable().setItems(this.getMainApp().getStaffData());
		});
	}

	@Override
	public void transferData(Staff data) {
		staffId_field.setText(data.getStaffID().getValue().toString());
		staffAccountNo_field.setText(data.getAccountNo().getValue().toString());
		staffAge_field.setText(data.getAge().getValue().toString());
		staffContact_field
				.setText(data.getContactNumber().getValue().toString());
		staffRole_field.setText(data.getRole().getValue());
		staffName_field.setText(data.getStaffName().getValue());
		staffAuthority_field.setText(data.getAuthority().getValue().toString());
	}
}
