package com.revature.users;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.revature.dao.movie.MovieService;



// houses the current state of a user and the methods that allow them to manipulate thier movie collection
public class User implements Serializable, NewUser{
private static final long serialVersionUID = -9008338397651161896L;
private String username; // the users username and password
private String password;
private int moviesOut;
public User() {

}
public User(String name,String pass) {
	setUsername(name);
	setPassword(pass);
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

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
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
public int getMoviesOut() {
	return moviesOut;
}
public void setMoviesOut(int moviesOut) {
	this.moviesOut = moviesOut;
}

}
