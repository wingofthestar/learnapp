package com.example.learnapp.service;

import com.example.learnapp.entity.*;
import com.example.learnapp.exception.RegisterException;
import com.example.learnapp.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterService {

    private static final Logger logger = LoggerFactory.getLogger(RegisterService.class);
    private RegisterInfoRepository registerInfoRepository;
    private UserInfoRepository userInfoRepository;
    private StudentRepository studentRepository;
    private TeacherRepository teacherRepository;
    private AdminRepository adminRepository;

    @Autowired
    public void setRegisterInfoRepository(RegisterInfoRepository registerInfoRepository) {
        this.registerInfoRepository = registerInfoRepository;
    }
    @Autowired
    public void setUserInfoRepository(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }
    @Autowired
    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    @Autowired
    public void setTeacherRepository(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }
    @Autowired
    public void setAdminRepository(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Transactional
    public void register(RegisterInfo registerInfo, UserInfo userInfo, String subject) throws RegisterException {
        logger.info("开始注册用户信息");
        RegisterInfo registerInfoUser =  registerInfoRepository.findByPhoneNumber(registerInfo.getPhoneNumber());
        if (registerInfoUser != null){
            throw new RegisterException("手机号"+registerInfo.getPhoneNumber() + "已经被注册");
        }

        if(registerInfo.getRole() == 1){
            Student student = new Student();
            student.setUserInfo(userInfo);
            studentRepository.save(student);
        }else if (registerInfo.getRole() == 2){
            Teacher teacher = new Teacher();
            teacher.setSubject(subject);
            teacher.setUserInfo(userInfo);
            teacherRepository.save(teacher);
        }else if (registerInfo.getRole() == 3){
            Admin admin = new Admin();
            admin.setUserInfo(userInfo);
            adminRepository.save(admin);
        }
        try {
            registerInfoRepository.save(registerInfo);
            userInfoRepository.save(userInfo);
        }catch (Exception e){
            logger.error("用户注册失败");
            throw new RegisterException("用户注册失败" + e);
        }
        logger.info("用户注册成功");
    }

}
