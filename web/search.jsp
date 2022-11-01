<%-- 
    Document   : search
    Created on : Jun 8, 2022, 7:44:44 AM
    Author     : PC
--%>

<%--@page import="hungmt.registration.RegistrationDTO"--%>
<%--@page import="java.util.List"--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html> 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>
    <body>

        <font color = "red">
        Welcome, ${sessionScope.USER.fullName}
        </font>
   
        <a href="logoutServlet">Logout</a> 
        <br><a href="viewShoppingPage">Clik here to buy book</a>
        <h1>Search Page</h1>
        <form action="searchLastnameServlet">
            Search value text <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" />
            <input type="submit" value="Search" name="btAction" />
        </form><br/>
        <c:set var="searchValue" value="${param.txtSearchValue}" />
        <c:if test="${not empty searchValue}" >
            <c:set var="result" value="${requestScope.SEARCH_RESULT}"/>
            <c:if test="${empty result}">
                <h2>No search result</h2>
            </c:if>
            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No. </th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Full name</th>
                            <th>Role</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${result}" varStatus="counter">
                        <form action="updateServlet">

                            <tr>
                                <td>
                                    ${counter.count}
                                </td>
                                <td>
                                    ${dto.username}
                                    <input type="hidden" name="txtUsername" value="${dto.username}" />
                                </td>
                                <td>
                                    <input type="text" name="txtPassword" value="${dto.password}" />
                                </td>
                                <td>
                                    ${dto.fullName}
                                </td>
                                <td> 

                                    <input type="checkbox" name="chkAdmin" value="ON"
                                           <%--
                                               if (dto.isRole()) {
                                           --%>
                                           <c:if test="${dto.role}">
                                               checked="checked"
                                           </c:if>
                                           <%--
                                               }// end user is admin
                                           --%>
                                           />
                                </td>
                                <td>
                                    <c:url var="deleteLink" value="deleteAccountServlet" >
                                        <c:param name="btAction" value="delete" />
                                        <c:param name="pk" value="${dto.username}" />
                                        <c:param name="lastSearchValue" value="${searchValue}" />
                                    </c:url>
                                    <a href="${deleteLink}">Delete</a>
                                </td>
                                <td>
                                    <input type="submit" value="Update" name="btAction" />
                                    <input type="hidden" name="lastSearchValue" value="${searchValue}" />
                                </td>
                            </tr>

                        </form> 
                    </c:forEach>
                    <%--
                        }//end traverse result list
                    --%>
                </tbody>
            </table>
        </c:if>
    </c:if>
</body>

</html>
