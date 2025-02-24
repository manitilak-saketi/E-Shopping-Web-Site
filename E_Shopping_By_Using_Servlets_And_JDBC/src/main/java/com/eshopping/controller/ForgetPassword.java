package com.eshopping.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eshopping.DAO.CustomerDAO;
import com.eshopping.DAO.CustomerDAOImp;
import com.eshopping.model.CustomerDetails;
import com.eshopping.service.CustomerService;
import com.eshopping.service.CustomerServiceImp;
@WebServlet("/forpass")
public class ForgetPassword extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		String firstName=req.getParameter("firstname");
		String lastName=req.getParameter("lastname");
		String middleName=req.getParameter("middlename");
		CustomerDetails cd=new CustomerDetails();
		cd.setCus_first_name(firstName);
		cd.setCus_last_name(lastName);
		cd.setCus_middle_name(middleName);
		CustomerService cs=new CustomerServiceImp();
		PrintWriter pw=resp.getWriter();
		try {
			if(cs.forgetPassword(cd)) {
				RequestDispatcher rd=req.getRequestDispatcher("Customerresetpassword");
				rd.forward(req, resp);	
			}
			else {
				RequestDispatcher rd=req.getRequestDispatcher("ForgetPassword.html");
				rd.include(req, resp);
				pw.println("<center><h1> invalid data entered</h1></center>");
			}
		} catch (Exception e) {
			RequestDispatcher rd=req.getRequestDispatcher("ForgetPassword.html");
			rd.include(req, resp);
			pw.println("<center><h1>"+e.getMessage()+"</center></h1>");
		}
	
	}

}
