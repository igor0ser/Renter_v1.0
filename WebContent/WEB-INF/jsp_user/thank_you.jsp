<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8">
<fmt:setLocale value="${language}" />
<title>Renter - <fmt:message key="Thank_you" /><c:out value="${sessionScope.login}"></c:out>!</title>
<link rel="stylesheet" href="main.css">
<link rel="stylesheet" href="animate.css">
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">

</head>
<body>

	<%@include file="title-menu.jsp"%>


	<div class="center-container  animated fadeInUpBig">
		<div id="message-container">
			<b><fmt:message key="Thank_you" /><c:out value="${sessionScope.login}"></c:out>!</b>
			<b><fmt:message key="We_received" /></b></br> <span></span>
			<div id="flying_man">
				<%@include file="flying_man.html"%>
			</div>
			<div class = "line_height"><b><fmt:message key="As_soon" /></b></div>
		</div>
	</div>


	</div>
</body>