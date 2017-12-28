package com.example.learnapp.service;

import com.example.learnapp.entity.Teacher;
import com.example.learnapp.entity.UserInfo;
import com.example.learnapp.repository.TeacherRepository;
import com.example.learnapp.repository.UserInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MarkService {
    private static final Logger logger = LoggerFactory.getLogger(MarkService.class);
    private TeacherRepository teacherRepository;
    private UserInfoRepository userInfoRepository;
    @Autowired
    public void setTeacherRepository(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }
    @Autowired
    public void setUserInfoRepository(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    @Transactional
    public void giveMark(String userInfoId, int points){
        UserInfo userInfo = userInfoRepository.findByUserInfoId(userInfoId);
        Teacher teacher = userInfo.getTeacher();
        teacher.setPoints(teacher.getPoints()+points);
        teacherRepository.save(teacher);
        logger.info("学生" + "给教师:" + userInfoId + "评分成功");
    }
}
