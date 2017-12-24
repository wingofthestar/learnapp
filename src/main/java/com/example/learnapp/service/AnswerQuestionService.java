package com.example.learnapp.service;

import com.example.learnapp.entity.AnswerInfo;
import com.example.learnapp.entity.QuestionInfo;
import com.example.learnapp.entity.Teacher;
import com.example.learnapp.entity.UserInfo;
import com.example.learnapp.repository.AnswerInfoRepository;
import com.example.learnapp.repository.QuestionInfoRepository;
import com.example.learnapp.repository.TeacherRepository;
import com.example.learnapp.repository.UserInfoRepository;
import com.example.learnapp.vo.AnswerVo;
import com.example.learnapp.vo.QuestionVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
public class AnswerQuestionService {

    private AnswerInfoRepository answerInfoRepository;
    private QuestionInfoRepository questionInfoRepository;
    private UserInfoRepository userInfoRepository;
    private TeacherRepository teacherRepository;
    @Autowired
    public void setAnswerInfoRepository(AnswerInfoRepository answerInfoRepository) {
        this.answerInfoRepository = answerInfoRepository;
    }
    @Autowired
    public void setQuestionInfoRepository(QuestionInfoRepository questionInfoRepository) {
        this.questionInfoRepository = questionInfoRepository;
    }
    @Autowired
    public void setUserInfoRepository(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }
    @Autowired
    public void setTeacherRepository(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Transactional
    public void answerQuestion(String questionInfoId, String content, String userInfoId){
        AnswerInfo answerInfo = new AnswerInfo();
        answerInfo.setAnswerContent(content);
        QuestionInfo questionInfo = questionInfoRepository.findByQuestionInfoId(questionInfoId);
        answerInfo.setQuestionInfo(questionInfo);
        UserInfo userInfo = userInfoRepository.findByUserInfoId(userInfoId);
        Teacher teacher = userInfo.getTeacher();
        teacher.setAnswerNumber(teacher.getAnswerNumber()+1);
        answerInfo.setTeacher(teacher);
        answerInfo.setAnswerDate(new Date());
        answerInfoRepository.save(answerInfo);
        teacherRepository.save(teacher);
    }

    public List<AnswerVo> stuGainAnswer(String userInfoId){
        Collection<QuestionInfo> questionInfos =  userInfoRepository.findByUserInfoId(userInfoId).getStudent().getQuestionInfos();
        List<AnswerVo> answerVos = new ArrayList<>();
        for (QuestionInfo questionInfo:questionInfos) {
            AnswerInfo answerInfo = questionInfo.getAnswerInfo();
            if (answerInfo != null){
                AnswerVo answerVo = new AnswerVo();
                BeanUtils.copyProperties(answerInfo, answerVo);
                answerVo.setAnswerUserInfoId(answerInfo.getTeacher().getTeacherId());
                answerVo.setAnswerUserName(answerInfo.getTeacher().getUserInfo().getUserName());
                answerVo.setSubject(answerInfo.getTeacher().getSubject());
                QuestionVo questionVo = new QuestionVo();
                BeanUtils.copyProperties(answerInfo.getQuestionInfo(), questionVo);
                questionVo.setQuestionUserId(answerInfo.getQuestionInfo().getStudent().getUserInfo().getUserInfoId());
                answerVo.setQuestionVo(questionVo);
                answerVos.add(answerVo);
            }
        }
        return answerVos;
    }

    public List<AnswerVo> teaGainAnswer(String userInfoId) {
        Collection<AnswerInfo> answerInfos = userInfoRepository.findByUserInfoId(userInfoId).getTeacher().getAnswerInfos();
        List<AnswerVo> answerVos = new ArrayList<>();
        for(AnswerInfo answerInfo: answerInfos){
            AnswerVo answerVo = new AnswerVo();
            BeanUtils.copyProperties(answerInfo, answerVo);
            answerVo.setAnswerUserInfoId(answerInfo.getTeacher().getTeacherId());
            answerVo.setAnswerUserName(answerInfo.getTeacher().getUserInfo().getUserName());
            answerVo.setSubject(answerInfo.getTeacher().getSubject());
            QuestionVo questionVo = new QuestionVo();
            BeanUtils.copyProperties(answerInfo.getQuestionInfo(), questionVo);
            questionVo.setQuestionUserId(answerInfo.getQuestionInfo().getStudent().getUserInfo().getUserInfoId());
            answerVo.setQuestionVo(questionVo);
            answerVos.add(answerVo);
        }
        return answerVos;
    }
}
