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
@WebServlet("/CustomerRegistration")
public class CustomerRegistration extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		String firstName=req.getParameter("firstname");
		String lastName=req.getParameter("lastname");
		String middleName=req.getParameter("middlename");
		String emailId=req.getParameter("emailid");
		String mobileNo=req.getParameter("mobileno");
		long mNo=Long.parseLong(mobileNo);
		String password=req.getParameter("password");
		String  gender=req.getParameter("gender"); 
		String address=req.getParameter("address");
		CustomerDetails cd=new CustomerDetails();
		cd.setCus_first_name(firstName);
		cd.setCus_last_name(lastName);
		cd.setCus_middle_name(middleName);
		cd.setCus_email_id(emailId);
		cd.setCus_mobile_no(mNo);
		cd.setCus_password(password);
		cd.setCus_gender(gender);
		cd.setCus_address(address);
		CustomerService cs=new CustomerServiceImp();
		PrintWriter pw=res.getWriter();
		try {
			if(cs.userRegistration(cd)) {
				RequestDispatcher rd=req.getRequestDispatcher("CustomerLogin.html");
				rd.forward(req, res);
			}
			else {
				RequestDispatcher rd=req.getRequestDispatcher("CustomerRegistration.html");
				rd.include(req, res);
				pw.print("<center><h1>invalid data</h1></center>");
			}
		} catch (Exception e) {
			RequestDispatcher rd=req.getRequestDispatcher("CustomerRegistration.html");
			rd.include(req, res);
			pw.print("<center><h1>"+ e.getMessage()+"</h1></center>");
		
		}
	}
}
