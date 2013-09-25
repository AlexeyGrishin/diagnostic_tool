<%@ page import="com.actimind.diagnostic.view.MonitorAction"%>
<%@ page import="com.actimind.diagnostic.view.StatView" %>
<%@ page import="com.actimind.diagnostic.view.ViewHelper" %>
<%@ page import="com.actimind.diagnostic.fw.Dispatcher" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    String[] names = request.getParameterValues("names");
    if (names == null) {
        response.sendRedirect("prepare-timeline.jsp");
        return;
    }
    new Dispatcher(application, request)
            .monitor()
            .produceTimeline(names)
            .then().render();
%>
<jsp:include page="mon-header.jsp"/>

<table class="timeline table table-striped">
    <tr>
        <th></th>
        <th>Time</th>
        <c:forEach var="item1" items="${view.stats}">
            <th class="stat">${item1}</th>
        </c:forEach>
    </tr>

    <c:forEach var="item" items="${view.list}" varStatus="status">
        <tr class="${(status.index % 2 == 1) ? 'even' : 'odd'}">
            <td class="marker">&nbsp;</td>
            <td class="date">${item.date} <span class="interval">${item.interval}</span></td>
            <c:forEach var="stat" items="${item.stats}">
                <td class="stat ${stat.normal ? "normal" : "error"}" title="${stat.description}">${stat.value}</td>
            </c:forEach>
        </tr>
    </c:forEach>
</table>



<jsp:include page="mon-footer.jsp"/>

<c:if test="${param['js']!=1}" >
<div class="btn-group pull-right">
    <button class="btn dropdown-toggle" data-toggle="dropdown">
        Export to
        <span class="caret"></span>
    </button>
    <ul class="dropdown-menu">
        <li><a href="<%= ViewHelper.linkToTimelineCsv(request.getParameterValues("names"))%>">CSV</a>
        </li>
        <li><a href="<%= ViewHelper.linkToTimelineCsv(request.getParameterValues("names"), ";")%>">Semicolon-SV</a></li>
    </ul>
</div>
<div class="clear"></div>
</c:if>