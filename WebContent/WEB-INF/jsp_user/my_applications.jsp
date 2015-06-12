<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8">
<fmt:setLocale value="${language}" />
<title>Renter - <fmt:message key="Apps_of_user" /></title>
<link rel="stylesheet" href="main.css">
<link rel="stylesheet" href="animate.css">
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">

</head>
<body>

	<%@include file="title-menu.jsp"%>



	<div class="appplication-container animated fadeInUpBig">
		<table class="app-table" rules="all">
			<caption>
				<fmt:message key="Apps_of_user" /> <b> <c:out value="${sessionScope.login}"></c:out>
				</b>
			</caption>
			<tbody>
				<tr>
					<th><fmt:message key="Status" /></th>
					<th><fmt:message key="Type" /> </th>
					<th><fmt:message key="About" /></th>
					<th><fmt:message key="Creation" /></th>
					<th><fmt:message key="Desirable" /></th>
					<th><fmt:message key="Start" /></th>
					<th><fmt:message key="End" /></th>
				</tr>
				<c:forEach var="item" items="${list}">

					<tr>
						<td>${item.status}</td>
						<td>${item.typeOfWork}</td>
						<td class="about">${item.about}</td>
						<td class="time"><fmt:formatDate type="both"
								timeStyle="short" value="${item.creation}" /></td>
						<td class="time"><fmt:formatDate type="both"
								timeStyle="short" value="${item.desirable}" /></td>
						<td class="time"><fmt:formatDate type="both"
								timeStyle="short" value="${item.start}" /></td>
						<td class="time"><fmt:formatDate type="both"
								timeStyle="short" value="${item.end}" /></td>
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

					</tr>
				</c:if>


			</tbody>
		</table>

	</div>
</body>