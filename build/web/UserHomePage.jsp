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
                <h1>Welcome to the user home page</h1>
                             
               
                <form action="UserController" class="login-form">                                      
                    <input type="submit" name="menu" value="Update User Details" />
                                    
                </form>
               
                <form action="NoticeController" class="login-form">                                      
                    <input type="submit" name="menu" value="Create Notice" />
                                    
                </form>
            
                

            </div>
        </div>

    </body>
</html>
