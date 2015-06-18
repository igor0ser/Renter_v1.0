<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8">
<fmt:setLocale value="${language}" />
<title>Renter - <fmt:message key="Workplan" /></title>
<link rel="stylesheet" href="main_admin.css">
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">

<script type="text/javascript" src="https://www.google.com/jsapi">
</script>
<script type="text/javascript">
	google.load("visualization", "1", {
		packages : [ "timeline" ]
	});
	google.setOnLoadCallback(drawChart);

	function drawChart() {
		var container = document.getElementById('timeline');
		var chart = new google.visualization.Timeline(container);
		var dataTable = new google.visualization.DataTable();
		var name = "${requestScope.name}";

		dataTable.addColumn({
			type : 'string',
			id : 'Worker'
		});
		dataTable.addColumn({
			type : 'date',
			id : 'Start'
		});
		dataTable.addColumn({
			type : 'date',
			id : 'End'
		});
		<c:forEach var="item" items="${workplan}">
		var name = "${item.name}";
		var date1 = new Date("${item.start}");
		var date2 = new Date("${item.end}");

		dataTable.addRows([ [ name, date1, date2 ] ]);

		</c:forEach>
		chart.draw(dataTable);
	}
</script>
</head>
<body>

	<%@include file="title-menu.jsp"%>
	
	<div class="appplication-container">
	<h3 align="center"><fmt:message key="Workplan" /></h3>
	<div id="timeline"></div>
	</div>
</body>