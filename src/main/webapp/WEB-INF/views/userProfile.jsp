<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="request" value="User Profile"/>
<%@ include file="header.jsp" %>

<h2>Your details</h2>
        <div id="user-details" class="well">
            ${userDetail.account_name} - ${userDetail.email_address}<br />
        </div>

		<h2>Your Orders</h2>
		<table class="table table-striped">
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