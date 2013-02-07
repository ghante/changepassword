<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="header.jsp" %>
<html>
	<head>
		<title>TrailBlazers - Admin</title>
	</head>
	<body>
		<h1>You are in Admin page</h1>
		<h2>*Manage your bike parts here.</h2>
		<p><a href="item">Add a item</a></p>
		<p><h2>*All orders</h2></p>
		<table id="prettyTable">
            <thead>
                <tr>
                    <th>User</th>
                    <th>Order</th>
                    <th>Order Time</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="order" items="${reserveOrders}" varStatus="row">
                <tr>
                    <td><c:out value="${order.account.account_name}"/></td>
                    <td><c:out value="${order.item.name}"/></td>
                    <td><c:out value="${order.reserve_time}"/></td>
                </tr>
             </c:forEach>
            </tbody>
        </table>
	</body>
</html>