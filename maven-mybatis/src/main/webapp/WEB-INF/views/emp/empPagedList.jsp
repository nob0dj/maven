<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<title>사원정보 페이징</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>
<style>
div#emp-container{text-align:center;}
table.tbl-emp{
	margin:0 auto;
	border:1px solid; 
	border-collapse:collapse;
}
table.tbl-emp th, table.tbl-emp td{
	border:1px solid;
	padding:5px;
}
div#pagebar-container{padding:15px;}
</style>
</head>
<body>
	<div id="emp-container">
		<h2>사원정보 페이징</h2>
		
		<table class="tbl-emp">
			<tr>
				<th></th>
				<th>사번</th>
				<th>사원명</th>
				<th>주민번호</th>
				<th>이메일</th>
				<th>전화번호</th>
				<th>부서코드</th>
				<th>직급코드</th>
				<th>급여레벨</th>
				<th>급여</th>
				<th>보너스율</th>
				<th>매니져사번</th>
				<th>입사일</th>
			</tr>
			<c:if test="${not empty list }">
			<c:forEach var="emp" items="${list}" varStatus="vs">
			<tr>
				<td>${vs.count}</td>
				<td>${emp["EMP_ID"]}</td>
				<td>${emp["EMP_NAME"]}</td>
				<td>${fn:substring(emp["EMP_NO"],0,8).concat("******")}</td>
				<td>${emp["EMAIL"]}</td>
				<td>${emp["PHONE"]}</td>
				<td>${emp["DEPT_CODE"]}</td>
				<td>${emp["JOB_CODE"]}</td>
				<td>${emp["SAL_LEVEL"]}</td>
				<fmt:setLocale value="ko_kr"/>
				<td><fmt:formatNumber value="${emp['SALARY']}" type="currency"/></td>
				<td><fmt:formatNumber value='${emp["BONUS"]}' type="percent"/></td>
				<td>${emp["MANAGER_ID"]}</td>
				<td><fmt:formatDate value='${emp["HIRE_DATE"]}' pattern="yyyy년 MM월 dd일"/> </td>
			</tr>
			
			</c:forEach>
			</c:if>
			<c:if test="${empty list }">
				<tr>
					<td colspan="13" align="center">
						검색결과가 없습니다.
					</td>
				</tr>
			</c:if>
		</table>
		<div id="pagebar-container">
			${pageBar }
		</div>
	</div>
</body>
</html>