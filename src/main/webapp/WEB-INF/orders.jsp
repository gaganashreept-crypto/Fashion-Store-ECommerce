<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.fashionstores.model.Order" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Orders</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/orders.css">
</head>
<body>

    <!-- NAVBAR -->
    <jsp:include page="/WEB-INF/navbar.jsp" />

    <!-- ORDERS SECTION -->
    <section class="orders-section">
        <div class="container">
            <h1 class="orders-title">My Orders</h1>

            <%
                List<Order> orders = (List<Order>) request.getAttribute("orders");
                if (orders != null && !orders.isEmpty()) {
                    for (Order order : orders) {
            %>

            <!-- ORDER CARD -->
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

                <!-- TRACK ORDER BUTTON -->
                <div class="order-actions">
                    <a href="${pageContext.request.contextPath}/order?action=track&orderId=<%= order.getOrderId() %>" class="btn">
                        Track Order
                    </a>
                </div>
            </div>

            <%
                    }
                } else {
            %>

            <!-- EMPTY ORDERS -->
            <div class="empty-orders">
                <h2>No Orders Found</h2>
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
