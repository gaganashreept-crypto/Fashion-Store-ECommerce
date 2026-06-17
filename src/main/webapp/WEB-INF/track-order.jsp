<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.fashionstores.model.Order" %>

<%
    Order order = (Order) request.getAttribute("order");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Track Order</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/orders.css">
</head>
<body>

    <!-- NAVBAR -->
    <jsp:include page="/WEB-INF/navbar.jsp" />

    <!-- TRACK ORDER SECTION -->
    <section class="orders-section">
        <div class="container">
            <h1 class="orders-title">Track Order</h1>

            <%
                if(order != null){
            %>
                <div class="order-card">
                    <div class="order-top">
                        <div>
                            <h3>Order ID: #<%= order.getOrderId() %></h3>
                            <p><%= order.getOrderDate() %></p>
                        </div>
                        <div class="order-status"><%= order.getStatus() %></div>
                    </div>

                    <div class="order-middle">
                        <h2>₹ <%= order.getTotalAmount() %></h2>
                        <p><%= order.getAddress() %></p>
                    </div>
                </div>
            <%
                } else {
            %>
                <div class="empty-orders">
                    <h2>Order not found</h2>
                </div>
            <%
                }
            %>
        </div>
    </section>

    <!-- FOOTER -->
    <jsp:include page="/WEB-INF/footer.jsp" />

</body>
</html>
