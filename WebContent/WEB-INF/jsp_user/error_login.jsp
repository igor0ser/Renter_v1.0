<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<fmt:setLocale value="${language}" />

<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8">
<fmt:setLocale value="${language}" />
<title>Renter - <c:out value="${error}">
		<fmt:message key="Some_errors" />
	</c:out></title>
<link rel="stylesheet" href="main.css">
<link rel="stylesheet" href="animate.css">
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
</head>
<body>

	<%@include file="title-menu-unlogined.jspf"%>

	<div class="center-container">
		<div id="message-container">
		<b><fmt:message key="${error}" /></b>
<button  onClick="history.back()"><fmt:message key="Back" /></button>

		</div>
	</div>
</body>