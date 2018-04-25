//package save_2;
//
//import java.io.Serializable;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//
//// Implements Serializable
//public class User implements Serializable {
//    // Private data field
//    private static final long serialVersionUID = 8026208272321552519L;
//    static List<User> listOfUsers = new ArrayList<>();
//    private String email;
//    private String password;        //Encrypt
//    private String firstName;
//    private String lastName;
//    private LocalDate birthday;
//    private double balance;
//    private String role;
//    private int lock; // -1 = new, 0 = lock,  1 = unlocked
//
//    // No arg Constructor
//    User() {
//    }
//
//    // Setters and Getters
//    public static long getSerialVersionUID() {
//        return serialVersionUID;
//    }
//
//    public List<User> getListOfUsers() {
//        return listOfUsers;
//    }
//
//    public void setListOfUsers(List<User> listOfUsers) {
//        this.listOfUsers = listOfUsers;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public LocalDate getBirthday() {
//        return birthday;
//    }
//
//    public void setBirthday(LocalDate birthday) {
//        this.birthday = birthday;
//    }
//
//    public double getBalance() {
//        return balance;
//    }
//
//    public void setBalance(double balance) {
//        this.balance = balance;
//    }
//
//    public String getRole() {
//        return role;
//    }
//
//    public void setRole(String role) {
//        this.role = role;
//    }
//    public int getLock() {
//        return lock;
//    }
//
//    public void setLock(int lock) {
//        this.lock = lock;
//    }
//
//    // hashCode() and equals()
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        User user = (User) o;
//        return Objects.equals(email, user.email) &&
//                Objects.equals(password, user.password) &&
//                Objects.equals(firstName, user.firstName) &&
//                Objects.equals(lastName, user.lastName) &&
//                Objects.equals(birthday, user.birthday) &&
//                Objects.equals(balance, user.balance) &&
//                Objects.equals(role, user.role);
//    }
//
//    @Override
//    public int hashCode() {
//
//        return Objects.hash(email, password, firstName, lastName, birthday, balance, role);
//    }
//
//    /**********************************
//
//     Start of methods.
//
//     **********************************/
//
//    // Check email for login
//    public static boolean checkLogin(String email, String password) {
//        boolean result = false;
//        for (User u : Serialization.deserializeUsers().getListOfUsers()) {
//            System.out.println("User: " + u.getFirstName() );
//            if (u.getEmail().equals(email) && u.getPassword().equals(password)) {
//                result = true;
//                break;
//            }
//        }
//        return result;
//    }
//
//    public static User getUser(String email) {
//        User user = new User();
//        for (User u : User.listOfUsers) {
//            if (u.getEmail().equals(email)) {
//                user = u;
//                break;
//            } else {
//                System.out.println("User not found!");
//                //todo log that user was not found
//            }
//        }
//        return user;
//    }
//}
