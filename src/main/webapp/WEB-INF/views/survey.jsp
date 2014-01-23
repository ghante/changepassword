<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="pageTitle" scope="request" value="Survey"/>

<form id="survey" action="/survey" method="post">
    <div id="surveyRating">
        <label>1. How likely is it that you would recommend Freewheelers to a friend or colleague? *</label>
        <table>
            <tr>
                <td></td>
                <td>0 - Not at all likely</td>
                <td>1</td>
                <td>2</td>
                <td>3</td>
                <td>4</td>
                <td>5 - Neutral</td>
                <td>6</td>
                <td>7</td>
                <td>8</td>
                <td>9</td>
                <td>10 - Extremely likely</td>
            </tr>
            <tr>
                <td></td>
                <td><input id="rating_0" type="radio" name="survey_rating" value="0"/></td>
                <td><input id="rating_1" type="radio" name="survey_rating" value="1"/></td>
                <td><input id="rating_2" type="radio" name="survey_rating" value="2"/></td>
                <td><input id="rating_3" type="radio" name="survey_rating" value="3"/></td>
                <td><input id="rating_4" type="radio" name="survey_rating" value="4"/></td>
                <td><input id="rating_5" type="radio" name="survey_rating" value="5"/></td>
                <td><input id="rating_6" type="radio" name="survey_rating" value="6"/></td>
                <td><input id="rating_7" type="radio" name="survey_rating" value="7"/></td>
                <td><input id="rating_8" type="radio" name="survey_rating" value="8"/></td>
                <td><input id="rating_9" type="radio" name="survey_rating" value="9"/></td>
                <td><input id="rating_10" type="radio" name="survey_rating" value="10"/></td>
            </tr>
        </table>
    </div>

    <div id="surveyComment">
        <label>2. How can we improve our offering?</label>
        <div><input name="survey_comment"/></div>
    </div>

    <c:if test="${validation.mandatoryFieldMissing}">
        <div id="validationMessage">
            * Please rate us.
        </div>
    </c:if>

    <button type="submit" value="Submit">Done</button>
</form>

<%@ include file="footer.jsp" %>