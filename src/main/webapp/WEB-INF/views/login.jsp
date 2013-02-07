<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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

	<form class="pretty_form" name='f' action="<c:url value='j_spring_security_check' />" method="post">
	    <label>
	        <span>User</span>
	        <input type='text' name='j_username'></td>
	    </label>

	    <label>
	        <span>Password</span>
	        <input type='password' name='j_password' />
	    </label>

	    <label>
	        <input name="submit" type="submit" value="Submit" />
	        <input name="reset" type="reset" value="Reset" />
		</label>

	</form>
<%@ include file="footer.jsp" %>