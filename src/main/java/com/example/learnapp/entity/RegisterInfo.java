package com.example.learnapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "register_info")
public class RegisterInfo {
    @Id
    @GeneratedValue(generator = "uuidGenerator")
    @GenericGenerator(name = "uuidGenerator", strategy = "uuid")
    @Column(name = "register_info_id")
    private String registerInfoId;
    private Long phoneNumber;
    private String password;
    private String email;
    private Integer role;
    @OneToOne(mappedBy = "registerInfo")
    @JsonIgnore //防止陷入死循环
    private UserInfo userInfo;

    public RegisterInfo(Long phoneNumber, String password, String email, Integer role) {
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public RegisterInfo() {
    }

    public String getRegisterInfoId() {
        return registerInfoId;
    }

    public void setRegisterInfoId(String registerInfoId) {
        this.registerInfoId = registerInfoId;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public String toString() {
        return "RegisterInfo{" +
                "registerInfoId='" + registerInfoId + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }
}
