package com.eshopping.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter @ToString @Getter @NoArgsConstructor @AllArgsConstructor
public class CustomerDetails {
	private int cus_id;
	private String cus_first_name;
	private String cus_last_name;
	private String cus_middle_name;
	private long cus_mobile_no;
	private String cus_email_id;
	private String cus_gender;
	private String cus_address;
	private String cus_password;


}
