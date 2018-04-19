package com.andrewsrahn.main;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamPractice {
	public static List<User> approved(Map<String, User> users){
		return users.values().stream()
			.filter( u -> u.getApprovedBy() != null)
			.filter( u -> u.getRejectedBy() == null )
			.collect(Collectors.toList());		
	}
	
	public static List<User> pending(Map<String, User> users){
		return users.values().stream()
				.filter( u -> u.getApprovedBy() == null)
				.filter( u -> u.getRejectedBy() == null)
				.collect(Collectors.toList());
	}
	
	public static List<User> rejected(Map<String, User> users){
		return users.values().stream()
				.filter( u -> u.getRejectedBy() != null)
				.filter( u -> u.getApprovedBy() == null)
				.collect(Collectors.toList());
	}
}

/*
 * 	//Map -> Stream -> Filter -> MAP
	Map<Integer, String> collect = map.entrySet().stream()
		.filter(x -> x.getKey() == 2)
		.collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));
 * 
 */