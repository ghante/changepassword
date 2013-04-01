<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="pageTitle" scope="request" value="Create Account"/>

<%@ include file="../header.jsp" %>

    <div class="alert alert-info">
        Create a new account
    </div>

    <c:if test="${not empty validationMessage.errors}">
        <div id="resultsMessage" class="alert alert-error">
            ${validationMessage.errors}
        </div>
    </c:if>

	<form class="form-horizontal" action="/account/create" method="post">
        <div class="control-group">
            <label class="control-label" for="fld_email">Email</label>
            <div class="controls">
                <input type="text" id="fld_email" placeholder="somebody@something.com" name="email">
            </div>
        </div>

        <div class="control-group">
            <label class="control-label" for="fld_password">Password</label>
            <div class="controls">
                <input type="text" id="fld_password" placeholder="secret password" name="password">
            </div>
        </div>

        <div class="control-group">
            <label class="control-label" for="fld_name">Name</label>
            <div class="controls">
                <input type="text" id="fld_name" placeholder="Your Name" name="name">
            </div>
        </div>

        <div class="control-group">
            <label class="control-label" for="fld_phoneNumber">Phone Number</label>
            <div class="controls">
                <input type="text" id="fld_phoneNumber" placeholder="555-123456" name="phoneNumber">
            </div>
        </div>

        <div class="control-group">
            <div class="controls">
                <button type="submit" id="createAccount" value="Submit" class="btn btn-success">Create Account</button>
            </div>
        </div>

	</form>

<%@ include file="../footer.jsp" %>
