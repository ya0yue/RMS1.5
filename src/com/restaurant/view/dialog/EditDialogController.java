package com.restaurant.view.dialog;

import com.restaurant.MainApp;

import javafx.stage.Stage;

public abstract class EditDialogController<T> {
	
	private Stage dialogStage;
	private MainApp mainApp;

	public Stage getDialogStage() {
		return dialogStage;
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	public abstract void transferData(T data);

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	public MainApp getMainApp() {
		return mainApp;
	}

}
