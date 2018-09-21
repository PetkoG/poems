<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body onload="populateYears();">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>List of poems</title>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <script type="text/javascript">
    
	function populateYears() {
		var years = [ "" ];
		var select = document.getElementById("years");
		var bool = false;
		select.innerHTML = "";
		<c:forEach var="poem" items="${model.allPoems}">
		if(inArray("${poem.year}",years) == false){
			years.push("${poem.year}");
		}
		</c:forEach>
		var size = years.length;
		for (var i = 0; i < size; i++) {
			var newOption = document.createElement("option");
				newOption.value = years[i];
				newOption.innerHTML = years[i];
				select.appendChild(newOption);
		}
	}

	function inArray(needle,haystack)
	{
	    var count=haystack.length;
	    for(var i=0;i<count;i++)
	    {
	        if(haystack[i]===needle){return true;}
	    }
	    return false;
	}
	
    function fetch() {
    	fetchList();
      	}
  
	
	var pageConstant = "?page=0&size=3";
	
	function fetchList(){
		modifyData("/list"+pageConstant)
	}
	
	function refresh() {
		modifyData("/refresh"+pageConstant);
	}
	
	function list(page, size) {
		modifyData("/list?page="+page+"&size="+size)
	}
	
	function goBack() {
	    window.history.back();
	}
	
	function modifyData(suffix) {
		$.ajax({
			type : "GET",
			url : "/api/poems"+suffix,
			success : function(data) {
				document.body.parentElement.innerHTML = data;
				populateYears();
			}
		});
	}

</script>
</head>
<body>
<h1>List of poems</h1>
<p>Here you can see the list of the poems, search or add a poem.</p>
	<div align="left">
	<h2>Search bar</h2>
	<form action="search" method="get">
		<h2>Choose title:</h2>
		<input type="text" name="title">
		<h2>Choose year:</h2>
		<select id="years" name="years">
		</select> 
		<input type="submit" name="submit" value="Search">
	</form>
	<form action="addPoem" method="get">
		<input type="submit" name="submit" value="Add New Poem">
	</form>
</div>

<h1>List of existing poems with pagination</h1>
<h1>Click on poem title for details</h1>
	<div id="myAnchor">
		<c:choose>

			<c:when test="${ model.poems.size() > 0}">
				<table>
					<tr>
						<td> <h3> Title </h3></td>
						<td> <h3> Author </h3></td>
						<td> <h3> Text </h3></td>
						<td> <h3> Year </h3></td>
					</tr>
					<c:forEach items="${ model.poems }" var="poem">
						<tr>
							<td><a href="/api/poems/${poem.id}"> ${poem.title} </a></td>
							<td>${poem.author}</td>
							<td>${poem.text}</td>
							<td>${poem.year}</td>
						</tr>
					</c:forEach>
				</table>
			</c:when>

			<c:otherwise>
				<p>There are no poems in this page</p>
			</c:otherwise>

		</c:choose>

	<c:if test="${ model.poems.size() > 0}">
			<c:if test="${ model.number == 0}">
					Showing ${model.number + 1} to ${model.size} of ${ model.totalElements }
					<ul>
	 			<c:forEach begin="0" end="${model.totalPages-1}" var="page">
	 				<c:if test="${ model.number < model.totalPages-1 }">
		 				<li>
		 					<a href="javascript:void(0);" onclick="list(${page}, ${model.size})">${page+1}</a>
		 				</li>
	 				</c:if>
	 			</c:forEach>
		</ul>
			</c:if>
				<c:if test="${ model.number > 0 && model.number != model.totalPages-1 }">
					Showing ${1 + model.size*model.number} to ${model.size*model.number+model.size} of ${ model.totalElements }
					<ul>
	 			<c:forEach begin="0" end="${model.totalPages-1}" var="page">
	 				<c:if test="${ page < model.totalPages-1 }">
		 				<li>
		 					<a href="javascript:void(0);" onclick="list(${page}, ${model.size})">${page+1}</a>
		 				</li>
	 				</c:if>
	 				<c:if test="${ page == model.totalPages-1 }">
		 				<li>
		 					<a href="javascript:void(0);" onclick="list(${page}, ${model.size})">${page+1}</a>
		 				</li>
	 				</c:if>
	 			</c:forEach>
		</ul>
			</c:if>
			<c:if test="${ model.number == model.totalPages-1 }">
					Showing last ${ model.totalElements - model.number*model.size } poems in the list
					<a href="javascript:void(0);" onclick="list(${model.number-1}, ${model.size})"> -1 Page	</a>
			</c:if>
			
	</c:if>
</div>

</body>
</html>