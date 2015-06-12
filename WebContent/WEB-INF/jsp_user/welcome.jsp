<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<!doctype html>
<html lang = "en">
<head>
<fmt:setLocale value="${language}" />
<meta charset ="UTF-8">
<title>Renter - <fmt:message key="Hello" /></title>
<link rel="stylesheet" href="main.css">
<link rel="stylesheet" href="animate.css">
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">

</head>
<body>

<%@include file="title-menu.jsp" %>

<div class= "center-container animated fadeInUpBig">
<div id="message-container">
<b><fmt:message key="Hello" />
<c:out value="${sessionScope.login}"></c:out>
!
</b><br>
<b><fmt:message key="Welcome2" /><b>
</div>
</div>
</body>