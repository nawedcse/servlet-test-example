<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>login Page</title>
		<style>
		h1{
		color:green;
		text-decoration: underline;
		text-align: center;

		}
		form{
		border:2px solid green;
		margin: 5em;
		}
		p{
		text-align:center;
		font-size: 16pt;
		}
		</style>
	</head>
	<body>
		<h1>Login Page</h1>
		<form action="LoginServlet" method="post">
			<table id="loginform" cellspacing="20" align="center">
			<tr><td>USER ID :</td><td><input type="text" id="id" name="userid" placeholder="Enter only digits" required="required"></td></tr>
			<tr><td>PASSWORD:</td><td> <input type="password" name="password" placeholder="Enter your password" required></td></tr>
			<tr><td colspan=5 align="center"><button type="submit">Login</button></td></tr></table>
		</form>
	</body>
</html>
