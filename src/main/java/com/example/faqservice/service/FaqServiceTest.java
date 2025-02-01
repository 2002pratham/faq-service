package com.example.faqservice.service;

import com.example.faqservice.model.FAQ;
import com.example.faqservice.repository.FaqRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class FaqServiceTest {

    @Mock
    private FaqRepository faqRepository;

    @Mock
    private TranslationService translationService;

    @InjectMocks
    private FaqService faqService;

    private FAQ faq;

    @BeforeEach
    void setUp() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this);

        // Create a sample FAQ object
        faq = FAQ.builder()
                .id(1L)
                .question("What is Spring Boot?")
                .answer("Spring Boot is a Java-based framework for building production-ready applications.")
                .build();
    }

    @Test
    void testGetAllFaqsByLanguage() {
        // Mocking the repository and translation service
        when(faqRepository.findAll()).thenReturn(Arrays.asList(faq));
        when(translationService.translateText("What is Spring Boot?", "hi")).thenReturn("स्प्रिंग बूट क्या है?");
        when(translationService.translateText("What is Spring Boot?", "bn")).thenReturn("স্প্রিং বুট কী?");

        // Call the service method
        List<FAQ> faqs = faqService.getAllFaqsByLanguage("hi");

        // Verify behavior and assertions
        assertNotNull(faqs);
        assertEquals(1, faqs.size());
        assertEquals("स्प्रिंग बूट क्या है?", faqs.get(0).getQuestionHi());
        assertEquals("What is Spring Boot?", faqs.get(0).getQuestion());  // Original question should still be in the FAQ

        // Verify interactions with mocks
        verify(faqRepository).findAll();
        verify(translationService).translateText("What is Spring Boot?", "hi");
    }

    @Test
    void testSaveFaqWithTranslations() {
        // Mocking the translation service
        when(translationService.translateText(faq.getQuestion(), "hi")).thenReturn("स्प्रिंग बूट क्या है?");
        when(translationService.translateText(faq.getQuestion(), "bn")).thenReturn("স্প্রিং বুট কী?");

        // Call the service method
        FAQ savedFaq = faqService.saveFaqWithTranslations(faq);

        // Verify that translations are set before saving
        assertNotNull(savedFaq.getQuestionHi());
        assertNotNull(savedFaq.getQuestionBn());

        // Verify that the repository's save method is called
        verify(faqRepository).save(faq);
    }
}
