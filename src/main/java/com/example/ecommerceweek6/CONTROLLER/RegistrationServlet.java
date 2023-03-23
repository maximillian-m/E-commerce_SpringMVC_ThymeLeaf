package com.example.ecommerceweek6.CONTROLLER;

import com.example.ecommerceweek6.CONNECTION.Connections;
import com.example.ecommerceweek6.DAO.UserDAO;
import com.example.ecommerceweek6.Models.Users;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "RegistrationServlet", value = "/register")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       // this block is getting the parameters from the registration form
        String firstname = request.getParameter("firstname");
        String username = request.getParameter("Username");
        String email = request.getParameter("emailaddress");
        String phone = request.getParameter("phonenumber");
        String password = request.getParameter("password");
        RequestDispatcher dispatcher = null;

        // A new user created and assigned to a DAO execute the register user..
        Users user = new Users(firstname, username, email, phone, password);
        UserDAO userDAO = new UserDAO();
        boolean check = false;
        try {

            //checking if the user exists
            check = userDAO.registerUser(user);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        //if the user exists, redirect to login....
        if (check) {
//            dispatcher = request.getRequestDispatcher("index.jsp");
//            dispatcher.forward(request, response);
            String redirectUrl = "index.jsp";
            String script = "<script>alert('Registration successful.');window.location='" + redirectUrl + "'</script>";
            response.setContentType("text/html");
            response.getWriter().println(script);
//            response.sendRedirect("index.jsp");
        }else {
//                response.sendRedirect("register");
//            session.setAttribute("Registration status", "Not successful");
            String redirectUrl = "Resgistration.jsp";
            String script =  "<script>alert('Registration Not successful, user name exists.');window.location='" + redirectUrl + "'</script>";
            response.setContentType("text/html");
            response.getWriter().println(script);
        }

    }
}
