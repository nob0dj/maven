<%@ page language="java" contentType="text/html; charset=UTF-8"
			pageEncoding="UTF-8" %>
<!doctype html>
<html>
<body>
	<h2>Hello World!</h2>
	<button id="btn">/helloworld</button>
	<script>
	document.querySelector("#btn").addEventListener("click", (e) => {
		fetch('${pageContext.request.contextPath}/helloworld')
			.then(response => response.text())
			.then(message => alert(message));
	});
	</script>
</body>
</html>
