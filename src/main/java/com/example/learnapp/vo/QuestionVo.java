package com.example.learnapp.vo;

import java.util.Date;

public class QuestionVo {
    private String questionInfoId;
    private String questionTitle;
    private String questionContent;
    private String questionPic;
    private Date questionDate;
    private String subject;
    private String questionUserId;

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

    public String getQuestionUserId() {
        return questionUserId;
    }

    public void setQuestionUserId(String questionUserId) {
        this.questionUserId = questionUserId;
    }

    @Override
    public String toString() {
        return "QuestionVo{" +
                "questionInfoId='" + questionInfoId + '\'' +
                ", questionTitle='" + questionTitle + '\'' +
                ", questionContent='" + questionContent + '\'' +
                ", questionPic='" + questionPic + '\'' +
                ", questionDate=" + questionDate +
                ", subject='" + subject + '\'' +
                ", questionUserId='" + questionUserId + '\'' +
                '}';
    }
}
