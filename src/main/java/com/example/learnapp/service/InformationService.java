package com.example.learnapp.service;

import com.example.learnapp.entity.RegisterInfo;
import com.example.learnapp.entity.Teacher;
import com.example.learnapp.entity.UserInfo;
import com.example.learnapp.repository.RegisterInfoRepository;
import com.example.learnapp.repository.TeacherRepository;
import com.example.learnapp.vo.UserVo;
import com.example.learnapp.repository.UserInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;

@Service
public class InformationService {
    private static final Logger logger = LoggerFactory.getLogger(InformationService.class);
    private UserInfoRepository userInfoRepository;
    private RegisterInfoRepository registerInfoRepository;
    private TeacherRepository teacherRepository;
    @Autowired
    public void setUserInfoRepository(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }
    @Autowired
    public void setRegisterInfoRepository(RegisterInfoRepository registerInfoRepository) {
        this.registerInfoRepository = registerInfoRepository;
    }
    @Autowired
    public void setTeacherRepository(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public UserVo getInformation(String userInfoId){
        UserInfo userInfo = userInfoRepository.findByUserInfoId(userInfoId);
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(userInfo, userVo);
        if (userInfo.getRegisterInfo().getRole() != 1){
            userVo.setSubject(userInfo.getTeacher().getSubject());

        }
        return userVo;
    }

    /**
     * 更新用户信息
     * @param userInfoId
     * @param userName
     * @param sex
     * @param birthday
     * @param grade
     * @param qqNumber
     * @param wechatNumber
     * @param signature
     * @param userPic
     * @param subject
     */
    @Transactional
    public void updateInformation(String userInfoId, String userName, String sex, Date birthday,
                                  String grade, String qqNumber, String wechatNumber, String signature,
                                  String userPic, String subject){
        UserInfo userInfo = userInfoRepository.findByUserInfoId(userInfoId);
        RegisterInfo registerInfo = userInfo.getRegisterInfo();
//        RegisterInfo registerInfo = registerInfoRepository.findByUserInfo(userInfo);
        if(StringUtils.hasLength(userName)){
            userInfo.setUserName(userName);
        }
        if(StringUtils.hasLength(sex)){
            userInfo.setSex(sex);
        }
        if (birthday != null){
            userInfo.setBirthday(birthday);
        }
        if (StringUtils.hasLength(grade)){
            userInfo.setGrade(grade);
        }
        if(StringUtils.hasLength(qqNumber)){
            userInfo.setQqNumber(qqNumber);
        }
        if(StringUtils.hasLength(wechatNumber)){
            userInfo.setWechatNumber(wechatNumber);
        }
        if(StringUtils.hasLength(signature)){
            userInfo.setSignature(signature);
        }
        if (StringUtils.hasLength(signature)){
            userInfo.setSignature(signature);
        }
        if(StringUtils.hasLength(userPic)){
            userInfo.setUserPic(userPic);
        }
        if (registerInfo.getRole() != 1 && StringUtils.hasLength(subject)){
            Teacher teacher = userInfo.getTeacher();
            teacher.setSubject(subject);
            teacherRepository.save(teacher);
        }
        userInfoRepository.save(userInfo);
        logger.info("更新用户:" + userInfoId + "成功");
    }

}
