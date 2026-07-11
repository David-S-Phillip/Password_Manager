package passwordValidator;

public class PasswordValidator {

//    public PasswordValidator(){}

    private static boolean isPasswordLengthValid(String password){
        boolean isPasswordLength = false;
        if (password.length() < 6) {
            throw new IllegalArgumentException("Password may not be less than 6 characters gazzi");
        }else{
            isPasswordLength = true;
        }
        return isPasswordLength;
    }

    private static boolean hasLetters(String password){
        boolean letters = false;
        for (int i = 0; i < password.length(); i++){
            char c = password.charAt(i);
            if (Character.isLetter(c)){
                letters = true;
                return letters;
            }
        }
        throw new IllegalArgumentException("Password has no letters in it");
    }

    private static boolean hasNumbers(String password){
        boolean numbers = false;
        for (char c : password.toCharArray()){
            if (Character.isDigit(c)){
                numbers = true;
                return numbers;
            }
        }
        throw new IllegalArgumentException("Password has no numbers in it");
    }

    private static boolean hasSymbols(String password){
        boolean symbols = false;
        for (char c : password.toCharArray()){
            if (!Character.isDigit(c) && !Character.isLetter(c)){
                symbols = true;
                return symbols;
            }
        }
        throw new IllegalArgumentException("Password has no special symbols");
    }

    public static boolean isValidPassword(String Password){
        boolean pass = false;
        if (hasSymbols(Password) && hasNumbers(Password) && hasLetters(Password) && isPasswordLengthValid(Password) == true){
            pass = true;
        }
    return pass;
    }


}
