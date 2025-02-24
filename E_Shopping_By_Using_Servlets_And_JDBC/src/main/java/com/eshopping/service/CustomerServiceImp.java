package com.eshopping.service;

import java.util.ArrayList;
import java.util.List;

import com.eshopping.DAO.CustomerDAO;
import com.eshopping.DAO.CustomerDAOImp;
import com.eshopping.Exception.CustomerException;
import com.eshopping.model.CustomerDetails;

public class CustomerServiceImp implements CustomerService {
	CustomerDAO cdao=new CustomerDAOImp();
	@Override
	public boolean userRegistration(CustomerDetails cd) {
		List<CustomerDetails>l=cdao.getCustomerDetails();
		boolean emailmatch=(l.stream().anyMatch(customer->customer.getCus_email_id()
				.equalsIgnoreCase(cd.getCus_email_id())));
		if(emailmatch) {
			throw new CustomerException("Email id already existed");
		}
		boolean mobilematch=(l.stream().anyMatch(customer->customer.getCus_mobile_no()
				==(cd.getCus_mobile_no())));
		if(mobilematch) {
			throw new CustomerException("mobile num already existed");
		}
		if(cdao.insertCustomerDetails(cd)!=0) {
			return true;
		}
		else {
			return false;
		}
	}
	@Override
	public boolean forgetPassword(CustomerDetails cd) {
		List<CustomerDetails>l=cdao.getCustomerDetailsBasedOnFirstLastMiddleNames(cd.getCus_first_name(),cd.getCus_last_name(),cd.getCus_middle_name());
		    if (cd == null|| l==null) {
		        throw new CustomerException("Customer details cannot be null");
		    }
		    if ( l.isEmpty()) {
		        throw new CustomerException("No customers found for the provided details.");
		    }
		    if (cd.getCus_first_name() == null || cd.getCus_last_name() == null || cd.getCus_middle_name() == null) {
		        throw new CustomerException("Customer details cannot contain null fields");
		    }
		    System.out.println("Customer list size: " + l.size());
		    System.out.println("Searching for customer with name: " + cd.getCus_first_name() + " " + cd.getCus_middle_name() + " " + cd.getCus_last_name());
		    boolean userExists = l.stream().anyMatch(customer ->
		        customer.getCus_first_name() != null && customer.getCus_first_name().equals(cd.getCus_first_name()) &&
		        customer.getCus_last_name() != null && customer.getCus_last_name().equals(cd.getCus_last_name()) &&
		        customer.getCus_middle_name() != null && customer.getCus_middle_name().equals(cd.getCus_middle_name())  
		    );
		    System.out.print(userExists);
		    if (!userExists) {
		        throw new CustomerException("No user exists with the provided details");
		    }
		    return userExists;
		}
	}
