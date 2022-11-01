<%-- 
    Document   : viewCart
    Created on : Jun 20, 2022, 7:15:44 AM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Book Store</title>
    </head>
    <body>
        <c:set var="cartItems" value="${sessionScope.CART.items}"/>
        <c:set var="productMap" value="${requestScope.PRODUCT_MAP}"/>
        <c:if test="${empty cartItems}">
            <h1>There a no book in cart</h1>
            <a href="viewShoppingPage">Add some book</a>
        </c:if>
        <c:if test="${not empty cartItems}">
            <h1>Your cart: </h1>
            <c:set var="total" value="${requestScope.TOTAL_CART}" />
            <table border="1" style="width: 80%">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>SKU</th>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Total</th>
                        <th>Remove Item From Cart</th>
                    </tr>
                </thead>
                <form action="removeItemServlet" method="POST">
                    <tbody>
                        <c:forEach var="key" items="${cartItems.keySet()}" varStatus="counter">
                            <tr>
                                <td>${counter.count}</td>
                                <td>${key}</td>
                                <td>${productMap.get(key).name}</td>
                                <td>${productMap.get(key).description}</td>
                                <td>${productMap.get(key).price}</td>
                                <td>${cartItems.get(key)}</td>
                                <td><fmt:formatNumber value="${productMap.get(key).price * cartItems.get(key)}" maxFractionDigits="2"/></td>
                                <td> <input type="checkbox" name="chkItem" value="${key}" /> </td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td colspan="7"></td>
                            <td> <input type="submit" value="Remove" /> </td>
                        </tr>
                    </tbody>
                </form>

            </table>
            <h2>Total money: $<fmt:formatNumber value="${total}" maxFractionDigits="2"/></h2>
            <a href="checkoutServlet">Checkout</a>
            <a href="viewShoppingPage">Add another book</a>
        </c:if>
    </body>
</html>
