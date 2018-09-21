<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
    <head>
    </head>
    <body>
        <h3>Welcome, Enter The Poem Details</h3>
        <h2>${ message }</h2>
        <form:form method="POST"
          action="/api/poems/addPoem" modelAttribute="poem">
             <table>
                <tr>
                    <td><form:label path="title">Title</form:label></td>
                    <td><form:input path="title"/></td>
                    <td><form:errors path="title" /></td>
                </tr>
                <tr>
                    <td><form:label path="author">Author</form:label></td>
                    <td><form:input path="author"/></td>
                    <td><form:errors path="author" /></td>
                </tr>
                <tr>
                    <td><form:label path="year">
                      Year</form:label></td>
                    <td><form:input path="year"/></td>
                    <td><form:errors path="year" /></td>
                </tr>
                <tr>
                    <td><form:label path="text">
                      Text</form:label></td>
                    <td><form:input path="text"/></td>
                    <td><form:errors path="text" /></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Submit"/></td>
                </tr>
            </table>
        </form:form>
    </body>
</html>