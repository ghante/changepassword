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
   <link rel="stylesheet" href="<c:url value='/scripts/lib/bootstrap/css/bootstrap.css' />" type="text/css" />
   <link rel="stylesheet" href="<c:url value='/styles/forms.css' />" type="text/css" />
   <script type="text/javascript" src="<c:url value='/scripts/lib/prototype.js' />"></script>
</head>
<body>

        <div class="navbar">
            <div class="navbar-inner">
                <a class="brand" href="<c:url value='/' />" class="nav_link">
                    <img width="20px;"src="<c:url value='/images/logo.png' />">
                    Trail Blazers
                </a>
                <ul class="nav">
                    <li><a href="<c:url value='/' />" class="nav_link">Home</a></li>
                    <li><a href="<c:url value='/admin' />" class="nav_link">Admin Profile</a></li>
                    <li><a href="<c:url value='/userProfile' />" class="nav_link">User Profile</a></li>
                    <li><a href="<c:url value='/account/create' />" class="nav_link">Create Account</a></li>
                    <security:authorize ifAnyGranted="ROLE_USER,ROLE_ADMIN">
                    <li><a href="<c:url value="j_spring_security_logout" />" class="nav_link"> Logout</a></li>
                    </security:authorize>
                </ul>
            </div>
        </div>

        <security:authorize ifAnyGranted="ROLE_USER,ROLE_ADMIN">
        <p>Welcome <security:authentication property="principal.username"/>!</p>
        </security:authorize>