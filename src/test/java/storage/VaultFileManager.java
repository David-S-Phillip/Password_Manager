package storage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class VaultFileManager {
    private final String filePath;

    public VaultFileManager(String filePath){
        if (filePath == null || filePath.isBlank()){
            throw new IllegalArgumentException("File path cannot be null or empty.");
        }else{
            this.filePath = filePath;
        }
    }

    public void saveVault(Map<String, Account> passwordMap) throws IOException {
        if (passwordMap == null){
            throw new IllegalArgumentException("Cannot save a null map.");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))){
            for (Map.Entry<String, Account> entry : passwordMap.entrySet()){
                String userNamekey = entry.getKey();
                String accountObj = entry.getValue();


            }
        }
    }
}
