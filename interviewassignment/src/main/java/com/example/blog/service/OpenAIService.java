package com.example.blog.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import java.util.*;

@Service
public class OpenAIService {
    private final RestTemplate restTemplate;
    private final String apiUrl;
    private final String apiKey; 

    public OpenAIService(RestTemplate restTemplate, @Value("${openai.api.key}") String apiKey) {
        this.restTemplate = restTemplate;
        this.apiUrl = "https://api.openai.com/v1/chat/completions";
        this.apiKey = apiKey; 
    }

    public String getSummarizedContent(String content) {
        
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "gpt-3.5-turbo");
        requestBody.put("messages", List.of(
        Map.of("role", "system", "content", "You are an AI that summarizes text concisely."),
        Map.of("role", "user", "content", "Summarize this: " + content)
    ));
    requestBody.put("max_tokens", 200);

        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);  

        
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

       
        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
            apiUrl, HttpMethod.POST, entity, new ParameterizedTypeReference<>() {}
        );

        
        return extractSummary(response.getBody());
    }

    private String extractSummary(Map<String, Object> responseBody) {
        if (responseBody == null || !responseBody.containsKey("choices")) {
            return "Error: No response from OpenAI API";
        }

        Object choicesObj = responseBody.get("choices");
        if (!(choicesObj instanceof List<?> rawChoices) || rawChoices.isEmpty()) {
            return "Error: Invalid response format";
        }

        Object firstChoice = rawChoices.get(0);
        if (!(firstChoice instanceof Map<?, ?> firstChoiceMap) || !firstChoiceMap.containsKey("message")) {
            return "Error: Missing message key";
        }

        Object messageObj = firstChoiceMap.get("message");
        if (!(messageObj instanceof Map<?, ?> messageMap) || !messageMap.containsKey("content")) {
            return "Error: No content found";
        }

        return Objects.toString(messageMap.get("content"), "").trim();
    }
}
