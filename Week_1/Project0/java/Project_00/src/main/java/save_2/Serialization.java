//package save_2;
//
//import java.io.*;
//
//public class Serialization {
//    public static void serializeUsers(User user) {
//        ObjectOutputStream objectOut = null;
//        FileOutputStream fileOut = null;
//
//        try {
//            fileOut = new FileOutputStream("Project_00/src/main/resources/Users.ser");
//            objectOut = new ObjectOutputStream(fileOut);
//            objectOut.writeObject(user);
//            // TODO - log saved User.ser
//        } catch (IOException i) {
//            // TODO - log exception
//            i.printStackTrace();
//        } finally {
//            // Close resources
//            try {
//                // TODO - log resource closed for each
//                if (fileOut != null) fileOut.close();
//                if (objectOut != null) objectOut.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public static User deserializeUsers() {
//        ObjectInputStream objectIn = null;
//        FileInputStream fileIn = null;
//        User user = new User();
//
//        try {
//            fileIn = new FileInputStream("Project_00/src/main/resources/Users.ser");
//            objectIn = new ObjectInputStream(fileIn);
//            user = (User) objectIn.readObject();
//        } catch (FileNotFoundException fnfe) {
//            System.out.println("File not found.");
//        } catch (IOException i) {
//            i.printStackTrace();
//        } catch (ClassNotFoundException c) {
//            System.out.println("Class not found");
//            c.printStackTrace();
//        } finally {
//            try {
//                if (objectIn != null) objectIn.close();
//                if (fileIn != null) fileIn.close();
//            } catch (IOException e) {
//                // TODO - log resource closed for each
//                System.out.println("IOE Exception");
//                e.printStackTrace();
//            }
//        }
//        return user;
//    }
//}
