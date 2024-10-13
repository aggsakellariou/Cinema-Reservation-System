<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="css/form.css">
	<link rel="stylesheet" type="text/css" href="css/navbar.css">
	<meta charset="ISO-8859-1">
	
	<title>Add Provolh</title>
	
	<script> 
        function validateDateTime(inputValue) {
	        var pattern = /^\d{4}-\d{2}-\d{2}\s\d{2}:\d{2}:\d{2}$/;
	        if (pattern.test(inputValue)) {
	          return true;
	        } else {
	          return false;
	        }
	    }
        
        function handleSubmit() {

	        var MOVIES_ID = document.getElementById("MOVIES_ID").value;
	        var MOVIES_NAME = document.getElementById("MOVIES_NAME").value;
	        var CINEMAS_ID = document.getElementById("CINEMAS_ID").value;
	        var TIME = document.getElementById("TIME").value;
	
	        var isValid = validateDateTime(TIME);
	
	        if (isValid) {
	          alert("Form submitted successfully!\n\nMOVIES_ID: " + MOVIES_ID + "\nMOVIES_NAME: " + MOVIES_NAME + "\nCINEMAS_ID: " + CINEMAS_ID + "\nTIME: " + TIME);
	        } else {
	            alert("Form not submitted successfully!");
	        }
	   }
    </script>
</head>
<body>
	
	<nav>
	  <div class="logo">
	    Filmood | Content Admin: <%=session.getAttribute("username")%>
	  </div>
	  <div class="links">
			<a href="MovieController?action=listMovie">Movies</a>
			<a href="addMovie.jsp">Add Movie</a>
			<a href="ProvolesController?action=listProv">Provoles</a>
			<a href="ProvolesController?action=movies" class="active">Add Provoles</a>
 	   	<a href="LogoutServlet">Log out</a>
	  </div>
	</nav>

	<form onsubmit="handleSubmit()" method="POST" action='<%=request.getContextPath()%>/ProvolesController' name="frmAddStudent">
    <input type="hidden" name="action" value="insert" />
    
    <div class="form" >
      	<div class="title" style="text-align: center;">Add Provolh</div>
    
    	<% int inputValue = Integer.parseInt("0");%>
    
      	<div class="input-container ic1">
			<input id="ID" name="ID" class="input" type="hidden" value="<c:out value="<%= inputValue %>" />" readonly/>
        	<div class="cut cut-short"></div>
       		<label for="ID" class="placeholder"></>
	  	</div>		
     
      	<div class="input-container ic2">
			<input id="MOVIES_ID" name="MOVIES_ID" class="input" type="text" value="<c:out value="${Provoles.MOVIES_ID}" />" placeholder=" " readonly/>
        	<div class="cut cut-short"></div>
       		<label for="MOVIES_ID" class="placeholder">MOVIES_ID</>
		</div>
     
		<div class="input-container ic2">
			<input id="MOVIES_NAME" name="MOVIES_NAME" class="input" type="text" value="<c:out value="${Provoles.MOVIES_NAME}" />" placeholder=" " readonly/>
        	<div class="cut cut-short"></div>
       		<label for="MOVIES_NAME" class="placeholder">MOVIES_NAME</>
		</div>
    
    	<div class="input-container ic2">
			<input id="CINEMAS_ID" name="CINEMAS_ID" class="input" type="text" value="<c:out value="${Provoles.CINEMAS_ID}" />" placeholder=" " required/>
        	<div class="cut cut-short"></div>
       		<label for="CINEMAS_ID" class="placeholder">CINEMAS_ID</>
		</div>
    
    	<div class="input-container ic2">
			<input id="TIME" name="TIME" class="input" type="text" value="<c:out value="${Provoles.TIME}" />" placeholder=" " required/>
        	<div class="cut cut-short"></div>
       		<label for="TIME" class="placeholder">TIME</>
		</div>
		
		<div class="input-container ic1">
			<input id="CONTENT_ADMIN_ID" name="CONTENT_ADMIN_ID" class="input" type="hidden" value="<c:out value="<%= inputValue %>" />" readonly/>
        	<div class="cut cut-short"></div>
       		<label for="CONTENT_ADMIN_ID" class="placeholder"></>
	  	</div>
		
     	<button type="submit" class="submit">Submit</button>
     </div>
    </form>
	
	<script src="navbar.js"></script>	
	
</body>
</html>