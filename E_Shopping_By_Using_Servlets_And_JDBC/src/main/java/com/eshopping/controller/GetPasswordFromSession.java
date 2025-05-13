package com.eshopping.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/getPasswordFromSession")
public class GetPasswordFromSession extends HttpServlet {
	 @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	            throws ServletException, IOException {
	        
	        resp.setContentType("text/plain");
	        HttpSession session = req.getSession(false);
	        if (session != null) {
	            String password = (String) session.getAttribute("newPassword");
	            if (password != null) {
	                resp.getWriter().write(password);
	                return;
	            }
	        }
	        resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
	        resp.getWriter().write("password cannot be null");
	    }

}
