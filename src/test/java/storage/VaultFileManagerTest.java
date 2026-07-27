package storage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VaultFileManagerTest {
    Account testAcc;
    Account testAcc2;
    Account testAcc3;

    @TempDir
    Path tempDir;
    Map<String, Account> testMapBob;
    VaultFileManager fileManager;
    Path testFilePath;
    String filePathString;

    @BeforeEach
    public void setUp() {
        testAcc = new Account("Bob", "b83dfdd8aaad716e1fcc91f582530164b0c40055a200cd2919a96bf148f6d512");

        testMapBob = new HashMap<>();
        testMapBob.put(testAcc.getUserName(), testAcc);
//        testMapBob.put(testAcc2.getUserName(), testAcc2);

        testFilePath = tempDir.resolve("test_vault.txt");
        filePathString = testFilePath.toString();
        fileManager = new VaultFileManager(filePathString);
    }


    @Test
    public void testFileContainsBob() throws IOException {
        fileManager.saveVault(testMapBob);
        List<String> lines = Files.readAllLines(testFilePath);
        assertEquals(1, lines.size());
        Assertions.assertEquals("Bob | b83dfdd8aaad716e1fcc91f582530164b0c40055a200cd2919a96bf148f6d512", lines.get(0));


    }

    @Test
    public void testFileContainsHasher() throws IOException {
        testAcc2 = new Account("Hasher", "b83dfdd8aaad716e1fcc91f582530164b0c40055a200cd2919a96bf148xfd512");
        testMapBob.put(testAcc2.getUserName(), testAcc2);
        fileManager.saveVault(testMapBob);
        List<String> lines = Files.readAllLines(testFilePath);
        Assertions.assertEquals(2, lines.size());
        Assertions.assertEquals("Hasher | b83dfdd8aaad716e1fcc91f582530164b0c40055a200cd2919a96bf148xfd512", lines.get(0));
        Assertions.assertEquals("Bob | b83dfdd8aaad716e1fcc91f582530164b0c40055a200cd2919a96bf148f6d512", lines.get(1));
    }


    @Test
    public void testFileContainsRashford() throws IOException{
        testAcc3 = new Account("Rashford", "b83dfdd8aaad716w2fcc91f582530164b0c40055a200cd2919a96bf148xfd512");
        testMapBob.clear();
        testMapBob.put(testAcc3.getUserName(), testAcc3);
        fileManager.saveVault(testMapBob);
        List<String> lines = Files.readAllLines(testFilePath);
        Assertions.assertEquals(1, lines.size());
        Assertions.assertEquals("Rashford | b83dfdd8aaad716w2fcc91f582530164b0c40055a200cd2919a96bf148xfd512", lines.get(0));

    }

    @Test
    public void testEmptyFilePath() {
        Account testAcc4 = new Account("XAXA", "b83dfdd8aaad716w2fcc91f582530164b0c40055a200cd2919a96bf148xfd512");
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            VaultFileManager nullManager = new VaultFileManager(null);
        });
        Assertions.assertEquals("File path cannot be null or empty.", exception.getMessage());

    }

    @Test
    public void testBlankFilePath(){
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            VaultFileManager blankManager = new VaultFileManager("  ");
        });

        Assertions.assertEquals("File path cannot be null or empty.", exception.getMessage());
    }



}
