package com.revature.project_0;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/*
 * This class allows the object to store two lists. One of pending users (waiting for admin approval)
 * And another of current users. The list of pending users, named waiting, is a list of user objects. This way when the admin 
 * is approving users, those who are approved will be immediately serialized.
 *  Those that the admin explicitly rejects will be deleted from the list
 */
public class UserList implements Serializable {
	
	private static final long serialVersionUID = 789605920107251276L;
	private List<User> waiting;
	private List<String> list;
	
	public UserList() {
		super();
		this.waiting = new ArrayList<>();
		this.list = new ArrayList<>();
	}
	
	public void Add(String user) {
		list.add(user);
	}
	
	public boolean contains(String user) {
		return list.contains(user);
	}
	
	public void Delete(String user) {
		list.remove(user);
	}
	
	public void Show() {
		for(String user: list) {
			System.out.println("User name: "+user);
		}
	}
	
	public boolean waitingContains(User user) {
		for(User list : waiting) {
			if(list.getUserName().equals(user.getUserName()))
				return true;
		}
		return waiting.contains(user);
	}
	
	public void waitingAdd(User user) {
		waiting.add(user);
	}
	
	public void waitingDelete(User user) {
		waiting.remove(user);
	}
	
	public void waitingShow() {
		for(User user:waiting) {
			System.out.println("User name: "+user.getUserName());
		}
	}
	public List<User> getWaiting(){
		return waiting;
	}
	
	public User getWaiting(int index) {
		return waiting.get(index);
	}
	
	public int waitingSize() {
		return waiting.size();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((list == null) ? 0 : list.hashCode());
		result = prime * result + ((waiting == null) ? 0 : waiting.hashCode());
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
		UserList other = (UserList) obj;
		if (list == null) {
			if (other.list != null)
				return false;
		} else if (!list.equals(other.list))
			return false;
		if (waiting == null) {
			if (other.waiting != null)
				return false;
		} else if (!waiting.equals(other.waiting))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserList [waiting=" + waiting + ", list=" + list + "]";
	}


	

}


