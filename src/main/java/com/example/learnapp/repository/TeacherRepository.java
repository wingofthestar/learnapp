package com.example.learnapp.repository;

import com.example.learnapp.entity.Teacher;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TeacherRepository extends JpaRepository<Teacher, String> {

    @Query(value = "update Teacher t set t.subject = :subject where t.teacherId = :teacherId")
    @Modifying
    void updateTeacherInfo(@Param("subject") String subject, @Param("teacherId") String teacherId);
}
