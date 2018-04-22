package BankApp;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.SortedMap;

public class PKBank {
    // Private Data field
    private static Scanner input = new Scanner(System.in);
    private static User masterUser = new User();
    private static User currentUser = new User();
    private static int selected;
    private static LocalTime time;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");


    // Default Constructor

    /**********************************

     Start of required methods.

     **********************************/

    private static void start() {
        //temporary
        masterUser = Serialization.deserializeUsers();
        for (User user : masterUser.getListOfUsers()) {
            System.out.println("Email: " + user.getEmail());
            System.out.println("Password: " + user.getPassword());

        }
        //
        intro();
        menu();
    }

    private static void intro() {
        // Add created by Kirk Legarda
        System.out.println("*=========== PKBank Console Application ===========*\n" +
                "|                    Welcome!                      |\n" +
                "*==================================================*\n");

    }

    private static void menu() {
        do {
            System.out.print("\n-= Menu =- " +
                    "\n1 - Login" +
                    "\n2 - Register" +
                    "\n0 - Exit" +
                    "\n\nEnter number: ");
            selected = input.nextInt();
            retrieveMenuSelection(selected);
        }
        while (selected != 0);
    }

    private static void retrieveMenuSelection(int selected) {
        switch (selected) {
            case 1:
                login();
                break;
            case 2:
                register();
                break;
            case 0:
                PKBank.exit();
                break;
            default:
                System.out.println("\nInvalid selection!");

        }
    }

    public static void customerMenu() {

    }

    public static void adminMenu() {

    }

    // Get string from User,
//    private static String getUserInput(String type) {
//        switch (type.toLowerCase()) {
//            case "int":
//                selected = Integer.parseInt(input.next());
//                break;
//
//            case "string":
//                selected = input.next();
//                break;
//
//            case "char":
//                selected = (String.valueOf(input.next().charAt(0))).toUpperCase();
//                break;
//
//            default:
//                //todo log error
//                System.out.println("did not type correct type. ");
//        }
//        return selected;
//    }

    private static void login() {
        String email;
        String password;
        System.out.print("E-mail: ");
        email = input.next();
        System.out.print("Password: ");
        password = input.next();
        if (masterUser.checkLogin(email, password)) {
            currentUser = masterUser.getUser(email);
            System.out.println("Current user is " + currentUser.getEmail());
        } else
            System.out.println("Invalid Email/Password!");
    }

    private static void register() {
        User u = new User();
        char reviewCheck;
        do {
            System.out.println("\n-= Registration =-");
            System.out.println("\nTo register, enter the following: ");
            System.out.print("\nFirst Name: ");
            u.setFirstName(input.next());
            System.out.print("\nLast Name: ");
            u.setLastName(input.next());
            System.out.print("\nBirthday (mm/dd/yyyy): ");
            u.setBirthday(LocalDate.parse(input.next(),formatter));
            System.out.print("\nE-mail: ");
            u.setEmail(input.next());
            System.out.print("\nPassword: ");
            u.setPassword(input.next()); //todo check if this is saved encrypted
            System.out.println("\n-= Registration Review =-");
            System.out.print("\n*==================================================*");
            System.out.printf("\n%-15s %-34s %s", "|Name:", u.getFirstName() + " " + u.getLastName(), "|");
            System.out.printf("\n%-15s %-34s %s", "|Birthday:", formatter.format(u.getBirthday()), "|"); // find a way to turn localdate to mm/dd/yyyy without formatter variable
            System.out.printf("\n%-15s %-34s %s", "|E-mail:", u.getEmail(), "|");
            System.out.printf("\n%-15s %-34s %s", "|Birthday:", u.getPassword(), "|"); // todo find a way to return as *********
            System.out.print("\n*==================================================*");
            System.out.println("\nIs this correct?\n(Y/N): ");
            reviewCheck = input.next().toUpperCase().charAt(0);
        }
        while (reviewCheck == 'N');
        //Add new user into the masterUser's listOfUsers
        masterUser.addUserToList(u);
        Serialization.serializeUsers(masterUser);
        System.out.println("User added to User list!");
    }

    //todo logout that makes currentUser null;

    /**********************************

     Start of Miscellaneous methods.

     **********************************/
//    private static String getDateTime(LocalDateTime date, String pattern) {
//        DateTimeFormatter formatter;
//        formatter = DateTimeFormatter.ofPattern(pattern);
//        System.out.println(pattern + " " + date.format(formatter));
//        return
//    }


    private static String partOfDay() {
        String partOfDay = "";
        LocalTime midnight = LocalTime.parse("00:00:00");
        LocalTime noon = LocalTime.parse("12:00:00");
        LocalTime evening = LocalTime.parse("17:00:00");
        time = LocalTime.now();
        if (time.isAfter(midnight) == time.isBefore(noon)) {
            partOfDay = "Great morning";
        } else if (time.isAfter(noon) == time.isBefore(evening)) {
            partOfDay = "Great afternoon";
        } else if (time.isAfter(noon) == time.isBefore(evening)) {
            partOfDay = "Great evening";
        }
        return partOfDay;
    }

    private static void exit() {
        //todo if user is logged in, add name after partOfDay
        if (currentUser != null) {
            System.out.println("\nThank you for using our PKBank App!");
            System.out.println("Have a " + partOfDay().toLowerCase() + ", " + currentUser.getFirstName() + "!");
        } else {
            System.out.println("\nThank you for using our PKBack App!");
            System.out.println("Have a " + partOfDay().toLowerCase() + "!");
        }
    }

    /**********************************

     Start of main method.

     **********************************/

    public static void main(String[] args) {
        start();

/*
        //register user 1
        User u1 = new User();
        System.out.println("User 1!");
        System.out.println("Enter name: ");
        u1.setEmail(input.next());
        System.out.println("Enter password: ");
        u1.setPassword(input.next());

        //Add new user into the masterUser's listOfUsers
        masterUser.addUserToList(u1);

        //Serialize masterUser
        Serialization.serializeUsers(masterUser);

        //Deserialize the newly serialized User.ser
        masterUser = Serialization.deserializeUsers();

        //Print data from deserialized Users.ser from resources.
        for (User user : masterUser.getListOfUsers()) {
            System.out.println("Email: " + user.getEmail());
            System.out.println("Password: " + user.getPassword());

        }

        System.out.println("\n======================================\n");
        //register user 2
        User u2 = new User();
        System.out.println("User 2!");
        System.out.println("Enter name: ");
        u2.setEmail(input.next());
        System.out.println("Enter password: ");
        u2.setPassword(input.next());
        masterUser.addUserToList(u2);
        Serialization.serializeUsers(masterUser);
        masterUser = Serialization.deserializeUsers();

        for (User user : masterUser.getListOfUsers()) {
            System.out.println("Name: " + user.getEmail());
            System.out.println("Password: " + user.getPassword());
        }
*/

        input.close();
    }


}
