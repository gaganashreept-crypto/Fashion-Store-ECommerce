<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="com.fashionstores.model.CartItem" %>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>My Cart</title>

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/assets/css/style.css">

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/assets/css/cart.css">

</head>

<body>

<!-- NAVBAR -->

<jsp:include page="/WEB-INF/navbar.jsp" />

<!-- CART SECTION -->

<section class="cart-section">

    <div class="container">

        <h1 class="cart-title">Shopping Cart</h1>

        <%
            List<CartItem> cartItems =
                (List<CartItem>) request.getAttribute("cartItems");

            double total = 0;
        %>

        <%
            if(cartItems != null && !cartItems.isEmpty()) {
                for(CartItem item : cartItems){
                    total += item.getPrice() * item.getQuantity();
        %>

        <!-- SINGLE CART ITEM -->
        <div class="cart-card">

            <!-- PRODUCT IMAGE -->
            <div class="cart-image">
                <img src="${pageContext.request.contextPath}/assets/images/<%= item.getImageUrl() %>"
                     alt="<%= item.getProductName() %>">
            </div>

            <!-- PRODUCT DETAILS -->
            <div class="cart-details">

                <h2><%= item.getProductName() %></h2>

                <p class="cart-size">Size: <%= item.getSize() %></p>

                <!-- UPDATE QUANTITY FORM -->
                <form action="${pageContext.request.contextPath}/cart"
                      method="post"
                      class="quantity-form">

                    <input type="hidden" name="action" value="update">
                    <input type="hidden" name="cartItemId" value="<%= item.getCartItemId() %>">

                    <label>Quantity:</label>
                    <input type="number" name="quantity" value="<%= item.getQuantity() %>" min="1">

                    <button type="submit" class="update-btn">Update</button>
                </form>

                <h3 class="cart-price">₹ <%= item.getPrice() * item.getQuantity() %></h3>
            </div>

            <!-- REMOVE BUTTON -->
            <div class="cart-actions">
                <form action="${pageContext.request.contextPath}/cart" method="post">
                    <input type="hidden" name="action" value="remove">
                    <input type="hidden" name="cartItemId" value="<%= item.getCartItemId() %>">
                    <button type="submit" class="remove-btn">Remove</button>
                </form>
            </div>

        </div>

        <%
                }
        %>

        <!-- TOTAL SECTION -->
        <div class="cart-total-box">
            <h2>Total: ₹ <%= total %></h2>
            <a href="${pageContext.request.contextPath}/order?action=checkout">
                <button class="checkout-btn">Proceed To Checkout</button>
            </a>
        </div>

        <%
            } else {
        %>

        <!-- EMPTY CART -->
        <div class="empty-cart">
            <h2>Your Cart Is Empty</h2>
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
