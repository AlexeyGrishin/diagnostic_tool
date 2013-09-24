<%@ page import="com.actimind.diagnostic.view.StatView" %>
<%@ page import="com.actimind.diagnostic.db.Stat" %>
<%@ page import="com.actimind.diagnostic.view.MonitorAction" %>
<%@ page import="com.actimind.diagnostic.fw.Dispatcher" %>
<%@ page import="com.actimind.diagnostic.view.ViewHelper" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    new Dispatcher(application, request)
            .monitor()
            .produceStatsListForName(request.getParameter("name"))
            .then().render();
%>
<jsp:include page="mon-header.jsp"/>
<div class="full"><a href="<%= ViewHelper.linkToFull(request.getParameter("name"))%>">View full journal</a></div>
<jsp:include page="single-stat-view.jsp"/>
<jsp:include page="mon-footer.jsp"/>