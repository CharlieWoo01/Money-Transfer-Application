import java.util.Scanner;

public class ATMGame {

    /* ToDo:
    Add logic to add accounts.
     */


    // Array of all the bank account data.
    public static String[][] userAccounts = {
            {"Charlie", "2000"},
            {"Charlie2", "3000"},
            {"Charlie3", "4000"},
            {"Charlie4", "5000"},
    };

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
                System.out.println("Please select which account you wish to transfer to:" +
                        " from 0-2");
                int currentAccountNumber = storeUserBank(userAccountNumber, userAccounts);

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
                        int recipientBalance = addRecipientMoney(userInput, userAccounts, currentAccountNumber);
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