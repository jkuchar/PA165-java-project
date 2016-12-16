<%-- 
    Document   : view
    Created on : 14.12.2016, 16:43:03
    Author     : charlliz
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Application Rejected Record Detail">
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
                <td>id</td>
                <td>${record.id}</td>
            </tr> 
            <tr>
                <td>Date</td>
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
        </tbody>
    </table>
    <p><a class="btn btn-lg btn-success" href="${pageContext.request.contextPath}/rejected/list"
              role="button"><span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span> Back</a></p>        

</jsp:attribute>
</my:pagetemplate>
