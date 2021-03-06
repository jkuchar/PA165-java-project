<%-- Author: jkuchar --%>
<%@ tag pageEncoding="utf-8" dynamic-attributes="dynattrs" trimDirectiveWhitespaces="true" %>
<%@ attribute name="logItems" required="true" type="java.util.List" %>
<%@ taglib prefix="carpark" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<table class="table">
    <caption></caption>
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
            <td><fmt:formatDate value="${logItem.created}" pattern="EEEE, yyyy-MM-dd HH:mm:ss"/></td>
            <td title="<c:out value="log item ID: ${logItem.id}" />" class="this-is-important">
                <c:out value="${logItem.type}"/>
                <br>
                <carpark:a href="/records/view/${logItem.id}" class="btn btn-default">details</carpark:a>
            </td>
            <td title="<c:out value="car ID: ${logItem.car.id}" />">
                <c:out value="${logItem.car.manufacturer}"/> <c:out value="${logItem.car.type}"/>
                (<c:out value="${logItem.car.regPlateNumber}"/>)
                <br>
                <carpark:a href="/car/view/${logItem.car.id}" class="btn btn-default">details</carpark:a>
            </td>
            <td>
                <c:out value="${logItem.comment}"/>
            </td>
            <td title="user ID: <c:out value="${logItem.user.id}"/>">
                <c:out value="${logItem.user.firstName}"/> <c:out value="${logItem.user.lastName}"/> (<c:out value="${logItem.user.email}"/>)
                <br>
                <carpark:a href="/user/view/${logItem.user.id}" class="btn btn-default">details</carpark:a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>