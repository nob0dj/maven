<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생 등록</title>
<style>
div#student-container{text-align:center;}
table.tbl-student{margin:0 auto;border:1px solid; border-collapse:collapse;}
table.tbl-student th,table.tbl-student td{
	border:1px solid;
	padding:5px;
}
</style>
</head>
<body>
	<h1>학생 등록</h1>
	<div id="student-container">
		<form name="studentCreateFrm" method="POST" action="${pageContext.request.contextPath}/student/studentCreate.do">
			<table class="tbl-student">
				<tbody>
					<tr>
						<th>이름</th>
						<td>
							<input type="text" name="name" required/>
						</td>
					</tr>
					<tr>
						<th>전화번호</th>
						<td>
							<input type="tel" name="tel" maxlength="11" required/>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>

</body>
</html>