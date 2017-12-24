package com.example.learnapp.controller;

import com.example.learnapp.pojo.ResponseInfo;
import com.example.learnapp.service.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MarkController {

    private MarkService markService;
    @Autowired
    public void setMarkService(MarkService markService) {
        this.markService = markService;
    }

    @PostMapping("/mark")
    public ResponseInfo giveMark(@RequestParam("userInfoId") String userInfoId,
                                 @RequestParam("point") int point){
        markService.giveMark(userInfoId, point);
        return new ResponseInfo(ResponseInfo.OK, "评分成功");
    }
}
