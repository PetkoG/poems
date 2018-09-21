<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>List of poems</title>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>
</head>
<body>
<h1>List of poems</h1>
<p>Here you can see the list of the poems, edit them, remove or update.</p>

	
${ model }
		<c:choose>

			<c:when test="${ model.poems.size() > 0}">
				<c:forEach items="${ model.poems }" var="poem">
					<tr>
						<td> ${poem.title} </td>
						<td> ${poem.author} </td>
						<td> ${poem.text} </td>
						<td> ${poem.createdOn} </td>
					</tr>
				</c:forEach>
			</c:when>

			<c:otherwise>

			</c:otherwise>

		</c:choose>


</body>
</html>