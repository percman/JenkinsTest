package com.revature.model;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {

    private static final long serialVersionUID = 6253450157826597834L;
    private int userId;
    private int userLock;
    private String username;
    private String userpass;
    private String firstName;
    private String lastName;
    private int userClassId;
    private int currentLocationId;
    private int HP;
    private int LVL;
    private int EXP;
    private int ATK;
    private int DEF;
    private double gold;

    public User() {
    }

    public User(String username, String userpass, String firstName, String lastName) {
        this.username = username;
        this.userpass = userpass;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(int userId, int userLock, String username, String userpass, String firstName, String lastName, int userClassId, int currentLocationId, int HP, int LVL, int EXP, int ATK, int DEF, int gold) {
        this.userId = userId;
        this.userLock = userLock;
        this.username = username;
        this.userpass = userpass;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userClassId = userClassId;
        this.currentLocationId = currentLocationId;
        this.HP = HP;
        this.LVL = LVL;
        this.EXP = EXP;
        this.ATK = ATK;
        this.DEF = DEF;
        this.gold = gold;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserLock() {
        return userLock;
    }

    public void setUserLock(int userLock) {
        this.userLock = userLock;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpass() {
        return userpass;
    }

    public void setUserpass(String userpass) {
        this.userpass = userpass;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getUserClassId() {
        return userClassId;
    }

    public void setUserClassId(int userClassId) {
        this.userClassId = userClassId;
    }

    public int getCurrentLocationId() {
        return currentLocationId;
    }

    public void setCurrentLocationId(int currentLocationId) {
        this.currentLocationId = currentLocationId;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getLVL() {
        return LVL;
    }

    public void setLVL(int LVL) {
        this.LVL = LVL;
    }

    public int getEXP() {
        return EXP;
    }

    public void setEXP(int EXP) {
        this.EXP = EXP;
    }

    public int getATK() {
        return ATK;
    }

    public void setATK(int ATK) {
        this.ATK = ATK;
    }

    public int getDEF() {
        return DEF;
    }

    public void setDEF(int DEF) {
        this.DEF = DEF;
    }

    public double getGold() {
        return gold;
    }

    public void setGold(double gold) {
        this.gold = gold;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId &&
                userLock == user.userLock &&
                userClassId == user.userClassId &&
                currentLocationId == user.currentLocationId &&
                HP == user.HP &&
                LVL == user.LVL &&
                EXP == user.EXP &&
                ATK == user.ATK &&
                DEF == user.DEF &&
                gold == user.gold &&
                Objects.equals(username, user.username) &&
                Objects.equals(userpass, user.userpass) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, userLock, username, userpass, firstName, lastName, userClassId, currentLocationId, HP, LVL, EXP, ATK, DEF, gold);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userLock=" + userLock +
                ", username='" + username + '\'' +
                ", userpass='" + userpass + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userClassId=" + userClassId +
                ", currentLocationId=" + currentLocationId +
                ", HP=" + HP +
                ", LVL=" + LVL +
                ", EXP=" + EXP +
                ", ATK=" + ATK +
                ", DEF=" + DEF +
                ", gold=" + gold +
                '}';
    }
}

