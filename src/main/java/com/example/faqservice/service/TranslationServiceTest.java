package com.example.faqservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class TranslationServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private TranslationService translationService;

    @BeforeEach
    void setUp() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTranslateText() {
        // Mock the translation response
        String mockResponse = "{\"translatedText\":\"Hola\"}";
        when(restTemplate.postForObject(anyString(), any(), eq(String.class))).thenReturn(mockResponse);

        // Call the method to test
        String translatedText = translationService.translateText("Hello", "es");

        // Verify the translation result
        assertEquals("Hola", translatedText);

        // Verify interactions with RestTemplate
        verify(restTemplate).postForObject(anyString(), any(), eq(String.class));
    }

    @Test
    void testTranslateText_WithNullResponse() {
        // Mock a null response from the translation API
        when(restTemplate.postForObject(anyString(), any(), eq(String.class))).thenReturn(null);

        // Call the method to test
        String translatedText = translationService.translateText("Hello", "es");

        // Verify the original text is returned if the translation is null
        assertEquals("Hello", translatedText);
    }
}
