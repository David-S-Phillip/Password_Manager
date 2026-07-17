package storage;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Store {
    private Map<String, Account> passwordMap;

    public Store(){
        this.passwordMap = new HashMap<>();
    }

    public void addAccount(String userName, Account account){
        this.passwordMap.put(userName, account);
    }

    public Map<String, Account> getPasswordMap(){
        return Collections.unmodifiableMap(this.passwordMap);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Password Vault Contents ===\n");

        if (this.passwordMap.isEmpty()) {
            sb.append("Vault is currently empty.");
        } else {
            // Iterate through each Account object in the unmodifiable map
            for (Account account : getPasswordMap().values()) {
                sb.append(account.toString()).append("\n");
            }
        }

        return sb.toString();
    }
}
