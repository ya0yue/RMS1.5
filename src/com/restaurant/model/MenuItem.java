package com.restaurant.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MenuItem {
	private final IntegerProperty ItemID;
	private final IntegerProperty ItemQuantity;
	private final DoubleProperty ItemPrice;
	private final IntegerProperty CategoryID;
	private final StringProperty ItemName;
	private final StringProperty ItemCode;
	private final StringProperty ItemInfo;

	public MenuItem(Integer ItemID, String ItemName, Integer ItemQuantity, Double ItemPrice, String ItemCode, 
			String ItemInfo, Integer CategoryID) {
		this.ItemID = new SimpleIntegerProperty(ItemID);
		this.ItemQuantity = new SimpleIntegerProperty(ItemQuantity);
		this.ItemPrice = new SimpleDoubleProperty(ItemPrice);
		this.CategoryID = new SimpleIntegerProperty(CategoryID);
		this.ItemName = new SimpleStringProperty(ItemName);
		this.ItemCode = new SimpleStringProperty(ItemCode);
		this.ItemInfo = new SimpleStringProperty(ItemInfo);
	}

	public IntegerProperty getItemID() {
		return ItemID;
	}

	public IntegerProperty getItemQuantity() {
		return ItemQuantity;
	}

	public DoubleProperty getItemPrice() {
		return ItemPrice;
	}

	public IntegerProperty getCategoryID() {
		return CategoryID;
	}

	public StringProperty getItemName() {
		return ItemName;
	}

	public StringProperty getItemCode() {
		return ItemCode;
	}

	public StringProperty getItemInfo() {
		return ItemInfo;
	}
}
