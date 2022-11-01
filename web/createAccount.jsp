<%-- 
    Document   : createAccount
    Created on : Jun 22, 2022, 7:58:40 AM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create account</title>
    </head>
    <body>
        <h1>Create new Account</h1>
        <form action="createAccountServlet">
            <c:set var="errors" value="${requestScope.CREATE_ERROR}"/>
            Username* <input type="text" name="txtUsername" value="${param.txtUsername}" /> e.g 6 - 12 chars </br>
            <c:if test="${not empty errors.usernameLenghtErr}">
                <font color ="red"> ${errors.usernameLenghtErr}</font><br>
            </c:if>
              <c:if test="${not empty errors.usernameIsExisted}">
                <font color ="red"> ${errors.usernameIsExisted}</font><br>
            </c:if>
            Password* <input type="password" name="txtPassword" value="" /> e.g 6 - 30 chars </br>
            <c:if test="${not empty errors.passwordLenghtErr}">
                <font color ="red"> ${errors.passwordLenghtErr}</font><br>
            </c:if>
                
            Confirm* <input type="password" name="txtConfirm" value="" />  </br>
            <c:if test="${not empty errors.confirmNoMatched}">
                <font color ="red"> ${errors.confirmNoMatched}</font><br>
            </c:if>
                
            Full name* <input type="text" name="txtFullname" value="${param.txtFullname}" /> e.g 2 - 50 chars </br>
            <c:if test="${not empty errors.fullNameLenghtErr}">
                <font color ="red"> ${errors.fullNameLenghtErr}</font><br>
            </c:if>
                
            <input type="submit" value="Create New Account" name="btAction" />
            <input type="reset" value="Reset" />
        </form>
    </body>
</html>
