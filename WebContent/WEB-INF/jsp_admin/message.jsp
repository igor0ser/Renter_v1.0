<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8">
<fmt:setLocale value="${language}" />
<title>Renter - <fmt:message key="${message}" /></title>
<link rel="stylesheet" href="main-adm.css">
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
</head>
<body>
	<%@include file="title-menu.jsp"%>

	<div class="center-container">
		<div id="message-container">
			<b><fmt:message key="${message}" /></b>
		</div>
	</div>

</body>