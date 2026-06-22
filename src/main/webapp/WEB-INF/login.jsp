<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Login</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/css/style.css">

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/css/login.css">

</head>

<body>

    <!-- NAVBAR -->

    <jsp:include page="/WEB-INF/navbar.jsp" />



    <section class="login-section">

        <div class="login-container">

            <h2>Welcome Back</h2>



            <%
                String error =
                    (String)request.getAttribute(
                            "errorMessage");

                if(error != null){
            %>

                <p class="error-message">

                    <%= error %>

                </p>

            <%
                }
            %>



            <form action="${pageContext.request.contextPath}/login"
                  method="post">

                <input type="email"
                       name="email"
                       placeholder="Email Address"
                       required>



                <input type="password"
                       id="password"
                       name="password"
                       placeholder="Password"
                       required>

                <!-- SHOW PASSWORD -->

                <div class="show-password">

                    <input type="checkbox"
                           id="showPassword">

                    <label for="showPassword">

                        Show Password

                    </label>

                </div>



                <button type="submit">

                    Login

                </button>

            </form>



            <p class="register-link">

                Don't have an account?

                <a href="${pageContext.request.contextPath}/register">

                    Register

                </a>

            </p>

        </div>

    </section>


    <script>

        const passwordField =
            document.getElementById("password");

        const showPassword =
            document.getElementById("showPassword");

        showPassword.addEventListener("change", () => {

            if(showPassword.checked){

                passwordField.type = "text";
            }
            else{

                passwordField.type = "password";
            }
        });

    </script>

</body>

</html>
