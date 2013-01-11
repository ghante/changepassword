<%@ include file="header.jsp" %>
<html>
	<head>
		<title>Frames</title>
		<script type="text/javascript" src="<c:url value='/scripts/lib/RowSelector.js' />"></script>
		<script type="text/javascript" src="<c:url value='/scripts/frame.js' />"></script>
	</head>
	<body>
		<!--[if lt IE 9]>
			<div class="legacy-browser">You are using a Legacy Browser - it is not supported. Please upgrade to <a href="http://windows.microsoft.com/en-US/internet-explorer/downloads/ie-9/worldwide-languages">IE9</a>, Firefox, Safari, Chrome or Opera.</div>
		<![endif]-->
		<h1>Frames</h1>
		<form:form name="addFrame" action="" method="post" modelAttribute="frameCommand" onsubmit="return validateForm()">
			<fieldset class="fieldcontainer">
				<legend>New Frame</legend>

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

				<div class="field">
					<form:label for="description" path="description">Description:</form:label>
					<form:errors path="description" cssClass="errors" />
					<form:textarea path="description" />
				</div>

				<div class="field vertical">
					<input type="submit" value="Create new frame">
				</div>

			</fieldset>
		</form:form>
		<form:form action="" method="put" modelAttribute="frameGrid">
			<table>
				<thead><tr><th><input type="checkbox" class="toggleAll" /></th><th>Name</th><th>Price</th><th>Description</th></tr></thead>
				<tbody>
					<c:forEach var="frameEntry" items="${frameGrid.frameMap}" varStatus="row">
						<tr>
							<td>
								<form:input cssClass="disablable-hidden" disabled="${!frameEntry.value.selected}" path="frameMap[${frameEntry.key}].id" />
								<c:if test="${frameMap[frameEntry.key].selected}">
									<input type="checkbox" checked="checked" class="rowSelector" />
								</c:if>
								<c:if test="${!frameMap[frameEntry.key].selected}">
									<input type="checkbox" class="rowSelector" />
								</c:if>
							</td>
							<td>
								<form:errors path="frameMap[${frameEntry.key}].name" cssClass="errors" />
								<form:input disabled="${!frameEntry.value.selected}" path="frameMap[${frameEntry.key}].name" />
							</td>
							<td>
								<form:errors path="frameMap[${frameEntry.key}].price" cssClass="errors" />
								<form:input disabled="${!frameEntry.value.selected}" path="frameMap[${frameEntry.key}].price" />
							</td>
							<td>
								<form:errors path="frameMap[${frameEntry.key}].description" cssClass="errors" />
								<form:input disabled="${!frameEntry.value.selected}" path="frameMap[${frameEntry.key}].description" />
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<p>
				<input type="submit" value="Update all enabled frames">
			</p>
		</form:form>
	</body>
</html>
