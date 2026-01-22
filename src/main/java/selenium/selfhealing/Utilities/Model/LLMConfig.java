package selenium.selfhealing.Utilities.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LLMConfig {

    @JsonProperty("Provider")
    private String provider;

    @JsonProperty("BaseUrl")
    private String baseUrl;

    @JsonProperty("Temperature")
    private double temperature;

    @JsonProperty("Model")
    private String model;

    @JsonProperty("MaxTokens")
    private int maxTokens;

    @JsonProperty("ApiKey")
    private String apiKey;

    // Getters and Setters
    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getMaxTokens() {
        return maxTokens;
    }

    public void setMaxTokens(int maxTokens) {
        this.maxTokens = maxTokens;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public String toString() {
        return "LLMConfig{" +
                "provider='" + provider + '\'' +
                ", baseUrl='" + baseUrl + '\'' +
                ", temperature=" + temperature +
                ", model='" + model + '\'' +
                ", maxTokens=" + maxTokens +
                ", apiKey='" + apiKey + '\'' +
                '}';
    }
}