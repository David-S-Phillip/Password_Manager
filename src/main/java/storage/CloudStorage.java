package storage;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CloudStorage {
    private final HttpClient client;
    private final String apiKey;
    private static final String API_URL = "https://api.jsonbin.io/v3/b";

    public CloudStorage(){
        this(HttpClient.newHttpClient(), System.getenv("JSONBIN_KEY"));
    }

    public CloudStorage(HttpClient client, String apiKey){
        this.client = client;
        this.apiKey = apiKey;
    }

    /**
     * SRP -- implementing single responsibility each method should only do one thing and one thing only
     */
    public boolean uploadVaultToCloud(String binId, String jsonVaultData){
        if (!isApiKeyValid()){
            System.err.println("Error: API key is missing or empty my gazzi, did you give it a lift?");
            return false;
        }

        try {
            HttpRequest request = buildPutRequest(binId, jsonVaultData);
            HttpResponse<String> response = sendRequest(request);

            logResponse(response);
            return response.statusCode() == 200;
        } catch(Exception e){
            System.err.println("Network error: Could not reach API -> " + e.getMessage());
            return false;
        }
    }

    /**
     * Helper methods (SRP)
     */

    private boolean isApiKeyValid(){
        return apiKey != null && !apiKey.isBlank();
    }

    private HttpRequest buildPutRequest(String binId, String jsonVaultData){
        return HttpRequest.newBuilder()
                .uri(URI.create(API_URL + "/" + binId))
                .header("Content-Type", "application/json")
                .header("X-Master-Key",apiKey)
                .PUT(HttpRequest.BodyPublishers.ofString(jsonVaultData))
                .build();
    }

    private HttpResponse<String> sendRequest(HttpRequest request) throws Exception {
        System.out.println("Sending vault data to cloud");
        return client.send(request, HttpResponse.BodyHandlers.ofString());

    }

    private void logResponse(HttpResponse<String> response){
        System.out.println("HTTP Response Code: " + response.statusCode());
        System.out.println("Server Response: " + response.body());
        if (response.statusCode() == 200){
            System.out.println("Success: Vault back up to cloud");
        }else{
            System.out.println("Failed: Server returned error status");
        }
    }


}