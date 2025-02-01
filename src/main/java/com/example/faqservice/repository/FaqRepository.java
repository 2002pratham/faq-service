package com.example.faqservice.repository;

import com.example.faqservice.model.FAQ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaqRepository extends JpaRepository<FAQ, Long> {

}
