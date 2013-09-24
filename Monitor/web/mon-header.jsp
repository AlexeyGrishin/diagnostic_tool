<!DOCTYPE HTML>
<%@ page import="com.actimind.diagnostic.view.ViewHelper" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${param['js']!=1}">
<html>
<head>
    <link href="bootstrap.css" rel="stylesheet"/>
    <link href="monitor.css" rel="stylesheet"/>
    <script type="text/javascript" src="jquery.js"></script>
    <script type="text/javascript" src="auto.js"></script>
    <script type="text/javascript" src="bootstrap.js"></script>
    <title>${view.title}</title>
</head>
<body class="container">
    <h1>${view.title}</h1>
    <h3>${view.subtitle}</h3>
    <jsp:include page="nav.jsp"/>
    <div id="table">
</c:if>