package com.eshopping.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eshopping.DAO.AdminDAO;
import com.eshopping.DAO.AdminDAOImp;
import com.eshopping.model.Admin;
@WebServlet("/adminLogin")
public class AdminLogin extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 String emailId=req.getParameter("emailid");
		 String password=req.getParameter("password");
		 AdminDAO adao=new AdminDAOImp();
		 PrintWriter pw=resp.getWriter();
		 resp.setContentType("text/html");
		 try {			 
			 if(adao.getAdminDetailsByUsingEmailAndPassword(emailId, password)){
				 RequestDispatcher rd=req.getRequestDispatcher("AdminOperations.html");
				 rd.forward(req, resp);
			 }
			 else {
				RequestDispatcher rd=req.getRequestDispatcher("AdminLogin.html");
				rd.include(req, resp);
				pw.print("<center><h1>Invalid Credentials</center></h1>");
			}
		} catch (Exception e) {
			
		}
		
	}

}
