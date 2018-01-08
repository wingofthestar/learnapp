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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(AnswerQuestionService.class);
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
    public void answerQuestion(String questionInfoId, String content, String userInfoId, String answerPic){
        AnswerInfo answerInfo = new AnswerInfo();
        answerInfo.setAnswerContent(content);
        answerInfo.setAnswerPic(answerPic);

        QuestionInfo questionInfo = questionInfoRepository.findByQuestionInfoId(questionInfoId);
        answerInfo.setQuestionInfo(questionInfo);

        //为了取出教师的内容，教师的回答问题数+1
        UserInfo userInfo = userInfoRepository.findByUserInfoId(userInfoId);
        Teacher teacher = userInfo.getTeacher();
        teacher.setAnswerNumber(teacher.getAnswerNumber()+1);

        answerInfo.setTeacher(teacher);
        answerInfo.setAnswerDate(new Date());
        answerInfoRepository.save(answerInfo);
        teacherRepository.save(teacher);
        logger.info("教师：" + userInfoId + "回答问题：" + questionInfoId + "成功");
    }

    /**
     * 学生获取自己已经被回答的问题列表
     * @param userInfoId 用户唯一标识id(这里标识学生)
     * @return
     */
    public List<AnswerVo> stuGainAnswer(String userInfoId){
        Collection<QuestionInfo> questionInfos =  userInfoRepository.findByUserInfoId(userInfoId).getStudent().getQuestionInfos();
        List<AnswerVo> answerVos = new ArrayList<>();
        for (QuestionInfo questionInfo:questionInfos) {
            AnswerInfo answerInfo = questionInfo.getAnswerInfo();
            if (answerInfo != null){
                AnswerVo answerVo = new AnswerVo();
                BeanUtils.copyProperties(answerInfo, answerVo);
                answerVo.setAnswerUserInfoId(answerInfo.getTeacher().getUserInfo().getUserInfoId());
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

    /**
     * 教师获得自己回答的问题的问题列表
     * @param userInfoId 用户唯一标识ID(这里标识老师)
     * @return
     */
    public List<AnswerVo> teaGainAnswer(String userInfoId) {
        Collection<AnswerInfo> answerInfos = userInfoRepository.findByUserInfoId(userInfoId).getTeacher().getAnswerInfos();
        List<AnswerVo> answerVos = new ArrayList<>();
        for(AnswerInfo answerInfo: answerInfos){
            AnswerVo answerVo = new AnswerVo();
            BeanUtils.copyProperties(answerInfo, answerVo);
            answerVo.setAnswerUserInfoId(answerInfo.getTeacher().getUserInfo().getUserInfoId());
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
