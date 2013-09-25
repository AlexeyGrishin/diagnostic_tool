<%@ page import="com.actimind.diagnostic.view.MonitorAction"%>
<%@ page import="com.actimind.diagnostic.view.StatView" %>
<%@ page import="com.actimind.diagnostic.fw.Dispatcher" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    new Dispatcher(application, request)
            .monitor()
            .produceLatestStatsList()
            .then().render();
%>

<jsp:include page="mon-header.jsp"/>
<jsp:include page="mon-view.jsp"/>
<jsp:include page="mon-footer.jsp"/>