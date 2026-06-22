<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Register</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/css/style.css">

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/css/register.css">

</head>

<body>

    <!-- NAVBAR -->

    <jsp:include page="/WEB-INF/navbar.jsp" />



    <section class="register-section">

        <div class="register-container">

            <h2>Create Account</h2>



            <!-- ERROR -->

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



            <form action="${pageContext.request.contextPath}/register"
                  method="post">

                <input type="text"
                       name="name"
                       placeholder="Full Name"
                       required>



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



                <input type="text"
                       name="phone"
                       placeholder="Phone Number"
                       required>



                <textarea name="address"
                          placeholder="Address"
                          required></textarea>



                <button type="submit">

                    Register

                </button>

            </form>



            <p class="login-link">

                Already have an account?

                <a href="${pageContext.request.contextPath}/login">

                    Login

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
