<%--
  Created by IntelliJ IDEA.
  User: Richard
  Date: 15.12.2016
  Time: 17:43
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="carpark" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<carpark:pagetemplate title="Application Approved Records">
<jsp:attribute name="body">

    <table class="table">
        <thead>
        <tr>
            <th>ID</th>
            <th>User name</th>
            <th>Car manufacturer</th>
            <th>Creation date</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${records}" var="record">
            <tr>
                <td>${record.id}</td>
                <td><c:out value="${record.user.firstName} ${record.user.lastName}"/></td>
                <td><c:out value="${record.car.manufacturer} ${record.car.type}"/></td>
                <td><fmt:formatDate value="${record.created}" pattern="yyyy-MM-dd"/></td>
                <td>
                    <carpark:a href="/approved/view/${record.id}" class="btn btn-primary">View</carpark:a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</jsp:attribute>
</carpark:pagetemplate>