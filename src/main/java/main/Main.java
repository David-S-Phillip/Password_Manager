package main;
import hashing.Hasher;
import passwordValidator.PasswordValidator;

import java.util.Scanner;

public class Main {
    public static void main(String [] args){
        System.out.println("Welcome to the password vault");
        Scanner userInput = new Scanner(System.in);
        boolean isPasswordValid = false;
        String password = "";

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

        System.out.println("=== Vault secured ===");
        System.out.println("Raw Input: " + password);
        System.out.println("SHA-256 Hash: " + hashedPassword);

        userInput.close();


    }
}
