<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="true" %>
<%@ taglib prefix="carpark" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<carpark:pagetemplate title="Users">
<jsp:attribute name="body">
    <table class="table">
        <thead>
        <tr>
            <sec:authorize access="hasRole('ROLE_MANAGER')">
                <th>Id</th>
            </sec:authorize>
            <th>Name</th>
            <th>Email</th>
            <th>Registration date</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
                <sec:authorize access="hasRole('ROLE_MANAGER')">
                    <td>${user.id}</td>
                </sec:authorize>
                <td><c:out value="${user.firstName} ${user.lastName}"/></td>
                <td><c:out value="${user.email}"/></td>
                <td><fmt:formatDate value="${user.created}" pattern="yyyy-MM-dd"/></td>
                <td><carpark:a href="/user/view/${user.id}" class="btn btn-primary">Detail</carpark:a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</jsp:attribute>
</carpark:pagetemplate>
