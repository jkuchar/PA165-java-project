<%-- 
    Document   : list
    Created on : 14.12.2016
    Author     : charlliz
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<my:pagetemplate title="Cars">
<jsp:attribute name="body">

    <div class="btn-group" role="group" aria-label="filter">
        <my:a href="/car/list/all" class="btn btn-default ${filter=='all'?'active':''}">All</my:a>
        <my:a href="/car/list/ok" class="btn btn-default ${filter=='ok'?'active':''}">Ok</my:a>
        <my:a href="/car/list/servicing" class="btn btn-default ${filter=='servicing'?'active':''}">Servicing</my:a>
        <my:a href="/car/list/discarded" class="btn btn-default ${filter=='discarded'?'active':''}">Discarded</my:a>    
    </div>  

    <sec:authorize access="hasRole('ROLE_MANAGER')">
        <my:a href="/car/new" class="btn btn-primary">
        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
        Add new car
    </my:a>
    </sec:authorize>

    <table class="table">
        <thead>
        <tr>
            <sec:authorize access="hasRole('ROLE_MANAGER')">
                <th>Id</th>
            </sec:authorize>
            <th>Manufacturer</th>
            <th>Type</th>
            <th>Registration plate</th>
            <th>State</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${cars}" var="car">
            <tr>
                <sec:authorize access="hasRole('ROLE_MANAGER')">
                    <td>${car.id}</td>
                </sec:authorize>
                <td><c:out value="${car.manufacturer}"/></td>
                <td><c:out value="${car.type}"/></td>
                <td><c:out value="${car.regPlateNumber}"/></td>
                <td><c:out value="${car.state}"/></td>
                <td>
                    <my:a href="/car/view/${car.id}" class="btn btn-primary">Detail</my:a>
                    <my:a href="/records/add/${car.id}" class="btn btn-default">Record</my:a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>


</jsp:attribute>
</my:pagetemplate>
