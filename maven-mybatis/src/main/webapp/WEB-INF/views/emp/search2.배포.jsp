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
		<form action="" >
			<table id="tbl-search">
				<tr>
					<td colspan="2">
						<input type="button" value="초기화" />
					</td>
				</tr>
				<tr>
					<th>성별</th>
					<td>
						<input type="radio" name="gender" value='남' id="gender0" />
						<label for="gender0">남</label>
						<input type="radio" name="gender" value='여' id="gender1" />
						<label for="gender1">여</label>
					</td>
				</tr>
				<!-- 급여기준 -->
				<tr>
					<th>급여</th>
					<td>
						<input type="number" name="salary" min="0" step="500000" value=""/>
						<input type="radio" name="salaryCompare" id="salary_ge" value='ge'/>
						<label for="salary_ge">이상</label>
						<input type="radio" name="salaryCompare" id="salary_le" value='le'/>
						<label for="salary_le">이하</label>
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
	
	<table class="tbl-emp"></table>
</div>

</body>
</html>