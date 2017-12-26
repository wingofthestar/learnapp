package com.example.learnapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "question_info")
public class QuestionInfo {
    @Id
    @GeneratedValue(generator = "uuidGenerator")
    @GenericGenerator(name = "uuidGenerator", strategy = "uuid")
    private String questionInfoId;
    private String questionTitle;
    private String questionContent;
    private String questionPic;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
    @OneToOne(mappedBy = "questionInfo")
    @JsonIgnore
    private AnswerInfo answerInfo;
    @Temporal(TemporalType.DATE)
    private Date questionDate;
    private String subject;

    /**
     * 关联UserInfo表用来做收藏
     */
    @ManyToOne
    @JoinColumn(name = "collection_user_info_id")
    private UserInfo userInfo;

    public String getQuestionInfoId() {
        return questionInfoId;
    }

    public void setQuestionInfoId(String questionInfoId) {
        this.questionInfoId = questionInfoId;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public String getQuestionPic() {
        return questionPic;
    }

    public void setQuestionPic(String questionPic) {
        this.questionPic = questionPic;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public AnswerInfo getAnswerInfo() {
        return answerInfo;
    }

    public void setAnswerInfo(AnswerInfo answerInfo) {
        this.answerInfo = answerInfo;
    }

    public Date getQuestionDate() {
        return questionDate;
    }

    public void setQuestionDate(Date questionDate) {
        this.questionDate = questionDate;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "QuestionInfo{" +
                "questionInfoId='" + questionInfoId + '\'' +
                ", questionTitle='" + questionTitle + '\'' +
                ", questionContent='" + questionContent + '\'' +
                ", questionPic='" + questionPic + '\'' +
                ", answerInfo=" + answerInfo +
                ", questionDate=" + questionDate +
                ", subject='" + subject + '\'' +
                '}';
    }
}
