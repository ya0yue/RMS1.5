package com.restaurant.view;

import com.restaurant.ConnectionDB;
import com.restaurant.MainApp;
import com.restaurant.model.Staff;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginViewController {

	private MainApp mainApp;

	@FXML
	private Button loginButton;
	@FXML
	private Button exitButton;
	@FXML
	private TextField accountNO_field;
	@FXML
	private PasswordField password_field;

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	@FXML
	private void initialize() {
		setEnterKeyEvent();

		loginButton.setOnMouseClicked(e -> {
			login();
		});

		exitButton.setOnMouseClicked(e -> {
			Platform.exit();
		});
	}

	private void setEnterKeyEvent() {
		accountNO_field.setOnKeyPressed(keyEvent -> {
			if (keyEvent.getCode() == KeyCode.ENTER) {
				login();
			}
		});

		password_field.setOnKeyPressed(keyEvent -> {
			if (keyEvent.getCode() == KeyCode.ENTER) {
				login();
			}
		});
	}

	private void login() {
		if (validateInput()) {
			this.mainApp.loginSuccess();// close this window and open the main
		} else {
			Alert a = new Alert(AlertType.WARNING);
			a.setTitle("Auth Error");
			a.setContentText("Wrong username or password.");
			a.showAndWait();
		}
	}

	private boolean validateInput() {
		String accountNO = accountNO_field.getText();
		String password = password_field.getText();
		mainApp.staffData = ConnectionDB.ConnectionStaff(0);
		for (Staff s : mainApp.staffData) {
			// the password should be encrypted, need decryption
			if (accountNO.equals(s.getAccountNo().getValue().toString())
					&& password.equals(s.getPassword().getValue())) {
				// set user authorities
				mainApp.setUserAuthorityList(ConnectionDB.getAuthID(s.getStaffID().getValue()));
				return true;
			}
		}
		return false;
	}

}
