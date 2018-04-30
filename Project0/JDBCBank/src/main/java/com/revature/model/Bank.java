package com.revature.model;

import java.io.Serializable;
import java.util.Objects;

public class Bank implements Serializable {

    private static final long serialVersionUID = -585357025872951110L;
    private int bankAccountId;
    private double bankGold;
    private int userId;

    private Bank() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(int bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public double getBankGold() {
        return bankGold;
    }

    public void setBankGold(double bankGold) {
        this.bankGold = bankGold;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bank bank = (Bank) o;
        return bankAccountId == bank.bankAccountId &&
                bankGold == bank.bankGold &&
                userId == bank.userId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(bankAccountId, bankGold, userId);
    }

    @Override
    public String toString() {
        return "Bank{" +
                "bankAccountId=" + bankAccountId +
                ", bankGold=" + bankGold +
                ", userId=" + userId +
                '}';
    }
}
