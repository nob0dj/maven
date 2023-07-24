<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mybatis실습</title>
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
div#search-container{
	padding:15px 0;
}
</style>
</head>
<body>
<div id="emp-container">
	<h2>사원정보 </h2>
	<div id="search-container">
		<form action="search1.do" >
			<select name="searchType" required>
				<option value="" disabled selected>검색타입</option>
				<!-- 최초보이는 값으로 설정함. required이기 때문에 반드시 다른값을 선택해야함. value="" 반드시 있어야함.-->
				<option value="emp_id" <c:if test="${'emp_id' eq param.searchType}">selected</c:if>>사번</option>
				<option value="emp_name" ${'emp_name' eq param.searchType?"selected":"" } >사원명</option>
				<option value="email" ${"email".equals(param.searchType)?"selected":""} >이메일</option>
				<option value="phone" ${"phone" == param.searchType?"selected":"" }>전화번호</option>
			</select>
			<input type="search" name="searchKeyword" value="${param.searchKeyword}" required/>	
			<input type="submit" value="검색" />
		</form>
	</div>
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
			<th>매니져 사번</th>
			<th>입사일</th>
		</tr>
		<c:if test="${not empty list}">
		<c:forEach var="e" items="${list}" varStatus="vs">
		<tr>
			<td>${vs.count }</td>
			<td>${e["EMP_ID"]}</td>
			<td>${e["EMP_NAME"]}</td>
			<td>${fn:substring(e["EMP_NO"],0,8)}******</td>
			<td>${e["EMAIL"]}</td>
			<td>${e["PHONE"]}</td>
			<td>${e["DEPT_CODE"]}</td>
			<td>${e["JOB_CODE"]}</td>
			<td>${e["SAL_LEVEL"]}</td>	
			<fmt:setLocale value="ko_kr"/>
			<td><fmt:formatNumber value="${e[\"SALARY\"]}" type="currency"/></td>
			<td><fmt:formatNumber value="${e['BONUS']}" type="percent"/></td>
			<td>${e["MANAGER_ID"]}</td>
			<td><fmt:formatDate value="${e['HIRE_DATE']}" type="date" pattern="yyyy/MM/dd"/> </td>
		</tr>			
		</c:forEach>
		</c:if>
		
		<c:if test="${empty list}">
		<tr>
			<td colspan="13">
			검색결과가 존재하지 않습니다.
			</td>
		</tr>
		</c:if>
	</table>
</div>

</body>
</html>