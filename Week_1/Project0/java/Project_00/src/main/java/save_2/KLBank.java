//package save_2;
//
//import java.io.File;
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.time.format.DateTimeFormatter;
//import java.util.Scanner;
//
//public class KLBank {
//    // Private Data field
//    private static Scanner input = new Scanner(System.in);
//    private static User currentUser;
//    private static LocalTime time;
//    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
//    private static boolean menuReturn = false;
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
////        currentUser = new User();
////        for (User u : currentUser.getListOfUsers()) {
////            System.out.println("User found: " + u.getFirstName());
////            System.out.println("Email: " + u.getEmail());
////            System.out.println("Password: " + u.getPassword());
////        }
//        checkUsersFile();
//        intro();
//        menu();
//    }
//
//    private static void menu() {
//        do {
//            //System.out.println("Current user: " + currentUser.getFirstName());
//            System.out.print("\n-= Menu =-\n" +
//                    "\n1  - Login" +
//                    "\n2  - Register" +
//                    "\n0  - Exit" +
//                    "\n\nEnter number: ");
//            retrieveMenuSelection(input.nextInt());
//        } while (currentUser == null);
//    }
////
////    private static void retrieveMenuSelection(int selected) {
////        switch (selected) {
////            case 1:
////                if (login()) {
////                    String role = currentUser.getRole();
////                   if ( role.equals("Administrator")) {
////                        adminMenu();
////                    } else {
////                        customerMenu();
////                    }
////                }
////                break;
////            case 2:
////                userRegistration();
////                break;
////            case 0:
////                exit();
////                break;
////            default:
////                System.out.println("\nInvalid selection!");
////        }
////    }
//
//    private static void customerMenu() {
//        do {
//            System.out.print("\n-= Menu =-\n" +
//                    "\n" + partOfDay() + ", " + currentUser.getFirstName() + "!\n" +
//                    "\n1  - Check Balance" +
//                    "\n2  - Deposit" +
//                    "\n3  - Withdraw" +
//                    "\n0  - Logout" +
//                    "\n-1 - Exit" +
//                    "\n\nEnter number: ");
//            retrieveCustomerMenuSelection(input.nextInt());
//        } while (currentUser != null);
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
//                break;
//            case 0:
//                logout();
//                break;
//            case -1:
//                exit();
//                break;
//            default:
//                System.out.println("\nInvalid selection!");
//        }
//    }
//
//    private static void customerBalance() {
//
//        System.out.println("\n-= Balance =-");
//        System.out.println("\nCurrent balance: " + currentUser.getBalance());
//        do {
//            balanceMenu();
//        } while (!menuReturn);
//    }
//
//    private static void balanceMenu() {
//        System.out.println("\nDo you want to:" +
//                "\n1 - Deposit" +
//                "\n2 - Withdraw" +
//                "\n0 - Return");
//        retrieveBalanceMenuSelection(input.nextInt());
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
//                menuReturn = true;
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
//        input.nextLine(); // Consume newline leftover
//        currentUser.setBalance(currentUser.getBalance() + depositAmount);
//        System.out.printf("\nSuccessfully deposited $%.2f!\nTotal Balance: %.2f\n", depositAmount, currentUser.getBalance());
//        Serialization.serializeUsers(currentUser);
//    }
//
//    private static void customerWithdraw() {
//        double withdrawAmount;
//        System.out.println("\n-= Withdraw =-");
//        System.out.printf("\nCurrent balance: %.2f", currentUser.getBalance());
//        System.out.print("\nEnter amount to withdraw: ");
//        withdrawAmount = input.nextDouble();
//        input.nextLine(); // Consume newline leftover
//        currentUser.setBalance(currentUser.getBalance() - withdrawAmount);
//        System.out.printf("\nSuccessfully withdrew $%.2f!\nTotal Balance: %.2f\n", withdrawAmount, currentUser.getBalance());
//        Serialization.serializeUsers(currentUser);
//    }
//
////    private static boolean login() {
////        boolean loginFlag = false;
////        String email;
////        String password;
////        System.out.print("\nE-mail: ");
////        email = input.next();
////        System.out.print("Password: ");
////        password = input.next();
////        //todo check for existing user
////        if (User.checkLogin(email, password)) {
////            //currentUser = Serialization.deserializeUsers().getUser(email);
////            currentUser = User.getUser(email);
////            System.out.println("\nCurrent user is " + currentUser.getEmail());
////            loginFlag = true;
////        } else
////            System.out.println("\nInvalid Email/Password!");
////        return loginFlag;
////    }
//
//    private static void userRegistration() {
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
////            System.out.printf("\n%-15s %-34s %s", "|Password:", u.getPassword(), "|"); // todo find a way to return as *********
////            System.out.printf("\n%-15s %-34.2f %s", "|Balance:", u.getBalance(), "|");
////            System.out.print("\n*==================================================*");
////            System.out.print("\nIs this correct?\n(Y/N): ");
////            // Lock new account
////            u.setLock(-1);
////            // Set Role
////            u.setRole("Customer");
////            // make input capital Y/N
////            reviewCheck = input.next().toUpperCase().charAt(0);
////        } while (reviewCheck == 'N');
////        // Add new user into the listOfUsers
////        //currentUser = Serialization.deserializeUsers();
////        //currentUser.getListOfUsers().add(u);
////        User.listOfUsers.add(u);
////        Serialization.serializeUsers(currentUser);
////        //currentUser = null;
////        System.out.println("\nSuccessfully registered!");
////    }
//
//    private static void checkUsersFile() {
//        File file = new File("Project_00/src/main/resources/Users.ser");
//
//        if (file.exists()) {
//            System.out.println(".ser file exist!");
//            //currentUser = Serialization.deserializeUsers();
//            //todo log Users.ser exist.
//        } else {
//            User u = new User();
//            u.setEmail("admin");
//            u.setPassword("admin");
//            u.setFirstName("Administrator");
//            u.setRole("Administrator");
//            u.getListOfUsers().add(u);
//            Serialization.serializeUsers(u);
//        }
//    }
//
//    private static void adminMenu() {
//        do {
//            System.out.print("\n-= Menu =-\n" +
//                    "\n1  - Check User List" +
//                    "\n2  - Lock/Unlock Account(s)" +
//                    "\n3  - Approve/Deny New Accounts" +
//                    "\n0  - Logout" +
//                    "\n-1 - Exit" +
//                    "\n\nEnter number: ");
//            retrieveAdminMenuSelection(input.nextInt());
//        } while (currentUser != null);
//    }
//
//    private static void retrieveAdminMenuSelection(int selected) {
//        switch (selected) {
//            case 1:
//                for (User u : currentUser.getListOfUsers()) {
//                    System.out.println("\nUser found: " + u.getFirstName());
//                    System.out.println("Email: " + u.getEmail());
//                    System.out.println("Password: " + u.getPassword());
//                }
//                break;
//            case 2:
//                break;
//            case 0:
//                logout();
//                break;
//            case -1:
//                exit();
//                break;
//            default:
//                System.out.println("\nInvalid selection!");
//        }
//    }
//
//    private static void logout() {
//        currentUser = null;
//        System.out.println("\nSuccessfully Logout!");
//
//    }
//
//    /**********************************
//
//     Start of miscellaneous methods.
//
//     **********************************/
//
//    private static void intro() {
//        // Add created by Kirk Legarda
//        System.out.println("*=========== KLBank Console Application ===========*\n" +
//                "|                    Welcome!                      |\n" +
//                "*==================================================*\n");
//
//    }
//
//    /**********************************
//
//     Start of main method.
//
//     **********************************/
//
//    public static void main(String[] args) {
//        start();
//    }
//}
