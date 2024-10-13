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
 
	<title>Edit Movie</title>
	
	<script>
	    function handleSubmit() {
			// Retrieve form values
			var NAME = document.getElementById("NAME").value;
			var CONTENT = document.getElementById("CONTENT").value;
			var LENGTH = document.getElementById("LENGTH").value;
			var TYPE = document.getElementById("TYPE").value;
			var DIRECTOR = document.getElementById("DIRECTOR").value;

			// Validate form fields
			if (NAME === "" || CONTENT === "" || LENGTH === "" || TYPE === "" || DIRECTOR === "" ) {
			alert("Please fill in all fields.");
			} else {
			//alert ("Form submitted successfully!");
			alert("Form updated successfully!\n\nName: " + NAME + "\nContent: " + CONTENT + "\nLength: " + LENGTH  + "\nType: " + TYPE + "\nDirector: "+ DIRECTOR);
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
			<a href="MovieController?action=listMovie" class="active">Movies</a>
			<a href="addMovie.jsp">Add Movie</a>
			<a href="ProvolesController?action=listProv">Provoles</a>
			<a href="ProvolesController?action=movies">Add Provoles</a>
 	   	<a href="LogoutServlet">Log out</a>
	  </div>
	</nav>	  
	  
	<form onsubmit="handleSubmit()" method="POST" action='<%=request.getContextPath()%>/MovieController' name="frmAddStudent">
    <input type="hidden" name="action" value="edit" />
    
    <div class="form" >
      <div class="title" style="text-align: center;">Edit Movie</div>
        
      <div class="input-container ic1">
			<input id="ID" name="ID" class="input" type="hidden" value="<c:out value="${Movies.ID}" />" readonly/>
        	<div class="cut cut-short"></div>
       		<label for="ID" class="placeholder"></>
		</div>		
     
      <div class="input-container ic2">
			<input id="NAME" name="NAME" class="input" type="text" value="<c:out value="${Movies.NAME}" />" placeholder=" " required/>
        	<div class="cut cut-short"></div>
       		<label for="NAME" class="placeholder">NAME</>
		</div>
     
		<div class="input-container ic2">
			<input id="CONTENT" name="CONTENT" class="input" type="text" value="<c:out value="${Movies.CONTENT}" />" placeholder=" " required/>
        	<div class="cut cut-short"></div>
       		<label for="CONTENT" class="placeholder">CONTENT</>
		</div>
    
    	<div class="input-container ic2">
			<input id="LENGTH" name="LENGTH" class="input" type="text" value="<c:out value="${Movies.LENGTH}" />" placeholder=" " required/>
        	<div class="cut cut-short"></div>
       		<label for="LENGTH" class="placeholder">LENGTH</>
		</div>
    
    	<div class="input-container ic2">
			<input id="TYPE" name="TYPE" class="input" type="text" value="<c:out value="${Movies.TYPE}" />" placeholder=" " required/>
        	<div class="cut cut-short"></div>
       		<label for="TYPE" class="placeholder">TYPE</>
		</div>
		
    	<div class="input-container ic2">
			<input id="SUMMARY" name="SUMMARY" class="input" type="text" value="<c:out value="${Movies.SUMMARY}" />" placeholder=" " required/>
        	<div class="cut cut-short"></div>
       		<label for="SUMMARY" class="placeholder">SUMMARY</>
		</div>
		
    	<div class="input-container ic2">
			<input id="DIRECTOR" name="DIRECTOR" class="input" type="text" value="<c:out value="${Movies.DIRECTOR}" />" placeholder=" " required/>
        	<div class="cut cut-short"></div>
       		<label for="DIRECTOR" class="placeholder">DIRECTOR</>
		</div>
		
    	<div class="input-container ic2">
			<input id="CONTENT_ADMIN_ID" name="CONTENT_ADMIN_ID" class="input" type="hidden" value="<c:out value="${Movies.CONTENT_ADMIN_ID}" />" readonly/>
        	<div class="cut cut-short"></div>
       		<label for="CONTENT_ADMIN_ID" class="placeholder"></>
		</div>
		
     	<button type="submit" class="submit">Submit</button>
     </div>
    </form>  
	
	<script src="navbar.js"></script>
	
</body>
</html>