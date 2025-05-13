package com.eshopping.DAO;

import com.eshopping.model.Cart;

public interface CartDAO {
	int getCustomerCartCount(int customerId);
	int insertCartDetails(Cart cart);
}
