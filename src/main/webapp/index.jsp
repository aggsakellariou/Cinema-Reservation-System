<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>

	<link rel="stylesheet" type="text/css" href="css/form.css">
	
	<meta charset="ISO-8859-1">

<title>Cinema</title>
</head>
<body style="background-color: #333333">
          	
           	
    <form style="display: flex; justify-content: center; align-items: center; height: 100vh;"
    	method="post" action='<%=request.getContextPath()%>/LoginServlet'>
	    <div class="form" >
	      <div class="title" style="text-align: center;">Login</div>
	        
	      	<div class="input-container ic1">
				<input type="text" name="username" class="input" placeholder=" " required>        	
				<div class="cut cut-short"></div>
	       		<label for="username" class="placeholder">Username</>
			</div>		
	     
	     	<div class="input-container ic2">
				<input type="password" name="password" class="input" placeholder=" " required>        	
				<div class="cut cut-short"></div>
	       		<label for="password" class="placeholder">Password</>
			</div>
	      	
			<button type="submit" value="Submit" class="submit">Submit</button>
	     </div>
    </form>
		
		      	
 	<script>
        var id = parseInt('<%= session.getAttribute("id") %>');
        document.getElementById('idField').value = id;
    </script>
    
</body>
</html>