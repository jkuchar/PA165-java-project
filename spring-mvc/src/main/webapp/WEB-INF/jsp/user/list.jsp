<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="carpark" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<carpark:pagetemplate title="Users">
<jsp:attribute name="body">
    <table class="table">
        <caption>Users</caption>
        <thead>
        <tr>
            <th>id</th>
            <th>name</th>
            <th>email</th>
            <th>created</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.id}</td>
                <td><c:out value="${user.name}"/></td>
                <td><c:out value="${user.email}"/></td>
                <td><fmt:formatDate value="${user.created}" pattern="yyyy-MM-dd"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</jsp:attribute>
</carpark:pagetemplate>
