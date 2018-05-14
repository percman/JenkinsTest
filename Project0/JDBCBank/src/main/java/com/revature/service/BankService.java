package com.revature.service;

import com.revature.doa.BankDao;
import com.revature.doa.BankDaoImpl;
import com.revature.model.User;

public class BankService {

    private static BankDao dao = new BankDaoImpl();

    private BankService() {
    }

    public static double getGold(User user) {
        return dao.getGold(user);
    }

    public static boolean withdrawBankGold(User user, double withdraw) {
        return dao.withdrawBankGold(user, withdraw);
    }

    public static boolean depositUserGold(User user, double deposit) {
        return dao.depositUserGold(user, deposit);
    }
}
