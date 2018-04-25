package BankApp;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Implements Serializable
public class User implements Serializable {
    // Private data field
    private static final long serialVersionUID = 4283633998630427366L;
    //final static Logger logger = Logger.getLogger(KLBankLogger.class);
    private String username;
    private String password;        //Encrypt
    private String email;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private double balance;
    private String role;
    private int lock; // -1 = new, 1 = locked,  0 = unlocked

    // No arg Constructor
    User() {
    }

    //Setters and Getters
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getLock() {
        return lock;
    }

    public void setLock(int lock) {
        this.lock = lock;
    }

    // hashCode() and equals()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Double.compare(user.balance, balance) == 0 &&
                lock == user.lock &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(birthday, user.birthday) &&
                Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {

        return Objects.hash(username, password, email, firstName, lastName, birthday, balance, role, lock);
    }

    public static boolean checkLogin(String username, String password) {
        User u = Serialization.deserializeUser(username);
        if (u.getPassword() != null) {
            return u.getPassword().equals(password);
        } else
            return false;
    }

    public static User getUser(String username, String password) {
        User u = Serialization.deserializeUser(username);
        if (u.getPassword() == password) {
            return u;
        } else
            return null;
    }
}
