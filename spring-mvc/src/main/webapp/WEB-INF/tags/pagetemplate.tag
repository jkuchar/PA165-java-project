<%@ tag pageEncoding="utf-8" dynamic-attributes="dynattrs" trimDirectiveWhitespaces="true" %>
<%@ attribute name="title" required="false" %>
<%@ attribute name="head" fragment="true" %>
<%@ attribute name="body" fragment="true" required="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="carpark" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="${pageContext.request.locale}">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><c:out value="${title}"/></title>
    <!-- bootstrap loaded from content delivery network -->
    <!-- for favicon uncomment
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon">
    -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css"  crossorigin="anonymous">
    <jsp:invoke fragment="head"/>
</head>
<body>
<!-- navigation bar -->
<nav class="navbar navbar-inverse navbar-static-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${pageContext.request.contextPath}"><f:message key="navigation.project"/></a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li><carpark:a href="/"><f:message key="navigation.home"/></carpark:a></li>
                <li><carpark:a href="/user/list"><f:message key="navigation.users"/></carpark:a></li>
                <li><carpark:a href="/car/list"><f:message key="navigation.cars"/></carpark:a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><f:message key="navigation.records"/><b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><carpark:a href="/application/list"><f:message key="navigation.records.application"/></carpark:a></li>
                        <li><carpark:a href="/approved/list"><f:message key="navigation.records.approved"/></carpark:a></li>
                        <li><carpark:a href="/rent/list"><f:message key="navigation.records.rent"/></carpark:a></li>
                        <li><carpark:a href="/return/list"><f:message key="navigation.records.return"/></carpark:a></li>
                        <li role="separator" class="divider"></li>
                        <li><carpark:a href="/rejected/list"><f:message key="navigation.records.rejected"/></carpark:a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><f:message key="navigation.docs"/><b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li class="dropdown-header">Javadocs</li>
                        <li><a href="http://docs.oracle.com/javase/8/docs/api/">JDK 8 API</a></li>
                        <li><a href="http://docs.oracle.com/javaee/6/api/">Java EE 6 API</a></li>
                        <li><a href="http://docs.spring.io/spring/docs/current/javadoc-api/">Spring API</a></li>
                        <li role="separator" class="divider"></li>
                        <li class="dropdown-header">Other</li>
                        <li><a href="http://getbootstrap.com/css/">Bootstrap CSS</a></li>
                        <li><a href="http://getbootstrap.com/components/">Bootstrap components</a></li>
                    </ul>
                </li>
                <c:choose>
                    <c:when test="${not empty authenticatedUser}">
                        <li><carpark:a href="/user/detail/${authenticatedUser.userName}"><f:message key="navigation.profile"/></carpark:a></li>
                    </c:when>
                    <c:otherwise>
                        <li><carpark:a href="/login"><f:message key="navigation.login"/></carpark:a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>

<div class="container">

    <!-- page title -->
    <c:if test="${not empty title}">
        <div class="page-header">
            <h1><c:out value="${title}"/></h1>
        </div>
    </c:if>

    <!-- authenticated user info -->
    <c:if test="${not empty authenticatedUser}">
        <div class="row">
            <div class="col-xs-6 col-sm-8 col-md-9 col-lg-10"></div>
            <div class="col-xs-6 col-sm-4 col-md-3 col-lg-2">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <c:out value="${authenticatedUser.givenName} ${authenticatedUser.surname}"/>
                    </div>
                </div>
            </div>
        </div>
    </c:if>

    <!-- alerts -->
    <c:if test="${not empty alert_danger}">
        <div class="alert alert-danger" role="alert">
            <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
            <c:out value="${alert_danger}"/></div>
    </c:if>
    <c:if test="${not empty alert_info}">
        <div class="alert alert-info" role="alert"><c:out value="${alert_info}"/></div>
    </c:if>
    <c:if test="${not empty alert_success}">
        <div class="alert alert-success" role="alert"><c:out value="${alert_success}"/></div>
    </c:if>
    <c:if test="${not empty alert_warning}">
        <div class="alert alert-warning" role="alert"><c:out value="${alert_warning}"/></div>
    </c:if>

    <!-- page body -->
    <jsp:invoke fragment="body"/>

    <!-- footer -->
    <footer class="footer">
        <p>&copy;&nbsp;<%=java.time.Year.now().toString()%>&nbsp;Masaryk University</p>
    </footer>
</div>
<!-- javascripts placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>
