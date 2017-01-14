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

<carpark:pagetemplate title="Approved rent application detail">
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
            <td>Id</td>
            <td>${record.id}</td>
        </tr>
        <tr>
            <td>Approved on</td>
            <td><c:out value="${record.created}"/></td>
        </tr>
        <tr>
            <td>Comment</td>
            <td><c:out value="${record.comment}"/></td>
        </tr>
        <tr>
            <td>User</td>
            <td><c:out value="${record.user.email}"/>                    
                <a class="btn btn-info" href="${pageContext.request.contextPath}/user/view/${record.user.id}"
                    role="button">
                        <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
                        View user detail</a>
            </td>
        </tr>
        <tr>
            <td>Car</td>
            <td><c:out value="${record.car.manufacturer} ${record.car.type}"/>
                <a class="btn btn-info" href="${pageContext.request.contextPath}/car/view/${record.car.id}"
                    role="button">
                    <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
                    View car detail</a>
            </td>
        </tr>
        <tr>
            <td>Vehicle registration plate</td>
            <td><c:out value="${record.car.regPlateNumber}"/></td>
        </tr>
        <tr>
            <td>Approved from</td>
            <td><c:out value="${record.from}"/></td>
        </tr>
        <tr>
            <td>Approved to</td>
            <td><c:out value="${record.to}"/></td>
        </tr>
        </tbody>
    </table>

</jsp:attribute>
</carpark:pagetemplate>