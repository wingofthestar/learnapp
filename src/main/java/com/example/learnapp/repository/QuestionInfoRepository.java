package com.example.learnapp.repository;

import com.example.learnapp.entity.QuestionInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionInfoRepository extends JpaRepository<QuestionInfo, String> {

    QuestionInfo findByQuestionInfoId(String questionId);

    List<QuestionInfo> findBySubject(String subject);

}
