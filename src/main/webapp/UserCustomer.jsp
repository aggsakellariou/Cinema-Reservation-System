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
	
	<title>Customer</title>
	
</head>
<body>

	<nav>
	  <div class="logo">
	    Filmood | Admin: <%=session.getAttribute("username")%>
	  </div>
	  <div class="links">
	  	<a href="AdminAdminController?action=listAdmin">Admin</a>
	   	<a href="AdminContentAdminController?action=listContentAdmin">Content Admin</a>
	   	<a href="UserAddContentAdmin.jsp">Add Content Admin</a>
	   	<a href="AdminCustomerController?action=listCustomer" class="active">Customer</a>
	   	<a href="UserAddCustomer.jsp">Add Customer</a>
 	   	<a href="LogoutServlet">Log out</a>
	  </div>
	</nav>
		
	<div>
	<br><br>
	<h1>Customer</h1>
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
            <c:forEach items="${requestScope.Customer}" var="Customer">
                <tr>
                    <td scope="row"><c:out value="${Customer.ID}" /></td>
                    <td><c:out value="${Customer.NAME}" /></td>
                    <td><c:out value="${Customer.user_username}" /></td>
                    <td><c:out value="${Customer.email}" /></td>
                    <td><c:out value="${Customer.create_time}" /></td>
                    <td><c:out value="${Customer.role}" /></td>
                    <td>
					    <button type="button" class="button-delete" 
					    onclick="deleteCustomer('${Customer.user_username}', '${Customer.ID}'); ">Delete</button>
					</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
	</div>
	
	<script src="navbar.js"></script>
	
	<script>
	    function deleteCustomer(username, id) {
	        var confirmation = confirm("Are you sure you want to delete this Customer?");
	        if (confirmation) {
	            // Perform the deletion action via AJAX or form submission
	            // For demonstration purposes, an alert is shown here
	            alert("Customer with username " + username + " and ID " + id + " deleted.");
	            deleteRedirect(username, id);
	        }
	        else{
	            alert("Customer not deleted.");
	        }
	    }
	
	    function deleteRedirect(username, id) {
	        window.location.href = "AdminCustomerController?action=delete&username=" + username+ "&ID=" + id;
	    }
	</script>
</body>
</html>