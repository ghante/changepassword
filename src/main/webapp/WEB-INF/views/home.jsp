<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>TrailBlazers</title>
		<link rel="stylesheet" href="<c:url value='/styles/default.css' />" type="text/css" />
		<script type="text/javascript" src="<c:url value='/scripts/lib/prototype.js' />"></script>
	</head>
	<body>
	<p><a href="<c:url value='/admin' />" class="afr">Admin</a></p>
		<!--[if lt IE 9]>
			<div class="legacy-browser">You are using a Legacy Browser - it is not supported. Please upgrade to <a href="http://windows.microsoft.com/en-US/internet-explorer/downloads/ie-9/worldwide-languages">IE9</a>, Firefox, Safari, Chrome or Opera.</div>
		<![endif]-->
		<h1><div class="heading">Welcome to Trail Blazers !</div></h1>
		<h2>Your chance to build a custom bike of your dreams.</h2>
	</body>
</html>
