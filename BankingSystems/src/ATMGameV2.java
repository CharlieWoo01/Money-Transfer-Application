import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class ATMGameV2 {

    /* ToDo:
    Change from array structure to hash map storage in memory.
    Add logic to add accounts.
     */


    // Array of all the bank account data.
    public static String[][] userAccounts = {
            {"Charlie", "2000"},
            {"Charlie2", "3000"},
            {"Charlie3", "4000"},
            {"Charlie4", "5000"},
    };

    // Refactored: Method to fetch all the bank account data from the hash map structure instead of array.
    public static void fetchBankAccounts() {
        Mapping map = new Mapping();
        Map mappingData = map.createBankAccountHashTable();
        Iterator<Map.Entry<String, Integer>> iterator = mappingData.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            System.out.println("Name: " + entry.getKey() + ", Balance: " + entry.getValue());
        }
    }

    // Refactored: Method to return the balance of the bank account name with the key that they user has searched.
    static int fetchAccountBalance(String userAccountInput) {
        Mapping map = new Mapping();
        Map mappingData = map.createBankAccountHashTable();
        System.out.println("Refactor: " + Integer.parseInt(mappingData.get(userAccountInput).toString()));
        return Integer.parseInt(mappingData.get(userAccountInput).toString());
    }

    // Refactored: Method to transfer the money to the recipient.
    static int refactoredAddRecipientMoney(int userInput, String userAccountInput) {
        Mapping map = new Mapping();
        Map mappingData = map.createBankAccountHashTable();
        int accountBalance = fetchAccountBalance(userAccountInput);
        int newBalance = accountBalance + userInput;
        mappingData.replace(userAccountInput, newBalance);
        System.out.println("Refactor: " + newBalance);
        return accountBalance + userInput;
    }









    // Method to check if there is enough money to transfer to the other user.
    static boolean checkTransferPossible(int balance, int userInput){
        int result = balance - userInput;
        boolean balanceStatus = (result >= 0) ? true : false;
        return balanceStatus;
    }

    // Method to give the user a status as to if their desired input can be transferred.
    static String moneyTransferStatus(int balance, int userInput) {
        boolean bool = checkTransferPossible(balance, userInput);
        String transferStatus = (bool) ? "You can transfer this." : "Cannot transfer";
        return transferStatus;
    }

    // Method to prompt the user to enter yes or no to questions
    static String userAnswerCollection() {
        Scanner scanner = new Scanner(System.in);
        String userAnswer = scanner.next();
        return userAnswer;
    }











    // Method to validate the answer from the user
    static boolean userAnswerValidation() {
        boolean status = false;
        boolean formStatus = true;
        String userAnswer = userAnswerCollection();
        while (formStatus) {
            if (userAnswer.toLowerCase().equals("y")) {
                status = true;
                formStatus = false;
            }
            else if (userAnswer.toLowerCase().equals("n")) {
                status = false;
                formStatus = false;
            }
            else {
                System.out.println("Please repeat that, we didn't understand your response.");
                userAnswerCollection();
            }
        }
        return status;
    }

    // Method to transfer the money to the recipient.
    static int addRecipientMoney(int userInput, String[][] userAccounts, int currentAccountNumber) {
        int recipientBalance = Integer.parseInt(userAccounts[currentAccountNumber][1]) + userInput;
        userAccounts[currentAccountNumber][1] = Integer.toString(Integer.parseInt(userAccounts[currentAccountNumber][1]) + userInput);
        return recipientBalance;
    }

    // Method to remove the money from the sender.
    static int removeSenderMoney(int userInput, int balance) {
        return balance - userInput;
    }

    // Method to collect the user decision on the account to transfer to.
    static int userAccountCollection(int userAccountNumber, String[][] userAccounts) {
        boolean status = true;
        Scanner scanner = new Scanner(System.in);
        int accountDecision = -1;
        while (status) {
            accountDecision = scanner.nextInt();
            if (accountDecision >= 0 && accountDecision <= userAccountNumber - 1) {
                System.out.println("This bank account number exists.");
                System.out.println(fetchAccountInformation(accountDecision, userAccounts));
                status = false;
            }
            else {
                System.out.println("This bank account number does not exist. Please try entering a valid one.");
            }
        }
        return accountDecision;
    }

    // Method to check if the transfer is a valid number.
    static boolean checkValidNumber(int userInput) {
        boolean status = false;
        if (userInput >= 0) {
            status = true;
        }
        else {
            status = false;
        }
        return status;
    }

    // Method to retrieve the user information that the user wishes to transfer to.
    static String fetchAccountInformation(int userAccountNumber, String[][] userAccounts) {
        return userAccounts[userAccountNumber][0] + ", Balance:" + userAccounts[userAccountNumber][1];
    }

    // Method to display all the available accounts to transfer to.
