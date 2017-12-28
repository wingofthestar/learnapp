package com.example.learnapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "user_info")
public class UserInfo {
    @Id
    @GeneratedValue(generator = "uuidGenerator")
    @GenericGenerator(name = "uuidGenerator", strategy = "uuid")
    private String userInfoId;
    private String userName;
    private String sex;
    @Temporal(TemporalType.DATE)
    private Date birthday;
    private String grade;
    private String qqNumber;
    private String wechatNumber;
    @Column(name = "signature", columnDefinition = "text")
    private String signature;

    @OneToOne
    @JoinColumn(name = "register_info_id")
    private RegisterInfo registerInfo;
    private String userPic;
    @OneToOne(mappedBy = "userInfo")
    @JsonIgnore
    private Teacher teacher;
    @OneToOne(mappedBy = "userInfo")
    @JsonIgnore
    private Student student;
    @OneToOne(mappedBy = "userInfo")
    @JsonIgnore
    private Admin admin;
    @ManyToMany(mappedBy = "userInfos")
    @JsonIgnore
    private Collection<QuestionInfo> questionInfos;

    private String lastIp;

    @Temporal(TemporalType.TIME)
    private Date visitDate;
    /**
     * 0标记用户状态正常，1标记用户状态异常
     */
    private Integer flag = 0;
    @OneToMany(mappedBy = "userInfo")
    @JsonIgnore
    private Collection<UserLoginInfo> userLoginInfos;

    public UserInfo(String userName, String sex, Date birthday, String grade, String qqNumber,
                    String wechatNumber, String signature, String userPic) {
        this.userName = userName;
        this.sex = sex;
        this.birthday = birthday;
        this.grade = grade;
        this.qqNumber = qqNumber;
        this.wechatNumber = wechatNumber;
        this.signature = signature;
        this.userPic = userPic;
    }

    public UserInfo(String userName, String sex, Date birthday, String grade, String qqNumber, String wechatNumber, String signature, String userPic, Integer flag) {
        this.userName = userName;
        this.sex = sex;
        this.birthday = birthday;
        this.grade = grade;
        this.qqNumber = qqNumber;
        this.wechatNumber = wechatNumber;
        this.signature = signature;
        this.userPic = userPic;
        this.flag = flag;
    }

    public UserInfo() {
    }

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

    public RegisterInfo getRegisterInfo() {
        return registerInfo;
    }

    public void setRegisterInfo(RegisterInfo registerInfo) {
        this.registerInfo = registerInfo;
    }

    public String getUserPic() {
        return userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }


    public Collection<QuestionInfo> getQuestionInfos() {
        return questionInfos;
    }

    public void setQuestionInfos(Collection<QuestionInfo> questionInfos) {
        this.questionInfos = questionInfos;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Collection<UserLoginInfo> getUserLoginInfos() {
        return userLoginInfos;
    }

    public void setUserLoginInfos(Collection<UserLoginInfo> userLoginInfos) {
        this.userLoginInfos = userLoginInfos;
    }

    public String getLastIp() {
        return lastIp;
    }

    public void setLastIp(String lastIp) {
        this.lastIp = lastIp;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userInfoId='" + userInfoId + '\'' +
                ", userName='" + userName + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday=" + birthday +
                ", grade='" + grade + '\'' +
                ", qqNumber='" + qqNumber + '\'' +
                ", wechatNumber='" + wechatNumber + '\'' +
                ", signature='" + signature + '\'' +
                ", userPic='" + userPic + '\'' +
                ", lastIp='" + lastIp + '\'' +
                ", flag=" + flag +
                '}';
    }
}
