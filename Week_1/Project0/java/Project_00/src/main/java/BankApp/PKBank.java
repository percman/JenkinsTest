package BankApp;

import java.util.Scanner;

public class PKBank {
    private static User masterUser = new User();
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        masterUser = Serialization.deserializeUsers();

        // register user 1
        User u1 = new User();
        System.out.println("User 1!");
        System.out.println("Enter name: ");
        u1.setFirstName(input.next());
        System.out.println("Enter password: ");
        u1.setPassword(input.next());

        // Add new user into the masterUser's listOfUsers
        masterUser.addUserToList(u1);

        // Serialize masterUser
        Serialization.serializeUsers(masterUser);

        // Deserialize the newly serialized User.ser
        masterUser = Serialization.deserializeUsers();

        // Print data from deserialized Users.ser from resources.
        for(User user : masterUser.getListOfUsers() ){
            System.out.println("Name: " + user.getFirstName());
            System.out.println("Password: " + user.getPassword());

        }

        System.out.println("\n======================================\n");
        // register user 2
        User u2 = new User();
        System.out.println("User 2!");
        System.out.println("Enter name: ");
        u2.setFirstName(input.next());
        System.out.println("Enter password: ");
        u2.setPassword(input.next());
        masterUser.addUserToList(u2);
        Serialization.serializeUsers(masterUser);
        masterUser = Serialization.deserializeUsers();
        for(User user : masterUser.getListOfUsers() ){
            System.out.println("Name: " + user.getFirstName());
            System.out.println("Password: " + user.getPassword());
        }



    }
}
