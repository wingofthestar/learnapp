package com.example.learnapp.service;

import com.example.learnapp.entity.QuestionInfo;
import com.example.learnapp.entity.Student;
import com.example.learnapp.entity.UserInfo;
import com.example.learnapp.exception.GradeSwitchException;
import com.example.learnapp.repository.QuestionInfoRepository;
import com.example.learnapp.repository.StudentRepository;
import com.example.learnapp.repository.UserInfoRepository;
import com.example.learnapp.tools.GradeUtils;
import com.example.learnapp.vo.QuestionVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class QuestionService {

    private QuestionInfoRepository questionInfoRepository;
    private UserInfoRepository userInfoRepository;
    private StudentRepository studentRepository;
    @Autowired
    public void setQuestionInfoRepository(QuestionInfoRepository questionInfoRepository) {
        this.questionInfoRepository = questionInfoRepository;
    }
    @Autowired
    public void setUserInfoRepository(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }
    @Autowired
    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Transactional
    public void saveQuestion(String pic, String subject, String questionTitle, String questionContent, String userInfoId){
        String filePath = pic;
        QuestionInfo questionInfo = new QuestionInfo();
        questionInfo.setQuestionPic(filePath);
        questionInfo.setQuestionTitle(questionTitle);
        questionInfo.setQuestionContent(questionContent);
        questionInfo.setQuestionDate(new Date());
        questionInfo.setSubject(subject);
        UserInfo userInfo = userInfoRepository.findByUserInfoId(userInfoId);
        Student student = userInfo.getStudent();
        questionInfo.setStudent(student);
        questionInfoRepository.save(questionInfo);
        student.setQuestionNumber(student.getQuestionNumber() + 1);
        studentRepository.save(student);
    }

    public List<QuestionInfo> showAllQuestion(){
        List<QuestionInfo> questionInfos =  questionInfoRepository.findAll();
        return questionInfos;
    }

    public List<QuestionVo> showByGradeAndSubject(String userInfoId) throws GradeSwitchException {
        UserInfo userInfo = userInfoRepository.findByUserInfoId(userInfoId);
        String Grade =  userInfo.getGrade();
        String subject = userInfo.getTeacher().getSubject();

        List<QuestionVo> resultQuestionInfos = new ArrayList<>();
        List<QuestionInfo> questionInfos = questionInfoRepository.findBySubject(subject);
        //取出提出问题学生的年级，将老师的年级与学生年级相比较，只将低于老师年级的问题展示给老师
        for (QuestionInfo questionInfo:questionInfos) {
            if(GradeUtils.GradeCompare(Grade, questionInfo.getStudent().getUserInfo().getGrade()) >= 0){
                QuestionVo questionVo = new QuestionVo();
                BeanUtils.copyProperties(questionInfo, questionVo);
                questionVo.setQuestionUserId(questionInfo.getStudent().getStudentId());
                resultQuestionInfos.add(questionVo);
            }
        }
        return resultQuestionInfos;
    }

    public List<QuestionVo> getMyAllQuestion(String userInfoId){
        UserInfo userInfo = userInfoRepository.findByUserInfoId(userInfoId);
        List<QuestionInfo> questionInfos  = (List<QuestionInfo>) userInfo.getStudent().getQuestionInfos();
        List<QuestionVo> questionVos = new ArrayList<>();
        for (QuestionInfo questionInfo: questionInfos){
            QuestionVo questionVo = new QuestionVo();
            questionVo.setQuestionUserId(questionInfo.getStudent().getUserInfo().getUserInfoId());
            BeanUtils.copyProperties(questionInfo, questionVo);
            questionVos.add(questionVo);
        }
        return questionVos;
    }
}
