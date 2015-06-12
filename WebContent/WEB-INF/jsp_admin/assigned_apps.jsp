<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8">
<fmt:setLocale value="${language}" />
<title>Renter - <fmt:message key="Assigned_apps" /></title>
<link rel="stylesheet" href="main_admin.css">
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
</head>
<body>

	<%@include file="title-menu.jsp"%>



	<div class="appplication-container">
		<table class="app-table" rules="all">
			<caption>
				<b> <fmt:message key="Assigned_apps" /> </b>
			</caption>
			<tbody>
				<tr>
					<th><fmt:message key="User_login" /></th>
					<th><fmt:message key="Phone" /></th>
					<th><fmt:message key="Address" /></th>
					<th><fmt:message key="Type" /></th>
					<th><fmt:message key="About" /></th>
					<th><fmt:message key="Creation" /></th>
					<th><fmt:message key="Desirable" /></th>
					<th><fmt:message key="Start" /></th>
					<th><fmt:message key="End" /></th>
					<th><fmt:message key="Workers" /></th>
					<th><fmt:message key="Complete" /></th>
				</tr>
				<c:forEach var="item" items="${list}">
					<tr>
					${item}
					
						<td><form action="controller" method="post">
						<input type="hidden" name="command" value="set_app_complete" />
						<input type="hidden" name="app_id" value= ${item.id} />
						<button type="submit"><fmt:message key="Complete" /></button>
						</form></td>

					</tr>
					
				</c:forEach>
				<c:if test="${list_size < 1}">
					<tr>
						<td class="time">----</td>
						<td class="time">----</td>
						<td class="time">----</td>
						<td class="time">----</td>
						<td class="time">----</td>
						<td class="time">----</td>
						<td class="time">----</td>
						<td class="time">----</td>
						<td class="time">----</td>
						<td class="time">----</td>
						<td class="time">----</td>

					</tr>
				</c:if>


			</tbody>
		</table>

	</div>
</body>