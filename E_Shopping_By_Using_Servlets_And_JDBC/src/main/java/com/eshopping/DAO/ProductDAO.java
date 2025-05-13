package com.eshopping.DAO;

import java.util.List;

import com.eshopping.model.ProductDetails;

public interface ProductDAO {
	int insertProductDetails(ProductDetails pd);
	List<ProductDetails>getAllProductDetails();
	List<ProductDetails>getProductsBasedOnSelection(String search);
	ProductDetails getProductDetailsById(int pro_id);
}
