<%-- 
    Document   : list
    Created on : 14.12.2016, 16:20:26
    Author     : charlliz
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Application Rejected Records">
<jsp:attribute name="body">

    <table class="table">
        <thead>
        <tr>
            <th>id</th>
            <th>user</th>
            <th>car</th>
            <th>date</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${records}" var="record">
            <tr>
                <td>${record.id}</td>
                <td><c:out value="${record.user.email}"/></td>
                <td><c:out value="${record.car.manufacturer} ${record.car.type}"/></td>
                <td><fmt:formatDate value="${record.created}" pattern="yyyy-MM-dd"/></td>
                <td>
                    <my:a href="/rejected/view/${record.id}" class="btn btn-primary">View</my:a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>


</jsp:attribute>
</my:pagetemplate>
