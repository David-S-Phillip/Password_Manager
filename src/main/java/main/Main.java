package main;
import hashing.Hasher;
import passwordValidator.PasswordValidator;
import storage.Account;
import storage.Store;

import java.util.Scanner;

public class Main {
    public static void main(String [] args){
        System.out.println("Welcome to the password vault");
        Scanner userInput = new Scanner(System.in);
        boolean isPasswordValid = false;
        String password = "";
        System.out.println("Please enter your user name: ");
        String userName = userInput.nextLine();

        while (!isPasswordValid) {
            System.out.println("please enter password: ");
            password = userInput.nextLine();

            try {
                PasswordValidator.isValidPassword(password);
                isPasswordValid = true;
                System.out.println("password success");
            } catch (IllegalArgumentException e){
                System.out.println("Error: " + e.getMessage());
                System.out.println("Please try again.\n");
            }
        }

        String hashedPassword = Hasher.hashPassword(password);
        Account newAccount = new Account(userName, hashedPassword);
        Store storePasswords = new Store();
        storePasswords.addAccount(userName, newAccount);


        System.out.println("=== Vault secured ===");
        System.out.println(storePasswords.toString());

        userInput.close();


    }
}
