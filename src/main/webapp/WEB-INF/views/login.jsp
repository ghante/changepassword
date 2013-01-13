<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<%@ include file="header.jsp" %>
<html>
	<head>
		<title>TrailBlazers - Login</title>
	</head>
<body onload='document.f.j_username.focus();'>
	<h1 id="TrailBlazers - Login">Login with Username and Password</h1>

	<c:if test="${not empty error}">
		<div class="errorblock">
			Your login attempt was not successful, try again.<br /> Caused :
			${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
		</div>
	</c:if>

	<form name='f' action="<c:url value='j_spring_security_check' />" method="post">
	    <table id="login">
	            <tr>
				    <td>User:<input type='text' name='j_username'></td>
				</tr>
				<tr>
				    <td>Password:<input type='password' name='j_password' /></td>
			    </tr>
			    <tr>
				    <td><div class="field"><input name="submit" type="submit" value="Submit" />
				    <div class="field"><input name="reset" type="reset" /></td>
		        </tr>
	    </table>
	</form>
</body>
</html>