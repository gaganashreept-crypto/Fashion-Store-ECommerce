<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib prefix="c"
           uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>All Products</title>

    <!-- GLOBAL CSS -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/css/style.css">

    <!-- PRODUCTS CSS -->
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/css/products.css">

</head>

<body>

    <!-- NAVBAR -->

    <jsp:include page="/WEB-INF/navbar.jsp" />



    <!-- PAGE TITLE -->

    <section class="page-title">

        <div class="container">

            <h1>All Products</h1>

        </div>

    </section>



    <!-- PRODUCTS SECTION -->

    <section class="products-section">

        <div class="container">

            <div class="products-layout">

                <!-- FILTER SIDEBAR -->

                <div class="filter-sidebar">

                    <h3>Filters</h3>
                    
                    <!-- SEARCH PRODUCT -->

                       

                           <div class="filter-group">

                                  <label>Search Product</label>

                                     <input type="text"
                                            id="searchProduct"
                                            placeholder="Search products...">

                            </div>



                    <!-- CATEGORY FILTER -->

                    <div class="filter-group">

                        <label>Category</label>

                        <select id="categoryFilter">

                            <option value="all">
                                All
                            </option>

                            <option value="Men">
                                Men
                            </option>

                            <option value="Women">
                                Women
                            </option>

                            <option value="Kids">
                                Kids
                            </option>

                            <option value="Footwear">
                                Footwear
                            </option>

                            <option value="Accessories">
                                Accessories
                            </option>

                        </select>

                    </div>



                    <!-- SORT FILTER -->

                    <div class="filter-group">

                        <label>Sort By</label>

                        <select id="sortProducts">

                            <option value="default">
                                Default
                            </option>

                            <option value="lowToHigh">
                                Price Low to High
                            </option>

                            <option value="highToLow">
                                Price High to Low
                            </option>

                        </select>

                    </div>

                </div>



                <!-- PRODUCTS CONTENT -->

                <div class="products-content">

                    <div class="products-grid">

                        <!-- PRODUCT LOOP -->

                        <c:forEach var="product"
                                   items="${products}">

                            <div class="product-card"

                                 data-category="${product.categoryName}"

                                 data-price="${product.price}">

                                <!-- IMAGE -->

                               <img src="${pageContext.request.contextPath}/assets/images/${product.imageUrl}" 
                                    alt="${product.name}">




                                <!-- PRODUCT NAME -->

                                <h3>
                                    ${product.name}
                                </h3>



                                <!-- CATEGORY -->

                                <p class="category-name">

                                    ${product.categoryName}

                                </p>



                                <!-- PRICE -->

                                <p class="price">

                                    ₹ ${product.price}

                                </p>



                                <!-- BUTTON -->

                                <a href="${pageContext.request.contextPath}/product-details?id=${product.productId}"
                                   class="btn">

                                    View Details

                                </a>

                            </div>

                        </c:forEach>

                    </div>

                </div>

            </div>

        </div>

    </section>



    <!-- FOOTER -->

    <jsp:include page="/WEB-INF/footer.jsp" />



    <!-- FILTER + SORT SCRIPT -->

    <script>

        const categoryFilter =
            document.getElementById("categoryFilter");

        const sortProducts =
            document.getElementById("sortProducts");

        const productsGrid =
            document.querySelector(".products-grid");
        
        const searchProduct =
            document.getElementById("searchProduct");



        // CATEGORY FILTER

        categoryFilter.addEventListener("change", () => {

            const selectedCategory =
                categoryFilter.value;

            const productCards =
                document.querySelectorAll(".product-card");

            productCards.forEach(card => {

                const category =
                    card.dataset.category;

                if(selectedCategory === "all" ||
                   category === selectedCategory){

                    card.style.display = "block";
                }
                else{

                    card.style.display = "none";
                }
            });
        });

     // SEARCH PRODUCTS

        searchProduct.addEventListener("keyup", () => {

            const searchValue =
                searchProduct.value.toLowerCase();

            const productCards =
                document.querySelectorAll(".product-card");

            productCards.forEach(card => {

                const productName =
                    card.querySelector("h3")
                        .innerText
                        .toLowerCase();

                if(productName.includes(searchValue)){

                    card.style.display = "block";
                }
                else{

                    card.style.display = "none";
                }
            });
        });


        // SORT PRODUCTS

        sortProducts.addEventListener("change", () => {

            const cards =
                Array.from(
                    document.querySelectorAll(
                        ".product-card"));

            const sortValue =
                sortProducts.value;

            cards.sort((a, b) => {

                const priceA =
                    parseFloat(a.dataset.price);

                const priceB =
                    parseFloat(b.dataset.price);

                if(sortValue === "lowToHigh"){

                    return priceA - priceB;
                }

                if(sortValue === "highToLow"){

                    return priceB - priceA;
                }

                return 0;
            });

            cards.forEach(card => {

                productsGrid.appendChild(card);
            });
        });

    </script>

</body>

</html>