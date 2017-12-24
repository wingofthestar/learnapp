package com.example.learnapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(generator = "uuidGenerator")
    @GenericGenerator(name = "uuidGenerator", strategy = "uuid")
    private String studentId;
    private Integer questionNumber = 0;
    @OneToOne
    @JoinColumn(name = "user_info_id")
    private UserInfo userInfo;
    @OneToMany(mappedBy = "student")
    @JsonIgnore //防止陷入死循环
    private Collection<QuestionInfo> questionInfos;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Integer getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(Integer questionNumber) {
        this.questionNumber = questionNumber;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Collection<QuestionInfo> getQuestionInfos() {
        return questionInfos;
    }

    public void setQuestionInfos(Collection<QuestionInfo> questionInfos) {
        this.questionInfos = questionInfos;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", questionNumber=" + questionNumber +
                ", userInfo=" + userInfo +
                '}';
    }
}
