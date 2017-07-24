package com.restaurant.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Order {
    private final IntegerProperty OrderID;
    private final IntegerProperty OrderStatus;
    private final IntegerProperty StaffID;
    private final StringProperty OrderDate;
    private final StringProperty OrderTime;
    private final StringProperty OrderInfo;

    public Order(Integer OrderID, Integer OrderStatus,String OrderDate, 
    		String OrderTime, String OrderInfo,	Integer StaffID) {
    	this.OrderID = new SimpleIntegerProperty(OrderID);
    	this.OrderStatus = new SimpleIntegerProperty(OrderStatus);
    	this.StaffID = new SimpleIntegerProperty(StaffID);
    	this.OrderDate = new SimpleStringProperty(OrderDate);
    	this.OrderTime = new SimpleStringProperty(OrderTime);
    	this.OrderInfo = new SimpleStringProperty(OrderInfo);
    }

	public IntegerProperty getOrderID() {
		return OrderID;
	}
	public IntegerProperty getOrderStatus() {
		return OrderStatus;
	}
	public IntegerProperty getStaffID() {
		return StaffID;
	}
	public StringProperty getOrderDate() {
		return OrderDate;
	}
	public StringProperty getOrderTime() {
		return OrderTime;
	}	
	public StringProperty getOrderInfo() {
		return OrderInfo;
	}	
}

