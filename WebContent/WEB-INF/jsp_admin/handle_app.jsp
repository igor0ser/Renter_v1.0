<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8">
<fmt:setLocale value="${language}" />
<title>Renter - <fmt:message key="Handle" /></title>
<link rel="stylesheet" href="main_admin.css">
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">

</head>
<body>

	<%@include file="title-menu.jsp"%>



	<div class="appplication-container">
		<form action="controller" method="post">
			<input type="hidden" name="command" value="free_workers" />
			<table class="app-table" rules="all">
				<caption>
					<b> <fmt:message key="Handle" />
					</b>
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
					</tr>
					<tr>
						<td>${app.user.login}</td>
						<td>${app.user.phoneNumber}</td>
						<td>${app.user.address}</td>
						<td>${app.typeOfWork}</td>
						<td class="about">${app.about}</td>
						<td class="time"><fmt:formatDate type="both"
								timeStyle="short" value="${app.creation}" /></td>
						<td class="time"><fmt:formatDate type="both"
								timeStyle="short" value="${app.desirable}" /></td>
						<td class="calendar" class="reg-input"><input name="start"
							type="datetime-local"
							value=<c:out value="${default_start}"></c:out> required>
						<td class="calendar" class="reg-input"><input name="end"
							type="datetime-local" required value="${default_end}"></td>


					</tr>
				</tbody>
			</table>

			<input type="checkbox" name="show-all" value="true"
				<c:if test="${is_checked}">checked</c:if>>
			<fmt:message key="Show_all_spec" />

			<button type="submit">
				<fmt:message key="Show_free_workers" />
			</button>
		</form>


		<c:if test="${list_workers_size == 0}">
		<table class="app-table" rules="all">
					<caption>
						<b> <fmt:message key="Free_workers" />
						</b>
					</caption>
					<tbody>
						<tr>
							<th><fmt:message key="Name" /></th>
							<th><fmt:message key="Specialty" /></th>
							<th><fmt:message key="Assign" /></th>
						</tr>
						
							<tr>
								<td>---</td>
								<td>---</td>
								<td>---</td>
							</tr>
						
					</tbody>
				</table>
</c:if>
		<c:if test="${list_workers_size > 0}">
			<form action="controller" method="post">
				<input type="hidden" name="command" value="add_workers" />
				<table class="app-table" rules="all">
					<caption>
						<b> <fmt:message key="Free_workers" />
						</b>
					</caption>
					<tbody>
						<tr>
							<th><fmt:message key="Name" /></th>
							<th><fmt:message key="Specialty" /></th>
							<th><fmt:message key="Assign" /></th>
						</tr>
						<c:forEach var="item" items="${list_workers}">
							<tr>
								<td>${item}</td>
								<td>${item.typeOfWork}</td>
								<td><input type="checkbox" name="${item.id}" value="true"></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<button type="submit">
					<fmt:message key="Add_to_this_work" />
				</button>
			</form>
		</c:if>
</body>