//    static String availableAccounts(String[][] userAccounts, int userAccountNumber) {
//        String myText = "";
//        for (int i = 0; i < userAccountNumber; i++) {
//            myText = myText + "\n" + userAccounts[i][0];
//        }
//        return myText;
//    }

    // Method to fetch all the bank account detail's keys and values.
    static void availableAccounts() {
        System.out.println("Available Accounts Are: \n");
        Mapping map = new Mapping();
        Map mappingData = map.createBankAccountHashTable();
        Iterator<Map.Entry<String, Integer>> iterator = mappingData.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            System.out.println("Name: " + entry.getKey() + ", Balance: " + entry.getValue());
        }
    }

    // Check if the bank transfer is possible e.g. existing account.
    static boolean bankTransferStatus(int userAccountNumber, String[][] userAccounts, int userAccount) {
        boolean transferStatus = false;
        if (userAccount != -1) {
            transferStatus = true;
        }
        else {
            transferStatus = false;
        }
        storeUserBank(userAccountNumber, userAccounts);
        return transferStatus;
    }

    // Method to store the selection of the user account.
    static int storeUserBank(int userAccountNumber, String[][] userAccounts) {
        return userAccountCollection(userAccountNumber, userAccounts);
    }

    public static void main(String args[]) {

        // Define variables.
        int balance = 1000;
        boolean systemStatus = true;

        // Define imports
        Scanner scanner = new Scanner(System.in);

        // Count the number of existing bank accounts.
        int userAccountNumber = userAccounts.length;

        // Run program until asked by the user to do so.
        while (systemStatus) {

            // Prompt user to enter the account to transfer to and store it.
            availableAccounts();
            System.out.println("\n Please select which account you wish to transfer to:" +
                    " from 0-2");
            int currentAccountNumber = storeUserBank(userAccountNumber, userAccounts);
            String userAccountInput = scanner.next();
            // Collect and check if the user input is a valid amount to transfer.
            boolean amountStatus = true;
            System.out.println("Please enter the amount you wish to transfer. ");
            int userInput = scanner.nextInt();
            while (amountStatus) {
                if (checkValidNumber(userInput)) {
                    amountStatus = false;
                }
                else {
                    System.out.println("Invalid number to transfer. Please try again. ");
                    userInput = scanner.nextInt();
                }
            }

            // Output if the user can transfer the money.
            System.out.println(moneyTransferStatus(balance, userInput));

            // Check if the transfer is possible without being overdraft else show unsuccessful error.
            if (checkTransferPossible(balance, userInput)) {

                // Make the user confirm their decision.
                System.out.println("Do you wish to proceed? y/n");

                // Check if the transfer decision is yes and proceed else show unsuccessful.
                if (userAnswerValidation()) {
                    System.out.println("Transfer was success. ");

                    // Transfer the user's input for the money to the recipient.
                    balance = removeSenderMoney(userInput, balance);
                    //int recipientBalance = addRecipientMoney(userInput, userAccounts, currentAccountNumber);
                    int recipientBalance = refactoredAddRecipientMoney(userInput, userAccountInput);
                    System.out.println("Your new balance is: " + balance + " and " +
                            userAccounts[currentAccountNumber][0] + " is now: " + recipientBalance);
                } else {
                    System.out.println("Transfer was unsuccessful");
                }
            } else {
                System.out.println("Transfer was unsuccessful as you do not have sufficient funds. ");
            }

            // Ask if the user wishes to do another transaction
            System.out.println("Do you wish to do another transaction? y/n");

            // Check the user decision to do another transaction else terminate the program.
            if (userAnswerValidation()) {
                systemStatus = true;
            } else {
                System.out.println("Thank you for using this service.");
                System.exit(0);
            }
        }
        scanner.close();
    }
}