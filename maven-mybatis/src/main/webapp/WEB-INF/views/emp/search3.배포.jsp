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
		<form>
			<p>
				<h3>검색</h3>
				<input type="button" value="초기화" onclick="location.href='search3.do';"/>
			</p>
			<table id="tbl-search">
				<tr>
					<th>직급</th>
					<td>
						<input type="checkbox" name="job_code" id="J1" value="J1"/>
						<label for="J1">대표</label>
						<input type="checkbox" name="job_code" id="J2" value="J2"/>
						<label for="J2">부사장</label>
						<input type="checkbox" name="job_code" id="J3" value="J3"/>
						<label for="J3">부장</label>
						<br />
						<input type="checkbox" name="job_code" id="J4" value="J4"/>
						<label for="J4">차장</label>
						<input type="checkbox" name="job_code" id="J5" value="J5"/>
						<label for="J5">과장</label>
						<input type="checkbox" name="job_code" id="J6" value="J6"/>
						<label for="J6">대리</label>
						<br />
						<input type="checkbox" name="job_code" id="J7" value="J7"/>
						<label for="J7">사원</label>	
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