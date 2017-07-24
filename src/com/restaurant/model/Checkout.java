package com.restaurant.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Checkout {
	private final IntegerProperty checkoutID;
	private final DoubleProperty Amount;
	private final DoubleProperty Payment;
	private final IntegerProperty PaymentType;
	private final DoubleProperty ChangeMoney;
	private final DoubleProperty Discount;
	private final IntegerProperty OrderID;
	private final IntegerProperty StaffID;
	private final StringProperty CheckoutDate;
	private final StringProperty CheckoutTime;
	private final StringProperty CheckoutInfo;

	public Checkout(Integer checkoutID, Double Amount, Double Payment, Integer PaymentType, Double ChangeMoney,
			Double Discount, Integer OrderID, Integer StaffID, String CheckoutDate, String CheckoutTime,
			String CheckoutInfo) {
		this.checkoutID = new SimpleIntegerProperty(checkoutID);
		this.Amount = new SimpleDoubleProperty(Amount);
		this.Payment = new SimpleDoubleProperty(Payment);
		this.PaymentType = new SimpleIntegerProperty(PaymentType);
		this.Discount = new SimpleDoubleProperty(Discount);
		this.ChangeMoney = new SimpleDoubleProperty(ChangeMoney);
		this.OrderID = new SimpleIntegerProperty(OrderID);
		this.StaffID = new SimpleIntegerProperty(StaffID);
		this.CheckoutDate = new SimpleStringProperty(CheckoutDate);
		this.CheckoutTime = new SimpleStringProperty(CheckoutTime);
		this.CheckoutInfo = new SimpleStringProperty(CheckoutInfo);
	}

	public IntegerProperty getCheckoutID() {
		return checkoutID;
	}

	public DoubleProperty getAmount() {
		return Amount;
	}

	public DoubleProperty getPayment() {
		return Payment;
	}

	public IntegerProperty getPaymentType() {
		return PaymentType;
	}

	public DoubleProperty getDiscount() {
		return Discount;
	}

	public DoubleProperty getChangeMoney() {
		return ChangeMoney;
	}

	public IntegerProperty getOrderID() {
		return OrderID;
	}

	public IntegerProperty getStaffID() {
		return StaffID;
	}

	public StringProperty getCheckoutDate() {
		return CheckoutDate;
	}

	public StringProperty getCheckoutTime() {
		return CheckoutTime;
	}

	public StringProperty getCheckoutInfo() {
		return CheckoutInfo;
	}
}
