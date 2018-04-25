//package save_1;
//
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
////todo create try catch for inputs
//
//public class PKBank {
//    // Private Data field
//    private static Scanner input = new Scanner(System.in);
//    //private static User masterUser = new User();
//    private static List<User> listOfUsers = new ArrayList<>();
//    private static User currentUser;
//    private static int selected;
//    private static int menuLevel = 0;
//    private static LocalTime time;
//    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
//
//
//    // Default Constructor
//
//    /**********************************
//
//     Start of required methods.
//
//     **********************************/
//
//    private static void start() {
//        //currentUser = new User();
//        listOfUsers = Serialization.deserializeList();
//        for (User user : listOfUsers) {
//            System.out.println("Email: " + user.getEmail());
//            System.out.println("Password: " + user.getPassword());
//        }
//        intro();
//        menu();
//    }
//
//    private static void intro() {
//        // Add created by Kirk Legarda
//        System.out.println("*=========== PKBank Console Application ===========*\n" +
//                "|                    Welcome!                      |\n" +
//                "*==================================================*\n");
//
//    }
//
//    private static void menu() {
//        do {
//            System.out.print("\n-= Menu =-\n" +
//                    "\n1  - Login" +
//                    "\n2  - Register" +
//                    "\n0  - Exit" +
//                    "\n\nEnter number: ");
//            selected = input.nextInt();
//            retrieveMenuSelection(selected);
//        } while (menuLevel == 0);
//    }
//
//    private static void retrieveMenuSelection(int selected) {
//        switch (selected) {
//            case 1:
//                if (login()) {
//                    customerMenu();
//                }
//                break;
//            case 2:
//                userRegistration();
//                break;
//            case 0:
//                exit();
//                break;
//            default:
//                System.out.println("\nInvalid selection!");
//
//        }
//    }
//
//    private static boolean login() {
//        boolean loginFlag = false;
//        String email;
//        String password;
//        System.out.print("\nE-mail: ");
//        email = input.next();
//        System.out.print("Password: ");
//        password = input.next();
//        //todo check for existing user
//        if (currentUser.checkLogin(email, password)) {
//            currentUser = currentUser.getUser(email);
//            System.out.println("\nCurrent user is " + currentUser.getEmail());
//            loginFlag = true;
//        } else
//            System.out.println("\nInvalid Email/Password!");
//        return loginFlag;
//    }
//
//    //todo merge menus
//    private static void customerMenu() {
//        menuLevel++;
//        do {
//            System.out.print("\n-= Menu =-\n" +
//                    "\n" + partOfDay() + ", " + currentUser.getFirstName() + "!\n" +
//                    "\n1  - Check Balance" +
//                    "\n2  - Deposit" +
//                    "\n3  - Withdraw" +
//                    "\n0  - Logout" +
//                    "\n-1 - Exit" +
//                    "\n\nEnter number: ");
//            selected = input.nextInt();
//            retrieveCustomerMenuSelection(selected);
//        } while (menuLevel >= 1);
//    }
//
//    private static void retrieveCustomerMenuSelection(int selected) {
//        switch (selected) {
//            case 1:
//                customerBalance();
//                break;
//            case 2:
//                customerDeposit();
//                break;
//            case 3:
//                customerWithdraw();
//            case 0:
//                logout();
//                break;
//            case -1:
//                exit();
//            default:
//                System.out.println("\nInvalid selection!");
//        }
//    }
//
//    private static void customerBalance() {
//        menuLevel++;
//        do {
//            System.out.println("\n-= Balance =-");
//            System.out.println("\nCurrent balance: " + currentUser.getBalance());
//            balanceMenu();
//            retrieveBalanceMenuSelection(selected);
//        } while (menuLevel >= 2);
//    }
//
//    private static void balanceMenu() {
//
//        System.out.println("\nDo you want to:" +
//                "\n1 - Deposit" +
//                "\n2 - Withdraw" +
//                "\n0 - Return");
//        selected = input.nextInt();
//        retrieveBalanceMenuSelection(selected);
//
//    }
//
//    private static void retrieveBalanceMenuSelection(int selected) {
//        switch (selected) {
//            case 1:
//                customerDeposit();
//                break;
//            case 2:
//                customerWithdraw();
//                break;
//            case 0:
//                menuLevel--;
//                break;
//            default:
//                System.out.println("Invalid selection!");
//        }
//    }
//
//
//    private static void customerDeposit() {
//        double depositAmount;
//        System.out.println("\n-= Deposit =-");
//        System.out.printf("\nCurrent balance: %.2f", currentUser.getBalance());
//        System.out.print("\nEnter amount to deposit: ");
//        depositAmount = input.nextDouble();
//        currentUser.setBalance(currentUser.getBalance() + depositAmount);
//        System.out.printf("\nSuccessfully deposited $%.2f!\nTotal Balance: %f", depositAmount, currentUser.getBalance());
//
//        Serialization.serializeList(listOfUsers);
//    }
//
//    private static void customerWithdraw() {
//        double withdrawAmount;
//        System.out.println("\n-= Withdraw =-");
//        System.out.printf("\nCurrent balance: %.2f", currentUser.getBalance());
//        System.out.print("\nEnter amount to withdraw: ");
//        withdrawAmount = input.nextDouble();
//        currentUser.setBalance(currentUser.getBalance() - withdrawAmount);
//        System.out.printf("\nSuccessfully withdrew $%.2f!\nTotal Balance: %f", withdrawAmount, currentUser.getBalance());
//        Serialization.serializeList(listOfUsers);
//    }
//
//    public static void adminMenu() {
//        do {
//            System.out.print("\n-= Menu =-\n" + partOfDay() + currentUser.getFirstName() + "!\n" +
//                    "\n1  - Show list of customers" +
//                    "\n2  - Search for customer's information" +
//                    "\n3  - Lock/Unlock Customer Accounts" +
//                    "\n0  - Logout" +
//                    "\n-1 - Exit" +
//                    "\n\nEnter number: ");
//            selected = input.nextInt();
//            retrieveMenuSelection(selected);
//        } while (selected != 0);
//    }
//
////    private static void userRegistration() {
////        User u = new User();
////        char reviewCheck;
////        do {
////            System.out.println("\n-= User Registration =-");
////            System.out.println("\nTo register, enter the following: ");
////            System.out.print("\nFirst Name: ");
////            u.setFirstName(input.next());
////            System.out.print("\nLast Name: ");
////            u.setLastName(input.next());
////            System.out.print("\nBirthday (mm/dd/yyyy): ");
////            u.setBirthday(LocalDate.parse(input.next(), formatter));
////            System.out.print("\nStreet Address: ");
////            u.setStreet(input.next());
////            System.out.print("\nState: ");
////            input.nextLine();  // Consume newline left-over
////            u.setState(input.next());
////            System.out.print("\nCountry: ");
////            u.setCountry(input.next());
////            System.out.print("\nSSN (xxx-xx-xxxx): "); //todo check format
////            u.setSocialSecurityNumber(input.next());
////            System.out.print("\nStarting Balance: ");
////            u.setBalance(input.nextDouble());
////            System.out.print("\nE-mail: ");
////            u.setEmail(input.next());
////            System.out.print("\nPassword: ");
////            u.setPassword(input.next()); //todo check if this is saved encrypted
////            System.out.println("\n-= Registration Review =-");
////            System.out.print("\n*==================================================*");
////            System.out.printf("\n%-15s %-34s %s", "|Name:", u.getFirstName() + " " + u.getLastName(), "|");
////            System.out.printf("\n%-15s %-34s %s", "|Birthday:", formatter.format(u.getBirthday()), "|"); // find a way to turn localdate to mm/dd/yyyy without formatter variable
////            System.out.printf("\n%-15s %-34s %s", "|E-mail:", u.getEmail(), "|");
////            System.out.printf("\n%-15s %-34s %s", "|Birthday:", u.getPassword(), "|"); // todo find a way to return as *********
////            System.out.print("\n*==================================================*");
////            System.out.print("\nIs this correct?\n(Y/N): ");
////            reviewCheck = input.next().toUpperCase().charAt(0);
////        } while (reviewCheck == 'N');
////        //Add new user into the masterUser's listOfUsers
////        listOfUsers.add(u);
////        Serialization.serializeList(listOfUsers);
////        listOfUsers = Serialization.deserializeList();
////        for (User user : listOfUsers) {
////            System.out.println("Email: " + user.getEmail());
////            System.out.println("Password: " + user.getPassword());
////        }
////        System.out.println("Successfully registered!");
//    }
//
//
//    //todo logout that makes currentUser null;
//
//    /**********************************
//
//     Start of Miscellaneous methods.
//
//     **********************************/
////    private static String getDateTime(LocalDateTime date, String pattern) {
////        DateTimeFormatter formatter;
////        formatter = DateTimeFormatter.ofPattern(pattern);
////        System.out.println(pattern + " " + date.format(formatter));
////        return
////    }
////    private static String partOfDay() {
////        String partOfDay = "";
////        LocalTime midnight = LocalTime.parse("00:00:00");
////        LocalTime noon = LocalTime.parse("12:00:00");
////        LocalTime evening = LocalTime.parse("17:00:00");
////        time = LocalTime.now();
////        if (time.isAfter(midnight) == time.isBefore(noon)) {
////            partOfDay = "Great morning";
////        } else if (time.isAfter(noon) == time.isBefore(evening)) {
////            partOfDay = "Great afternoon";
////        } else if (time.isAfter(noon) == time.isBefore(evening)) {
////            partOfDay = "Great evening";
////        }
////        return partOfDay;
////    }
//
//    private static void logout() {
//        menuLevel = 0;
//        System.out.println("\nSuccessfully Logout!");
//    }
//
////    private static void exit() {
////        //todo if user is logged in, add name after partOfDay
////        if (currentUser.getFirstName() != null && currentUser != null) {
////            System.out.println("\nThank you for using our PKBank App!");
////            System.out.println("Have a " + partOfDay().toLowerCase() + ", " + currentUser.getFirstName() + "!");
////            System.exit(0);
////        } else {
////            System.out.println("\nThank you for using our PKBack App!");
////            System.out.println("Have a " + partOfDay().toLowerCase() + "!");
////            System.exit(0);
////        }
////    }
//
//    /**********************************
//
//     Start of main method.
//
//     **********************************/
//
//    public static void main(String[] args) {
//        start();
//
///*
//        //register user 1
//        User u1 = new User();
//        System.out.println("User 1!");
//        System.out.println("Enter name: ");
//        u1.setEmail(input.next());
//        System.out.println("Enter password: ");
//        u1.setPassword(input.next());
//
//        //Add new user into the masterUser's listOfUsers
//        masterUser.addUserToList(u1);
//
//        //Serialize masterUser
//        Serialization.serializeUsers(masterUser);
//
//        //Deserialize the newly serialized User.ser
//        masterUser = Serialization.deserializeUsers();
//
//        //Print data from deserialized Users.ser from resources.
//        for (User user : masterUser.getListOfUsers()) {
//            System.out.println("Email: " + user.getEmail());
//            System.out.println("Password: " + user.getPassword());
//
//        }
//
//        System.out.println("\n======================================\n");
//        //register user 2
//        User u2 = new User();
//        System.out.println("User 2!");
//        System.out.println("Enter name: ");
//        u2.setEmail(input.next());
//        System.out.println("Enter password: ");
//        u2.setPassword(input.next());
//        masterUser.addUserToList(u2);
//        Serialization.serializeUsers(masterUser);
//        masterUser = Serialization.deserializeUsers();
//
//        for (User user : masterUser.getListOfUsers()) {
//            System.out.println("Name: " + user.getEmail());
//            System.out.println("Password: " + user.getPassword());
//        }
//*/
//
//        input.close();
//    }
//
//
//}
