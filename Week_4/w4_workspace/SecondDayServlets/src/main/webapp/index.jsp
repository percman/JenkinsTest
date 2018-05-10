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
	
	<br>
	<br>
	
	<form action="/SecondDayServlets/FormProcessingServlet" method="POST">
		<input type="text" name="username" placeholder="Please enter username">
		<br><br>
		<input type="password" name="password" placeholder="Please enter password">
		<br><br>
		<label for="email">Email</label>
		<input id="email" type="radio" name="contact" value="Email">
		<br><br>
		<label for="call">Call</label>
		<input id="call" type="radio" name="contact" value="Call">
		<br><br>
		<label for="text">Text</label>
		<input id="text" type="radio" name="contact" value="Text">
		<br><br>
		<input type="submit" value="Submit form">
	</form>

	<br>
	<br>
	
	<h2>Insert Pokemon</h2>
	
	<form action="/SecondDayServlets/InsertPokemonServlet" method="POST">
		<input type="text" name="name" placeholder="Please enter a Pokemon Name">
		<br><br>
		<input type="text" name="type" placeholder="Please enter a Pokemon Type">
		<br><br>
		<input type="submit" value="Submit Pokemon">
	</form>
	


</body>
</html>
