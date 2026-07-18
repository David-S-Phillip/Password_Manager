package passwordValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import passwordValidator.PasswordValidator;
import org.junit.jupiter.api.Test;

public class PasswordValidatorTest {
    private static String validPassword;
    private static String badLengthPassword;
    private static String noNumbersPassword;
    private static String noLettersPassword;
    private static String noSpecialSymbolsPassword;

    @BeforeAll
    public static void setUp(){
        validPassword = "ASD!#!@#123";
        badLengthPassword = "A1d@";
        noNumbersPassword = "asdasdasd!@#!@#!@#!@#";
        noLettersPassword = "123#!@#!@#";
        noSpecialSymbolsPassword = "AsdadD1231231";
    }
    @Test
    public void TestValidationCheck() {
        String password = "Hello123!@#!@#";
        boolean valid = PasswordValidator.isValidPassword(password);
        Assertions.assertTrue(valid);
    }

    @Test
    public void TestDoesNotThrowAsserions() {
        String password = "Hello123!@#!@#";
        Assertions.assertDoesNotThrow(() -> {
            PasswordValidator.isValidPassword(password);
        });
    }

    @Test
    public void TestDoesThrowAssertions() {
        String password = "hello";
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
                    PasswordValidator.isValidPassword(password);
                });
        Assertions.assertTrue(exception.getMessage().contains("Password may not be less than 6 characters gazzi"));
    }

    @Test
    public void TestDoesNotThrowAssertionsValidPassword(){
        Assertions.assertDoesNotThrow(() -> {
            PasswordValidator.isValidPassword(validPassword);
        });
    }

    @Test
    public void TestThrowsbadLength(){
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->{
            PasswordValidator.isValidPassword(badLengthPassword);
        });
        Assertions.assertTrue(exception.getMessage().contains("Password may not be less than 6 characters gazzi"));
    }

    @Test
    public void TestThrowsNoNumbers(){
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () ->{
            PasswordValidator.isValidPassword(noNumbersPassword);
        });
        Assertions.assertTrue(exception.getMessage().contains("Password has no numbers in it"));
    }

    @Test
    public void TestThrowNoLetters(){
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            PasswordValidator.isValidPassword(noLettersPassword);
        });
        Assertions.assertTrue(exception.getMessage().contains("Password has no letters in it"));
    }

    @Test
    public void TestThrowNoSpecialCharacters(){
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            PasswordValidator.isValidPassword(noSpecialSymbolsPassword);
        });
        Assertions.assertTrue(exception.getMessage().contains("Password has no special symbols"));
    }




}