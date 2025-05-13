package com.eshopping.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eshopping.service.CustomerService;
import com.eshopping.service.CustomerServiceImp;

@WebServlet("/forpass")
public class ForgetPassword extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        
        // Fetch the mobile number and password from the request
        String mobileNo = req.getParameter("mobile_no");
        Long tempMobileNo = Long.parseLong(mobileNo);
        String password = req.getParameter("newPassword");
        
        // Create a service instance
        CustomerService cs = new CustomerServiceImp();
        
        // Update the password using the mobile number and password
        boolean status = cs.updateCustomerPasswordByUsingMobileNo(tempMobileNo, password);
        
        // PrintWriter for sending the response
        PrintWriter pw = resp.getWriter();

        if (status) {
            // If password update is successful, store the mobile number in session
            req.getSession().setAttribute("mobile_no", mobileNo);
            req.getSession().setAttribute("newPassword",password);
            
            // Forward to reset password page
            RequestDispatcher rd = req.getRequestDispatcher("CustomerLogin.html");
            rd.include(req, resp);
            pw.println("<center><h1>password updated successfully</h1></center>");
           
        } else {
            // If update fails, return to the forget password page with an error message
            RequestDispatcher rd = req.getRequestDispatcher("ForgetPassword.html");
            rd.include(req, resp);
            pw.println("<center><h1>Mobile number not registered or password update failed</h1></center>");
        }
    }
}
