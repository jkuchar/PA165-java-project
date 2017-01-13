<%--
  Created by IntelliJ IDEA.
  User: Richard
  Date: 16.12.2016
  Time: 19:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="true" %>
<%@ taglib prefix="carpark" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<carpark:pagetemplate title="Application Approved Record Detail">
<jsp:attribute name="body">

    <table class="table">
        <thead>
        <tr>
            <th>Name</th>
            <th>Value</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>ID</td>
            <td>${record.id}</td>
        </tr>
        <tr>
            <td>Date</td>
            <td><c:out value="${record.created}"/></td>
        </tr>
        <tr>
            <td>Comment</td>
            <td><c:out value="${record.comment}"/></td>
        </tr>
        <tr>
            <td>User</td>
            <td><c:out value="${record.user.email}"/></td>
        </tr>
        <tr>
            <td>Car</td>
            <td><c:out value="${record.car.manufacturer} ${record.car.type}"/></td>
        </tr>
        <tr>
            <td>Vehicle registration plate</td>
            <td><c:out value="${record.car.regPlateNumber}"/></td>
        </tr>
        </tbody>
    </table>
    <p><a class="btn btn-lg btn-success" href="${pageContext.request.contextPath}/approved/list" role="button">
        <span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span> Back</a></p>

</jsp:attribute>
</carpark:pagetemplate>