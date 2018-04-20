package com.revature.project0;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

// houses the current state of a user and the methods that allow them to manipulate thier movie collection
public class User implements Serializable, NewUser{
String username, password; // the users username and password
Set<String> movies = new TreeSet<>();// the users library of movies
public User() {
	
}
public User(String name,String pass) {
	username = name;
	password = pass;
}
//allows a user to add a movie into thier collection
public void addMovie(String title) {	
this.movies.add(title);
}
//allows a user to remove a movie from thier collection
public void removeMovie(String title) {	
this.movies.remove(title);	
}
//allows a user to view thier collection
public void viewMovies() {	
for(String movie : this.movies) {
	System.out.println(movie);
}
}
//checks if a user is locked
public boolean isUserLocked() {
	return Record.lockedUsers.contains(this.username);
}
//checks if a user is approved
public boolean isUserApproved() {
	return !Record.unApprovedUsers.contains(this.username);
}

}
