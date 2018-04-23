package BankApp;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class KLBank {
    // Private Data field
    private static Scanner input = new Scanner(System.in);
    private static User currentUser = null;
    private static User tempUser;
    private static LocalTime time;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private static boolean menuReturn = false;
    private final static Logger logger = Logger.getLogger(KLBankLogger.class);

    // Default Constructor

    /**********************************

     Start of required methods.

     **********************************/
    private static void start() {
        checkAdmin();
        intro();
        menu();
        input.close();
        logger.info("Scanner resources closed.");
    }

    private static void menu() {
        do {
            //System.out.println("Current user: " + currentUser.getFirstName());
            System.out.print("\n-= Menu =-\n" +
                    "\n1  - Login" +
                    "\n2  - Register" +
                    "\n0  - Exit" +
                    "\n\nEnter number: ");
            try {
                retrieveMenuSelection(input.nextInt());
            } catch (InputMismatchException ime) {
                logger.warn("Input Mismatch Exception", ime);
                break;
            }
        } while (currentUser == null);
    }

    private static void retrieveMenuSelection(int selected) {
        switch (selected) {
            case 1:

                if (login()) {
                    if (currentUser.getRole().equals("Administrator")) {
                        adminMenu();
                    } else {
                        if (currentUser.getLock() == -1) {
                            currentUser = null;
                            System.out.println("\n" +
                                    "*=========== KLBank Console Application ===========*\n" +
                                    "|        Your account is awaiting approval!        |\n" +
                                    "*==================================================*");

                        } else if (currentUser.getLock() == 1) {
                            currentUser = null;
                            System.out.println("\n" +
                                    "*=========== KLBank Console Application ===========*\n" +
                                    "|          Your account is denied/locked!          |\n" +
                                    "*==================================================*");
                        } else
                            customerMenu();
                    }
                }
                break;
            case 2:
                userRegistration();
                break;
            case 0:
                exit();
                break;
            default:
                System.out.println("\nInvalid selection!");
        }
    }

    private static void customerMenu() {
        do {
            System.out.print("\n-= Menu =-\n" +
                    "\n" + partOfDay() + ", " + currentUser.getFirstName() + "!\n" +
                    "\n1  - Check Balance" +
                    "\n2  - Deposit" +
                    "\n3  - Withdraw" +
                    "\n0  - Logout" +
                    "\n-1 - Exit" +
                    "\n\nEnter number: ");
            retrieveCustomerMenuSelection(input.nextInt());
        } while (currentUser != null);
    }

    private static void retrieveCustomerMenuSelection(int selected) {
        switch (selected) {
            case 1:
                customerBalance();
                break;
            case 2:
                customerDeposit();
                break;
            case 3:
                customerWithdraw();
                break;
            case 0:
                logout();
                break;
            case -1:
                exit();
                break;
            default:
                System.out.println("\nInvalid selection!");
        }
    }

    private static void customerBalance() {
        System.out.println("\n-= Balance =-");
        System.out.printf("\nCurrent balance: %.2f" + currentUser.getBalance() + "\n");
        do {
            balanceMenu();
        } while (!menuReturn);
    }

    private static void balanceMenu() {
        System.out.println("\nDo you want to:" +
                "\n1 - Deposit" +
                "\n2 - Withdraw" +
                "\n0 - Return\n" +
                "\nEnter number: ");
        retrieveBalanceMenuSelection(input.nextInt());
    }

    private static void retrieveBalanceMenuSelection(int selected) {
        switch (selected) {
            case 1:
                customerDeposit();
                break;
            case 2:
                customerWithdraw();
                break;
            case 0:
                menuReturn = true;
                break;
            default:
                System.out.println("Invalid selection!");
        }
    }


    private static void customerDeposit() {
        double depositAmount;
        System.out.println("\n-= Deposit =-");
        System.out.printf("\nCurrent balance: %.2f", currentUser.getBalance());
        System.out.print("\nEnter amount to deposit: ");
        depositAmount = input.nextDouble();
        input.nextLine(); // Consume newline leftover
        currentUser.setBalance(currentUser.getBalance() + depositAmount);
        System.out.printf("\nSuccessfully deposited $%.2f!\nTotal Balance: %.2f\n", depositAmount, currentUser.getBalance());
        Serialization.serializeUser(currentUser);
    }

    private static void customerWithdraw() {
        double withdrawAmount;
        System.out.println("\n-= Withdraw =-");
        System.out.printf("\nCurrent balance: %.2f", currentUser.getBalance());
        System.out.print("\nEnter amount to withdraw: ");
        withdrawAmount = input.nextDouble();
        input.nextLine(); // Consume newline leftover
        currentUser.setBalance(currentUser.getBalance() - withdrawAmount);
        System.out.printf("\nSuccessfully withdrew $%.2f!\nTotal Balance: %.2f\n", withdrawAmount, currentUser.getBalance());
        Serialization.serializeUser(currentUser);
    }


    private static void adminMenu() {
        do {
            System.out.print("\n-= Menu =-\n" +
                    "\n" + partOfDay() + ", " + currentUser.getFirstName() + "!\n" +
                    "\n1  - Check User List" +
                    "\n2  - Lock Account" +
                    "\n3  - Unlock Account" +
                    "\n4  - Approve New Account" +
                    "\n5  - Deny New Account" +
                    "\n0  - Logout" +
                    "\n-1 - Exit" +
                    "\n\nEnter number: ");
            try {
                retrieveAdminMenuSelection(input.nextInt());
            } catch (InputMismatchException ime) {
                logger.warn("Input Mismatch Exception", ime);
            }
        } while (currentUser != null);
    }

    private static void retrieveAdminMenuSelection(int selected) {
        switch (selected) {
            case 1:
                showAllUsers();
                break;
            case 2:
                lockAccount();
                break;
            case 3:
                unlockAccount();
                break;
            case 4:
                approveAccount();
                break;
            case 5:
                denyAccount();
                break;
            case 0:
                logout();
                break;
            case -1:
                exit();
                break;
            default:
                System.out.println("\nInvalid selection!");
        }
    }

    private static void showAllUsers() {

        File f = new File("Project_00/src/main/resources/Users/");

        File[] files = f.listFiles();
        List<String> stringFiles = new ArrayList<>();
        String str;
        if (files != null) {
            for (File file : files) {
                str = file.getName();
                stringFiles.add(str.substring(0, str.indexOf(".")));
            }
        }
        for (String s : stringFiles) {
            tempUser = Serialization.deserializeUser(s);
            System.out.print("\n*==================================================*");
            System.out.printf("\n%-15s %-34s %s", "|Name:", tempUser.getFirstName() + " " + tempUser.getLastName(), "|");
            System.out.printf("\n%-15s %-34s %s", "|Birthday:", formatter.format(tempUser.getBirthday()), "|");
            System.out.printf("\n%-15s %-34s %s", "|E-mail:", tempUser.getEmail(), "|");
            System.out.printf("\n%-15s %-34s %s", "|Username:", tempUser.getUsername(), "|");
            System.out.printf("\n%-15s %-34s %s", "|Password:", tempUser.getPassword(), "|");
            System.out.printf("\n%-15s %-34.2f %s", "|Balance:", tempUser.getBalance(), "|");
            System.out.printf("\n%-15s %-34d %s", "|Lock:", tempUser.getLock(), "|");
            System.out.print("\n*==================================================*\n");
        }
    }

    private static void showAllNewUsers() {

        File f = new File("Project_00/src/main/resources/Users/");

        File[] files = f.listFiles();
        List<String> stringFiles = new ArrayList<>();
        String str;
        if (files != null) {
            for (File file : files) {
                str = file.getName();
                stringFiles.add(str.substring(0, str.indexOf(".")));
            }
        }
        for (String s : stringFiles) {
            tempUser = Serialization.deserializeUser(s);
            if (tempUser.getLock() == -1) {
                System.out.print("\n*==================================================*");
                System.out.printf("\n%-15s %-34s %s", "|Name:", tempUser.getFirstName() + " " + tempUser.getLastName(), "|");
                System.out.printf("\n%-15s %-34s %s", "|Birthday:", formatter.format(tempUser.getBirthday()), "|");
                System.out.printf("\n%-15s %-34s %s", "|E-mail:", tempUser.getEmail(), "|");
                System.out.printf("\n%-15s %-34s %s", "|Username:", tempUser.getUsername(), "|");
                System.out.printf("\n%-15s %-34s %s", "|Password:", tempUser.getPassword(), "|");
                System.out.printf("\n%-15s %-34.2f %s", "|Balance:", tempUser.getBalance(), "|");
                System.out.printf("\n%-15s %-34d %s", "|Lock:", tempUser.getLock(), "|");
                System.out.print("\n*==================================================*\n");
            }
        }
    }

    private static void lockAccount() {
        System.out.print("\n-= Lock Account =-\n");
        showAllUsers();
        System.out.print("\nEnter username: ");
        String username = input.next();
        tempUser = Serialization.deserializeUser(username);
        tempUser.setLock(1);
        System.out.println("\n" + tempUser.getUsername() + " is now locked!");
        logger.info(username + " is locked!");
        Serialization.serializeUser(tempUser);
        logger.info("Serialized current user.");

    }

    private static void unlockAccount() {
        System.out.print("\n-= Unlock Account =-\n");
        showAllUsers();
        System.out.print("\nEnter username: ");
        String username = input.next();
        tempUser = Serialization.deserializeUser(username);
        tempUser.setLock(0);
        System.out.println("\n" + tempUser.getUsername() + " is now unlocked!");
        logger.info(username + " is unlocked!");
        Serialization.serializeUser(tempUser);
        logger.info("Serialized current user.");
    }

    private static void approveAccount() {
        System.out.print("\n-= Approve Account =-\n");
        showAllNewUsers();
        System.out.print("\nWhich account do you want approve?" +
                "\nEnter username: ");
        String username = input.next();
        tempUser = Serialization.deserializeUser(username);
        tempUser.setLock(0);
        System.out.println("\n" + tempUser.getUsername() + " is now approved!");
        logger.info(username + " is approved!");
        Serialization.serializeUser(tempUser);
        logger.info("Serialized current user.");
    }

    private static void denyAccount() {
        System.out.print("\n-= Deny Account =-");
        showAllNewUsers();
        System.out.print("\nWhich account do you want deny?" +
                "\nEnter username: ");
        String username = input.next();
        tempUser = Serialization.deserializeUser(username);
        tempUser.setLock(1);
        System.out.println("\n" + tempUser.getUsername() + " is denied!");
        logger.info(username + " is denied!");
        Serialization.serializeUser(tempUser);
        logger.info("Serialized current user.");
    }

    private static boolean login() {
        String username;
        String password;
        System.out.print("\nUsername: ");
        username = input.next();
        System.out.print("Password: ");
        password = input.next();
        if (User.checkLogin(username, password)) {
            currentUser = Serialization.deserializeUser(username);
            logger.info("User is logged in!");
            //System.out.println("Current user is " + currentUser.getUsername());
            return true;
        }
        //todo check for existing user
        else {
            System.out.println("\nInvalid Email/Password!");
            logger.info("Failed login!");
            return false;
        }
    }

    private static void logout() {
        logger.info("User is logged out!");
        currentUser = null;
        System.out.println("\nLogout successful!");
    }

    private static void userRegistration() {
        User u = new User();
        char reviewCheck;
        do {
            System.out.println("\n-= User Registration =-");
            System.out.println("\nTo register, enter the following: ");
            System.out.print("\nFirst Name: ");
            u.setFirstName(input.next());
            System.out.print("\nLast Name: ");
            u.setLastName(input.next());
            System.out.print("\nBirthday (mm/dd/yyyy): ");
            u.setBirthday(LocalDate.parse(input.next(), formatter));
            System.out.print("\nStarting Balance: ");
            u.setBalance(input.nextDouble());
            System.out.print("\nE-mail: ");
            u.setEmail(input.next());
            System.out.print("\nUsername: ");
            u.setUsername(input.next());
            System.out.print("\nPassword: ");
            u.setPassword(input.next()); //todo check if this is saved encrypted
            System.out.println("\n-= Registration Review =-");
            System.out.print("\n*==================================================*");
            System.out.printf("\n%-15s %-34s %s", "|Name:", u.getFirstName() + " " + u.getLastName(), "|");
            System.out.printf("\n%-15s %-34s %s", "|Birthday:", formatter.format(u.getBirthday()), "|"); // find a way to turn localdate to mm/dd/yyyy without formatter variable
            System.out.printf("\n%-15s %-34s %s", "|E-mail:", u.getEmail(), "|");
            System.out.printf("\n%-15s %-34s %s", "|Username:", u.getUsername(), "|");
            System.out.printf("\n%-15s %-34s %s", "|Password:", u.getPassword(), "|"); // todo find a way to return as *********
            System.out.printf("\n%-15s %-34.2f %s", "|Balance:", u.getBalance(), "|");
            System.out.print("\n*==================================================*\n");
            System.out.print("\nIs this correct?\n(Y/N): ");
            // Lock new account
            u.setLock(-1);
            // Set Role
            u.setRole("Customer");
            // make input capital Y/N
            reviewCheck = input.next().toUpperCase().charAt(0);
        } while (reviewCheck == 'N');
        Serialization.serializeUser(u);
        System.out.println("\nSuccessfully registered!");
        System.out.println("\nYou will gain access to your account when " +
                "\nthe Administrator approves your account.");
        logger.info(".ser created.");
        currentUser = null;
    }

    public static void checkAdmin() {
        File file = new File("Project_00/src/main/resources/Users/admin.ser");
        if (file.exists()) {
            logger.info("Admin file exist.");
        } else {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword("admin");
            admin.setRole("Administrator");
            admin.setFirstName("Administrator");
            admin.setLastName("0");
            admin.setLock(0);
            admin.setEmail("admin@KLBank.com");
            admin.setBirthday(LocalDate.parse("11/11/1111", formatter));
            admin.setBalance(1000000);
            Serialization.serializeUser(admin);
            logger.info("admin.ser file created.");
        }
    }

    /**********************************

     Start of miscellaneous methods.

     **********************************/

    private static void intro() {
        // Add created by Kirk Legarda
        System.out.println("*=========== KLBank Console Application ===========*\n" +
                "|                    Welcome!                      |\n" +
                "*==================================================*\n");

    }

    private static String partOfDay() {
        String partOfDay = "";
        LocalTime midnight = LocalTime.parse("00:00:00");
        LocalTime noon = LocalTime.parse("12:00:00");
        LocalTime evening = LocalTime.parse("17:00:00");
        time = LocalTime.now();
        if (time.isAfter(midnight) && time.isBefore(noon)) {
            partOfDay = "Good morning";
        } else if (time.isAfter(noon) && time.isBefore(evening)) {
            partOfDay = "Good afternoon";
        } else {
            partOfDay = "Good evening";
        }
        return partOfDay;
    }


    private static void exit() {
        //todo if user is logged in, add name after partOfDay
        if (currentUser != null) {
            System.out.println("\nThank you for using our KLBank App!");
            System.out.println("Have a " + partOfDay().toLowerCase() + ", " + currentUser.getFirstName() + "!");
            input.close();
            logger.info("Scanner resources closed.");
            System.exit(0);
        } else {
            System.out.println("\nThank you for using our KLBack App!");
            System.out.println("Have a " + partOfDay().toLowerCase() + "!");
            input.close();
            logger.info("Scanner resources closed.");
            System.exit(0);
        }
    }

    /**********************************

     Start of main method.

     **********************************/

    public static void main(String[] args) {
        start();
    }
}
