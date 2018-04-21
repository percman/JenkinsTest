package BankApp;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Implements Serializable
public class User implements Serializable {
    // Private variables/data
    private static final long serialVersionUID = 8042201454532240354L;
    private List<User> listOfUsers = new ArrayList<>();
    private int identityNumber;
    private String email;
    private String password;                // Encrypt
    private String firstName;
    private String lastName;
    private String birthday;
    private String street;
    private String state;
    private String country;
    private String zipcode;
    private String socialSecurityNumber;    // Encrypt
    private String role;

    // No arg constructor
    public User() {
    }

    // Setters and Getters
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<User> getListOfUsers() {
        return listOfUsers;
    }

    public void setListOfUsers(List<User> listOfUsers) {
        this.listOfUsers = listOfUsers;
    }

    public int getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(int identityNumber) {
        this.identityNumber = identityNumber;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // Hashcode and equals


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return identityNumber == user.identityNumber &&
                Objects.equals(listOfUsers, user.listOfUsers) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(birthday, user.birthday) &&
                Objects.equals(street, user.street) &&
                Objects.equals(state, user.state) &&
                Objects.equals(country, user.country) &&
                Objects.equals(zipcode, user.zipcode) &&
                Objects.equals(socialSecurityNumber, user.socialSecurityNumber) &&
                Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {

        return Objects.hash(listOfUsers, identityNumber, email, password, firstName, lastName, birthday, street, state, country, zipcode, socialSecurityNumber, role);
    }

    // Methods
    // Static Instance Initializer
    // Check if file exists if not create a new one.
    static {
        File file = new File("Project_00/src/main/resources/Users.ser");

        if (file.exists()) {
            System.out.println("File is exist");
        } else {
            User u = new User();
            Serialization.serializeUsers(u);
        }
    }

    public void addUserToList(User u) {
        this.listOfUsers.add(u);
    }

//    // main used to quick test
//    public static void main(String[] args) {
//
//    }
}
