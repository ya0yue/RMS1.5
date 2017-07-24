package com.restaurant.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Belong {
    private final IntegerProperty OrderID;
    private final IntegerProperty TableNo;

    public Belong(Integer OrderID, Integer TableNo) {
    	this.OrderID = new SimpleIntegerProperty(OrderID);
    	this.TableNo = new SimpleIntegerProperty(TableNo);
    }

	public IntegerProperty getOrderID() {
		return OrderID;
	}
	public IntegerProperty getTableNo() {
		return TableNo;
	}
}

