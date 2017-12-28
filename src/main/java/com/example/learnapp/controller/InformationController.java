package com.example.learnapp.controller;

import com.example.learnapp.exception.RegisterException;
import com.example.learnapp.pojo.ResponseInfo;
import com.example.learnapp.tools.DateUtils;
import com.example.learnapp.vo.UserVo;
import com.example.learnapp.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class InformationController {

    private InformationService informationService;
    @Autowired
    public void setInformationService(InformationService informationService) {
        this.informationService = informationService;
    }

    /**
     * 根据用户id获取用户的信息
     * @param userInfoId 用户唯一标识ID
     * @return
     */
    @RequestMapping(value = "/get/userInfo/{userInfoId}")
    public Map getUserInfo(@PathVariable String userInfoId){
        Map userInfoMap = new HashMap();
        UserVo userVo = informationService.getInformation(userInfoId);
        userInfoMap.put("code", 1);
        userInfoMap.put("userVo", userVo);
        return userInfoMap;
    }

    /**
     * 更新用户信息接口
     * @param userInfoId 用户唯一标识id
     * @param userName 用户名
     * @param sex 性别
     * @param birthday 生日
     * @param grade 年级
     * @param qqNumber qq号
     * @param wechatNumber 微信号
     * @param signature 个性签名
     * @param userPic 用户头像
     * @param subject 老师的科目
     * @return
     * @throws RegisterException
     */
    @RequestMapping(value = "/update/userInfo/{userInfoId}")
    public ResponseInfo updateUserInfo(@PathVariable String userInfoId,
                                       @RequestParam(value = "userName", required = false) String userName,
                                       @RequestParam(value = "sex", required = false) String sex,
                                       @RequestParam(value = "birthday", required = false) String birthday,
                                       @RequestParam(value = "grade", required = false) String grade,
                                       @RequestParam(value = "qqNumber", required = false) String qqNumber,
                                       @RequestParam(value = "wechatNumber", required = false) String wechatNumber,
                                       @RequestParam(value = "signature", required = false) String signature,
                                       @RequestParam(value = "userPic", required = false) String userPic,
                                       @RequestParam(value = "subject", required = false) String subject) throws RegisterException {

        Date date = null;
        if (StringUtils.hasLength(birthday)){
            try {
                date = DateUtils.stringToDate(birthday);
            } catch (ParseException e) {
                throw new RegisterException("日期转化失败，导致注册失败" + e);
            }
        }

        informationService.updateInformation(userInfoId, userName, sex, date, grade, qqNumber, wechatNumber, signature, userPic, subject);
        return  new ResponseInfo(ResponseInfo.OK, "更新个人信息成功");

    }

}
