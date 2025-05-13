package com.eshopping.service;

import com.eshopping.model.CustomerDetails;

public interface CustomerService {
	boolean userRegistration(CustomerDetails cd);
	boolean forgetPassword(CustomerDetails cd);
	boolean updateCustomerPasswordByUsingMobileNo(long mobileNo, String password);
}
