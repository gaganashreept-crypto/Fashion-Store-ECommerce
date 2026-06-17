<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="com.fashionstores.model.User" %>

<%
    User loggedInUser =
        (User) session.getAttribute("loggedInUser");
%>

<nav class="navbar">

    <div class="container nav-container">

        <!-- LOGO -->
        <a href="${pageContext.request.contextPath}/home"
           class="logo">

            FashionStore

        </a>

        <!-- NAV LINKS -->
        <div class="nav-links">

            <a href="${pageContext.request.contextPath}/home">

                Home

            </a>

            <a href="${pageContext.request.contextPath}/products">

                Products

            </a>

            <a href="${pageContext.request.contextPath}/cart">

                Cart

            </a>

            <a href="${pageContext.request.contextPath}/order?action=view">

                Orders

            </a>

            <%
                if(loggedInUser == null){
            %>

                <!-- BEFORE LOGIN -->

                <a href="${pageContext.request.contextPath}/login">

                    Login

                </a>

                <a href="${pageContext.request.contextPath}/register"
                   class="register-btn">

                    Register

                </a>

            <%
                }
                else{
            %>

                <!-- AFTER LOGIN -->

                <a href="${pageContext.request.contextPath}/logout">

                    Logout

                </a>

            <%
                }
            %>

        </div>

    </div>

</nav>