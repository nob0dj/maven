<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/code/highcharts.js"></script>
<script src="${pageContext.request.contextPath}/js/code/modules/exporting.js"></script>
<script src="${pageContext.request.contextPath}/js/code/modules/export-data.js"></script>

<div id="container" style="min-width: 310px; height: 400px; max-width: 600px; margin: 0 auto"></div>
<script type="text/javascript">

// Build the chart
Highcharts.chart('container', {
    chart: {
        plotBackgroundColor: null,
        plotBorderWidth: null,
        plotShadow: false,
        type: 'pie'
    },
    title: {
        text: '소나기 네트워크 사원현황, 2018'
    },
    tooltip: {
    	//pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>:'
        pointFormat: '{series.name}: <b>{point.y}명({point.percentage:.1f}%)</b>'
    },
    plotOptions: {
        pie: {
            allowPointSelect: true,
            cursor: 'pointer',
            dataLabels: {
                enabled: false
            },
            showInLegend: true
        }
    },
    //list버젼
    series: [{
        name: '퍼센트',
        colorByPoint: true,
        data: [{
            name:'${list[0]["GENDER"]}',
            y: ${list[0]["CNT"]},
            sliced:true,
            selected:true
        },{
        	name:'${list[1]["GENDER"]}',
            y: ${list[1]["CNT"]}
        }]
    }]
	//map버젼
    /* series: [{
        name: '퍼센트',
        colorByPoint: true,
        data: [{
            name:'남',
            y: ${map["MALECNT"]},
            sliced:true,
            selected:true
        },{
        	name:'여',
            y: ${map["FEMALECNT"]}
        }]
    }] */
    /*
    series: [{
        data: [{
            name: 'Chrome',
            y: 61.41,
            sliced: true,
            selected: true
        }, {
            name: 'Internet Explorer',
            y: 11.84
        }, {
            name: 'Firefox',
            y: 10.85
        }, {
            name: 'Edge',
            y: 4.67
        }, {
            name: 'Safari',
            y: 4.18
        }, {
            name: 'Other',
            y: 7.05
        }] 
    }]*/
});
</script>
</body>
</html>