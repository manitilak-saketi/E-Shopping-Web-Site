package com.eshopping.model;

import java.sql.Date;

import lombok.Data;
@Data
public class OrderDetails {
	private int order_id;
	private int  customer_id;
	private int product_id;
	private int purchase_quantity;
	private Date purchase_date;
	private double purchase_price;

}
