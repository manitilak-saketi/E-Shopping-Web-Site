package com.eshopping.DAO;
import java.util.List;

import com.eshopping.model.CustomerDetails;

public interface CustomerDAO {
	int insertCustomerDetails( CustomerDetails cd);
	List<CustomerDetails>getCustomerDetails();
	CustomerDetails getCustomerDetailsBasedOnEmailIdAndPassword(String emailId, String password);
	List<CustomerDetails> getCustomerDetailsBasedOnFirstLastMiddleNames(String firstName,String lastname,String middleName);
	

}
