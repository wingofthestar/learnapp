package com.example.learnapp.vo;

import java.util.Date;

public class AnswerVo {
    private String answerInfoId;
    private String answerContent;
    private String answerUserInfoId;
    private String answerUserName;
    private Date answerDate;
    private QuestionVo questionVo;
    private String subject;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setAnswerUserName(String answerUserName) {
        this.answerUserName = answerUserName;
    }

    public String getAnswerUserName() {
        return answerUserName;
    }

    public String getAnswerInfoId() {
        return answerInfoId;
    }

    public void setAnswerInfoId(String answerInfoId) {
        this.answerInfoId = answerInfoId;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    public String getAnswerUserInfoId() {
        return answerUserInfoId;
    }

    public void setAnswerUserInfoId(String answerUserInfoId) {
        this.answerUserInfoId = answerUserInfoId;
    }

    public Date getAnswerDate() {
        return answerDate;
    }

    public void setAnswerDate(Date answerDate) {
        this.answerDate = answerDate;
    }

    public QuestionVo getQuestionVo() {
        return questionVo;
    }

    public void setQuestionVo(QuestionVo questionVo) {
        this.questionVo = questionVo;
    }
}
