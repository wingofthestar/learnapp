package com.example.learnapp.controller;

import com.example.learnapp.pojo.ResponseInfo;
import com.example.learnapp.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 收藏功能控制器
 */
@RestController
public class CollectionController {

    private CollectService collectService;
    @Autowired
    public void setCollectService(CollectService collectService) {
        this.collectService = collectService;
    }

    /**
     * 用户添加收藏
     * @param userInfoId 用户唯一标识id
     * @param questionInfoId 问题唯一标识id
     * @return
     */
    @RequestMapping("/addCollection")
    public ResponseInfo addCollectQuestion(@RequestParam("userInfoId") String userInfoId,
                                        @RequestParam("questionInfoId") String questionInfoId){
       return collectService.addCollection(userInfoId, questionInfoId);
    }

    /**
     * 用户删除收藏
     * @param userInfoId 用户唯一标识id
     * @param questionInfoId 问题唯一标识id
     * @return
     */
    @RequestMapping("/removeCollection")
    public ResponseInfo removeCollectionQuestion(@RequestParam("userInfoId") String userInfoId,
                                                 @RequestParam("questionInfoId") String questionInfoId){
        return collectService.removeCollection(userInfoId, questionInfoId);
    }

    /**
     * 获取用户的收藏列表
     * @param userInfoId 用户唯一标识id
     * @return 用户收藏列表
     */
    @RequestMapping("/showCollection")
    public List showCollectionQuestion(@RequestParam("userInfoId") String userInfoId){
        return collectService.showCollection(userInfoId);
    }
}
