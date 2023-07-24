<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="f" uri="http://nobodj.space/jsp/jstl/functions"%>
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
input#btn-search{
	width: 350px;
    background: lightslategray;
    color: white;
    box-shadow: 0px 3px 15px grey;
}
table#tbl-search{
	margin:0 auto;
}
table#tbl-search th,table#tbl-search td{padding:5px 15px;}
table#tbl-search td{text-align:left;}
</style>
</head>
<body>
<div id="emp-container">
	<h2>사원정보 </h2>
	<div id="search-container">
		<form action="search3.do" >
			<p>
				<h3>검색</h3>
				<input type="button" value="초기화" onclick="location.href='search3.do';"/>
			</p>
			<table id="tbl-search">
				<tr>
					<th>직급</th>
					<td>
						<%
							String[] job_codeArr = (String[])request.getParameterValues("job_code"); 
							List<String> jobList = null;
							if(job_codeArr!=null) jobList = Arrays.asList(job_codeArr);		
							
						%>
						
						
						<%-- checked 스크립틀릿 처리 --%>
						<%-- <input type="checkbox" name="job_code" id="J1" value="J1" <%=jobList!=null && jobList.contains("J1")?"checked":"" %>/>
						<label for="J1">대표</label>
						<input type="checkbox" name="job_code" id="J2" value="J2" <%=jobList!=null && jobList.contains("J2")?"checked":"" %>/>
						<label for="J2">부사장</label>
						<input type="checkbox" name="job_code" id="J3" value="J3" <%=jobList!=null && jobList.contains("J3")?"checked":"" %>/>
						<label for="J3">부장</label>
						<br />
						<input type="checkbox" name="job_code" id="J4" value="J4" <%=jobList!=null && jobList.contains("J4")?"checked":"" %>/>
						<label for="J4">차장</label>
						<input type="checkbox" name="job_code" id="J5" value="J5" <%=jobList!=null && jobList.contains("J5")?"checked":"" %>/>
						<label for="J5">과장</label>
						<input type="checkbox" name="job_code" id="J6" value="J6" <%=jobList!=null && jobList.contains("J6")?"checked":"" %>/>
						<label for="J6">대리</label>
						<br />
						<input type="checkbox" name="job_code" id="J7" value="J7" <%=jobList!=null && jobList.contains("J7")?"checked":"" %>/>
						<label for="J7">사원</label> --%>				
						
						
						<!-- checked el:functions로 처리 -->
						<input type="checkbox" name="job_code" id="J1" value="J1" ${f:contains(paramValues.job_code, "J1")?"checked":""}/>
						<label for="J1">대표</label>
						<input type="checkbox" name="job_code" id="J2" value="J2" ${f:contains(paramValues.job_code, "J2")?"checked":""}/>
						<label for="J2">부사장</label>
						<input type="checkbox" name="job_code" id="J3" value="J3" ${f:contains(paramValues.job_code, "J3")?"checked":""}/>
						<label for="J3">부장</label>
						<br />
						<input type="checkbox" name="job_code" id="J4" value="J4" ${f:contains(paramValues.job_code, "J4")?"checked":""}/>
						<label for="J4">차장</label>
						<input type="checkbox" name="job_code" id="J5" value="J5" ${f:contains(paramValues.job_code, "J5")?"checked":""}/>
						<label for="J5">과장</label>
						<input type="checkbox" name="job_code" id="J6" value="J6" ${f:contains(paramValues.job_code, "J6")?"checked":""}/>
						<label for="J6">대리</label>
						<br />
						<input type="checkbox" name="job_code" id="J7" value="J7" ${f:contains(paramValues.job_code, "J7")?"checked":""}/>
						<label for="J7">사원</label>	
					</td>
				</tr>
				<!-- @실습문제 : 부서명(부서없는 사원도 조회) -->
				<tr>
					<th>부서명</th>
					<td>
						<% 
						
							String[] dept_codeArr = (String[])request.getParameterValues("dept_code"); 
							List<String> deptList = null;
							if(dept_codeArr!=null) deptList = Arrays.asList(dept_codeArr);
							
							//el로 처리하려면, pageContext 속성에 담는다.
							pageContext.setAttribute("deptList", deptList);
						%>
						<!-- (input:checkbox[name=dept_code][value=d$]#d$+label[for=d$])*9 -->
						<input type="checkbox" name="dept_code" id="d1" value="D1" ${deptList!=null && deptList.contains("D1")?"checked":"" }/>
						<label for="d1">인사관리부</label>
						<input type="checkbox" name="dept_code" id="d2" value="D2" ${deptList!=null && deptList.contains("D2")?"checked":"" }/>
						<label for="d2">회계관리부</label>
						<input type="checkbox" name="dept_code" id="d3" value="D3" ${deptList!=null && deptList.contains("D3")?"checked":"" }/>
						<label for="d3">마케팅부</label>
						<br />
						<input type="checkbox" name="dept_code" id="d4" value="D4" ${deptList!=null && deptList.contains("D4")?"checked":"" }/>
						<label for="d4">국내영업부</label>
						<input type="checkbox" name="dept_code" id="d5" value="D5" ${deptList!=null && deptList.contains("D5")?"checked":"" }/>
						<label for="d5">해외영업1부</label>
						<input type="checkbox" name="dept_code" id="d6" value="D6" ${deptList!=null && deptList.contains("D6")?"checked":"" }/>
						<label for="d6">해외영업2부</label>
						<br />
						<input type="checkbox" name="dept_code" id="d7" value="D7" ${deptList!=null && deptList.contains("D7")?"checked":"" }/>
						<label for="d7">해외영업3부</label>
						<input type="checkbox" name="dept_code" id="d8" value="D8" ${deptList!=null && deptList.contains("D8")?"checked":"" }/>
						<label for="d8">기술지원부</label>
						<input type="checkbox" name="dept_code" id="d9" value="D9" ${deptList!=null && deptList.contains("D9")?"checked":"" }/>
						<label for="d9">총무부</label>
						<br />
						<input type="checkbox" name="dept_code" id="d0" value="D0" ${deptList!=null && deptList.contains("D0")?"checked":"" }/>
						<label for="d0">부서없음</label>
						
					</td>
				</tr>
				<tr>
					<th colspan="2">
						<input type="submit" id="btn-search" value="검색"  />
					</th>
				</tr>
			</table>
		</form>
	</div>
	<!-- 총검색건수구하기 -->
	<c:if test="${not empty param }">
	<p>총 검색건수는 <span style="color:red">${totalContents }</span>건 입니다.</p>
	</c:if>
	<table class="tbl-emp">
		<tr>
			<th></th>
			<th>사번</th>
			<th>사원명</th>
			<th>주민번호</th>
			<th>이메일</th>
			<th>전화번호</th>
			<th>부서</th>
			<th>직급</th>
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
			<td>${fn:substring(e["EMP_NO"],0,8).concat("******")}</td>
			<td>${e["EMAIL"]}</td>
			<td>${e["PHONE"]}</td>
			<td>${e["DEPT_TITLE"]}</td>
			<td>${e["JOB_NAME"]}</td>
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
			<td colspan="14">
			검색결과가 존재하지 않습니다.
			</td>
		</tr>
		</c:if>
	</table>
</div>

</body>
</html>