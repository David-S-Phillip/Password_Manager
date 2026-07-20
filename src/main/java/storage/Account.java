package storage;

public class Account {
    private final String userName;
    private final String hashedPassword;

    public Account(String userName, String hashedPassword) {
        if (userName == null || hashedPassword == null) {
            throw new NullPointerException("Username or Password cannot be null");
        }
        if (userName.isEmpty() || hashedPassword.isEmpty()){
            throw new IllegalArgumentException("Username or Password cannot be empty");
        }else{
            this.userName = userName;
            this.hashedPassword = hashedPassword;
        }
    }

    public String getUserName(){
        return this.userName;
    }

    public String getHashedPassword(){
        return this.hashedPassword;
    }

    @Override
    public String toString() {
        return "User: " + this.userName + " | Hash: " + this.hashedPassword;
    }


}
