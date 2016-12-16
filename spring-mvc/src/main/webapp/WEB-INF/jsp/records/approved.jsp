
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib prefix="carpark" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<carpark:pagetemplate title="Approved record form">
<jsp:attribute name="body">


    <form:form method="post" action="${pageContext.request.contextPath}${formSubmitUrl}"
               modelAttribute="recordDTO" cssClass="form-horizontal">

        <div class="form-group ${comment_error?'has-error':''}">
            <form:label path="comment" cssClass="col-sm-2 control-label">comment:</form:label>
            <div class="col-sm-10">
                <form:input path="comment" cssClass="form-control"/>
                <form:errors path="comment" cssClass="help-block"/>
            </div>
        </div>
        
        <button class="btn btn-primary" type="submit">Approved rent application</button>
    </form:form>

</jsp:attribute>
</carpark:pagetemplate>
