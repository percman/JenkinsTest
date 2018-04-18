package com.revature.project0;

import java.io.Serializable;
import java.util.ArrayList;

// houses the current state of a user and the methods that allow them to manipulate thier movie collection
public class User implements Serializable{
private boolean approved,locked,admin = false;//keeps track of whether a user has been approved, is currently locked and is an admin
private static boolean adminSet = false;// checks if there is already one admin
String username, password;// the users username and password
ArrayList<String> movies;// the users library of movies
public User() {
	
}
public User(String name,String pass) {
	username = name;
	password = pass;
	if(!adminSet) {
		admin = true;
		adminSet = true;
		approved = true;
	}
	
}
//allows a user to add a movie into thier collection
public void addMovie(String title) {	
movies.add(title);
}
//allows a user to remove a movie from thier collection
public void removeMovie(String title) {	
movies.remove(title);	
}
//allows a user to view thier collection
public void viewMovies(String title) {	
}
//checks if a user is locked
public boolean isUserLocked() {
	return this.locked;
}
//checks if a user is approved
public boolean isUserApproved() {
	return this.approved;
}
//either locks or unlocks a user
public void setLocked(boolean lock) {
	locked = lock;
}
// approves a user
public void approved() {
	approved = true;
}
}
