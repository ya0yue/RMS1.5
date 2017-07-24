package com.restaurant.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

public class OrderItem {
	private IntegerProperty SequenID;
	private IntegerProperty menuID;
	private StringProperty menuName;
	private IntegerProperty quantity;
	private DoubleProperty price;
	private DoubleProperty amount;// price * quantity

	public OrderItem(Integer orderID, Integer menuID, String menuName, Integer quantity,
			Double price, Double amount) {
		super();
		this.SequenID = new SimpleIntegerProperty(orderID);
		this.menuID = new SimpleIntegerProperty(menuID);
		this.menuName = new SimpleStringProperty(menuName);
		this.quantity = new SimpleIntegerProperty(quantity);
		this.price = new SimpleDoubleProperty(price);
		this.amount = new SimpleDoubleProperty(amount);
	}

	public IntegerProperty getSequenID() {
		return SequenID;
	}

	public void setSequenID(IntegerProperty SequenID) {
		this.SequenID = SequenID;
	}

	public IntegerProperty getMenuID() {
		return menuID;
	}

	public void setMenuID(IntegerProperty menuID) {
		this.menuID = menuID;
	}

	public StringProperty getMenuName() {
		return menuName;
	}

	public void setMenuName(StringProperty menuName) {
		this.menuName = menuName;
	}

	public IntegerProperty getQuantity() {
		return quantity;
	}

	public void setQuantity(IntegerProperty quantity) {
		this.quantity = quantity;
	}

	public DoubleProperty getPrice() {
		return price;
	}

	public void setPrice(DoubleProperty price) {
		this.price = price;
	}

	public DoubleProperty getAmount() {
		return amount;
	}

	public void setAmount(DoubleProperty amount) {
		this.amount = amount;
	}

}
