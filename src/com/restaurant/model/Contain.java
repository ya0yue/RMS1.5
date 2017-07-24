package com.restaurant.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Contain {
    private final IntegerProperty OrderID;
    private final Integer ItemID;
    private final Integer Quantity;

    public Contain(Integer OrderID, Integer ItemID, Integer Quantity) {
    	this.OrderID = new SimpleIntegerProperty(OrderID);
    	this.ItemID = new Integer(ItemID);
    	this.Quantity = new Integer(Quantity);
    }

	public IntegerProperty getOrderID() {
		return OrderID;
	}
	public Integer getItemID() {
		return ItemID;
	}
	public Integer getQuantity() {
		return Quantity;
	}	
}

