<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="request" value="Create Account"/>

<%@ include file="../header.jsp" %>

    <h2>Create a new account</h2>

	<form class="pretty_form" method="post">
        <label>
	        <span>Email Address</span>
	        <input type="text" />
	    </label>

        <label>
	        <span>Password</span>
	        <input type="password" />
	    </label>

	    <label>
	        <span>Name</span>
	        <input type="text" />
	    </label>

	    <label>
	        <span>Phone Number</span>
	        <input type="text" />
	    </label>

	    <label>
	        <span>Address</span>
	        <textarea></textarea>
	    </label>

        <label>
	        <input type="submit" />
	    </label>
	</form>

<%@ include file="../footer.jsp" %>
