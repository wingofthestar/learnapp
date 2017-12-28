package com.example.learnapp.vo;

import java.util.Date;

public class CollectionVo {
    private String questionInfoId;
    private String questionTitle;
    private String questionContent;
    private String questionPic;
    private String questionerUserInfoId;
    private Date questionDate;
    private String subject;

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

    public String getQuestionerUserInfoId() {
        return questionerUserInfoId;
    }

    public void setQuestionerUserInfoId(String questionerUserInfoId) {
        this.questionerUserInfoId = questionerUserInfoId;
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
        return "CollectionVo{" +
                "questionInfoId='" + questionInfoId + '\'' +
                ", questionTitle='" + questionTitle + '\'' +
                ", questionContent='" + questionContent + '\'' +
                ", questionPic='" + questionPic + '\'' +
                ", questionerUserInfoId='" + questionerUserInfoId + '\'' +
                ", questionDate=" + questionDate +
                ", subject='" + subject + '\'' +
                '}';
    }
}
