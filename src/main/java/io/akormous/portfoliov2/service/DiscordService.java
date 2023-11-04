package io.akormous.portfoliov2.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.akormous.portfoliov2.model.Message;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DiscordService {
    private String webhookUrl;

    private static DiscordService instance;

    private DiscordService() {}

    public static synchronized DiscordService getInstance() {
        if(instance == null) {
            instance = new DiscordService();
        }
        return instance;
    }

    public void setWebhookUrl(String webhookUrl) {
        this.webhookUrl = webhookUrl;
    }
    private String buildJSONString(Message message) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode bodyObject = objectMapper.createObjectNode();
        bodyObject.put("content", message.toString());
        return bodyObject.toString();
    }

    private HttpResponse<String> sendMessageToChannel(Message message) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(this.webhookUrl))
                .POST(HttpRequest.BodyPublishers.ofString(buildJSONString(message)))
                .header("Content-Type", "application/json")
                .build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }
    public Boolean send(Message message) {
        try {
            if(message.getMessage().isEmpty() || message.getName().isEmpty() || message.getEmail().isEmpty()) {
                return false;
            }
            HttpResponse<String> response = sendMessageToChannel(message);
            System.out.println(response.toString());
            if(response.statusCode() < 200 || response.statusCode() >= 300) {
                return false;
            }
        }
        catch(IOException ioException) {
            return false;
        }
        catch (InterruptedException interruptedException) {
            return false;
        }
        return true;
    }
}
