package com.eshopping.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/getMobileFromSession")
public class GetMobileFromSession extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        resp.setContentType("text/plain");
        HttpSession session = req.getSession(false);
        if (session != null) {
            String mobile = (String) session.getAttribute("mobile_no");
            if (mobile != null) {
                resp.getWriter().write(mobile);
                return;
            }
        }
        resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        resp.getWriter().write("No mobile number found");
    }
}

