<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib prefix="carpark" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<carpark:pagetemplate title="Car audit log items">

<jsp:attribute name="body">
    <table class="table">
        <caption>Latest book records:</caption>
        <thead>
        <tr>
            <th>Recorded at</th>
            <th>Type</th>
            <th>Car</th>
            <th>Comment</th>
            <th>Employee</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach items="${logItems}" var="logItem">
            <%-- BTW: this whole block is wrong; these data should be prepared into custumly designed DTO in controller to make future refactoring easy --%>
            <%-- Because there is no static analysis right now, this will be hell to refactor in case there will be change in DTO --%>
            <tr>
                <td><fmt:formatDate value="${logItem.created}" pattern="yyyy-MM-dd"/></td>
                <td title="<c:out value="ID: ${logItem.id}" />" class="this-is-important">
                    <c:out value="${logItem.type}"/>
                </td>
                <td title="<c:out value="ID: ${logItem.car.id}" />">
                    <c:out value="${logItem.car.manufacturer}"/> <c:out value="${logItem.car.type}"/>
                    (<c:out value="${logItem.car.regPlateNumber}"/>)
                </td>
                <td>
                    <c:out value="${logItem.comment}"/>
                </td>
                <td title="ID: <c:out value="${logItem.user.id}"/>">
                    <c:out value="${logItem.user.firstName}"/> <c:out value="${logItem.user.lastName}"/> (<c:out value="${logItem.user.email}"/>)
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <%--move to head--%>
    <style type="text/css">
        table .this-is-important {
            font-weight: bold;
        }
    </style>
</jsp:attribute>
</carpark:pagetemplate>
