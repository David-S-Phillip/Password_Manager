package hashing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class HasherTest {

    static String validPassword;
    static String similarLowercasePassword;
    static String emptyString;

    @BeforeAll
    public static void setUp(){
       validPassword = "BobHad12$";
       similarLowercasePassword = "boBhAd12$";
       emptyString = "";
    }

    @Test
    public void testExpectedHashResult(){
        String password = "abc123N!@#";
        String hashedPassword = Hasher.hashPassword(password);

        Assertions.assertEquals("5a70fc1e224f429834df9aeec8f8ad915be96fc410ac913022d56d4a1569cb69",hashedPassword);
    }

    @Test
    public void testSimilarPasswordsLowerCaseGenerateDiffrentHashed(){
        String hashedPassword = Hasher.hashPassword(validPassword);
        Assertions.assertEquals("b83dfdd8aaad716e1fcc91f582530164b0c40055a200cd2919a96bf148f6d512", hashedPassword);

        String hashedPassword2 = Hasher.hashPassword(similarLowercasePassword);
        Assertions.assertEquals("e0db30e98e52602af933ca67168e95ee143ddcd1cae770777f933764eb1e9895", hashedPassword2);
    }

    @Test
    public void testEmptyString(){
        String hashedPassword = Hasher.hashPassword(emptyString);
        Assertions.assertEquals("e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855", hashedPassword);

    }

    @Test
    public void testHashPasswordNullInputThrowsException() { // assisted
        // Assert: Verify that passing null triggers a NullPointerException
        Assertions.assertThrows(NullPointerException.class, () -> {
            Hasher.hashPassword(null);
        });
    }


}
