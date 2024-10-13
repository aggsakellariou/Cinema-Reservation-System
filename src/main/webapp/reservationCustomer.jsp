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

	<div>
	<br><br>
	<h1>Provoles</h1>
    <table>
        <thead>
            <tr>
                <th scope="col" style="display: none;">MOVIES_ID</th>
                <th scope="col">MOVIES_NAME</th>
                <th scope="col">Cinema hall</th>
                <th scope="col">TIME</th>
                <th scope="col">Reservation</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${requestScope.Provoles}" var="Provolh">
                <tr>
                    <td scope="row" style="display: none;"><c:out value="${Provolh.MOVIES_ID}" /></td>
                    <td><c:out value="${Provolh.MOVIES_NAME}" /></td>
                    <td><c:out value="${Provolh.CINEMAS_ID}" /></td>
                    <td><c:out value="${Provolh.TIME}" /></td>
                    <td>
                    <button type="button" class="button-update" onclick="window.location.href='ReservationController?action=reservation2&TIME=' + '${Provolh.TIME}' 
                    + '&MOVIES_ID=' + '${Provolh.MOVIES_ID}' + '&CINEMAS_ID=' + '${Provolh.CINEMAS_ID}';">Reservation</button>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
	</div>

	<script src="navbar.js"></script>
	
</body>
</html>