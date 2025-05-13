package com.eshopping.DAO;
import java.util.List;

import com.eshopping.model.CustomerDetails;

public interface CustomerDAO {
	int insertCustomerDetails( CustomerDetails cd);
	List<CustomerDetails>getCustomerDetails();
	CustomerDetails getCustomerDetailsBasedOnEmailIdAndPassword(String emailId, String password);
	CustomerDetails getCustomerDetailsBasedOnMobileNo(long mobileNo);
	boolean updateCustomerPasswordByUsingMobileNo(long mobileNo,String password);

}
