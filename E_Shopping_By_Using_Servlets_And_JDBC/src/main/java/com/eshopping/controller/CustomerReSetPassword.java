package com.eshopping.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eshopping.model.CustomerDetails;
import com.eshopping.service.CustomerService;
import com.eshopping.service.CustomerServiceImp;
@WebServlet("/Customerresetpassword")
public class CustomerReSetPassword extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		String password=req.getParameter("password");
		CustomerDetails cd=new CustomerDetails();
		cd.setCus_password(password);
		PrintWriter pw=resp.getWriter();
		try {
			if(cd.getCus_password()!=null) {
				RequestDispatcher rd=req.getRequestDispatcher("CustomerLogin.html");
				rd.forward(req, resp);
			}
			else {
				RequestDispatcher rd=req.getRequestDispatcher("Customerresetpassword");
				rd.include(req, resp);
				pw.println("<center><h1> invalid password please check</h1></center>");
			}
		} catch (Exception e) {
			RequestDispatcher rd=req.getRequestDispatcher("Customerresetpassword");
			rd.include(req, resp);
			pw.println("<center><h1>"+e.getMessage()+"</h1></center>");
		}
	}

}
