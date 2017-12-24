package com.example.learnapp.controller;

import com.example.learnapp.entity.AnswerInfo;
import com.example.learnapp.entity.QuestionInfo;
import com.example.learnapp.entity.UserInfo;
import com.example.learnapp.pojo.ResponseInfo;
import com.example.learnapp.repository.UserInfoRepository;
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

    @PostMapping(value = "/answer")
    public ResponseInfo answerQuestion(@RequestParam("questionInfoId") String questionInfoId,
                                       @RequestParam("content") String content,
                                       @RequestParam("userInfoId") String userInfoId){
        answerQuestionService.answerQuestion(questionInfoId, content, userInfoId);
        return new ResponseInfo(ResponseInfo.OK, "回答问题成功");
    }

    /**
     * 学生获得已经回答问题的列表
     * @return
     */
    @PostMapping(value = "/student/getAnswer/{userInfoId}")
    public List<AnswerVo> studentGainAnswer(@PathVariable String userInfoId){
        return answerQuestionService.stuGainAnswer(userInfoId);
    }

    @PostMapping(value = "/teacher/getAnswer/{userInfoId}")
    public List<AnswerVo> teacherGainAnswer(@PathVariable String userInfoId){
        return answerQuestionService.teaGainAnswer(userInfoId);
    }
}
