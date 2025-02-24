package com.eshopping.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eshopping.DAO.CartDAO;
import com.eshopping.DAO.CartDAOImp;
import com.eshopping.model.Cart;
@WebServlet("/addtocart")
public class AddToCart extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String customerId=req.getParameter("customerid");
		int tempCustomerId=Integer.parseInt(customerId);
		String  productId=req.getParameter("productid");
		int tempProductId=Integer.parseInt(productId);
		Cart cart=new Cart();
		cart.setCustomer_id(tempCustomerId);
		cart.setProduct_id(tempProductId);
		CartDAO cDAO=new CartDAOImp();
		if(cDAO.insertCartDetails(cart)>0) {
			RequestDispatcher rd=req.getRequestDispatcher("CustomerProduct.jsp");
			rd.forward(req, resp);
		}	
		else {
			RequestDispatcher rd=req.getRequestDispatcher("CustomerProduct.jsp");
			rd.include(req, resp);
		}
	}

}
