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

	<div class="title-container">
		<div id="title-string">RENTER</div>
		<form action="controller" method="post" name="command">
			<input type="hidden" name="command" value="change_language" />
			<div id="language">
				<button name="lang" value="en" type="submit">EN</button>
				</br>
				<button name="lang" value="ru" type="submit">RU</button>
				</br>
			</div>
		</form>

		<form id="login-form" action="controller" method="post" name="command">
			<input type="hidden" name="command" value="login" />
			<table>
				<tr>
					<td><fmt:message key="Login" />:</td>
					<td class="info"><input type="text" name="login"></td>
				</tr>
				<tr>
					<td><fmt:message key="Password" />:</td>
					<td class="info"><input type="password" name="password"></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><button type="submit">
							<fmt:message key="Log_in" />
						</button></td>
				</tr>
			</table>
		</form>


	</div>

	<div class="center-container">
		<div id="message-container">
		<b><fmt:message key="${error}" /></b>
<button  onClick="history.back()"><fmt:message key="Back" /></button>

		</div>
	</div>
</body>