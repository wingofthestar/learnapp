package com.example.learnapp.service;

import com.example.learnapp.entity.RegisterInfo;
import com.example.learnapp.entity.UserInfo;
import com.example.learnapp.entity.UserLoginInfo;
import com.example.learnapp.exception.LoginException;
import com.example.learnapp.repository.RegisterInfoRepository;
import com.example.learnapp.repository.UserInfoRepository;
import com.example.learnapp.repository.UserLoginInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class LoginAndLogoutService {
    private static final Logger logger = LoggerFactory.getLogger(LoginAndLogoutService.class);
    private RegisterInfoRepository registerInfoRepository;
    private UserLoginInfoRepository userLoginInfoRepository;
    private UserInfoRepository userInfoRepository;
    @Autowired
    public void setRegisterInfoRepository(RegisterInfoRepository registerInfoRepository) {
        this.registerInfoRepository = registerInfoRepository;
    }
    @Autowired
    public void setUserLoginInfoRepository(UserLoginInfoRepository userLoginInfoRepository) {
        this.userLoginInfoRepository = userLoginInfoRepository;
    }
    @Autowired
    public void setUserInfoRepository(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    @Transactional
    public RegisterInfo LoginCheck(String ip, Long phoneNumber, String password) throws LoginException {
       RegisterInfo registerInfo =  registerInfoRepository.findByPhoneNumberAndPassword(phoneNumber, password);
       if (registerInfo != null){
           UserInfo userInfo = registerInfo.getUserInfo();
           userInfo.setLastIp(ip);
           userInfo.setVisitDate(new Date());
           userInfoRepository.save(userInfo);
           UserLoginInfo userLoginInfo = new UserLoginInfo(userInfo, ip, new Date());
           userLoginInfoRepository.save(userLoginInfo);
           return registerInfo;
       }else{
           throw new LoginException("用户或密码错误");
       }
    }
}
