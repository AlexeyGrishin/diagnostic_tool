<%@ page import="com.actimind.diagnostic.view.MonitorAction"%>
<%@ page import="com.actimind.diagnostic.view.StatView" %>
<%@ page import="com.actimind.diagnostic.view.ViewHelper" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:choose>
    <c:when test="${view.updating}">
        <span class="updating">Updating...</span>
    </c:when>
    <c:otherwise>
        <table class="table table-striped img-rounded">
            <tr>
                <th></th>
                <th>Time</th>
                <th>Value</th>
                <th>State</th>
            </tr>

            <c:forEach var="item" items="${view.list}" varStatus="status">
                <tr class="${item.normal ? "normal" : "error"} ${(status.index % 2 == 1) ? 'even' : 'odd'} ${status.index == 0 ? view.classForFirst: ''}">
                    <td class="marker">&nbsp;</td>
                    <td class="date">
                        <span>${item.linkToDate}</span>
                        <span class="interval">${item.dateInterval}</span>
                    </td>
                    <td>${item.value}</td>
                    <td class="state">${item.description}&nbsp;</td>
                </tr>
            </c:forEach>
        </table>
    </c:otherwise>
</c:choose>