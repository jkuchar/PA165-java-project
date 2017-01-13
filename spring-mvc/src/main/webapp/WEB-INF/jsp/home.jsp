<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="carpark" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<carpark:pagetemplate>
    <jsp:attribute name="body">
        <div class="jumbotron">
            <h1><fmt:message key="home.welcome"/></h1>
            <p><a class="btn btn-lg btn-success" href="${pageContext.request.contextPath}/car/list/all"
                  role="button"><fmt:message key="home.viewCars"/></a></p>
        </div>
    </jsp:attribute>
</carpark:pagetemplate>
