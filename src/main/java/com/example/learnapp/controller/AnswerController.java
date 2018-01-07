package com.example.learnapp.controller;

import com.example.learnapp.pojo.ResponseInfo;
import com.example.learnapp.service.AnswerQuestionService;
import com.example.learnapp.vo.AnswerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AnswerController {

    private AnswerQuestionService answerQuestionService;
    @Autowired
    public void setAnswerQuestionService(AnswerQuestionService answerQuestionService) {
        this.answerQuestionService = answerQuestionService;
    }

    /**
     * 教师回答问题接口
     * @param questionInfoId 问题唯一标识id
     * @param content   回答的内容
     * @param userInfoId 用户唯一标识id
     * @return
     */
    @PostMapping(value = "/answer")
    public ResponseInfo answerQuestion(@RequestParam("questionInfoId") String questionInfoId,
                                       @RequestParam("content") String content,
                                       @RequestParam("userInfoId") String userInfoId,
                                       @RequestParam(value = "answerPic", required = false) String answerPic){
        answerQuestionService.answerQuestion(questionInfoId, content, userInfoId, answerPic);
        return new ResponseInfo(ResponseInfo.OK, "回答问题成功");
    }

    /**
     * 学生获得已经回答问题的列表
     * @return 得到回答的问题列表
     */
    @PostMapping(value = "/student/getAnswer/{userInfoId}")
    public List<AnswerVo> studentGainAnswer(@PathVariable String userInfoId){
        return answerQuestionService.stuGainAnswer(userInfoId);
    }

    /**
     * 教师查看自己回答的问题列表
     * @param userInfoId 唯一用户表示id
     * @return 该教师已经回答的问题列表
     */
    @PostMapping(value = "/teacher/getAnswer/{userInfoId}")
    public List<AnswerVo> teacherGainAnswer(@PathVariable String userInfoId){
        return answerQuestionService.teaGainAnswer(userInfoId);
    }
}
