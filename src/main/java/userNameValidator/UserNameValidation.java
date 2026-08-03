package userNameValidator;

public class UserNameValidation {

    public static boolean isUserNameAlphanumeric(String userName){
        boolean inputUserName = false;
        if (userName == null || userName.isBlank()){
            throw new IllegalArgumentException("username cannot be empty");
        }

        if (userName.matches("^[a-zA-A-Z0-9]")) {
            inputUserName = true;
        }

        return inputUserName;
    }
}
