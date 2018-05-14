package com.revature.doa;

import com.revature.model.User;

public interface BankDao {

    double getGold(User user);

    boolean withdrawBankGold(User user, double withdraw);

    boolean depositUserGold(User user, double deposit);
}
