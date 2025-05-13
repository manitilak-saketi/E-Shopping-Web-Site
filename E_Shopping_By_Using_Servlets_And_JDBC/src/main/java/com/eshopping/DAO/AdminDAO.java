package com.eshopping.DAO;

import java.util.List;

import com.eshopping.model.Admin;

public interface AdminDAO {
	boolean getAdminDetailsByUsingEmailAndPassword(String email,String password);
	List<Admin>getAllAdminDetails();
}
