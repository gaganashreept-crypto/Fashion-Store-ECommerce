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
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

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
                "/WEB-INF/login.jsp")
                .forward(request, response);
    }



    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)

            throws ServletException, IOException {

        String email =
                request.getParameter("email");

        String password =
                request.getParameter("password");



        User user =
                userDAO.loginUser(email, password);



        if(user != null) {

            HttpSession session =
                    request.getSession();

            session.setAttribute("loggedInUser",
                                 user);

            response.sendRedirect(
                    request.getContextPath()
                    + "/home");
        }
        else {

            request.setAttribute(
                    "errorMessage",
                    "Invalid Email or Password");

            request.getRequestDispatcher(
                    "/WEB-INF/login.jsp")
                    .forward(request, response);
        }
    }
}