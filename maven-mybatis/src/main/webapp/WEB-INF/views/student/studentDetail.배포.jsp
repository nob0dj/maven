<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생 상세</title>
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
	<h1>학생 상세</h1>
	<div id="student-container">
		<form name="studentUpdatFrm" method="POST" action="${pageContext.request.contextPath}/student/studentUpdate.do">
			<table class="tbl-student">
				<tbody>
					<tr>
						<th>ID</th>
						<td>
							<input type="text" name="id" readonly/>
						</td>
					</tr>
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
					<tr>
						<th>등록일</th>
						<td>
							<input type="datetime-local" value="" readonly>
						</td>
					</tr>
					<tr>
						<th colspan="2">
							<button type="submit">수정</button>
							<button type="button">삭제</button>
						</th>
					</tr>
				</tbody>
			</table>
		</form>
	</div>

</body>
</html>