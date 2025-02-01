package com.example.faqservice.service;

import com.example.faqservice.model.FAQ;
import com.example.faqservice.repository.FaqRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FaqService {

    @Autowired
    private FaqRepository faqRepository;

    @Autowired
    private TranslationService translationService;

    // Fetching all FAQs and returning translated questions based on the language
    @Cacheable(value = "faqs", key = "#lang + '_' + #faq.id")
    public List<FAQ> getAllFaqsByLanguage(String lang) {
        List<FAQ> faqs = faqRepository.findAll();

        // Update the translated question fields in each FAQ object
        faqs.forEach(faq -> {
            String translatedQuestion = faq.getTranslatedQuestion(lang);
            // Modify the FAQ's question based on the language
            if ("hi".equalsIgnoreCase(lang)) {
                if (faq.getQuestionHi() == null) {  // Only update if translation is missing
                    faq.setQuestionHi(translatedQuestion);
                }
            } else if ("bn".equalsIgnoreCase(lang)) {
                if (faq.getQuestionBn() == null) {  // Only update if translation is missing
                    faq.setQuestionBn(translatedQuestion);
                }
            }
        });
        return faqs;
    }

    // Save FAQ with translated questions
    public FAQ saveFaqWithTranslations(FAQ faq) {
        // Translate the question before saving it
        if (faq.getQuestion() != null) {
            // Translate and save only if the translation doesn't already exist
            if (faq.getQuestionHi() == null) {
                faq.setQuestionHi(translationService.translateText(faq.getQuestion(), "hi"));
            }
            if (faq.getQuestionBn() == null) {
                faq.setQuestionBn(translationService.translateText(faq.getQuestion(), "bn"));
            }
        }
        return faqRepository.save(faq);
    }
}
