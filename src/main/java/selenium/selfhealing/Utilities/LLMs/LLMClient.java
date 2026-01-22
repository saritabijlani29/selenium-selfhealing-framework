package selenium.selfhealing.Utilities.LLMs;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import selenium.selfhealing.Utilities.Model.LLMConfig;

public class LLMClient {

    HttpClient httpClient;
    LLMConfig llmConfig;

    public LLMClient() throws IOException {
        this.httpClient = HttpClient.newHttpClient();
        this.llmConfig = readJsonFile();
    }

    public String callLocalLLM(String prompt) throws IOException, InterruptedException {
        JSONObject requestBody = new JSONObject();
        requestBody.put("model", llmConfig.getModel());
        requestBody.put("prompt", prompt);
        requestBody.put("stream", false);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(llmConfig.getBaseUrl() + "api/generate"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody.toString()))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() >= 200 && response.statusCode() < 300) {
            JSONObject jsonResponse = new JSONObject(response.body());
            return jsonResponse.optString("response", "");
        } else {
            throw new RuntimeException("Local LLM request failed with status: " + response.statusCode());
        }

    }

    public LLMConfig readJsonFile() throws IOException {
        InputStream inputStream = new FileInputStream(new File("src/main/resources/appSettings.json"));

        ObjectMapper mapper = new ObjectMapper();
        // Suppose JSON is {"name":"John", "age":30}
        LLMConfig config = mapper.readValue(inputStream, LLMConfig.class);
        return config;
    }

    public String callLLMModel(String prompt) throws IOException, InterruptedException {
        String response ;
        switch (llmConfig.getProvider().toLowerCase()){
            case "local":
                response = new LLMClient().callLocalLLM(prompt);
                break;
            case "openApi":
                System.out.println("callOpenApi");
                response =  "calling open api";
                break;
            default:
                response = llmConfig.getProvider();
                break;
        }
        return response;
    }
}