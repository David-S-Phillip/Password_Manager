package storage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;


public class CloudStorageTest {
    private HttpClient mockClient;
    private HttpResponse<String> mockResponse;

    @BeforeEach
    public void setup(){
        mockClient = Mockito.mock(HttpClient.class);
        mockResponse = (HttpResponse<String>) Mockito.mock(HttpResponse.class);

    }

    @Test
    void uploadValutToCloudShouldReturnFalse_WhenApiKeyIsNull(){
        CloudStorage storage = new CloudStorage(mockClient, null);

        boolean result = storage.uploadVaultToCloud("bine123", "{\"vault\":\"data\"}");
        Assertions.assertFalse(result,"Should fail bc API key is null");
    }

}
