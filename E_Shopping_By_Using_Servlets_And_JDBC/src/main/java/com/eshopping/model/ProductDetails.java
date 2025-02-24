package com.eshopping.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @NoArgsConstructor @AllArgsConstructor @ToString
// instead of using @ annotations we can make use of @data 
public class ProductDetails {
	private int pro_id;
	private String pro_name;
	private String  pro_brand;
	private double pro_price;
	private double pro_discount;
	private String  pro_category;
	private int pro_quantity;

}
