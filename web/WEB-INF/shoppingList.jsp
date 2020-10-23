<%-- 
    Document   : shoppingList
    Created on : Oct 22, 2020, 5:06:31 PM
    Author     : 727045
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping List</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        <p>Hello, ${user}</p>
        <a href="<c:url value='ShoppingList?action=logout'/>">Logout</a>
        <h2>List</h2>
        
        <form action="ShoppingList" method="post">
            Add Item: <input type="text" name="item">
            <input type="hidden" name="action" value="add">
            <input type="submit" value="Add">
        </form>
        
        <form action="ShoppingList" method="post">
            <c:if test="${shoppingList != null}">
                <c:forEach var="listItem" items="${shoppingList}">
                    <li>
                        <input type="radio" name="items" value="${listItem}">${listItem}
                    </li>
                </c:forEach>
            </c:if>
            <input type="hidden" name="action" value="delete">
            <input type="submit" value="Delete">
        </form>
    </body>
</html>
