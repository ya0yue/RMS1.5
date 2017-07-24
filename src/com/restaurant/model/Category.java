package com.restaurant.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Category {
    private final IntegerProperty CategoryID;
    private final StringProperty CategoryName;
    private final StringProperty CategoryInfo;

    public Category(Integer CategoryID, String CategoryName,String CategoryInfo) {
    	this.CategoryID = new SimpleIntegerProperty(CategoryID);
    	this.CategoryName = new SimpleStringProperty(CategoryName);
    	this.CategoryInfo = new SimpleStringProperty(CategoryInfo);
    }

	public IntegerProperty getCategoryID() {
		return CategoryID;
	}
	public StringProperty getCategoryName() {
		return CategoryName;
	}
	public StringProperty getCategoryInfo() {
		return CategoryInfo;
	}	
}

