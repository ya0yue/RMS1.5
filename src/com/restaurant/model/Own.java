package com.restaurant.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Own {
    private final IntegerProperty StaffID;
    private final IntegerProperty AuthorityID;

    public Own(Integer StaffID, Integer AuthorityID) {
    	this.StaffID = new SimpleIntegerProperty(StaffID);
    	this.AuthorityID = new SimpleIntegerProperty(AuthorityID);
    }

	public IntegerProperty getStaffID() {
		return StaffID;
	}
	public IntegerProperty getAuthorityID() {
		return AuthorityID;
	}
}

