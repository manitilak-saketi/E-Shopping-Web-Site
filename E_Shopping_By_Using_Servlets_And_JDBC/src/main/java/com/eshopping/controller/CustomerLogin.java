package com.eshopping.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eshopping.DAO.CartDAO;
import com.eshopping.DAO.CartDAOImp;
import com.eshopping.DAO.CustomerDAO;
import com.eshopping.DAO.CustomerDAOImp;
import com.eshopping.DAO.ProductDAO;
import com.eshopping.DAO.ProductDAOImp;
import com.eshopping.model.CustomerDetails;
import com.eshopping.model.ProductDetails;
@WebServlet("/customerlogin")
public class CustomerLogin extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String emailId=req.getParameter("emailid");
		String password=req.getParameter("password");
		
		CustomerDAO cdao=new CustomerDAOImp();
		CartDAO cart=new CartDAOImp();
		CustomerDetails cd= cdao.getCustomerDetailsBasedOnEmailIdAndPassword(emailId, password);
		int count=cart.getCustomerCartCount(cd.getCus_id());
		resp.setContentType("text/html");
		PrintWriter pw=resp.getWriter();
		ProductDAO pDAO= new ProductDAOImp();
		List<ProductDetails> allProducts=pDAO.getAllProductDetails();
		HttpSession session=req.getSession();
		System.out.println(cd+"/n"+count+"/n"+pDAO);
			if(cd.getCus_id()!=0) {
				session.setAttribute("customerlogin",cd);
				session.setAttribute("cartCount", count);
				session.setAttribute("allProducts", allProducts);
				RequestDispatcher rd=req.getRequestDispatcher("CustomerProduct.jsp");
				rd.forward(req, resp);
				pw.print("<center><h1>Login successful</center></h1>");
			}
			else {
				RequestDispatcher rd=req.getRequestDispatcher("CustomerLogin.html");
				rd.include(req, resp);
				pw.print("<center><h1>invalid credentials</h1></center>");
			}		
	}
}
