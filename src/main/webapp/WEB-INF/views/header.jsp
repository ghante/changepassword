<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
   <meta charset="utf-8">
   <title>${pageTitle}</title>
   <link rel="stylesheet" href="<c:url value='/styles/default.css' />" type="text/css" />
   <script type="text/javascript" src="<c:url value='/scripts/lib/prototype.js' />"></script>
</head>
<body>
        <div class="header_container">
            <ul>
                <li><a href="<c:url value='/admin' />" class="nav_link">Admin Profile</a></li>
                <li><a href="<c:url value='/' />" class="nav_link">Home</a></li>
                <li><a href="<c:url value='/userProfile' />" class="nav_link">User Profile</a></li>
                <li><a href="<c:url value='/account/create' />" class="nav_link">Create Account</a></li>
            </ul>
            <security:authorize ifAnyGranted="ROLE_ADMIN">
                  Welcome <security:authentication property="principal.username"/>!
                  <p><a href="<c:url value="j_spring_security_logout" />" class="afr"> Logout</a>
            </security:authorize>

            <security:authorize ifAnyGranted="ROLE_USER">
                   Welcome <security:authentication property="principal.username"/>!
                   <p><a href="<c:url value="j_spring_security_logout" />" class="afr"> Logout</a>
            </security:authorize>

            <h1><div class="heading">Trail Blazers</div></h1>
            <h2>Custom order bikes today !</h2>

        </div>
