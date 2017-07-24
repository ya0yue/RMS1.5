package com.restaurant.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Larry
 * menu statistics in report
 */
public class MenuStat {
	private IntegerProperty menuID;
    private StringProperty menuName;
    private IntegerProperty quantity;
    private DoubleProperty income;
    private IntegerProperty orderid;
    
    public MenuStat(Integer menuID, String menuName, Integer quantity, Double Income,Integer orderid) {
    	this.menuID = new SimpleIntegerProperty(menuID);
    	this.menuName = new SimpleStringProperty(menuName);
    	this.quantity = new SimpleIntegerProperty(quantity);
    	this.income = new SimpleDoubleProperty(Income);
    	this.orderid = new SimpleIntegerProperty(orderid);
    }

	public IntegerProperty getMenuID() {
		return menuID;
	}

	public StringProperty getMenuName() {
		return menuName;
	}

	public IntegerProperty getQuantity() {
		return quantity;
	}
	public DoubleProperty getIncome() {
		return income;
	}
	public IntegerProperty getOrderID() {
		return orderid;
	}

}
