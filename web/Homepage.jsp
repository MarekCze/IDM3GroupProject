<%-- 
    Document   : index
    Created on : 19-Dec-2016, 18:30:35
    Author     : AMarie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.User"%>
<%@page import="model.Notices"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet" type="text/css">
        <title>MM3 web site</title>
    </head>
    <body>

        <div class="login-page">
            <header class="main-header">



            </header>

            <div class="form">               
                <h1>Welcome to my web site</h1>
                <table id="m">
                    <tr>             
                        <td>image</td>
                        <td>short Description</td>
                    </tr>
                    <c:forEach var="notice" items="${userNotices}">                  
                        <tr>                                            
                            <td >${notice.image}</td>
                            <td >${notice.shortDescription}</td>
                         </tr>
                    </c:forEach>


                        <form action="UserController" class="login-form">                                      
                            <input type="submit" name="menu" value="Login" />

                        </form>

                        <form action="UserController">                    
                            <input type="submit" name="menu" value="SignUp" />
                        </form>

                </div>
            </div>

        </body>
    </html>
