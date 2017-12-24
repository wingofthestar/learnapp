package com.example.learnapp.controller;

import com.example.learnapp.entity.RegisterInfo;
import com.example.learnapp.entity.UserInfo;
import com.example.learnapp.exception.RegisterException;
import com.example.learnapp.pojo.ResponseInfo;
import com.example.learnapp.service.RegisterService;
import com.example.learnapp.tools.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Date;

@RestController
public class RegisterController {

    private RegisterService registerService;
    @Autowired
    public void setRegisterService(RegisterService registerService) {
        this.registerService = registerService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseInfo register(@RequestParam("role") int role, @RequestParam("phoneNumber") long phoneNumber,
                                 @RequestParam("password") String password, @RequestParam("email") String email,
                                 @RequestParam("userName") String userName, @RequestParam("sex") String sex,
                                 @RequestParam("birthday") String birthday,
                                 @RequestParam(value = "subject", required = false) String subject,
                                 @RequestParam("grader") String grader,
                                 @RequestParam(value = "qqNumber", required = false) String qqNumber,
                                 @RequestParam(value = "wechatNumber", required = false) String wechatNumber,
                                 @RequestParam(value = "signature", required = false) String signature)
            throws RegisterException {
        Date date = null;
        try {
            date = DateUtils.stringToDate(birthday);
        } catch (ParseException e) {
            throw new RegisterException("日期转化失败，导致注册失败" + e);
        }
        //教师角色
        if(role != 1){
            if(subject == null){
                throw new RegisterException("注册失败，subject信息未填写");
            }
        }

        RegisterInfo registerInfo = new RegisterInfo(phoneNumber, password, email, role);
        String pic= "";
        UserInfo userInfo = new UserInfo(userName, sex, date, grader, qqNumber, wechatNumber, signature, pic);
        userInfo.setRegisterInfo(registerInfo);

        registerService.register(registerInfo, userInfo, subject);
        return new ResponseInfo(ResponseInfo.OK, "注册成功");
    }
}
