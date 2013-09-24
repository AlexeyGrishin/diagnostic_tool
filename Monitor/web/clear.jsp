<%@ page import="com.actimind.diagnostic.view.MonitorAction" %>
<%@ page import="com.actimind.diagnostic.fw.Dispatcher" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    MonitorAction v = new Dispatcher(application, response).monitor();

    String name = request.getParameter("name");
    if (name == null)
        v.clearAllStats();
    else
        v.clearStat(name);

    //v.then().redirect("monitor.jsp");

%>