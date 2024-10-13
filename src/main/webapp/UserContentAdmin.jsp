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
    
	<title>Content Admin</title>

</head>
<body>
	
	<nav>
	  <div class="logo">
	    Filmood | Admin: <%=session.getAttribute("username")%>
	  </div>
	  <div class="links">
	  	<a href="AdminAdminController?action=listAdmin">Admin</a>
	   	<a href="AdminContentAdminController?action=listContentAdmin" class="active">Content Admin</a>
	   	<a href="UserAddContentAdmin.jsp">Add Content Admin</a>
	   	<a href="AdminCustomerController?action=listCustomer">Customer</a>
	   	<a href="UserAddCustomer.jsp">Add Customer</a>
 	   	<a href="LogoutServlet">Log out</a>
	  </div>
	</nav>
		
	<div>
	<br><br>
	<h1>Content Admin</h1>
    <table>
        <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">NAME</th>
                <th scope="col">username</th>
                <th scope="col">email</th>
                <th scope="col">create_time</th>
                <th scope="col">role</th>
                <th scope="col"></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${requestScope.ContentAdmin}" var="ContentAdmin">
                <tr>
                    <td scope="row"><c:out value="${ContentAdmin.ID}" /></td>
                    <td><c:out value="${ContentAdmin.NAME}" /></td>
                    <td><c:out value="${ContentAdmin.user_username}" /></td>
                    <td><c:out value="${ContentAdmin.email}" /></td>
                    <td><c:out value="${ContentAdmin.create_time}" /></td>
                    <td><c:out value="${ContentAdmin.role}" /></td>
                    <td>
                    <button type="button" class="button-delete" 
                    	onclick="deleteContentAdmin('${ContentAdmin.user_username}', '${ContentAdmin.ID}'); ">Delete</button>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
	</div>
	
	<script src="navbar.js"></script>
	
	<script>
        function deleteContentAdmin(username, id) {
            var confirmation = confirm("Are you sure you want to delete this content admin?");
            if (confirmation) {
                // Perform the deletion action via AJAX or form submission
                // For demonstration purposes, an alert is shown here
                alert("Content admin with username " + username + " and ID " + id + " deleted.");
                deleteRedirect(username, id);
            }
            else{
                alert("Content admin not deleted.");
            }
        }

        function deleteRedirect(username, id) {
            window.location.href = "AdminContentAdminController?action=delete&NAME=" + name + "&ID=" + id;
        }
    </script>
</body>
</html>