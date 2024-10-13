<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="css/table2.css">
	<link rel="stylesheet" type="text/css" href="css/navbar.css">
	<meta charset="ISO-8859-1">
	<meta charset="UTF-8">
	
	<title>Admin</title>
	
</head>
<body>

	<nav>
	  <div class="logo">
	    Filmood | Admin: <%=session.getAttribute("username")%>
	  </div>
	  <div class="links">
	   	<a href="AdminAdminController?action=listAdmin" class="active">Admin</a>
	   	<a href="AdminContentAdminController?action=listContentAdmin">Content Admin</a>
	   	<a href="UserAddContentAdmin.jsp">Add Content Admin</a>
	   	<a href="AdminCustomerController?action=listCustomer">Customer</a>
	   	<a href="UserAddCustomer.jsp">Add Customer</a>
 	   	<a href="LogoutServlet">Log out</a>
	  </div>
	</nav>
		
	<div>
	<br><br>
	<h1>Admin</h1>
    <table>
        <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">NAME</th>
                <th scope="col">username</th>
                <th scope="col">email</th>
                <th scope="col">create_time</th>
                <th scope="col">role</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${requestScope.Admin}" var="Admin">
                <tr>
                    <td scope="row"><c:out value="${Admin.ID}" /></td>
                    <td><c:out value="${Admin.NAME}" /></td>
                    <td><c:out value="${Admin.user_username}" /></td>
                    <td><c:out value="${Admin.email}" /></td>
                    <td><c:out value="${Admin.create_time}" /></td>
                    <td><c:out value="${Admin.role}" /></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
	</div>
	
	<script src="navbar.js"></script>
	
</body>
</html>