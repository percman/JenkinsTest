package com.revature.DAO;

import java.util.List;

import com.revature.model.Account;
import com.revature.model.User;

public interface AccountAccessDAO {
	List<User> getLockedUsers();
	List<User> getUnlockedUsers();
	Boolean getPermission(Account username);
	List<User> getPending();
	Boolean isPending(Account account);
	Boolean isLocked(Account account);
	boolean insertAccountAccess(Account account);
	boolean ApproveUser(User user);
	
	boolean LockUser(User user);
	boolean UnLockUser(User user);
}
