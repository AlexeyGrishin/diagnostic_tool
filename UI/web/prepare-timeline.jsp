<%@ page import="com.actimind.diagnostic.view.MonitorAction"%>
<%@ page import="com.actimind.diagnostic.view.StatView" %>
<%@ page import="com.actimind.diagnostic.view.ViewHelper" %>
<%@ page import="com.actimind.diagnostic.fw.Dispatcher" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    new Dispatcher(application, request)
            .monitor()
            .produceListForPrepareTimeline()
            .then().render();
%>
<html>
<head>
    <link href="bootstrap.css" rel="stylesheet"/>
    <link href="monitor.css" rel="stylesheet"/>
    <script type="text/javascript" src="jquery.js"></script>
    <title>Prepare Timeline</title>
</head>
<body class="container">
<h1>Select several stats to be shown</h1>
<jsp:include page="nav.jsp"/>

<form action="timeline.jsp" class="well">
    <select id="all-stats" multiple="multiple" size="15" name="names" class="input-xxlarge">
        <c:forEach var="item" items="${view.list}">
            <option value="${item.name}">${item.name}</option>
        </c:forEach>
    </select>
    <br/>
    <input type="Submit" class="btn btn-primary" value="Show">
</form>

</body>
</html>