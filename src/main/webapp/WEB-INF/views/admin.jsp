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

<p>
    <h2>*All orders</h2>
</p>
<table id="prettyTable">
    <tbody>
    <c:forEach var="order" items="${reserveOrders}" varStatus="row">
        <tr>
            <form:form action="admin" method="post">
                <td>
                    <a href="/userProfile/${order.account.account_name}">
                        <c:out value="${order.account.account_name}"/>
                    </a>
                </td>
                <td><c:out value="${order.item.name}"/></td>
                <td><c:out value="${order.reserve_time}"/></td>
                <td>
                    <select name="state">
                        <c:forEach var="statusoption" items="${order.statusOptions}" varStatus="row">
                            <option ${order.status == statusoption ? 'selected="selected"' : ""}>${statusoption}</option>
                        </c:forEach>
                    </select>
                </td>
                <td><textarea name="note">${order.note}</textarea></td>
                <td>
                    <input type="hidden" value="${order.orderId}" name="orderId" />
                    <input type="submit" value="Save Changes" name="save">
                </td>
            </form:form>
        </tr>
    </c:forEach>
    </tbody>
    <thead>
    <tr>
        <th>User</th>
        <th>Order</th>
        <th>Order Time</th>
        <th>Status</th>
        <th>Note</th>
        <th></th>
    </tr>
    </thead>
</table>
<%@ include file="footer.jsp" %>