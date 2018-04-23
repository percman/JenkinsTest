package com.andrewsrahn.main;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Stream {
	public static List<User> approved(Map<String, User> users){
		return users.values().stream()
			.filter( u -> u.getApprovedBy() != null )
			.filter( u -> u.getRejectedBy() == null )
			.collect(Collectors.toList());		
	}
	
	public static List<User> pending(Map<String, User> users){
		return users.values().stream()
				.filter( u -> u.getApprovedBy() == null )
				.filter( u -> u.getRejectedBy() == null )
				.collect(Collectors.toList());
	}
	
	public static List<User> rejected(Map<String, User> users){
		return users.values().stream()
				.filter( u -> u.getRejectedBy() != null)
				.filter( u -> u.getApprovedBy() == null)
				.collect(Collectors.toList());
	}
	
	public static String pendingUsers(Map<String, User> users) {
		List<User> usersList = Stream.pending(users);
		String string = usersList.stream()
			.map(u -> u.getName() + ", ")
			.reduce("pending: ", String::concat);
		return string.substring(0, string.length()-2);
	}

	public static String approvedUsers(Map<String, User> users) {
		List<User> usersList = Stream.approved(users);
		String string = usersList.stream()
			.map(u -> u.getName() + ", ")
			.reduce("approved: ", String::concat);
		return string.substring(0, string.length()-2);
	}

	public static String rejectedUsers(Map<String, User> users) {
		List<User> usersList = Stream.rejected(users);
		String string = usersList.stream()
			.map(u -> u.getName() + ", ")
			.reduce("rejected: ", String::concat);
		return string.substring(0, string.length()-2);
	}

	public static String lockedUsers(Map<String, User> users) {
		String string = users.values().stream()
			.filter( u -> u.isLocked() )
			.map( u -> u.getName() + ", ")
			.reduce("locked: ", String::concat);
		return string.substring(0, string.length()-2);
	}

	public static String unlockedUsers(Map<String, User> users) {
		String string = users.values().stream()
			.filter( u -> !u.isLocked() )
			.map( u -> u.getName() + ", ")
			.reduce("unlocked: ", String::concat);
		return string.substring(0, string.length()-2);
	}
	
	public static String existingAdministrators(Map<String, Administrator> map) {
		String string = map.values().stream()
				.map( a -> a.getName() )
				.reduce("existing administrators: ", String::concat);
		return string.substring(0, string.length()-2);
	}

	public static String existingUsers(Map<String, User> users) {
		String string = users.values().stream()
				.map( a -> a.getName() )
				.reduce("existing users: ", String::concat);
		return string.substring(0, string.length()-2);
	}
}