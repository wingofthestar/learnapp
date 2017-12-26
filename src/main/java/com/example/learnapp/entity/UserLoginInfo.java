package com.example.learnapp.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_login_info")
public class UserLoginInfo {

    @Id
    @GeneratedValue(generator = "uuidGenerator")
    @GenericGenerator(name = "uuidGenerator", strategy = "uuid")
    private String userLoginInfoId;

    @ManyToOne
    @JoinColumn(name = "user_info_id")
    private UserInfo userInfo;

    private String ip;

    @Temporal(TemporalType.TIME)
    private Date loginDatetime;

    public UserLoginInfo(UserInfo userInfo, String ip, Date loginDatetime) {
        this.userInfo = userInfo;
        this.ip = ip;
        this.loginDatetime = loginDatetime;
    }

    public UserLoginInfo() {

    }

    public String getUserLoginInfoId() {
        return userLoginInfoId;
    }

    public void setUserLoginInfoId(String userLoginInfoId) {
        this.userLoginInfoId = userLoginInfoId;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getLoginDatetime() {
        return loginDatetime;
    }

    public void setLoginDatetime(Date loginDatetime) {
        this.loginDatetime = loginDatetime;
    }

}
