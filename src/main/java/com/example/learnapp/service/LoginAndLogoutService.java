package com.example.learnapp.service;

import com.example.learnapp.entity.RegisterInfo;
import com.example.learnapp.exception.LoginException;
import com.example.learnapp.repository.RegisterInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginAndLogoutService {
    private static final Logger logger = LoggerFactory.getLogger(LoginAndLogoutService.class);
    private RegisterInfoRepository registerInfoRepository;
    @Autowired
    public void setRegisterInfoRepository(RegisterInfoRepository registerInfoRepository) {
        this.registerInfoRepository = registerInfoRepository;
    }

    @Transactional
    public RegisterInfo LoginCheck(Long phoneNumber, String password) throws LoginException {
       RegisterInfo registerInfo =  registerInfoRepository.findByPhoneNumberAndPassword(phoneNumber, password);
       if (registerInfo != null){
           return registerInfo;
       }else{
           throw new LoginException("用户或密码错误");
       }
    }

}
