<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="pageTitle" scope="request" value="Create Account"/>

<%@ include file="../header.jsp" %>

    <h2>Create a new account</h2>

    ${validationMessage.errors}

	<form class="pretty_form" action="/account/create" method="post">
        <label>
	        <span>Email Address</span>
	        <input id="fld_email" type="text" name="email"  />
	    </label>

        <label>
	        <span>Password</span>
	        <input id="fld_password" type="text" name="password" />
	    </label>

	    <label>
	        <span>Name</span>
	        <input id="fld_name" type="text" name="name" />
	    </label>

	    <label>
	        <span>Phone Number</span>
	        <input id="fld_phoneNumber" type="text" name="phoneNumber" />
	    </label>

	    <label>
	        <span>Address</span>
	        <textarea id="fld_address" name="address"></textarea>
	    </label>

        <label>
	        <input id="fld_submit" type="submit" value="Submit" />
	    </label>
	</form>

<%@ include file="../footer.jsp" %>
