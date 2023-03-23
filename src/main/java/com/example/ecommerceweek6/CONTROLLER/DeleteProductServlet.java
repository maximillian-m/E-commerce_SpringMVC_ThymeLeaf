package com.example.ecommerceweek6.CONTROLLER;

import com.example.ecommerceweek6.DAO.AdminDao;
import com.example.ecommerceweek6.Models.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DeleteProductServlet", value = "/remove")
public class DeleteProductServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String name = request.getParameter("product");

        AdminDao dao = new AdminDao();
        Product product;
        boolean check;
        try {
            product = dao.searchProduct(name);
            check = dao.removeProduct(product);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String redirectUrl = "AdminHome.jsp";
        String script;
        if(check){
            script = "<script>alert('Removal successful');window.location='" + redirectUrl + "'</script>";
        }else{
            script = "<script>alert('Removal Unsuccessful product not in the list');window.location='" + redirectUrl + "'</script>";
        }
        response.setContentType("text/html");
        response.getWriter().println(script);
    }
}
