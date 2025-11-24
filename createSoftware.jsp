<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>Create Software</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<%
    HttpSession session = request.getSession(false);
    String role = session != null ? (String) session.getAttribute("role") : null;
    if (role == null || !"Admin".equals(role)) {
%>
    <h3>Forbidden - Admins only</h3>
    <p><a href="<%=request.getContextPath()%>/login">Login</a></p>
<%
        return;
    }
%>

    <h2>Create Software</h2>
    <form method="post" action="${pageContext.request.contextPath}/software">
        <label>Software Name: <input name="name" required></label><br/><br/>
        <label>Description: <br/><textarea name="description" rows="4" cols="50"></textarea></label><br/><br/>
        Access Levels:<br/>
        <label><input type="checkbox" name="accessLevels" value="Read"> Read</label>
        <label><input type="checkbox" name="accessLevels" value="Write"> Write</label>
        <label><input type="checkbox" name="accessLevels" value="Admin"> Admin</label><br/><br/>
        <button type="submit">Add Software</button>
    </form>

    <p><a href="<%=request.getContextPath()%>/">Home</a></p>
</body>
</html>
