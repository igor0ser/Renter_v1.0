<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@page contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html lang="en">
<head>
<fmt:setLocale value="${language}" />
<meta charset="UTF-8">
<title><fmt:message key="Welcome" /></title>
<link rel="stylesheet" href="main.css">
<link rel="stylesheet" href="animate.css">
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">

<script>
	function checkPw(form) {
		pw1 = form.password.value;
		pw2 = form.repeat_password.value;
		if (pw1 != pw2) {
			alert("Password doesn't equal to repeated password!")
			return false;
		} else
			return true;
	}
</script>

</head>
<body>
	<div class="title-container">
		<div id="title-string">RENTER</div>
		<form action="controller" method="post" name="command">
			<input type="hidden" name="command" value="change_language" /> <input
				type="hidden" name="index" value="true" />
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



	<div class="center-container animated fadeInUpBig">
		<div id="hello">
			<b><fmt:message key="Welcome" /><span id="Renter1">RENTER</span>!</b>
		</div>
		<b><fmt:message key="R_form" /></b>
		<form id="reg-form" onSubmit="return checkPw(this)"
			action="controller" method="post">
			<input type="hidden" name="command" value="registration" />
			<table>
				<tr>
					<td><fmt:message key="Login" />:</td>
					<td class="reg-input"><input type="text" name="login" required>
						<span class="required"></span></td>
				</tr>
				<tr>
					<td><fmt:message key="Password" />:</td>
					<td class="reg-input"><input type="password" name="password"
						required> <span class="required"></span></td>
				</tr>
				<tr>
					<td><fmt:message key="Repeat_password" />:</td>
					<td class="reg-input"><input type="password"
						name="repeat_password" required> <span class="required"></span></td>
				</tr>
				<tr>
					<td><fmt:message key="Email" />:</td>
					<td class="reg-input"><input type="email" name="email"
						required> <span class="required"></span></td>
				</tr>
				<tr>
					<td><fmt:message key="Name" />:</td>
					<td class="reg-input"><input type="text" name="name" required>
						<span class="required"></span></td>
				</tr>
				<tr>
					<td><fmt:message key="Surname" />:</td>
					<td class="reg-input"><input type="text" name="surname"
						required> <span class="required"></span></td>
				</tr>
				<tr>
					<td><fmt:message key="Phone" />:</td>
					<td class="reg-input"><input type="tel" name="phone_number"
						required> <span class="required"></span></td>
				</tr>
				<tr>
					<td><b><fmt:message key="Address" />:</b></td>
				</tr>
				<tr>
					<td><fmt:message key="Street" />:</td>
					<td class="reg-input"><input type="text" name="street"
						required> <span class="required"></span></td>
				</tr>
				<tr>
					<td><fmt:message key="House" />:</td>
					<td class="reg-input"><input type="text" name="house" required>
						<span class="required"></span></td>
				</tr>
				<tr>
					<td><fmt:message key="Apartment" />:</td>
					<td class="reg-input"><input type="text" name="appartment"
						required> <span class="required"></span></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<button name="registatin" type="submit">
							<fmt:message key="Sign_up" />
						</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>