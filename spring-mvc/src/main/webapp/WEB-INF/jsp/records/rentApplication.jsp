<%-- 
    Author     : jkuchar
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="true" %>
<%@ taglib prefix="carpark" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<carpark:pagetemplate title="Rent application form">
<jsp:attribute name="body">


    <form:form method="post" action="${pageContext.request.contextPath}${formSubmitUrl}"
               modelAttribute="recordDTO" cssClass="form-horizontal">
        Dates should be in format "dd. mm. YYYY".
        <div class="form-group ${manufacturer_error?'has-error':''}">
            <form:label path="from" cssClass="col-sm-2 control-label">From date:</form:label>
            <div class="col-sm-10">
                <form:input path="from" cssClass="form-control"/>
                <form:errors path="from" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${manufacturer_error?'has-error':''}">
            <form:label path="to" cssClass="col-sm-2 control-label">to date:</form:label>
            <div class="col-sm-10">
                <form:input path="to" cssClass="form-control"/>
                <form:errors path="to" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${manufacturer_error?'has-error':''}">
            <form:label path="comment" cssClass="col-sm-2 control-label">comment:</form:label>
            <div class="col-sm-10">
                <form:input path="comment" cssClass="form-control"/>
                <form:errors path="comment" cssClass="help-block"/>
            </div>
        </div>
        

        <button class="btn btn-primary" type="submit">Send rent applicaton form</button>
    </form:form>

</jsp:attribute>
</carpark:pagetemplate>
