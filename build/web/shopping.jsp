<%-- 
    Document   : shopping
    Created on : Jul 5, 2022, 4:46:08 AM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Online Shopping</title>
    </head>

    <body>
        <h1>View Book</h1>
        <c:set var="bookList" value="${requestScope.BOOK_LIST}"/>
        <table border="1" style="width: 80%">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>SKU</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Add to cart</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="dto" items="${bookList}" varStatus="counter">
                <form action="addToCartServlet">
                    <tr>
                        <td>${counter.count}</td>
                        <td>${dto.sku}</td>
                        <td>${dto.name}</td>
                        <td>${dto.description}</td>
                        <td>$${dto.price}</td>
                        <td>
                            <input type="submit" value="Add" /> 
                            <input type="hidden" name="txtBookSKU" value="${dto.sku}" />
                        </td>
                    </tr>
                </form>

            </c:forEach>
        </tbody>
    </table>
        <a href="viewCartServlet">View your cart</a> 
        <br><a href="searchPage">Search Page</a>
</body>
</html>
