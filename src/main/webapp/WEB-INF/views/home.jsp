<%@ include file="header.jsp" %>
<html>
	<head>
		<title>TrailBlazers</title>
	</head>
	<body>
		<!--[if lt IE 9]>
			<div class="legacy-browser">You are using a Legacy Browser - it is not supported. Please upgrade to <a href="http://windows.microsoft.com/en-US/internet-explorer/downloads/ie-9/worldwide-languages">IE9</a>, Firefox, Safari, Chrome or Opera.</div>
		<![endif]-->
		<table id="prettyTable">
		    <thead>
		        <tr>
		            <th>Name</th>
		            <th>Price</th>
		            <th>Description</th>
		            <th>Type</th>
		            <th>Quantity</th>
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
		            <td><input type="submit" value="Reserve Item" id="reserve" name="reserve"></td>
		        </form:form>
		        </tr>
		     </c:forEach>
		    </tbody>
		</table>
	</body>
</html>
