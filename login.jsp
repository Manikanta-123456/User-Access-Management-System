<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>Login</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <h2>Login</h2>

    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>

    <form method="post" action="${pageContext.request.contextPath}/login">
        <label>Username: <input name="username" required></label><br/><br/>
        <label>Password: <input type="password" name="password" required></label><br/><br/>
        <button type="submit">Login</button>
    </form>

    <p>No account? <a href="${pageContext.request.contextPath}/signup">Sign up</a></p>
</body>
</html>
