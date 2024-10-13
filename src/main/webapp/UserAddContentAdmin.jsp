<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="css/navbar.css">
 	<link rel="stylesheet" type="text/css" href="css/form.css">
	<meta charset="ISO-8859-1">
	<meta charset="UTF-8">
	
	<title>Add Content Admin</title>
	
	<script>
        function handleSubmit() {
            // Retrieve form values
            var NAME = document.getElementById("NAME").value;
            var user_username = document.getElementById("user_username").value;
            var email = document.getElementById("email").value;

            // Validate form fields
            if (NAME === "" || user_username === "" || email === "") {
            	alert("Please fill in all fields.");
            } else {
            	alert("Form submitted successfully!\n\nName: " + NAME + "\nUsername: " + user_username + "\nEmail: " + email );
            }
        }
      </script>
      
</head>
<body>

	<nav>
	  <div class="logo">
	    Filmood | Admin: <%=session.getAttribute("username")%>
	  </div>
	  <div class="links">
	  	<a href="AdminAdminController?action=listAdmin">Admin</a>
	   	<a href="AdminContentAdminController?action=listContentAdmin">Content Admin</a>
	   	<a href="UserAddContentAdmin.jsp" class="active">Add Content Admin</a>
	   	<a href="AdminCustomerController?action=listCustomer">Customer</a>
	   	<a href="UserAddCustomer.jsp">Add Customer</a>
 	   	<a href="LogoutServlet">Log out</a>
	  </div>
	</nav>
	
	  
    <form onsubmit="handleSubmit()" method="POST" action='<%=request.getContextPath()%>/AdminContentAdminController' name="frmAddStudent">
    <input type="hidden" name="action" value="insert" />
    
    <div class="form" >
      <div class="title" style="text-align: center;">Add Content Admin</div>
    
    	<% int inputValue = Integer.parseInt("0");%>
    
      	<div class="input-container ic3">
			<input id="ID" name="ID" class="input" type="hidden" value="<c:out value="<%= inputValue %>" />" readonly/>
       		<label for="ID" class="placeholder"></>
		</div>		
     
      	<div class="input-container ic2">
			<input id="NAME" name="NAME" class="input" type="text" value="<c:out value="${ContentAdmin.NAME}" />" placeholder=" " required/>
        	<div class="cut cut-short"></div>
       		<label for="NAME" class="placeholder">NAME</>
		</div>
     
		<div class="input-container ic2">
			<input id="user_username" name="user_username" class="input" type="text" value="<c:out value="${ContentAdmin.user_username}" />" placeholder=" " required/>
        	<div class="cut cut-short"></div>
       		<label for="user_username" class="placeholder">user_username</>
		</div>
    
    	<div class="input-container ic2">
			<input id="email" name="email" class="input" type="email" value="<c:out value="${ContentAdmin.email}" />" placeholder=" " required/>
        	<div class="cut cut-short"></div>
       		<label for="email" class="placeholder">email</>
		</div>
    
    	<div class="input-container ic2">
			<input id="password" name="password" class="input" type="password" value="<c:out value="${ContentAdmin.password}" />" placeholder=" " required/>
        	<div class="cut cut-short"></div>
       		<label for="password" class="placeholder">password</>
		</div>
		
    	<div class="input-container ic2">
			<input id="salt" name="salt" class="input" type="text" value="<c:out value="${ContentAdmin.salt}" />" placeholder=" " required/>
        	<div class="cut cut-short"></div>
       		<label for="salt" class="placeholder">salt</>
		</div>
		
		<div class="input-container ic2">
    		<input id="create_time" name="create_time" class="input" type="hidden" value="<c:out value="<%= inputValue %>" />" readonly/>
       		<label for="create_time" class="placeholder"></>
		</div>
		
		<div class="input-container ic3">
			<input id="role" name="role" class="input" type="hidden" value="<c:out value="${ContentAdmin.role}" />" readonly/>
       		<label for="role" class="placeholder"></>
		</div>
		
     	<button type="submit" class="submit">Submit</button>
     </div>
    </form>

	<script src="navbar.js"></script>
	
</body>
</html>