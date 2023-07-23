<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>부서별 인원수</title>
</head>
<body>
<script src="${pageContext.request.contextPath}/js/code/highcharts.js"></script>
<script src="${pageContext.request.contextPath}/js/code/modules/exporting.js"></script>
<script src="${pageContext.request.contextPath}/js/code/modules/export-data.js"></script>

<div id="container" style="min-width: 300px; height: 400px; margin: 0 auto"></div>

<script type="text/javascript">
	Highcharts.chart('container', {
	    chart: {
	        type: 'column'
	    },
	    title: {
	        /* text: 'World\'s largest cities per 2017' */
	        text: '부서별 인원수 2018'
	    },
	    subtitle: {
	        text: 'Source: <a href="http://en.wikipedia.org/wiki/List_of_cities_proper_by_population">Wikipedia</a>'
	    },
	    xAxis: {
	        type: 'category',
	        labels: {
	            rotation: -45,
	            style: {
	                fontSize: '13px',
	                fontFamily: 'Verdana, sans-serif'
	            }
	        }
	    },
	    yAxis: {
	        min: 0,
	        title: {
	            /* text: 'Population (millions)' */
	            text: '인원수(명)'
	        }
	    },
	    legend: {
	        enabled: false
	    },
	    tooltip: {
	        /* pointFormat: 'Population in 2017: <b>{point.y:.1f} millions</b>' */
	        pointFormat: '인원수: <b>{point.y} 명</b>'
	    },
	    series: [{
	        name: 'Population',
	        data :[
		    <c:forEach items="${list}" var="data" varStatus="vs">
				['${data["DEPT_TITLE"]}', ${data["CNT"]}]
				<%--반복문의 마지막인지를 검사하는 varStatus속성--%>
				<c:if test="${!vs.last}">,</c:if>
    		</c:forEach>
		    ],
	        /* data: [
	            ['Shanghai', 24.2],
	            ['Beijing', 20.8],
	            ['Karachi', 14.9],
	            ['Shenzhen', 13.7],
	            ['Guangzhou', 13.1],
	            ['Istanbul', 12.7],
	            ['Mumbai', 12.4],
	            ['Moscow', 12.2],
	            ['São Paulo', 12.0],
	            ['Delhi', 11.7],
	            ['Kinshasa', 11.5],
	            ['Tianjin', 11.2],
	            ['Lahore', 11.1],
	            ['Jakarta', 10.6],
	            ['Dongguan', 10.6],
	            ['Lagos', 10.6],
	            ['Bengaluru', 10.3],
	            ['Seoul', 9.8],
	            ['Foshan', 9.3],
	            ['Tokyo', 9.3]
	        ], */
	        dataLabels: {
	            enabled: true,
	            /* rotation: -90, */
	            rotation: 0,
	            color: '#FFFFFF',
	            /* align: 'right', */
	            align: 'center',
	            /* format: '{point.y:.1f}', */
	            format: '<a href="#">{point.y}</a>', // one decimal
	            /* y: 10, */ // 10 pixels down from the top
	            y: 25, 
	            style: {
	                fontSize: '13px',
	                fontFamily: 'Verdana, sans-serif'
	            }
	        }
	    }]
	});
</script>
</body>
</html>