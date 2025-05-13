package com.eshopping.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eshopping.DAO.ProductDAO;
import com.eshopping.DAO.ProductDAOImp;
import com.eshopping.model.ProductDetails;
@WebServlet("/addproduct")
public class AddProductDetails extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String category=req.getParameter("pro_category");
		String brand=req.getParameter("pro_brand");
		String productName=req.getParameter("pro_name");
		String price=req.getParameter("pro_price");
		double tempPrice=Double.parseDouble(price);
		String quantity=req.getParameter("pro_quantity");
		int tempQuantity=Integer.parseInt(quantity);
		String discount=req.getParameter("pro_discount");
		double tempDiscount=Double.parseDouble(discount);
		ProductDetails pd=new ProductDetails();
		pd.setPro_name(productName);
		pd.setPro_brand(brand);
		pd.setPro_category(category);
		pd.setPro_discount(tempDiscount);
		pd.setPro_price(tempPrice);
		pd.setPro_quantity(tempQuantity);
		System.out.println(pd);
		ProductDAO pDAO=new ProductDAOImp();
		PrintWriter pw=resp.getWriter();
		if(pDAO.insertProductDetails(pd)>0) {
			RequestDispatcher rd=req.getRequestDispatcher("AllProductDetails.jsp");
			rd.forward(req, resp);
		}
		else {
			RequestDispatcher rd=req.getRequestDispatcher("AddProduct.html");
			rd.include(req, resp);
			pw.print("<center><h1>server error</h1></center>");
			
		}
	}

}
