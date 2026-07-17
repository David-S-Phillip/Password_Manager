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
}
