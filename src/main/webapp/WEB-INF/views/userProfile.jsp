<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="header.jsp" %>
<html>
	<head>
		<title>TrailBlazers - UserProfile</title>
	</head>
	<body>
		<h1>You are in User page</h1>
		<h2>These are your details</h2>
        <p id="userDetails">
            ${userDetail.account_name} - ${userDetail.emailAddress}<br />
            ${userDetail.address}
        </p>
		<h2>These are your reserved orders</h2>
		<table id="prettyTable">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Description</th>
                    <th>Type</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="itemEntry" items="${itemGrid.itemMap}" varStatus="row">
                <tr>
                    <td><c:out value="${itemEntry.value.name}"/></td>
                    <td><c:out value="${itemEntry.value.price}"/></td>
                    <td><c:out value="${itemEntry.value.description}"/></td>
                    <td><c:out value="${itemEntry.value.type}"/></td>
                </tr>
             </c:forEach>
            </tbody>
        </table>

<%@ include file="footer.jsp" %>