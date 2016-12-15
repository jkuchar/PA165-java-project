<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib prefix="carpark" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<carpark:pagetemplate title="Car audit log items">

<jsp:attribute name="body">
    <carpark:logitemlist logItems="${logItems}" />

    <%--move to head--%>
    <style type="text/css">
        table .this-is-important {
            font-weight: bold;
        }
    </style>
</jsp:attribute>
</carpark:pagetemplate>
