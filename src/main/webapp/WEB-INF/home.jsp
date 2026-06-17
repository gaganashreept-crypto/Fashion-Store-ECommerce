<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib prefix="c"
           uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Fashion Store</title>

    <!-- CSS -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/css/style.css">

</head>

<body>

    <!-- NAVBAR -->
    <jsp:include page="navbar.jsp" />



    <!-- HERO SECTION -->
    <section class="hero">

        <div class="container">

            <div class="hero-content">

                <h1>
                    Discover Your Fashion Style
                </h1>

                <p>
                    Explore trending outfits, stylish accessories,
                    footwear and more at Fashion Store.
                </p>

                <a href="products" class="btn">
                    Shop Now
                </a>

            </div>

        </div>

    </section>



    <!-- CATEGORIES SECTION -->
<section class="categories-section">

    <div class="container">

        <div class="section-title">
            <h2>Shop By Category</h2>
        </div>

        <div class="categories-grid">

            <c:forEach var="category" items="${categories}">
                <div class="category-card">
                    <a href="${pageContext.request.contextPath}/products?category=${category.categoryName}">
                        <h3>${category.categoryName}</h3>
                    </a>
                </div>
            </c:forEach>

        </div>

    </div>

</section>




    <!-- PRODUCTS SECTION -->
    <section class="products-section">

        <div class="container">

            <div class="section-title">

                <h2>Trending Products</h2>

            </div>

            <div class="products-grid">

                <c:forEach var="product"
                           items="${products}">

                    <div class="product-card">

                        <!-- PRODUCT IMAGE -->
                        <img src="${pageContext.request.contextPath}/assets/images/${product.imageUrl}" alt="${product.name}">


                        <!-- PRODUCT NAME -->
                        <h3>
                            ${product.name}
                        </h3>

                        <!-- PRODUCT PRICE -->
                        <p class="price">

                            ₹ ${product.price}

                        </p>

                    </div>

                </c:forEach>

            </div>

        </div>

    </section>



    <!-- FOOTER -->
    <jsp:include page="footer.jsp" />

</body>

</html>