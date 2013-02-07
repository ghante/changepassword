<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="header.jsp" %>
<html>
	<head>
		<title>TrailBlazers - Items</title>
		<script type="text/javascript" src="<c:url value='/scripts/lib/RowSelector.js' />"></script>
		<script type="text/javascript" src="<c:url value='/scripts/item.js' />"></script>
	</head>
	<body>
		<!--[if lt IE 9]>
			<div class="legacy-browser">You are using a Legacy Browser - it is not supported. Please upgrade to <a href="http://windows.microsoft.com/en-US/internet-explorer/downloads/ie-9/worldwide-languages">IE9</a>, Firefox, Safari, Chrome or Opera.</div>
		<![endif]-->
		<h1>Items</h1>
		<form:form name="addItem" action="" method="post" modelAttribute="itemCommand" onsubmit="return validateForm()">
			<fieldset class="fieldcontainer">
				<legend>New Item</legend>

				<div class="block">
					<div class="field">
						<form:label for="name" path="name">Name:</form:label>
						<form:errors path="name" cssClass="errors" />
						<form:input path="name" />
					</div>
	
					<div class="field vertical">
						<form:label for="price" path="price">Price:</form:label>
						<form:errors path="price" cssClass="errors" />
						<form:input path="price" />
					</div>
				</div>

				<div class="block">
						<div class="field">
                    	    <form:label for="type" path="type">Type:</form:label>
      					    <form:errors path="type" cssClass="errors" />
           				    <form:select path="type">
           				        <form:option value="" label="Select" />
           				        <form:options items="${itemTypes}"/>
           				    </form:select>
                        </div>
				</div>

                <div class="block">
				    <div class="field">
					    <form:label for="description" path="description">Description:</form:label>
					    <form:errors path="description" cssClass="errors" />
					    <form:textarea path="description" />
				    </div>

                    <div class="field">
                        <form:label for="quantity" path="quantity">Quantity:</form:label>
                        <form:errors path="quantity" cssClass="errors" />
                        <form:input path="quantity" />
                    </div>

				    <div class="field vertical">
                	    <input type="submit" value="Create new item" id="createItem">
               	    </div>
				</div>

			</fieldset>
		</form:form>
		<form:form action="" method="post" modelAttribute="itemGrid">
			<table>
				<thead><tr><th><input type="checkbox" class="toggleAll" /></th>
				<th>Name</th>
				<th>Price</th>
				<th>Description</th>
				<th>ItemType</th>
				<th>Quantity</th>
				</tr></thead>
				<tbody>
					<c:forEach var="itemEntry" items="${itemGrid.itemMap}" varStatus="row">
						<tr>
							<td>
								<form:input cssClass="disablable-hidden" disabled="${!itemEntry.value.selected}" path="itemMap[${itemEntry.key}].itemId" />
								<c:if test="${itemMap[itemEntry.key].selected}">
									<input type="checkbox" checked="checked" class="rowSelector" />
								</c:if>
								<c:if test="${!itemMap[itemEntry.key].selected}">
									<input type="checkbox" class="rowSelector" />
								</c:if>
							</td>
							<td>
								<form:errors path="itemMap[${itemEntry.key}].name" cssClass="errors" />
								<form:input disabled="${!itemEntry.value.selected}" path="itemMap[${itemEntry.key}].name" />
							</td>
							<td>
								<form:errors path="itemMap[${itemEntry.key}].price" cssClass="errors" />
								<form:input disabled="${!itemEntry.value.selected}" path="itemMap[${itemEntry.key}].price" />
							</td>
							<td>
								<form:errors path="itemMap[${itemEntry.key}].description" cssClass="errors" />
								<form:input disabled="${!itemEntry.value.selected}" path="itemMap[${itemEntry.key}].description" />
							</td>
							<td>
                            	<form:errors path="itemMap[${itemEntry.key}].type" cssClass="errors" />
       							<form:input disabled="${!itemEntry.value.selected}" path="itemMap[${itemEntry.key}].type" />
     					    </td>
     					    <td>
                                <form:errors path="itemMap[${itemEntry.key}].quantity" cssClass="errors" />
                                <form:input disabled="${!itemEntry.value.selected}" path="itemMap[${itemEntry.key}].quantity" />
                            </td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<p>
				<input type="submit" value="Update all enabled items" name="update">
				<input type="submit" value="Delete all enabled items" name="delete">
			</p>
		</form:form>
	</body>
</html>
