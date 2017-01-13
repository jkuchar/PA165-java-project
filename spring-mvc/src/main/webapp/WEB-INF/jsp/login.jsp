<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="carpark" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<carpark:pagetemplate title="Login">
    <jsp:attribute name="body">
        <h2><fmt:message key="login.prompt"/></h2>
        <form method="post" action="${pageContext.request.contextPath}/login">
            <div class="form-group">
                <label for="email"><fmt:message key="login.email"/></label>
                <fmt:message key="login.emailPlaceholder" var="emailPlaceholder"/>
                <input type="text" class="form-control" name="email" id="email" placeholder="${emailPlaceholder}" autofocus>
            </div>
            <div class="form-group">
                <label for="password"><fmt:message key="login.password"/></label>
                <fmt:message key="login.passwordPlaceholder" var="passwordPlaceholder"/>
                <input type="password" class="form-control" name="password" id="password" placeholder="${passwordPlaceholder}">
            </div>
            <button type="submit" class="btn btn-primary"><fmt:message key="login.loginButton"/></button>
        </form>
    </jsp:attribute>
</carpark:pagetemplate>
