package com.example.learnapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "teacher")
public class Teacher {
    @Id
    @GeneratedValue(generator = "uuidGenerator")
    @GenericGenerator(name = "uuidGenerator", strategy = "uuid")
    private String teacherId;
    @Column(name = "points")
    private Integer points = 0;
    private Integer answerNumber = 0;
    @ManyToOne
    @JoinColumn(name = "user_info_id")
    private UserInfo userInfo;
    @OneToMany(mappedBy = "teacher") //关系反方
    @JsonIgnore
    private Collection<AnswerInfo> answerInfos;
    private String subject;

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getAnswerNumber() {
        return answerNumber;
    }

    public void setAnswerNumber(Integer answerNumber) {
        this.answerNumber = answerNumber;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Collection<AnswerInfo> getAnswerInfos() {
        return answerInfos;
    }

    public void setAnswerInfos(Collection<AnswerInfo> answerInfos) {
        this.answerInfos = answerInfos;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId='" + teacherId + '\'' +
                ", points=" + points +
                ", answerNumber=" + answerNumber +
                ", subject='" + subject + '\'' +
                '}';
    }
}
