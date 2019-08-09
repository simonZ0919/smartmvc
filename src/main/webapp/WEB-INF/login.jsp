<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	<form action="login.do" method="post">
		<fieldset>
			<legend>Login</legend>
			Username: <input name="username">
			<span style="color:red;">${login_failed}</span><br>
			Password: <input type="password" name="pwd"><br>
			<input type="submit" value="Confirm">
		</fieldset>
	</form>
</body>
</html>