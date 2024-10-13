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

	<title>FILMOOD Cinemas</title>

</head>
<body>
	
	<nav>
	  <div class="logo">
	    Filmood | Customer: <%=session.getAttribute("username")%>
	  </div>
	  <div class="links">
	   	<a href="CustomerController?action=listMovieCustomer" class="active">Movies</a>
	   	<a href="HistoryController?action=History">History</a>
 	   	<a href="LogoutServlet">Log out</a>
	  </div>
	</nav>
	
	<div>
	<br><br>
	<h1>Movies</h1>
    <table>
        <thead>
            <tr>
                <th scope="col">Name</th>
                <th scope="col">Content</th>
                <th scope="col">Length</th>
                <th scope="col">Type</th>
                <th scope="col">Summary</th>
                <th scope="col">Director</th>
	            <th scope="col">ID</th>
         		<th scope="col"></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${requestScope.Movies}" var="Movie">
                <tr>
                    <td scope="row"><c:out value="${Movie.NAME}" /></td>
                    <td><c:out value="${Movie.CONTENT}" /></td>
                    <td><c:out value="${Movie.LENGTH}" /></td>
                    <td><c:out value="${Movie.TYPE}" /></td>
                    <td><c:out value="${Movie.SUMMARY}" /></td>
                    <td><c:out value="${Movie.DIRECTOR}" /></td>
                    <td><c:out value="${Movie.ID}" /></td>
                    
                    <td>
                   <button type="button" class="button-update" 
                   		onclick="window.location.href='CustomerController?action=reservation&ID=<c:out value="${Movie.ID}"/>';">Availability</button>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
	</div>

	<script src="navbar.js"></script>
	
</body>
</html>