package com.eshopping.service;
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
		CustomerDetails userExists=cdao.getCustomerDetailsBasedOnMobileNo(cd.getCus_mobile_no());
		    if (userExists==null) {
		        throw new CustomerException("No user exists with the provided mobileNo");
		    }
			return true;
		    
		}
	@Override
	public boolean updateCustomerPasswordByUsingMobileNo(long mobileNo, String password) {
		 return cdao.updateCustomerPasswordByUsingMobileNo(mobileNo, password);
	}
	}
