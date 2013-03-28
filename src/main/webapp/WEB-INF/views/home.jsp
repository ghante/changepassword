<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="header.jsp" %>

		<table class="table table-striped">
		    <thead>
		        <tr>
		            <th>Name</th>
		            <th>Price</th>
		            <th>Description</th>
		            <th>Type</th>
		            <th>Quantity</th>
		            <th></th>
		        </tr>
		    </thead>
		    <tbody>
		    <c:forEach var="itemEntry" items="${itemGrid.itemMap}" varStatus="row">
		        <tr>
		        <form:form action="reserve" method="post" commandName="itemCommand">
		            <td><c:out value="${itemEntry.value.name}"/></td>
		            <td><c:out value="${itemEntry.value.price}"/></td>
		            <td><c:out value="${itemEntry.value.description}"/></td>
		            <td><c:out value="${itemEntry.value.type}"/></td>
		            <td><c:out value="${itemEntry.value.quantity}"/></td>
		            <form:hidden path="itemId" value="${itemEntry.value.itemId}"/>
		            <td>
                        <button class="btn btn-success" type="submit" name="reserve" value="Reserve Item">Reserve Item</button>
                    </td>
		        </form:form>
		        </tr>
		     </c:forEach>
		    </tbody>
		</table>

<%@ include file="footer.jsp" %>