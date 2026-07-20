package storage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AccountTest {
    private Account acc;

    @BeforeEach
    public void setUp(){
        this.acc = new Account("Bob","5a70fc1e224f429834df9aeec8f8ad915be96fc410ac913022d56d4a1569cb69");
    }

    @Test
    public void testGetUserName(){
        String userName = acc.getUserName();
        Assertions.assertEquals("Bob",userName);
    }

    @Test
    public void testGetHashedPassword(){
        String hashedPassword = acc.getHashedPassword();
        Assertions.assertEquals("5a70fc1e224f429834df9aeec8f8ad915be96fc410ac913022d56d4a1569cb69", hashedPassword);
    }

    @Test
    public void testToSting(){
        String toString = acc.toString();
        Assertions.assertEquals("User: Bob | Hash: 5a70fc1e224f429834df9aeec8f8ad915be96fc410ac913022d56d4a1569cb69", toString);
    }

    @Test
    public void testEmptyUserNameHashedPassword(){
        Assertions.assertThrows(IllegalArgumentException.class, () ->{
            Account emptyAccUserName = new Account("","");
        });
    }

    @Test
    public void testEmptyHashedPassword(){
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Account emptyPassword = new Account("Bob","");
        });

        Assertions.assertEquals("Username or Password cannot be empty", exception.getMessage());
    }

    @Test
    public void testNullValues(){
        NullPointerException exception = Assertions.assertThrows(NullPointerException.class, () -> {
            Account nullAccount = new Account(null,null);
        });

        Assertions.assertEquals("Username or Password cannot be null", exception.getMessage());
    }
}
