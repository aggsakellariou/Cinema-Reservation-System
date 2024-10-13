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
	
	<title>Reservation</title>
	
</head>
<body>

	<nav>
	  <div class="logo">
	    Filmood | Customer: <%=session.getAttribute("username")%>
	  </div>
	  <div class="links">
	   	<a href="CustomerController?action=listMovieCustomer" class="active">Movies</a>
	   	<a href='HistoryController?action=History'>History</a>
 	   	<a href="LogoutServlet">Log out</a>
	  </div>
	</nav>
	
	
	<form method="POST" action='<%=request.getContextPath()%>/ReservationController' name="frmAddStudent">
    <input type="hidden" name="action" value="reservation2" />
    
    <div class="form" >
      <div class="title" style="text-align: center;">Edit Movie</div>
        
      	<div class="input-container ic1">
			<input id="PROVOLES_MOVIES_ID" name="PROVOLES_MOVIES_ID" class="input" type="text" value="<c:out value="${Reservation.PROVOLES_MOVIES_ID}" />" readonly/>
        	<div class="cut cut-short"></div>
       		<label for="PROVOLES_MOVIES_ID" class="placeholder">PROVOLES_MOVIES_ID</>
		</div>		
     
     	<div class="input-container ic1">
			<input id="PROVOLES_MOVIES_NAME" name="PROVOLES_MOVIES_NAME" class="input" type="text" value="<c:out value="${Reservation.PROVOLES_MOVIES_NAME}" />" readonly/>
        	<div class="cut cut-short"></div>
       		<label for="PROVOLES_MOVIES_NAME" class="placeholder">PROVOLES_MOVIES_NAME</>
		</div>	
		
		<div class="input-container ic1">
			<input id="PROVOLES_CINEMAS_ID" name="PROVOLES_CINEMAS_ID" class="input" type="text" value="<c:out value="${Reservation.PROVOLES_CINEMAS_ID}" />" readonly/>
        	<div class="cut cut-short"></div>
       		<label for="PROVOLES_CINEMAS_ID" class="placeholder">PROVOLES_CINEMAS_ID</>
		</div>	
     
      	<div class="input-container ic2">
			<input  name="NUMBER_OF_SEATS" class="input" type="number" value="<c:out value="${Reservation.NUMBER_OF_SEATS}" />" min="1" id="max" placeholder=" " required/>
        	<div class="cut cut-short"></div>
       		<label for="NUMBER_OF_SEATS" class="placeholder">NUMBER_OF_SEATS</>
		</div>
     
		<div class="input-container ic2">
			<input id="MaxSeats" name="MaxSeats" class="input" type="text" value="<c:out value="${MaxSeats}" />" placeholder=" " readonly/>
        	<div class="cut cut-short"></div>
       		<label for="MaxSeats" class="placeholder">Available Seats</>
		</div>
    
    	<div class="input-container ic1">
			<input id="PR_TIME" name="PR_TIME" class="input" type="hidden" value="<c:out value="${Reservation.PR_TIME}" />" readonly/>
        	<div class="cut cut-short"></div>
       		<label for="PR_TIME" class="placeholder"></>
		</div>	
		
     	<button type="submit" class="submit">Submit</button>
     </div>
    </form>  
	
	<script>
		var max = parseFloat(document.getElementsByName("MaxSeats")[0].value);
		document.getElementById("max").max = max.toString();
		
		function validateForm() {
			var inputValue = parseFloat(document.getElementsByName("NUMBER_OF_SEATS")[0].value);
			var comparisonValue = parseFloat(document.getElementsByName("MaxSeats")[0].value);

			if (inputValue > comparisonValue) {
				alert("Input value must be smaller than or equal to " + comparisonValue);
				window.location.href = "Reservation.jsp";

				return false; // Prevent form submission
			} else{
				alert("Form submitted successfully!\n\n You reserved: " + inputValue + "seat(s)");				
				return true; // Allow form submission 
			}
		}
		
		document.querySelector("form").addEventListener("submit", validateForm);
	</script>
	
</body>
</html>