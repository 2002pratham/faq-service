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

    @Cacheable(value = "faqs", key = "#lang + '_' + #faq.id")
    public List<FAQ> getAllFaqsByLanguage(String lang) {
        List<FAQ> faqs = faqRepository.findAll();
        // Calling the translation method (this will be cached)
        faqs.forEach(faq -> faq.getTranslatedQuestion(lang));
        return faqs;
    }
}
