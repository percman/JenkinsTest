package BankApp;

import org.apache.log4j.Logger;

import java.io.*;

public class Serialization implements Serializable {
    private static final long serialVersionUID = 4025645288366771784L;
    final static Logger logger = Logger.getLogger(KLBankLogger.class);
    public static void serializeUser(User user){
        ObjectOutputStream objectOut = null;
        FileOutputStream fileOut = null;

        try {
            fileOut = new FileOutputStream("Project_00/src/main/resources/Users/"+user.getUsername()+".ser");
            objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(user);
            logger.info(".ser file created.");
        } catch (IOException ioe) {
            logger.warn("I/O Exception", ioe);
        } finally {
            // Close resources
            try {
                if (fileOut != null) fileOut.close();
                if (objectOut != null) objectOut.close();
                logger.info("Out resources closed.");
            } catch (IOException ioe) {
                logger.warn("I/O Exception", ioe);
            }
        }
    }

    public static User deserializeUser(String username) {
        ObjectInputStream objectIn = null;
        FileInputStream fileIn = null;
        User user = new User();
        try {
            fileIn = new FileInputStream("Project_00/src/main/resources/Users/"+username+".ser");
            objectIn = new ObjectInputStream(fileIn);
            user = (User) objectIn.readObject();
        } catch (FileNotFoundException fnfe) {
            logger.warn("File not found", fnfe);
        } catch (IOException ioe) {
            logger.warn("I/O Exception", ioe);
        } catch (ClassNotFoundException c) {
            logger.warn("Class not found.", c);
        } finally {
            try {
                if (objectIn != null) objectIn.close();
                if (fileIn != null) fileIn.close();
                logger.info("In resources closed.");
            } catch (IOException ioe) {
                logger.warn("I/O Exception", ioe);
            }
        }
        return user;
    }
}
