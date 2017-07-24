package com.restaurant.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Authority {
    private final IntegerProperty AuthorityID;
    private final StringProperty AuthorityName;
    private final StringProperty AuthorityInfo;

    public Authority(Integer AuthorityID, String AuthorityName,String AuthorityInfo) {
    	this.AuthorityID = new SimpleIntegerProperty(AuthorityID);
    	this.AuthorityName = new SimpleStringProperty(AuthorityName);
    	this.AuthorityInfo = new SimpleStringProperty(AuthorityInfo);
    }

	public IntegerProperty getAuthorityID() {
		return AuthorityID;
	}
	public StringProperty getAuthorityName() {
		return AuthorityName;
	}
	public StringProperty getAuthorityInfo() {
		return AuthorityInfo;
	}	
}

