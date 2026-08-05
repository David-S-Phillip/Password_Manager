package storage;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CloudStorage {
    // create a reusable Http client
    private final HttpClient client = HttpClient.newHttpClient();
    private static final String API_KEY = "$2a$10$zsaFohdm/zR/zv779m7TIeeE58/c/r4mtFb85BQQOXdmUCa1TlJ3W";
    private static final String API_URL = "https://api.jsonbin.io/v3/b";

    public void uploadVaultToCloud(String jsonVaultData) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .header("Content-Type", "application/json")
                    .header("X-Master-Key", API_KEY)
                    .header("X-Bin-Name", "password_vault_backup")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonVaultData))
                    .build();

            System.out.println("Sending vault data to cloud...");

            // Send the request synchronously and capture the response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // 4. Inspect the Result
            System.out.println("HTTP Response Code: " + response.statusCode());
            System.out.println("Server Response: " + response.body());

            if (response.statusCode() == 200) {
                System.out.println("SUCCESS: Vault backed up to cloud!");
            } else {
                System.out.println("FAILED: Server returned error status.");
            }

        } catch (Exception e) {
            System.out.println("Network error: Could not reach API -> " + e.getMessage());
        }
    }
}
