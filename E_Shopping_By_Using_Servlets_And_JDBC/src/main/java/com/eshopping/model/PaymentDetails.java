package com.eshopping.model;

import java.sql.Date;
import java.sql.Time;

import lombok.Data;
@Data
public class PaymentDetails {
	
	private int payment_id;
	private double amount;
	private Date payment_date;
	private Time payment_time;
	private String  payment_type;
	private int customer_id;
	private int product_id;

}
