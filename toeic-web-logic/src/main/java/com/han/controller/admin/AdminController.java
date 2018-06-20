package com.han.controller.admin;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by VO VAN NHAN on 6/4/2018.
 */
@WebServlet (urlPatterns = {"/admin-home","/admin-home.html"})
public class AdminController extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/admin/home.jsp");
        requestDispatcher.forward(request,response);
    }
}
