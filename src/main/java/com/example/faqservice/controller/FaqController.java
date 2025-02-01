package com.example.faqservice.controller;

import com.example.faqservice.model.FAQ;
import com.example.faqservice.service.FaqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FaqController {

    @Autowired
    private FaqService faqService;

    @GetMapping("/api/faqs")
    public List<FAQ> getAllFaqs(@RequestParam(value = "lang", defaultValue = "en") String lang) {
        return faqService.getAllFaqsByLanguage(lang);
    }
}
