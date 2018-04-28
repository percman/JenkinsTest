package com.revature.dao;

import java.util.List;

import com.revature.users.Person;
import com.revature.users.Principal;
import com.revature.users.Teacher;

public class PrincipalDaoImpl implements PrincipalDao{

	@Override
	public Person getPrincipal(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertPrincipal(Principal principal) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updatePrincipal(Principal principal) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getPasswordHash(Principal principal) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Teacher> getAllTeachers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Teacher> getUnapprovedTeachers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Teacher> getUnlockedTeachers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Teacher> getLockedTeachers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean approveAllTeachers() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean approveTeacher(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean lockTeacher(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean unlockTeacher(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteTeacher(String username) {
		// TODO Auto-generated method stub
		return false;
	}

}
