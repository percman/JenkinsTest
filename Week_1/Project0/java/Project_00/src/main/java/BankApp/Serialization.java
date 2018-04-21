package BankApp;

import java.io.*;

public class Serialization {
    public static void serializeUsers(User u) {
        ObjectOutputStream objectOut = null;
        FileOutputStream fileOut = null;

        try {
            fileOut = new FileOutputStream("Project_00/src/main/resources/Users.ser");
            objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(u);
            // TODO - log saved User.ser
            //System.out.print("Serialized data is saved in Project_00/src/main/resources/" + name + ".ser");
        } catch (IOException i) {
            // TODO - log exception
            i.printStackTrace();
        } finally {
            // Close resources
            try {
                // TODO - log resource closed for each
                if (fileOut != null) fileOut.close();
                if (fileOut != null) objectOut.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static User deserializeUsers() {
        ObjectInputStream objectIn = null;
        FileInputStream fileIn = null;
        User u = new User();

        try {
            fileIn = new FileInputStream("Project_00/src/main/resources/Users.ser");
            objectIn = new ObjectInputStream(fileIn);
            u = (User) objectIn.readObject();
        } catch (FileNotFoundException fnfe) {
            System.out.println("File not found.");
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
        } finally {
            try {
                if (objectIn != null) objectIn.close();
                if (fileIn != null) fileIn.close();
            } catch (IOException e) {
                // TODO - log resource closed for each
                System.out.println("IOE Exception");
                e.printStackTrace();
            }

        }
        return u;
    }
}
