<html>
<body>
	<h2>Hello World!</h2>


	<form action="/SecondDayServlets/EagerServlet" method="GET">
		<input type="submit" value="EagerServlet">
	</form>

	<br>
	<br>
	
	<form action="/SecondDayServlets/ForwardServlet" method="GET">
		<input type="submit" value="EagerServlet via ForwardServlet">
	</form>

	<br>
	<br>

	<form action="/SecondDayServlets/AlsoEagerServlet" method="GET">
		<input type="submit" value="AlsoEagerServlet">
	</form>

	<br>
	<br>

	<form action="/SecondDayServlets/LazyServlet" method="GET">
		<input type="submit" value="LazyServlet">
	</form>
	
	<br>
	<br>

	<form action="/SecondDayServlets/IncludeServlet" method="GET">
		<input type="submit" value="LazyServlet via IncludeServlet">
	</form>
	
	<br>
	<br>

	<form action="/SecondDayServlets/SendRedirectServlet" method="GET">
		<input type="text" name="searchQuery">
		<input type="submit" value="SendRedirectServlet">
	</form>

</body>
</html>
