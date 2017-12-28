package com.example.learnapp.service;

import com.example.learnapp.entity.QuestionInfo;
import com.example.learnapp.entity.UserInfo;
import com.example.learnapp.pojo.ResponseInfo;
import com.example.learnapp.repository.QuestionInfoRepository;
import com.example.learnapp.repository.UserInfoRepository;
import com.example.learnapp.vo.CollectionVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CollectService {
    private static final Logger logger = LoggerFactory.getLogger(CollectService.class);
    private UserInfoRepository userInfoRepository;
    private QuestionInfoRepository questionInfoRepository;
    @Autowired
    public void setUserInfoRepository(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }
    @Autowired
    public void setQuestionInfoRepository(QuestionInfoRepository questionInfoRepository) {
        this.questionInfoRepository = questionInfoRepository;
    }

    @Transactional
    public ResponseInfo addCollection(String userInfoId, String questionId){
        UserInfo userInfo = userInfoRepository.findByUserInfoId(userInfoId);
        QuestionInfo questionInfo = questionInfoRepository.findByQuestionInfoId(questionId);
        userInfo.getQuestionInfos().add(questionInfo);
        questionInfo.getUserInfos().add(userInfo);
        userInfoRepository.save(userInfo);
        questionInfoRepository.save(questionInfo);
        logger.info("用户:" + userInfoId + "收藏问题:" + questionId + "成功");
        return new ResponseInfo(ResponseInfo.OK, "收藏成功");
    }

    @Transactional
    public ResponseInfo removeCollection(String userInfoId, String questionInfoId) {
        UserInfo userInfo = userInfoRepository.findByUserInfoId(userInfoId);
        QuestionInfo questionInfo = questionInfoRepository.findByQuestionInfoId(questionInfoId);
        userInfo.getQuestionInfos().remove(questionInfo);
        questionInfo.getUserInfos().remove(userInfo);
        userInfoRepository.save(userInfo);
        questionInfoRepository.save(questionInfo);
        logger.info("用户:" + userInfoId + "删除收藏的问题:" + questionInfoId + "成功");
        return new ResponseInfo(ResponseInfo.OK, "删除收藏成功");
    }

    public List showCollection(String userInfoId) {
        UserInfo userInfo = userInfoRepository.findByUserInfoId(userInfoId);
        List<QuestionInfo> collectionList = (List) userInfo.getQuestionInfos();
        List<CollectionVo> collectionVos= new ArrayList<>();
        for (QuestionInfo questionInfo: collectionList){
            CollectionVo collectionVo = new CollectionVo();
            BeanUtils.copyProperties(questionInfo, collectionVo);
            collectionVo.setQuestionerUserInfoId(questionInfo.getStudent().getUserInfo().getUserInfoId());
            collectionVos.add(collectionVo);
        }
        return collectionVos;
    }
}
