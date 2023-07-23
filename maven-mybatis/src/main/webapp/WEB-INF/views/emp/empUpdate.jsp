<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>동적쿼리(set, where, trim)</title>
<!-- 최신 jquery cdn 사용하기 -->
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<style>
div.wrapper{
	text-align: center;
}
div.update-wrapper{
	background: lightgray;
	width: 500px;
	padding: 20px;
	margin: 0 auto;
}

table#emp{
	margin: 5px auto;
	border-collapse: collapse;
	border: 1px solid;
}
table#emp th, table#emp td{
	border: 1px solid;
}

</style>
</head>
<body>
<div class="wrapper">
<h2>동적쿼리(set, where, trim)</h2>
<form action="">
사번 선택 :
<select name="empId">
	<c:forEach items="${empIdList }" var="e">
		<option value="${e}" ${e==emp.EMP_ID?"selected":"" }>${e}</option>
	</c:forEach>
</select>
<input type="submit" value="검색" />
</form>

<%-- 조회한 사번이 있는 경우 --%>
<c:if test="${not empty emp }">
	<table id="emp">
		<tr>
			<th>사번</th>
			<td>${emp.EMP_ID}</td>
		</tr>
		<tr>
			<th>사원명</th>
			<td>${emp.EMP_NAME}</td>
		</tr>
		<tr>
			<th>직급코드</th>
			<td>${emp.JOB_CODE}</td>
		</tr>
		<tr>
			<th>급여레벨</th>
			<td>${emp.SAL_LEVEL}</td>
		</tr>
		<tr>
			<th>부서코드</th>
			<td>${emp.DEPT_CODE}</td>
		</tr>
	</table>
	<hr />
	<div class="update-wrapper">
		<form action="${pageContext.request.contextPath }/emp/empUpdateEnd.do" method="post">
			<input type="hidden" name="empId" value="${emp.EMP_ID }" />
		   	급여레벨: 
		    <select name="salLevel">
		    	<option value="">선택</option>
		    <c:forEach items="${salLevelList }" var="s">
		    	<option value="${s.SAL_LEVEL }">${s.SAL_LEVEL }</option>
		    </c:forEach>
		    </select>
		   	직급코드: 
		    <select name="jobCode">
		    	<option value="">선택</option>
		    <c:forEach items="${jobCodeList }" var="s">
		    	<option value="${s.JOB_CODE }">${s.JOB_CODE}</option>
		    </c:forEach>
		    </select>
		   	부서코드: 
		    <select name="deptCode">
		    	<option value="">선택</option>
		    <c:forEach items="${deptCodeList }" var="s">
		    	<option value="${s.DEPT_CODE }">${s.DEPT_CODE }</option>
		    </c:forEach>
		    </select>
		    <input type="submit" onclick="return validate()" value="수정" />
		</form>
	</div>
</c:if>

	
</div>
<script>
function validate(){
	//아무것도 입력하지 않은 경우, 전송하지 않는다.
	if($("[name=salLevel]").val()=='' && $("[name=jobCode]").val()=='' && $("[name=deptId]").val()=='')
		return false;
	return true;
}
</script>
</body>
</html>