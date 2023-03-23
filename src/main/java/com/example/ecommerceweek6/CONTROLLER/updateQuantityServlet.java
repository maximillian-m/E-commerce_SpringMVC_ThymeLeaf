package com.example.ecommerceweek6.CONTROLLER;

import com.example.ecommerceweek6.DAO.AdminDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "updateQuantityServlet", value = "/quantity")
public class updateQuantityServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nick = request.getParameter("product_name");
        int price = Integer.parseInt(request.getParameter("quantity"));
        AdminDao dao = new AdminDao();

        boolean check;
        try {
            check = dao.setQuantityOfProduct(price, nick);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String script;
        String redirectUrl = "AdminHome.jsp";
        if(check){
            script = "<script>alert('Set quantity successful');window.location='" + redirectUrl + "'</script>";
        }else{
            script = "<script>alert('Set quantity Unsuccessful');window.location='" + redirectUrl + "'</script>";
        }
        response.setContentType("text/html");
        response.getWriter().println(script);
    }
}