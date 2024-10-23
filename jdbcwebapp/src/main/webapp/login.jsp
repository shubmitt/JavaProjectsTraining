<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
<title>
Login
</title>
</head>

<body>
<h1>Enter your credentials</h1>
<%if(request.getAttribute("errorMessage") != null){%>
<h2 style="color:red;"><%= request.getAttribute("errorMessage") %></h2>
<% }%>
<form method="POST" action="loginServlet">

<label for="email">Email</label> 
<input type="email" id="email" name="email"/>
<br/><br/>
<label for="password">Password</label> 
<input type="password" id="password" name="password"/>
<br/><br/>
<input type="submit" value="Submit"/>
</form>
</body>
</html>