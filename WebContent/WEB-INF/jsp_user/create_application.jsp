<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="mytag"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8">
<fmt:setLocale value="${language}" />
<title><fmt:message key="App_form" /></title>
<link rel="stylesheet" href="main.css">
<link rel="stylesheet" href="animate.css">
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">

</head>
<body>


	<%@include file="/WEB-INF/jsp_user/title-menu.jsp"%>



	<div class="center-container animated fadeInUpBig">

		<b><fmt:message key="App_form" /></b>
		<form id="app-form" action="controller" method="post">
			<input type="hidden" name="command" value="create_app" />
			<table>
				<tr>
					<td><fmt:message key="About" />:</td>
					<td class="reg-input"><input type="text" name="about" required>
						<span class="required"></span></td>
				</tr>

				<tr>
					<td><fmt:message key="Type" />:</td>
					<td class="reg-input"><select name="type_of_work" required>
							<option></option>
							<option value="ELECTRIC">Electric</option>
							<option value="SANTECHNIC">Santechnic</option>
							<option value="HEATING">Heating</option>
							<option value="AIR_CONDITIONING">Air conditioning</option>
							<option value="OTHER">Other</option>
					</select> <span class="required"></span></td>
				</tr>
				<tr>
					<td class =  "line_height"><fmt:message key="Desirable" />:</td>
					<td class="calendar"><input class="desirable" name="desirable"
						type="datetime-local" required value=<mytag:tomorrow9am/> ></td>
				</tr>

				<tr>
					<td colspan="2" align="center"><button name="application"
							type="submit">
							<fmt:message key="Submit" />
						</button></td>
				</tr>
			</table>
		</form>
	</div>


</body>