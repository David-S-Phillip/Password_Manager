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

}
