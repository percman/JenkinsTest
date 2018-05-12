<html>
<body>
<h2>Hello World!</h2>

<form action="/SecondDayServlets/EagerServlet" method="GET">
	<input type="submit" value="EagerServlet">
</form>
<br><br>
<form action="/SecondDayServlets/AlsoEagerServlet" method="GET">
	<input type="submit" value="AlsoEagerServlet">
</form>
<br><br>
<form action="/SecondDayServlets/LazyServlet" method="GET">
	<input type="submit" value="LazyServlet">
</form>
<br><br>
<form action="/SecondDayServlets/IncludeServlet" method="GET">
	<input type="submit" value="IncludeServlet">
</form>
<br><br>
<form action="/SecondDayServlets/ForwardServlet" method="GET">
	<input type="submit" value="ForwardServlet">
</form>
<br><br>
<form action="/SecondDayServlets/SendRedirectServlet" method="GET">
	<input type="text" name="searchQuery" placeholder="Enter search text">
	<input type="submit" value="SendRedirectServlet">
</form>
<br><br><br>
<form action="/SecondDayServlets/FormProcessingServlet" method="POST">
	<input type="text" name="username" placeholder="Please enter username"><br>
	<input type="password" name="password" placeholder="Please enter password"><br>
	<label for="email">Email</label>
	<input id="email" type="radio" name="contact" value="Email">
	<label for="phone">Phone</label>
	<input id="phone" type="radio" name="contact" value="Phone">
	<label for="msg">Text Message</label>
	<input id="msg" type="radio" name="contact" value="Text MSG">
	
	<input type="submit" value="Submit Form">
</form>
<br><br><br>
<h2>Insert Pokemon</h2>

<!-- Removed both the action and method attributes from the form -->
<form>
	<!-- Added the id so we can get the text content of the input using JavaScript -->
	<input type="text" name="name" id="pokemonName" placeholder="Please enter a Pokemon Name">
	
	<!-- Added the id so we can get the text content of the input using JavaScript -->
	<input type="text" name="type" id="pokemonType" placeholder="Please enter the Pokemon Type">
	
	<!-- Added the id so we can get the reference to the button using JavaScript -->
	<!-- Also, changed the type to button so that it wouldn't refresh the page every time -->
	<input type="button" id="pokemonSubmitBtn" value="Submit Pokemon">
</form>
<!-- Added this JavaScript resource to send the request for Jackson to use properly -->
<script src="./index.js" type="text/javascript"></script>
</body>
</html>






