package com.example.learnapp.controller;

import com.example.learnapp.entity.QuestionInfo;
import com.example.learnapp.exception.GradeSwitchException;
import com.example.learnapp.pojo.ResponseInfo;
import com.example.learnapp.service.QuestionService;
import com.example.learnapp.vo.QuestionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QuestionController {

    private QuestionService questionService;

    @Autowired
    public void setQuestionService(QuestionService questionService) {
        this.questionService = questionService;
    }

    /**
     * 学生提问接口
     * @param questionPic
     * @param subject
     * @param questionTitle
     * @param questionContent
     * @param userInfoId
     * @return
     */
    @PostMapping(value = "/askQuestion")
    public ResponseInfo saveQuestion(@RequestParam(value = "pic", required = false) String questionPic,
                                     @RequestParam("subject") String subject,
                                     @RequestParam("title") String questionTitle,
                                     @RequestParam("content") String questionContent,
                                     @RequestParam("userInfoId") String userInfoId
                            ){
        questionService.saveQuestion(questionPic, subject, questionTitle, questionContent, userInfoId);
        return new ResponseInfo(ResponseInfo.OK, "提问成功");
    }

    /**
     * 展示所有问题接口
     * @return
     */
    @RequestMapping(value = "/showAllQuestion")
    public List<QuestionInfo> showALLQuestion(){
        List<QuestionInfo> questionInfos =  questionService.showAllQuestion();
        return questionInfos;
    }


    /**
     * 根据老师的学科年级来展示问题列表
     * @param userInfoId
     * @return
     * @throws GradeSwitchException
     */
    @PostMapping(value = "/showQuestionBySubjectAndGrade/{userInfoId}")
    public List<QuestionVo> showQuestionBySubjectAndGrade(@PathVariable String userInfoId) throws GradeSwitchException {
        return questionService.showByGradeAndSubject(userInfoId);
    }

    /**
     * 在学生问题页面，展示所有学生提出的问题
     * @param userInfoId
     * @return
     */
    @RequestMapping(value = "/showMyQuestionAll/{userInfoId}")
    public List<QuestionVo> showMyQuestionAll(@PathVariable String userInfoId){
        return questionService.getMyAllQuestion(userInfoId);
    }

}
