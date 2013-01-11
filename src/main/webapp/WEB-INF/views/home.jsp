<%@ include file="header.jsp" %>
<html>
	<head>
		<title>TrailBlazers</title>
	</head>
	<body>
		<!--[if lt IE 9]>
			<div class="legacy-browser">You are using a Legacy Browser - it is not supported. Please upgrade to <a href="http://windows.microsoft.com/en-US/internet-explorer/downloads/ie-9/worldwide-languages">IE9</a>, Firefox, Safari, Chrome or Opera.</div>
		<![endif]-->
		<table id="homepage">
		    <thead>
		        <tr>
		            <th>Name</th>
		            <th>Price</th>
		            <th>Description</th>
		            <th>Type</th>
		        </tr>
		    </thead>
		    <tbody>
		    <c:forEach var="itemMap" items="${itemGrid.itemMap}" varStatus="row">
		        <tr>
		            <td><c:out value="${itemMap.value.name}"/></td>
		            <td><c:out value="${itemMap.value.price}"/></td>
		            <td><c:out value="${itemMap.value.description}"/></td>
		            <td><c:out value="${itemMap.value.type}"/></td>
		        </tr>
		     </c:forEach>
		    </tbody>
		</table>
	</body>
</html>
