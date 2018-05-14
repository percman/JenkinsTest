package com.revature.util;

import com.revature.model.User;
import com.revature.service.BankService;
import com.revature.service.UserService;
import org.apache.log4j.Logger;

import java.time.LocalTime;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private static User currentUser = null;
    private static User tempUser = null;
    private static LocalTime time;
    private final static Logger logger = Logger.getLogger(JDBCBankLogger.class);

    public static void mainMenu(Scanner input) {
        intro();
        do {
            System.out.print("\n-= Menu =-\n" +
                    "\n1  - Login" +
                    "\n2  - Register" +
                    "\n0  - Exit" +
                    "\n\nEnter number: ");
            try {
                switch (input.nextInt()) {
                    case 1:
                        if (login(input)) {
                            if (currentUser.getUsername().equals("admin")) {
                                adminMenu(input);
                            } else if (currentUser.getUserLock() == -1) {
                                currentUser = null;
                                System.out.println("\n" +
                                        "*========== JDBCBank Console Application ==========*\n" +
                                        "|        Your account is awaiting approval!        |\n" +
                                        "*==================================================*");

                            } else if (currentUser.getUserLock() == 1) {
                                currentUser = null;
                                System.out.println("\n" +
                                        "*========== JDBCBank Console Application ==========*\n" +
                                        "|          Your account is denied/locked!          |\n" +
                                        "*==================================================*");
                            } else
                                userMenu(input);
                        }
                        break;
                    case 2:
                        userRegistration(input);
                        break;
                    case 0:
                        exit(input);
                        break;
                    default:
                        System.out.println("\nInvalid selection!");
                }
            } catch (InputMismatchException ime) {
                logger.warn("Input Mismatch Exception", ime);
                break;
            }
        } while (currentUser == null);
    }

    public static void userMenu(Scanner input) {
        do {
            System.out.print("\n-= Menu =-\n" +
                    "\n" + partOfDay() + ", " + currentUser.getFirstName() + "!\n" +
                    "\n1  - Check Gold Pouch" +
                    "\n2  - Check Bank" +
                    "\n3  - Bank Deposit" +
                    "\n4  - Bank Withdraw " +
                    "\n0  - Logout" +
                    "\n-1 - Exit" +
                    "\n\nEnter number: ");
            switch (input.nextInt()) {
                case 1:
                    userGoldBalance();
                    break;
                case 2:
                    bankGoldBalance();
                    break;
                case 3:
                    userDeposit(input);
                    break;
                case 4:
                    userWithdraw(input);
                    break;
                case 0:
                    logout();
                    break;
                case -1:
                    exit(input);
                    break;
                default:
                    System.out.println("\nInvalid selection!");
            }
        } while (currentUser != null);
    }

    public static void userGoldBalance() {
        System.out.println("\n-= Gold Pouch Balance =-");
        System.out.printf("\nCurrent balance: %.2f gold\n", currentUser.getGold());
    }

    public static void bankGoldBalance() {
        System.out.println("\n-= Gold Bank Balance =-");
        System.out.printf("\nCurrent balance: %.2f gold\n", BankService.getGold(currentUser));
    }

    public static void userDeposit(Scanner input) {
        double depositAmount;
        System.out.println("\n-= Gold Bank Deposit =-");
        System.out.printf("\nGold(Bank): %.2f gold", BankService.getGold(currentUser));
        System.out.printf("\nGold(Pouch): %.2f gold\n", currentUser.getGold());
        System.out.print("\nEnter amount to deposit: ");
        depositAmount = input.nextDouble();
        BankService.depositUserGold(currentUser, depositAmount);
        currentUser = UserService.getUser(currentUser.getUserId());
        System.out.printf("\nSuccessfully deposited %.2f gold!\n", depositAmount);

    }

    public static void userWithdraw(Scanner input) {
        double withdrawAmount;
        System.out.println("\n-= Gold Bank Withdraw =-");
        System.out.printf("\nGold(Bank): %.2f gold", BankService.getGold(currentUser));
        System.out.printf("\nGold(Pouch): %.2f gold\n", currentUser.getGold());
        System.out.print("\nEnter amount to withdraw: ");
        withdrawAmount = input.nextDouble();
        BankService.withdrawBankGold(currentUser, withdrawAmount);
        currentUser = UserService.getUser(currentUser.getUserId());
        System.out.printf("\nSuccessfully withdrew %.2f gold!\n", withdrawAmount);

    }

    public static void adminMenu(Scanner input) {
        do {
            System.out.print("\n-= Menu =-\n" +
                    "\n" + partOfDay() + ", " + currentUser.getFirstName() + "!\n" +
                    "\n1  - Check User List" +
                    "\n2  - Unlock Account" +
                    "\n3  - Lock Account" +
                    "\n4  - Approve New Account" +
                    "\n5  - Deny New Account" +
                    "\n0  - Logout" +
                    "\n-1 - Exit" +
                    "\n\nEnter number: ");
            switch (input.nextInt()) {
                case 1:
                    showAllUsers();
                    break;
                case 2:
                    unlockAccount(input);
                    break;
                case 3:
                    lockAccount(input);
                    break;
                case 4:
                    approveAccount(input);
                    break;
                case 5:
                    denyAccount(input);
                    break;
                case 0:
                    logout();
                    break;
                case -1:
                    exit(input);
                    break;
                default:
                    System.out.println("\nInvalid selection!");
            }
        } while (currentUser != null);
    }

    public static void showAllUsers() {
        List<User> users;
        users = UserService.getAllUsers();
        for (User u : users) {
            System.out.print("\n*==================================================*");
            System.out.printf("\n%-15s %-34d %s", "|User ID:", u.getUserId(), "|");
            System.out.printf("\n%-15s %-34s %s", "|Name:", u.getFirstName() + " " + u.getLastName(), "|");
            System.out.printf("\n%-15s %-34s %s", "|Username:", u.getUsername(), "|");
            System.out.printf("\n%-15s %-34s %s", "|Password:", u.getUserpass(), "|");
            System.out.printf("\n%-15s %-34d %s", "|Class:", u.getUserClassId(), "|");
            System.out.printf("\n%-15s %-34d %s", "|Location:", u.getCurrentLocationId(), "|");
            System.out.printf("\n%-15s %-34d %s", "|HP:", u.getHP(), "|");
            System.out.printf("\n%-15s %-34d %s", "|LVL:", u.getLVL(), "|");
            System.out.printf("\n%-15s %-34d %s", "|EXP:", u.getEXP(), "|");
            System.out.printf("\n%-15s %-34d %s", "|ATK:", u.getATK(), "|");
            System.out.printf("\n%-15s %-34d %s", "|DEF:", u.getDEF(), "|");
            System.out.printf("\n%-15s %-34.2f %s", "|Gold:", u.getGold(), "|");
            System.out.printf("\n%-15s %-34d %s", "|Lock:", u.getUserLock(), "|");
            System.out.print("\n*==================================================*\n");
        }
    }

    public static void showAllNewUsers() {
        List<User> users;
        users = UserService.getAllUsers();
        for (User u : users) {
            if (u.getUserLock() == -1) {
                System.out.print("\n*==================================================*");
                System.out.printf("\n%-15s %-34d %s", "|User ID:", u.getUserId(), "|");
                System.out.printf("\n%-15s %-34s %s", "|Name:", u.getFirstName() + " " + u.getLastName(), "|");
                System.out.printf("\n%-15s %-34s %s", "|Username:", u.getUsername(), "|");
                System.out.printf("\n%-15s %-34s %s", "|Password:", u.getUserpass(), "|");
                System.out.printf("\n%-15s %-34d %s", "|Class:", u.getUserClassId(), "|");
                System.out.printf("\n%-15s %-34d %s", "|Location:", u.getCurrentLocationId(), "|");
                System.out.printf("\n%-15s %-34d %s", "|HP:", u.getHP(), "|");
                System.out.printf("\n%-15s %-34d %s", "|LVL:", u.getLVL(), "|");
                System.out.printf("\n%-15s %-34d %s", "|EXP:", u.getEXP(), "|");
                System.out.printf("\n%-15s %-34d %s", "|ATK:", u.getATK(), "|");
                System.out.printf("\n%-15s %-34d %s", "|DEF:", u.getDEF(), "|");
                System.out.printf("\n%-15s %-34.2f %s", "|Gold:", u.getGold(), "|");
                System.out.printf("\n%-15s %-34d %s", "|Lock:", u.getUserLock(), "|");
                System.out.print("\n*==================================================*\n");
            }
        }
    }

    public static void unlockAccount(Scanner input) {
        System.out.print("\n-= Unlock Account =-\n");
        showAllUsers();
        System.out.print("\nWhich account do you wish to unlock?\n" +
                "\nEnter User ID: ");
        tempUser = UserService.getUser(input.nextInt());
        tempUser.setUserLock(0);
        UserService.updateUser(tempUser);
        System.out.println("\n" + tempUser.getUsername() + " is now unlocked!");
        logger.info("User ID: " + tempUser.getUserId() + " is unlocked!");
    }

    public static void lockAccount(Scanner input) {
        System.out.print("\n-= Lock Account =-\n");
        showAllUsers();
        System.out.print("\nWhich account do you wish to lock?\n" +
                "\nEnter User ID: ");
        tempUser = UserService.getUser(input.nextInt());
        tempUser.setUserLock(1);
        UserService.updateUser(tempUser);
        System.out.println("\n" + tempUser.getUsername() + " is now locked!");
        logger.info("User ID: " + tempUser.getUserId() + " is locked!");

    }

    public static void approveAccount(Scanner input) {
        System.out.print("\n-= Approve Account =-\n");
        showAllNewUsers();
        System.out.print("\nWhich new account do you wish to approve?\n" +
                "\nEnter User ID: ");
        tempUser = UserService.getUser(input.nextInt());
        tempUser.setUserLock(0);
        UserService.updateUser(tempUser);
        System.out.println("\n" + tempUser.getUsername() + " is now approved!");
        logger.info("User ID: " + tempUser.getUserId() + " is now approved!");
    }

    public static void denyAccount(Scanner input) {
        System.out.print("\n-= Deny Account =-");
        showAllNewUsers();
        System.out.print("\nWhich new account do you wish to deny?\n" +
                "\nEnter User ID: ");
        tempUser = UserService.getUser(input.nextInt());
        tempUser.setUserLock(1);
        UserService.updateUser(tempUser);
        System.out.println("\n" + tempUser.getUsername() + " is now denied!");
        logger.info("User ID: " + tempUser.getUserId() + " is now denied!");
    }

    public static void userRegistration(Scanner input) {
        currentUser = new User();
        char reviewCheck;
        do {
            System.out.println("\n-= User Registration =-");
            System.out.println("\nTo register, enter the following: ");
            System.out.print("\nFirst Name: ");
            currentUser.setFirstName(input.next());
            System.out.print("\nLast Name: ");
            currentUser.setLastName(input.next());
            System.out.print("\nUsername: ");
            currentUser.setUsername(input.next());
            System.out.print("\nPassword: ");
            currentUser.setUserpass(input.next());
            System.out.println("\n-= Registration Review =-");
            System.out.print("\n*==================================================*");
            System.out.printf("\n%-15s %-34s %s", "|Name:", currentUser.getFirstName() + " " + currentUser.getLastName(), "|");
            System.out.printf("\n%-15s %-34s %s", "|Username:", currentUser.getUsername(), "|");
            System.out.printf("\n%-15s %-34s %s", "|Password:", currentUser.getUserpass(), "|");
            System.out.print("\n*==================================================*\n");
            System.out.print("\nIs this correct?\n(Y/N): ");
            reviewCheck = input.next().toUpperCase().charAt(0);
        } while (reviewCheck == 'N');
        if (UserService.insertUser(currentUser)) {
            System.out.println("\nSuccessfully registered!");
            System.out.println("\nYou will gain access to your account when " +
                    "\nthe Administrator approves your account.");
        } else {
            System.out.println("\nRegistration failed!" +
                    "\nUsername is taken!");
        }
        logger.info("New currentUser added into database!");
        currentUser = null;
    }

    public static boolean login(Scanner input) {
        String username;
        String password;
        System.out.print("\nUsername: ");
        username = input.next();
        System.out.print("Password: ");
        password = input.next();
        if (UserService.login(username, password)) {
            currentUser = UserService.getUser(username);
            logger.info("User is logged in!");
            return true;
        } else {
            System.out.println("\nInvalid Email/Password!");
            logger.info("Failed login!");
            return false;
        }
    }

    /************************************************************************************

     Start of miscellaneous methods.

     ***********************************************************************************/

    public static void intro() {
        System.out.print("" +
                "*========== JDBCBank Console Application ==========*\n" +
                "|                    Welcome!                      |\n" +
                "*==================================================*\n");

    }

    public static String partOfDay() {
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

    public static void logout() {
        logger.info("User is logged out!");
        currentUser = null;
        System.out.println("\nLogout successful!");
    }

    public static void exit(Scanner input) {
        if (currentUser != null) {
            System.out.println("\nThank you for using our JDBCBank App!");
            System.out.println("Have a " + partOfDay().toLowerCase() + ", " + currentUser.getFirstName() + "!");
            input.close();
            logger.info("Scanner resources closed.");
            System.exit(0);
        } else {
            System.out.println("\nThank you for using our JDBCBank App!");
            System.out.println("Have a " + partOfDay().toLowerCase() + "!");
            input.close();
            logger.info("Scanner resources closed.");
            System.exit(0);
        }
    }
}
