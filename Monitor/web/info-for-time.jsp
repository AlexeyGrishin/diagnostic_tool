<%@ page import="com.actimind.diagnostic.view.StatView" %>
<%@ page import="com.actimind.diagnostic.db.Stat" %>
<%@ page import="com.actimind.diagnostic.view.MonitorAction" %>
<%@ page import="com.actimind.diagnostic.fw.Dispatcher" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    new Dispatcher(application, request)
            .monitor()
            .produceStatsListForTime(request.getParameter("date"))
            .then().render();
%>

<jsp:include page="mon-header.jsp"/>
<jsp:include page="mon-view.jsp"/>
<jsp:include page="mon-footer.jsp"/>