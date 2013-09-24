<%@ page import="com.actimind.diagnostic.fw.Dispatcher"%><%@ page contentType="application/csv;charset=UTF-8" language="java" %><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%
    new Dispatcher(application, request, response)
            .monitor()
            .produceTimeline(request.getParameterValues("names"))
            .then().download("timeline.csv");
    String s = request.getParameter("s");
    request.setAttribute("s", s == null ? "," :s );

%>Time${s}Interval<c:forEach var="item1" items="${view.stats}">${s}${item1}</c:forEach>
<c:forEach var="item" items="${view.list}" varStatus="status">${item.date}${s}${item.interval}${s}<c:forEach var="stat" items="${item.stats}">${s}${stat.value}</c:forEach>
</c:forEach>