<%-- 
    Document   : register
    Created on : 07-Dec-2016, 21:39:41
    Author     : AMarie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.User"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet" type="text/css">
        <title>Sign Up for web site</title>
    </head>
    <body>
        <div class="login-page">
            
            <div class="form">
                <form action="UserController" method="get" class="register-form" name="registration">


                    <br>
                    <label>User name</label>
                    <input type="text" name="username" id="firstname" value="${user.username}"/>
                    <br>
                    <label>Password</label>
                    <input type="password" name="password" id="password" value="${user.password}"/>
                    <br>
                    <label>Phone</label>
                    <input type="text" name="phone" id="phone" value="${user.phone}"/>
                    <br>
                    
                    <label>Email</label>
                    <input type="email" name="email" id="email" value="${user.email}"/>
                    <br>


                    <input type="submit" name="menu" value="Save User Details" />

                </form>
            </div>
        </div>
        <script
            src="https://code.jquery.com/jquery-1.12.4.js"
            integrity="sha256-Qw82+bXyGq6MydymqBxNPYTaUXXq7c8v3CwiYwLLNXU="
        crossorigin="anonymous"></script>
        <script src="js/jquery.validate.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/form-validation.js"></script>
    </body>
</html>
