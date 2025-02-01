package com.example.faqservice.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TranslationService {

    private final RestTemplate restTemplate;

    public TranslationService() {
        this.restTemplate = new RestTemplate();
    }

    // Method to translate a given text into a specific language
    public String translateText(String text, String targetLanguage) {
        String apiUrl = "https://libretranslate.de/translate";

        // Prepare the request body
        String requestBody = "{"
                + "\"q\":\"" + text + "\","
                + "\"source\":\"en\","
                + "\"target\":\"" + targetLanguage + "\""
                + "}";

        // Make the POST request and get the response
        String response = restTemplate.postForObject(apiUrl, requestBody, String.class);

        // Extract and return the translated text from the response
        return response != null ? response : text;
    }
}
