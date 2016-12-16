<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib prefix="carpark" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<carpark:pagetemplate title="User detail">
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
                <td>${user.id}</td>
            </tr>
            <tr>
                <td>Name</td>
                <td><c:out value="${user.firstName} ${user.lastName}"/></td>
            </tr>
            <tr>
                <td>Email</td>
                <td><c:out value="${user.email}"/></td>
            </tr>
            <tr>
                <td>Registration date</td>
                <td><fmt:formatDate value="${user.created}" pattern="yyyy-MM-dd"/></td>
            </tr>
            <tr>
                <td>Role</td>
                <td><c:out value="${user.role}"/></td>
            </tr>
        </tbody>
    </table>

    Latest records for user
    <carpark:logitemlist logItems="${logItems}" />
    <p><a class="btn btn-lg btn-success" href="${pageContext.request.contextPath}/user/list" role="button">
        <span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span> Back</a></p>

	

</jsp:attribute>
</carpark:pagetemplate>
