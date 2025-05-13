package com.eshopping.controller;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.eshopping.service.CustomerService;
import com.eshopping.service.CustomerServiceImp;

@WebServlet("/Customerresetpassword")
public class CustomerReSetPassword extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter pw = resp.getWriter();

        String password = req.getParameter("newPassword");

        if (password == null || password.trim().isEmpty()) {
            pw.println("<center><h1>Password cannot be empty</h1></center>");
            RequestDispatcher rd = req.getRequestDispatcher("CustomerReSetPassword.html");
            rd.include(req, resp);
            return;
        }
        HttpSession session = req.getSession(false);
        if (session != null) {
            String mobileNoStr = (String) session.getAttribute("mobile_no");
            if (mobileNoStr!=null && password!=null) {
                long mobileNo = Long.parseLong(mobileNoStr);

                CustomerService cs = new CustomerServiceImp();
                boolean isUpdated = cs.updateCustomerPasswordByUsingMobileNo(mobileNo, password);

                if (isUpdated) {
                    pw.println("<center><h1>Password updated successfully</h1></center>");
                    RequestDispatcher rd = req.getRequestDispatcher("CustomerLogin.html");
                    rd.forward(req, resp);
                } else {
                    pw.println("<center><h1>Password update failed. Try again.</h1></center>");
                    RequestDispatcher rd = req.getRequestDispatcher("CustomerReSetPassword.html");
                    rd.include(req, resp);
                }
                return;
            }
        }
        pw.println("<center><h1>Session expired or invalid mobile number</h1></center>");
        RequestDispatcher rd = req.getRequestDispatcher("ForgetPassword.html");
        rd.include(req, resp);
    }
}
