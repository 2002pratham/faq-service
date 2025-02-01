package com.example.faqservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "faqs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FAQ {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String question;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String answer;

    // Translations for multilingual support
    @Column(columnDefinition = "TEXT")
    private String questionHi;  // Hindi

    @Column(columnDefinition = "TEXT")
    private String questionBn;  // Bengali

    // Method to get translated question based on language
    public String getTranslatedQuestion(String lang) {
        return switch (lang.toLowerCase()) {
            case "hi" -> questionHi != null ? questionHi : question;  // Fallback to English
            case "bn" -> questionBn != null ? questionBn : question;
            default -> question;  // Default to English
        };
    }
}
