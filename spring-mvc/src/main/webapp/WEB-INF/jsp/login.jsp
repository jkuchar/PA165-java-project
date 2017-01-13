<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="carpark" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<carpark:pagetemplate title="Login">
    <jsp:attribute name="body">
        <h2><fmt:message key="login.prompt"/></h2>
        <c:url value="/login" var="loginUrl"/>
        <form action="${loginUrl}" method="post">
            <c:if test="${param.logout != null}">
		        <fmt:message key="success.logout" var="success"/>
                <div class="alert alert-success alert-dismissable fade in">
                    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                    <strong>Success!</strong><c:out value=" ${success}"/>
                </div>
	        </c:if>
            <c:if test="${param.error != null}">
		        <fmt:message key="danger.login.wrong" var="danger"/>
                <div class="alert alert-danger alert-dismissable fade in">
                    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                    <strong>Danger!</strong><c:out value=" ${danger}"/>
                </div>
	        </c:if>
            <div class="form-group">
                <label for="username"><fmt:message key="login.email"/></label>
                <fmt:message key="login.emailPlaceholder" var="emailPlaceholder"/>
                <input type="text" class="form-control" name="username" id="username" placeholder="${emailPlaceholder}" autofocus>
            </div>
            <div class="form-group">
                <label for="password"><fmt:message key="login.password"/></label>
                <fmt:message key="login.passwordPlaceholder" var="passwordPlaceholder"/>
                <input type="password" class="form-control" name="password" id="password" placeholder="${passwordPlaceholder}">
            </div>
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
            <button type="submit" class="btn btn-primary"><fmt:message key="login.loginButton"/></button>
        </form>
    </jsp:attribute>
</carpark:pagetemplate>
