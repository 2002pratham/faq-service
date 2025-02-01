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

    // Getter method for question
    public String getQuestion() {
        return question;
    }

    // Getter method for questionHi
    public String getQuestionHi() {
        return questionHi;
    }

    // Getter method for questionBn
    public String getQuestionBn() {
        return questionBn;
    }

    public String getTranslatedQuestion(String lang) {
        return switch (lang.toLowerCase()) {
            case "hi" -> questionHi != null ? questionHi : question;
            case "bn" -> questionBn != null ? questionBn : question;
            default -> question;  // Default to English
        };
    }

    // Setter for questionHi
    public void setQuestionHi(String questionHi) {
        this.questionHi = questionHi;
    }

    // Setter for questionBn
    public void setQuestionBn(String questionBn) {
        this.questionBn = questionBn;
    }
}
