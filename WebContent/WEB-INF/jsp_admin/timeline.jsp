<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="mytag"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<html>
<head>
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
		<c:forEach var="item" items="${day_worker_list}">
		var name = "${item}";
		var x = <%=new java.sql.Date(111) %>;
	  	dataTable.addRows([ [ name, new Date(x) ,
				new Date(2015, 6, 19, 3, 00) ] ]);

		</c:forEach>
		chart.draw(dataTable);
	}
</script>
</head>
<body>
	<div id="timeline" style="width: 900px; height: 180px;"></div>
	</body>
</html>