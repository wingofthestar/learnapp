package com.example.learnapp.service;

import com.example.learnapp.entity.UserInfo;
import com.example.learnapp.pojo.ResponseInfo;
import com.example.learnapp.repository.AdminRepository;
import com.example.learnapp.repository.UserInfoRepository;
import com.example.learnapp.vo.UserManageVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {

    private static final Logger logger = LoggerFactory.getLogger(AdminService.class);
    private AdminRepository adminRepository;
    private UserInfoRepository userInfoRepository;
    @Autowired
    public void setAdminRepository(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }
    @Autowired
    public void setUserInfoRepository(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    /**
     * 封禁用户与解禁用户操作
     * @param userInfoId
     */
    public ResponseInfo managerUser(String userInfoId){
       UserInfo userInfo = userInfoRepository.findByUserInfoId(userInfoId);
       if (userInfo.getFlag() == 0){
           userInfo.setFlag(1);
           userInfoRepository.save(userInfo);
           logger.info("封禁用户:" + userInfoId + "成功");
           return new ResponseInfo(ResponseInfo.OK, "用户封禁");
       }else{
           userInfo.setFlag(0);
           userInfoRepository.save(userInfo);
           logger.info("解封用户" + userInfoId + "成功");
           return new ResponseInfo(ResponseInfo.OK, "用户解封");
       }
    }

    /**
     * 管理员查看用户列表
     * @return 用户列表
     */
    public List<UserManageVo> lookOverUser() {
        List<UserInfo> userInfos = userInfoRepository.findAll();
        List<UserManageVo> userManageVoList = new ArrayList<>();
        for (UserInfo u :userInfos) {
            UserManageVo userManageVo = new UserManageVo();
            BeanUtils.copyProperties(u, userManageVo);
            userManageVo.setPhoneNumber(u.getRegisterInfo().getPhoneNumber());
            userManageVoList.add(userManageVo);
        }
        return userManageVoList;
    }
}
