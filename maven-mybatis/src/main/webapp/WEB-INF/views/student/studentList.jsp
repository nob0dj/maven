<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="com.sh.app.student.entity.Student, java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mybatis - Student</title>
<style>
div.student-container{text-align:center;}
table.tbl-student{margin:0 auto;border:1px solid; border-collapse:collapse;}
table.tbl-student th,table.tbl-student td{
	border:1px solid;
	padding:5px;
}
tr[data-id] {cursor: pointer;}
</style>
<script>
window.addEventListener('click', () => {	
	document.querySelectorAll("tr[data-id]").forEach((tr) => {
		tr.addEventListener('click', (e) => {
			const id = tr.dataset.id;
			console.log(id);
			location.href = `${pageContext.request.contextPath}/student/studentDetail.do?id=\${id}`;
		});
	});
});
</script>
</head>
<body>
<div class="student-container">
	<h1>Mybatis - Student</h1>
	
	<p>SqlSession의 selectList메소드를 호출해서 List&lt;Student>를 리턴받음.</p>
	<table class="tbl-student">
		<tr>
			<th>학번</th>
			<th>이름</th>
			<th>전화번호</th>
			<th>등록일</th>
		</tr>
		<c:if test="${not empty students}">
			<c:forEach var="s" items="${students}">
				<tr data-id="${s.id}">
					<td>${s.id}</td>
					<td>${s.name}</td>
					<td>${s.tel}</td>
					<td>
						<%-- java.time.LocalDateTime ---parseDate---> java.util.Date  ---formatDate--> 출력 --%>
						<%-- fmt:parseDate[pattern]에 ss 형식 작성시 오류가 나는 경우가 있다. --%>
						<fmt:parseDate value="${s.createdAt}" pattern="yyyy-MM-dd'T'HH:mm" var="createdAt"/>
						<fmt:formatDate value="${createdAt}" pattern="yy-MM-dd HH:mm"/>
					</td>
				</tr>
			
			</c:forEach>
		</c:if>
		<c:if test="${empty students}">
			<tr>
				<td colspan="6" align="center">등록된 학생이 없습니다.</td>
			<tr>		
		</c:if>	
	</table>
	<br/><br/>
	
	<p>SqlSession의 selectList메소드를 호출해서 List&lt;Map&lt;String, Object>>를 리턴받음.</p>
	<table class="tbl-student">
		<tr>
			<th>학번</th>
			<th>이름</th>
			<th>전화번호</th>
			<th>등록일</th>
		</tr>
		<c:if test="${not empty studentMaps}">
			<c:forEach var="map" items="${studentMaps}">
				<tr>
					<td>${map.id}</td>
					<td>${map.name}</td>
					<td>${map.tel}</td>
					<td><fmt:formatDate value="${map.createdAt}" pattern="yyyy/MM/dd(E)"/> </td>
				</tr>
			
			</c:forEach>
		</c:if>
		<c:if test="${empty studentMaps}">
			<tr>
				<td colspan="6" align="center">등록된 학생이 없습니다.</td>
			<tr>		
		</c:if>	
	</table>
</div>
</body>
</html>