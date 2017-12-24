package com.example.learnapp.pojo;

public class Login {
    private Long phoneNumber;
    private String password;

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

    @Override
    public String toString() {
        return "login{" +
                "phoneNumber=" + phoneNumber +
                ", password='" + password + '\'' +
                '}';
    }
}
