package com.restaurant.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Table {
    private final IntegerProperty TableID;
    private final IntegerProperty TableNo;
    private final StringProperty TableStatus;
    private final StringProperty TableInfo;

    public Table(Integer TableID, String TableStatus, String TableInfo, Integer TableNo) {
    	this.TableID = new SimpleIntegerProperty(TableID);
    	this.TableNo = new SimpleIntegerProperty(TableNo);
    	this.TableStatus = new SimpleStringProperty(TableStatus);
    	this.TableInfo = new SimpleStringProperty(TableInfo);
    }

	public IntegerProperty getTableID() {
		return TableID;
	}
	public IntegerProperty getTableNo() {
		return TableNo;
	}
	public StringProperty getTableStatus() {
		return TableStatus;
	}
	public StringProperty getTableInfo() {
		return TableInfo;
	}	
}

