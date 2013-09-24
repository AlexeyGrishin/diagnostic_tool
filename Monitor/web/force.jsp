<%@ page import="com.actimind.diagnostic.view.MonitorAction" %>
<%@ page import="com.actimind.diagnostic.fw.Dispatcher" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    new Dispatcher(application, response)
            .monitor()
            .enableForce(Boolean.valueOf(request.getParameter("force")));
            //.then().redirect("monitor.jsp");
%>