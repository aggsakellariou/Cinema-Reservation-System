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
    
	<title>Movies</title>
	
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
	
	<div>
	<br><br>
	<h1>Movies</h1>
    <table>
        <thead>
            <tr>
                <th scope="col">Id</th>
                <th scope="col">Name</th>
                <th scope="col">Content</th>
                <th scope="col">Length</th>
                <th scope="col">Type</th>
                <th scope="col">Summary</th>
                <th scope="col">Director</th>
                <th scope="col">Content_Admin_Id</th>
                <th scope="col"></th>
                <th scope="col"></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${requestScope.Movies}" var="Movie">
                <tr>
                    <td scope="row"><c:out value="${Movie.ID}" /></td>
                    <td><c:out value="${Movie.NAME}" /></td>
                    <td><c:out value="${Movie.CONTENT}" /></td>
                    <td><c:out value="${Movie.LENGTH}" /></td>
                    <td><c:out value="${Movie.TYPE}" /></td>
                    <td><c:out value="${Movie.SUMMARY}" /></td>
                    <td><c:out value="${Movie.DIRECTOR}" /></td>
                    <td><c:out value="${Movie.CONTENT_ADMIN_ID}" /></td>
                    <td>
                    <button type="button" class="button-update" 
                    	onclick="window.location.href='MovieController?action=edit&ID=<c:out value="${Movie.ID}"/>';">Update</button>                   
                    </td>
                    <td>
                    <button type="button" class="button-delete" 
                    	onclick="deleteMovie('${Movie.NAME}', '${Movie.ID}'); ">Delete</button>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
	</div>
	
	<script src="navbar.js"></script>
	<script>
        function deleteMovie(name, id) {
            var confirmation = confirm("Are you sure you want to delete this Movie?");
            if (confirmation) {
                // Perform the deletion action via AJAX or form submission
                // For demonstration purposes, an alert is shown here
                alert("Movie with NAME " + name + " and ID " + id + " deleted.");
                deleteRedirect(id);
            }
            else{
                alert("Movie not deleted.");
            }
        }

        function deleteRedirect(id) {
            window.location.href = "MovieController?action=delete&ID=" + id;
        }
    </script>
</body>
</html>