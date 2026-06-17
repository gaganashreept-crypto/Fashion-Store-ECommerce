package com.fashionstores.controller;

import java.io.IOException;

import com.fashionstores.dao.UserDAO;
import com.fashionstores.dao.impl.UserDAOImpl;
import com.fashionstores.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UserDAO userDAO;



    @Override
    public void init() {

        userDAO = new UserDAOImpl();
    }



    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)

            throws ServletException, IOException {

        request.getRequestDispatcher(
                "/WEB-INF/register.jsp")
                .forward(request, response);
    }



    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)

            throws ServletException, IOException {

        String name =
                request.getParameter("name");

        String email =
                request.getParameter("email");

        String password =
                request.getParameter("password");

        String phone =
                request.getParameter("phone");

        String address =
                request.getParameter("address");



        User user = new User();

        user.setName(name);

        user.setEmail(email);

        user.setPassword(password);

        user.setPhone(phone);

        user.setAddress(address);



        boolean isRegistered =
                userDAO.registerUser(user);



        if(isRegistered) {

            response.sendRedirect(
                    request.getContextPath()
                    + "/login");
        }
        else {

            request.setAttribute(
                    "errorMessage",
                    "Registration Failed!");

            request.getRequestDispatcher(
                    "/WEB-INF/register.jsp")
                    .forward(request, response);
        }
    }
}