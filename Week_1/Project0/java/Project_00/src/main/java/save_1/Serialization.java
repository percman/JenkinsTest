package save_1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Serialization {
//    public static void serializeList(List<User> list) {
//        ObjectOutputStream objectOut = null;
//        FileOutputStream fileOut = null;
//
//        try {
//            fileOut = new FileOutputStream("Project_00/src/main/resources/Users.ser");
//            objectOut = new ObjectOutputStream(fileOut);
//            objectOut.writeObject(list);
//            // TODO - log saved User.ser
//        } catch (IOException i) {
//            // TODO - log exception
//            i.printStackTrace();
//        } finally {
//            // Close resources
//            try {
//                // TODO - log resource closed for each
//                if (fileOut != null) fileOut.close();
//                if (fileOut != null) objectOut.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public static List deserializeList() {
//        ObjectInputStream objectIn = null;
//        FileInputStream fileIn = null;
//        List<User> list = new ArrayList<>();
//
//        try {
//            fileIn = new FileInputStream("Project_00/src/main/resources/Users.ser");
//            objectIn = new ObjectInputStream(fileIn);
//            list = (List) objectIn.readObject();
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
//
//        }
//        return list;
//    }
}
