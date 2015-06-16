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

	<%@include file="WEB-INF/jsp_user/title-menu-unlogined.jspf"%>

	<div class="center-container">
		<div id="message-container">
			<form action="controller" method="post">
				<input type="hidden" name="command" value="send_pass" /> <b><fmt:message
						key="Enter_email" /></b> <input type="email" name="email">
				<button type="Submit">
					<fmt:message key="Send" />
				</button>
			</form>
		</div>
	</div>
</body>