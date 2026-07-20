package storage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class StoreTest {
    Account acc;
    Store storeHashMap;
    @BeforeEach
    public void setUp(){
        this.acc = new Account("Bob","5a70fc1e224f429834df9aeec8f8ad915be96fc410ac913022d56d4a1569cb69");
        this.storeHashMap = new Store();
    }


    @Test
    public void testAddAccount(){
        Assertions.assertEquals(0,storeHashMap.getPasswordMap().size());
        storeHashMap.addAccount(acc.getUserName(),acc);
        Assertions.assertEquals(1,storeHashMap.getPasswordMap().size());
    }

}
