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
	
	<title>History</title>

</head>
<body>
	
	<nav>
	  <div class="logo">
	    Filmood | Customer: <%=session.getAttribute("username")%>
	  </div>
	  <div class="links">
	   	<a href="CustomerController?action=listMovieCustomer">Movies</a>
	   	<a href="HistoryController?action=History" class="active">History</a>
 	   	<a href="LogoutServlet">Log out</a>
	  </div>
	</nav>
	
	<div>
	<br><br>
	<h1>History</h1>
    <table>
        <thead>
            <tr>
                <th scope="col">PROVOLES_MOVIES_NAME</th>
                <th scope="col">PROVOLES_CINEMAS_ID</th>
                <th scope="col">NUMBER_OF_SEATS</th>
                <th scope="col">PR_TIME</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${requestScope.Reservation}" var="Reservations">
                <tr>
                    <td scope="row"><c:out value="${Reservations.PROVOLES_MOVIES_NAME}"/></td>
                    <td><c:out value="${Reservations.PROVOLES_CINEMAS_ID}" /></td>
                    <td><c:out value="${Reservations.NUMBER_OF_SEATS}" /></td>
                    <td><c:out value="${Reservations.PR_TIME}" /></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
	</div>
	
	<script src="navbar.js"></script>
	
</body>
</html>