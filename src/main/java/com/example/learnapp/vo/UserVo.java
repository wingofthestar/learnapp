package com.example.learnapp.vo;

import java.util.Date;

public class UserVo {
    private String userInfoId;
    private String userName;
    private String sex;
    private Date birthday;
    private String grade;
    private String qqNumber;
    private String wechatNumber;
    private String signature;
    private String subject;
    private Integer mark;

    public String getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(String userInfoId) {
        this.userInfoId = userInfoId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getQqNumber() {
        return qqNumber;
    }

    public void setQqNumber(String qqNumber) {
        this.qqNumber = qqNumber;
    }

    public String getWechatNumber() {
        return wechatNumber;
    }

    public void setWechatNumber(String wechatNumber) {
        this.wechatNumber = wechatNumber;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "UserVo{" +
                "userInfoId='" + userInfoId + '\'' +
                ", userName='" + userName + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday=" + birthday +
                ", grade='" + grade + '\'' +
                ", qqNumber='" + qqNumber + '\'' +
                ", wechatNumber='" + wechatNumber + '\'' +
                ", signature='" + signature + '\'' +
                ", subject='" + subject + '\'' +
                ", mark=" + mark +
                '}';
    }
}
