<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="request" value="Admin Profile"/>
<%@ include file="header.jsp" %>

<h2>Admin Profile</h2>

<div class="well">
    <a class="btn" href="item"><i class="icon-briefcase"></i> Manage items</a>
</div>

<h2>All orders</h2>

<table class="table table-striped">
    <tbody>
    <c:forEach var="order" items="${reserveOrders}" varStatus="row">
        <tr>
            <form:form action="admin" method="post">
                <td>
                    <a class="btn" href="/userProfile/${order.account.account_name}">
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
                <td><textarea rows="3" name="note">${order.note}</textarea></td>
                <td>
                    <input type="hidden" value="${order.orderId}" name="orderId" />
                    <button type="submit" class="btn btn-warning" name="save" value="Save Changes">Save Changes</button>
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