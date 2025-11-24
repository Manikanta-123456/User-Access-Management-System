<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>Sign Up</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <h2>Sign Up</h2>
    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>

    <form method="post" action="${pageContext.request.contextPath}/signup">
        <label>Username: <input name="username" required></label><br/><br/>
        <label>Password: <input type="password" name="password" required></label><br/><br/>
        <button type="submit">Sign Up</button>
    </form>

    <p>Already have an account? <a href="${pageContext.request.contextPath}/login">Login</a></p>
</body>
</html>
