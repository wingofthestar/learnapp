package com.example.learnapp.repository;

import com.example.learnapp.entity.AnswerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerInfoRepository extends JpaRepository<AnswerInfo, String> {
}
