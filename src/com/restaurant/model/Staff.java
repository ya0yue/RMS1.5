package com.restaurant.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Staff {
    private final IntegerProperty StaffID;
    private final IntegerProperty Age;
    private final IntegerProperty ContactNumber;
    private final IntegerProperty AccountNo;
    private final IntegerProperty ManagerID;
    private final StringProperty StaffName;
    private final StringProperty Role;
    private final IntegerProperty Authority;
    private final StringProperty Password;
    
    public Staff(Integer StaffID, Integer Age, Integer ContactNumber, Integer AccountNo, Integer ManagerID,
    		 String StaffName, String Role, String Password, Integer Authority) {
    	this.StaffID = new SimpleIntegerProperty(StaffID);
    	this.Age = new SimpleIntegerProperty(Age);
    	this.ContactNumber = new SimpleIntegerProperty(ContactNumber);
    	this.ManagerID = new SimpleIntegerProperty(ManagerID);
    	this.AccountNo = new SimpleIntegerProperty(AccountNo);
    	this.StaffName = new SimpleStringProperty(StaffName);
    	this.Role = new SimpleStringProperty(Role);
    	this.Password = new SimpleStringProperty(Password);
    	this.Authority = new SimpleIntegerProperty(Authority);
    }

	public IntegerProperty getStaffID() {
		return StaffID;
	}
	public IntegerProperty getAge() {
		return Age;
	}
	public IntegerProperty getContactNumber() {
		return ContactNumber;
	}
	public IntegerProperty getManagerID() {
		return ManagerID;
	}
	public IntegerProperty getAccountNo() {
		return AccountNo;
	}
	public StringProperty getStaffName() {
		return StaffName;
	}
	public StringProperty getRole() {
		return Role;
	}	
	public IntegerProperty getAuthority() {
		return Authority;
	}
	public StringProperty getPassword() {
		return Password;
	}	
}

