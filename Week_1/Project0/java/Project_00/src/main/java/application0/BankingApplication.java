package application0;

import java.io.*;
import java.util.Scanner;

public class BankingApplication {
    public static void main(String[] args) {
        String name;
        int balance;
        String password;

        Scanner input = new Scanner(System.in);

        System.out.println("Hello User, To make an account please enter the following:");
        System.out.print("\nName: ");
        name = input.next();
        System.out.print("\nStarting Balance: ");
        balance = input.nextInt();
        System.out.print("\nGender: ");
        password = input.next();
        User u1 = new User.UserBuilder().balance(100).name("Kirk").password("Male").build();
        User u2 = new User.UserBuilder().balance(10000).name("Paulina").password("Female").build();
        User u3 = new User.UserBuilder().balance(balance).name(name).password(password).build();

        System.out.println(u1.toString());
        System.out.println(u2.toString());
        System.out.println(u3.toString());

        try {
            FileOutputStream fileOut = new FileOutputStream("Project_00/src/main/resources/" + name + ".ser");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(u3);
            objectOut.close();
            fileOut.close();
            System.out.print("Serialized data is saved in Project_00/src/main/resources/" + name + ".ser");
        } catch (IOException i) {
            i.printStackTrace();
        }

        System.out.println("\nPlease enter the name of the user that you want to retrieve: ");
        name = input.next();
        DeserializeUser.retrieveUser(name);

        input.close();
    }
}

class DeserializeUser {

    public static void retrieveUser(String name) {
        User u = null;

        try {
            FileInputStream fileIn = new FileInputStream("Project_00/src/main/resources/" + name + ".ser");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            u = (User) objectIn.readObject();
            objectIn.close();
            fileIn.close();
        } catch (FileNotFoundException fnfe) {
            System.out.println("User not found.");
            return;
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Employee class not found");
            c.printStackTrace();
            return;
        }

        System.out.println("Deserialized User...");
        System.out.println("Name: " + u.getName());
        System.out.println("Password: " + u.getPassword());
        System.out.println("Address: " + u.getBalance());


    }
}