package com.example.learnapp.controller;

import com.example.learnapp.pojo.ResponseInfo;
import com.example.learnapp.service.AdminService;
import com.example.learnapp.vo.UserManageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 管理员端控制器
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    private AdminService adminService;
    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    /**
     * 管理用户账户状态接口
     * @param userInfoId 用户唯一标示ID
     * @return
     */
    @RequestMapping("/manage")
    public ResponseInfo manageUser(@RequestParam("userInfoId") String userInfoId){
       return adminService.managerUser(userInfoId);
    }

    /**
     * 查看用户列表接口
     * @return 用户列表
     */
    @RequestMapping("/lookOver")
    public List<UserManageVo> LookOverUser(){
        return adminService.lookOverUser();
    }
}
