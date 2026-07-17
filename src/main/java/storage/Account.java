package storage;

public class Account {
    private final String userName;
    private final String hashedPassword;

    public Account(String userName, String hashedPassword){
        this.userName = userName;
        this.hashedPassword = hashedPassword;
    }

    public String getUserName(){
        return this.userName;
    }

    public String getHashedPassword(){
        return this.hashedPassword;
    }


}
