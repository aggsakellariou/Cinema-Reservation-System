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

	<title>Provoles</title>
	
</head>
<body>

	<nav>
	  <div class="logo">
	    Filmood | Content Admin: <%=session.getAttribute("username")%>
	  </div>
	  <div class="links">
			<a href="MovieController?action=listMovie">Movies</a>
			<a href="addMovie.jsp">Add Movie</a>
			<a href="ProvolesController?action=listProv" class="active">Provoles</a>
			<a href="ProvolesController?action=movies">Add Provoles</a>
 	  		<a href="LogoutServlet">Log out</a>
	  </div>
	</nav>


	<div>
	<br><br>
	<h1>Provoles</h1>
    <table>
        <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">MOVIES_ID</th>
                <th scope="col">MOVIES_NAME</th>
                <th scope="col">CINEMAS_ID</th>
                <th scope="col">CONTENT_ADMIN_ID</th>
                <th scope="col">TIME</th>
                                
                <th scope="col">UPDATE</th>
                <th scope="col">DELETE</th>                
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${requestScope.Provoles}" var="Provoles">
                <tr>
                    <td scope="row"><c:out value="${Provoles.ID}" /></td>
                    <td><c:out value="${Provoles.MOVIES_ID}" /></td>
                    <td><c:out value="${Provoles.MOVIES_NAME}" /></td>
                    <td><c:out value="${Provoles.CINEMAS_ID}" /></td>
                    <td><c:out value="${Provoles.CONTENT_ADMIN_ID}" /></td>
                    <td><c:out value="${Provoles.TIME}" /></td>
                                        
                    <td>
                    <button type="button" class="button-update" 
                    	onclick="window.location.href='ProvolesController?action=edit&ID=<c:out value="${Provoles.ID}"/>';">Update</button>
                    </td>
                    <td>
                    <button type="button" class="button-delete" 
                    	onclick="deleteProvolh('${Provoles.ID}');">Delete</button>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
	</div>
	
	<script src="navbar.js"></script>

        <script>
        function deleteProvolh(id) {
            var confirmation = confirm("Are you sure you want to delete this Provolh?");
            if (confirmation) {
                // Perform the deletion action via AJAX or form submission
                // For demonstration purposes, an alert is shown here
                alert(" Provoli  ID " + id + " deleted.");
                deleteRedirect(id);
            }
            else{
                alert("Provolh not deleted.");
            }
        }

        function deleteRedirect(id) {
            window.location.href = "ProvolesController?action=delete&ID=" + id;
        }
    </script>
</body>
</html>