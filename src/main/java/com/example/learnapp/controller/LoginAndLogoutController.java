package com.example.learnapp.controller;

import com.example.learnapp.entity.RegisterInfo;
import com.example.learnapp.entity.UserInfo;
import com.example.learnapp.exception.LoginException;
import com.example.learnapp.service.LoginAndLogoutService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginAndLogoutController {

    private LoginAndLogoutService loginAndLogoutService;
    @Autowired
    public void setLoginAndLogoutService(LoginAndLogoutService loginAndLogoutService) {
        this.loginAndLogoutService = loginAndLogoutService;
    }

    @RequestMapping(value = "/login")
    public Map login(@RequestParam("phoneNumber") Long phoneNumber, @RequestParam("password") String password, HttpServletRequest request) throws LoginException {
        String ip = request.getRemoteAddr();
        RegisterInfo registerInfo =  loginAndLogoutService.LoginCheck(ip, phoneNumber, password);
        int role = registerInfo.getRole();
        String userInfoId = registerInfo.getUserInfo().getUserInfoId();
        Map registerInfoMap = new HashMap();
        registerInfoMap.put("code", 1);
        registerInfoMap.put("role", role);
        registerInfoMap.put("userInfoId", userInfoId);
        return registerInfoMap;
    }
}
