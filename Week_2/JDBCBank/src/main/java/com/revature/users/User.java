package com.revature.users;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;



// houses the current state of a user and the methods that allow them to manipulate thier movie collection
public class User implements Serializable, NewUser{
private static final long serialVersionUID = -9008338397651161896L;
private String username; // the users username and password
private String password;
private Set<String> movies;// the users library of movies
public User() {

}
public User(String name,String pass) {
	setMovies(new HashSet<>());
	setUsername(name);
	setPassword(pass);
}
//allows a user to add a movie into thier collection
public void addMovie(String title) {	
this.getMovies().add(title);
}
//allows a user to remove a movie from thier collection
public void removeMovie(String title) {	
this.getMovies().remove(title);	
}
//allows a user to view thier collection
public void viewMovies() {	
for(String movie : this.getMovies()) {
	System.out.println(movie);
}
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public Set<String> getMovies() {
	return movies;
}
public void setMovies(Set<String> movies) {
	this.movies = movies;
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((movies == null) ? 0 : movies.hashCode());
	result = prime * result + ((password == null) ? 0 : password.hashCode());
	result = prime * result + ((username == null) ? 0 : username.hashCode());
	return result;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	User other = (User) obj;
	if (movies == null) {
		if (other.movies != null)
			return false;
	} else if (!movies.equals(other.movies))
		return false;
	if (password == null) {
		if (other.password != null)
			return false;
	} else if (!password.equals(other.password))
		return false;
	if (username == null) {
		if (other.username != null)
			return false;
	} else if (!username.equals(other.username))
		return false;
	return true;
}

}
