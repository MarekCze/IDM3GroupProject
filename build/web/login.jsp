<%-- 
    Document   : index
    Created on : 19-Dec-2016, 18:30:35
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
        <title>Log in to Art Space</title>
    </head>
    <body>

        <div class="login-page">
            <header class="main-header">
               
            </header>
           
           
                <div class="form">               
                    <h1>Welcome, Log in below</h1>

                    <form action="UserController" method="get" class="login-form" name="Login"> 
                        
                        <label for="email">Email</label>
                        <input type="email" name="email" id="email">
                        <br>
                        <label for="password">Password</label>
                        <input type="password" name="password" id="password">
                        <br>
                        <p class="message">${message}</p>               
                        <input type="submit" name="menu" value="Process Login" />

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

