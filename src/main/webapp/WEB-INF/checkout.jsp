<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="com.fashionstores.model.CartItem" %>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>Checkout</title>

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/assets/css/style.css">

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/assets/css/checkout.css">

</head>

<body>

<jsp:include page="/WEB-INF/navbar.jsp" />

<section class="checkout-section">

    <div class="container">

        <h1 class="checkout-title">

            Checkout

        </h1>

        <%
            List<CartItem> cartItems =
                (List<CartItem>)
                request.getAttribute("cartItems");

            double total =
                (double)
                request.getAttribute("totalAmount");
        %>

        <!-- ORDER SUMMARY -->

        <div class="checkout-box">

            <h2>

                Order Summary

            </h2>

            <%
                for(CartItem item : cartItems){
            %>

            <div class="checkout-item">

                <p>

                    <%= item.getProductName() %>

                    ×

                    <%= item.getQuantity() %>

                </p>

                <p>

                    ₹
                    <%= item.getPrice()
                        * item.getQuantity() %>

                </p>

            </div>

            <%
                }
            %>

            <hr>

            <h3>

                Total:
                ₹ <%= total %>

            </h3>

        </div>



        <!-- ADDRESS FORM -->

        <form action="${pageContext.request.contextPath}/order"
              method="post"
              class="checkout-form">

            <label>

                Delivery Address

            </label>

            <textarea name="address"
                      required>

            </textarea>

            <button type="submit"
                    class="place-order-btn">

                Place Order

            </button>

        </form>

    </div>

</section>

<jsp:include page="/WEB-INF/footer.jsp" />

</body>

</html>