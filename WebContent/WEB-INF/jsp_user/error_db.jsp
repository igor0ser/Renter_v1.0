<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!doctype html>
<html lang = "en">
<head>
<meta charset ="UTF-8">
<fmt:setLocale value="${language}" />
<title>Renter - <fmt:message key="Some_errors" /></title>
<link rel="stylesheet" href="main.css">
<link rel="stylesheet" href="animate.css">
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">

</head>
<body>

<%@include file="title-menu.jsp" %>

<div class= "center-container">
<div id="message-container">
<b><div class="line_height"><span><fmt:message key="Some_errors" /></span>
<fmt:message key="Try_again" /></b></div>
<button  onClick="history.back()"><fmt:message key="Back" /></button>
</div>
</div>
</body>