<%-- 
    Document   : list
    Created on : 16.12.2016
    Author     : charlliz
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="carpark" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<carpark:pagetemplate title="Rent records">
<jsp:attribute name="body">

    <div class="btn-group" role="group" aria-label="filter">
        <carpark:a href="/rent/list/all" class="btn btn-default ${filter=='all'?'active':''}">All</carpark:a>
        <carpark:a href="/rent/list/my" class="btn btn-default ${filter=='my'?'active':''}">My</carpark:a>
    </div>

    <table class="table">
        <thead>
        <tr>
            <sec:authorize access="hasRole('ROLE_MANAGER')">
                <th>Id</th>
            </sec:authorize>
            <th>User</th>
            <th>Car</th>
            <th>Rented on</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${records}" var="record">
            <tr>
                <sec:authorize access="hasRole('ROLE_MANAGER')">
                    <td>${record.id}</td>
                </sec:authorize>
                <td><c:out value="${record.user.email}"/></td>
                <td><c:out value="${record.car.manufacturer} ${record.car.type}"/></td>
                <td><fmt:formatDate value="${record.created}" pattern="yyyy-MM-dd"/></td>
                <td><carpark:a href="/rent/view/${record.id}" class="btn btn-primary">View</carpark:a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>


</jsp:attribute>
</carpark:pagetemplate>
