<%-- 
    Author     : jkuchar
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib prefix="carpark" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<carpark:pagetemplate title="Record car event">
<jsp:attribute name="body">

    <c:if test="${currentState != null}">
        <h2>Currect state is "<c:out value="${currentState}" />"</h2>
    </c:if>
    <c:if test="${currentState == null}">
        <h2>Car is available </h2>
    </c:if>

    <p>These are next possible actions:</p>

    <ul>
        <c:forEach items="${possibleNextSteps}" var="possibleNextStep">
            <li>
                Register <carpark:a href="${possibleNextStep.url}" class="btn btn-default">
                    ${possibleNextStep}
                </carpark:a>
            </li>
        </c:forEach>
    </ul>

    <%----%>
    <%--<form:form method="post" action="${pageContext.request.contextPath}/car/create"--%>
               <%--modelAttribute="createCar" cssClass="form-horizontal">--%>
        <%--<div class="form-group ${manufacturer_error?'has-error':''}">--%>
            <%--<form:label path="manufacturer" cssClass="col-sm-2 control-label">Manufacturer</form:label>--%>
            <%--<div class="col-sm-10">--%>
                <%--<form:input path="manufacturer" cssClass="form-control"/>--%>
                <%--<form:errors path="manufacturer" cssClass="help-block"/>--%>
            <%--</div>--%>
        <%--</div>--%>
        <%--<div class="form-group ${type_error?'has-error':''}">--%>
            <%--<form:label path="type" cssClass="col-sm-2 control-label">Model</form:label>--%>
            <%--<div class="col-sm-10">--%>
                <%--<form:input path="type" cssClass="form-control"/>--%>
                <%--<form:errors path="type" cssClass="help-block"/>--%>
            <%--</div>--%>
        <%--</div>--%>
        <%--<div class="form-group ${regPlateNumber_error?'has-error':''}">--%>
            <%--<form:label path="regPlateNumber" cssClass="col-sm-2 control-label">Registration plate</form:label>--%>
            <%--<div class="col-sm-10">--%>
                <%--<form:input path="regPlateNumber" cssClass="form-control"/>--%>
                <%--<form:errors path="regPlateNumber" cssClass="help-block"/>--%>
            <%--</div>--%>
        <%--</div>--%>
        <%--<div class="form-group ${serialNumber_error?'has-error':''}">--%>
            <%--<form:label path="serialNumber" cssClass="col-sm-2 control-label">Serial Number</form:label>--%>
            <%--<div class="col-sm-10">--%>
                <%--<form:input path="serialNumber" cssClass="form-control"/>--%>
                <%--<form:errors path="serialNumber" cssClass="help-block"/>--%>
            <%--</div>--%>
        <%--</div>--%>
        <%--<div class="form-group ${seats_error?'has-error':''}" >--%>
            <%--<form:label path="seats" cssClass="col-sm-2 control-label">Seats</form:label>--%>
            <%--<div class="col-sm-10">--%>
                <%--<form:input path="seats" cssClass="form-control"/>--%>
                <%--<form:errors path="seats" cssClass="help-block"/>--%>
            <%--</div>--%>
        <%--</div>--%>

        <%--<button class="btn btn-primary" type="submit">Create car</button>--%>
    <%--</form:form>--%>

</jsp:attribute>
</carpark:pagetemplate>
