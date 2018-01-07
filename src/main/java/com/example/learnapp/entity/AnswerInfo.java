package com.example.learnapp.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "answer_info")
public class AnswerInfo {
    @Id
    @GeneratedValue(generator = "uuidGenerator")
    @GenericGenerator(name = "uuidGenerator", strategy = "uuid")
    private String answerInfoId;
    private String answerContent;
    @ManyToOne //多对一关系所有者
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
    @Temporal(TemporalType.DATE)
    private Date answerDate;
    private String answerPic;
    @OneToOne
    @JoinColumn(name = "question_info_id")
    private QuestionInfo questionInfo;

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

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Date getAnswerDate() {
        return answerDate;
    }

    public void setAnswerDate(Date answerDate) {
        this.answerDate = answerDate;
    }

    public QuestionInfo getQuestionInfo() {
        return questionInfo;
    }

    public void setQuestionInfo(QuestionInfo questionInfo) {
        this.questionInfo = questionInfo;
    }

    public String getAnswerPic() {
        return answerPic;
    }

    public void setAnswerPic(String answerPic) {
        this.answerPic = answerPic;
    }

    @Override
    public String toString() {
        return "AnswerInfo{" +
                "answerInfoId='" + answerInfoId + '\'' +
                ", answerContent='" + answerContent + '\'' +
                ", teacher=" + teacher +
                ", answerDate=" + answerDate +
                ", answerPic='" + answerPic + '\'' +
                '}';
    }
}