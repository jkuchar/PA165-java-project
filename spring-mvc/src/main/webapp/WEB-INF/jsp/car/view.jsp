<%-- 
    Document   : view
    Created on : 14.12.2016
    Author     : charlliz
--%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="carpark" tagdir="/WEB-INF/tags" %>

<my:pagetemplate title="Car detail">
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
                <td>${car.id}</td>
            </tr> 
            <tr>
                <td>Manufacturer</td>
                <td><c:out value="${car.manufacturer}"/></td>
            </tr>    
            <tr>
                <td>Type</td>
                <td><c:out value="${car.type}"/></td>
            </tr> 
            <tr>
                <td>Reg plate number</td>
                <td><c:out value="${car.regPlateNumber}"/></td>  
            </tr>  
            <tr>
                <td>Serial number</td>
                <td><c:out value="${car.serialNumber}"/></td>                     
            </tr> 
            <tr>
                <td>Number of seats</td>
                <td><c:out value="${car.seats}"/></td>                     
            </tr>    
            <tr>
                <td>Established date</td>
                <td><fmt:formatDate value="${car.establishDate}" pattern="yyyy-MM-dd"/></td>                
            </tr>  
            <c:if test="${not empty car.discardDate}">
                <tr>
                    <td>Discard date</td>
                    <td><fmt:formatDate value="${car.discardDate}" pattern="yyyy-MM-dd"/></td>                
                </tr>  
            </c:if>>
            <tr>    
                <td>State</td>
                <td><c:out value="${car.state}"/></td>
            </tr>
        </tbody>
    </table>          
              
    <carpark:logitemlist logItems="${logItems}" />

    <c:choose>
        <c:when test="${car.state=='OK'}">
            <div class="col-xs-2">
            <form method="post" action="${pageContext.request.contextPath}/car/service/${car.id}">
                <button type="submit" class="btn btn-lg btn-info">Service car</button>
            </form>
            </div>
            <div class="col-xs-2">
            <form method="post" action="${pageContext.request.contextPath}/car/discard/${car.id}">
                <button type="submit" class="btn btn-lg btn-warning">Discard car</button>
            </form>
            </div>
        </c:when>
        <c:when test="${car.state=='SERVICING'}">
            <div class="col-xs-2">
            <form method="post" action="${pageContext.request.contextPath}/car/ok/${car.id}">
                <button type="submit" class="btn btn-lg btn-primary">Finish service</button>
            </form>
            </div>
            <div class="col-xs-2">
            <form method="post" action="${pageContext.request.contextPath}/car/discard/${car.id}">
                <button type="submit" class="btn btn-lg btn-warning">Discard car</button>
            </form>
            </div>    
        </c:when>
    </c:choose>       
</jsp:attribute>
</my:pagetemplate>
