package storage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class VaultFileManager {
    private final String filePath;

    public VaultFileManager(String filePath) {
        if (filePath == null || filePath.isBlank()) {
            throw new IllegalArgumentException("File path cannot be null or empty.");
        }
        this.filePath = filePath;
    }

    // Pass your Store object (or its map) right into this method
    public void saveVault(Map<String, Account> passwordMap) throws IOException {
        if (passwordMap == null) {
            throw new IllegalArgumentException("Cannot save a null map.");
        }

        // Try-with-resources automatically closes the file writer when done
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {

            // Loop through each entry (Key = Username, Value = Account object)
            for (Map.Entry<String, Account> entry : passwordMap.entrySet()) {
                String usernameKey = entry.getKey();
                Account accountObj = entry.getValue();

                // Format each line: username,hashedPassword
                String line = usernameKey + "," + accountObj.getHashedPassword();

                writer.write(line);
                writer.newLine(); // Move to the next line in the text file
            }
        }
    }
}