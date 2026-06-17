<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Product Details</title>

    <!-- CSS -->

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/css/style.css">

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/css/product-details.css">

</head>

<body>

    <!-- NAVBAR -->

    <jsp:include page="/WEB-INF/navbar.jsp" />



    <!-- PRODUCT DETAILS -->

    <section class="product-details-section">

        <div class="container">

            <div class="product-details-card">

                <!-- LEFT IMAGE -->

                <div class="product-image">

                    <img src="${pageContext.request.contextPath}/assets/images/${product.imageUrl}" 
     alt="${product.name}" 
     style="max-width:300px; height:auto;">



                </div>



                <!-- RIGHT CONTENT -->

                <div class="product-info">

                    <p class="product-category">

                        ${product.categoryName}

                    </p>



                    <h1>

                        ${product.name}

                    </h1>



                    <p class="product-description">

                        ${product.description}

                    </p>



                    <h2 class="product-price">

                        ₹ ${product.price}

                    </h2>



                    <!-- SIZE -->

                    <div class="size-section">

                        <h3>Select Size</h3>

                        <div class="sizes">

                            <button type="button"
                                    class="size-btn">

                                S

                            </button>

                            <button type="button"
                                    class="size-btn">

                                M

                            </button>

                            <button type="button"
                                    class="size-btn">

                                L

                            </button>

                            <button type="button"
                                    class="size-btn">

                                XL

                            </button>

                        </div>

                    </div>



                    <!-- ADD TO CART FORM -->

                    <form action="${pageContext.request.contextPath}/add-to-cart"
                          method="post">

                        <!-- PRODUCT ID -->

                        <input type="hidden"
                               name="variantId"
                               value="${product.productId}">

                        <!-- QUANTITY -->

                        <div class="quantity-section">

                            <h3>Quantity</h3>

                            <input type="number"
                                   name="quantity"
                                   value="1"
                                   min="1">

                        </div>



                        <!-- BUTTONS -->

                        <div class="product-buttons">

                            <button type="submit"
                                    class="add-cart-btn">

                                Add To Cart

                            </button>



                            <a href="${pageContext.request.contextPath}/products"
                               class="back-btn">

                                Back To Products

                            </a>

                        </div>

                    </form>

                </div>

            </div>

        </div>

    </section>



    <!-- FOOTER -->

    <jsp:include page="/WEB-INF/footer.jsp" />



    <!-- JAVASCRIPT -->

    <script>

        // SIZE BUTTONS

        const sizeButtons =
            document.querySelectorAll(".size-btn");

        sizeButtons.forEach(button => {

            button.addEventListener("click", () => {

                sizeButtons.forEach(btn => {

                    btn.classList.remove("active-size");
                });

                button.classList.add("active-size");
            });
        });

    </script>

</body>

</html>