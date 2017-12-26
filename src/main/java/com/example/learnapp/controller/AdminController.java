package com.example.learnapp.controller;

import com.example.learnapp.service.AdminService;
import com.example.learnapp.vo.UserManageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private AdminService adminService;
    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    @RequestMapping("/manage")
    public void manageUser(@RequestParam("userInfoId") String userInfoId){
        adminService.managerUser(userInfoId);
    }


    @RequestMapping("/lookOver")
    public List<UserManageVo> LookOverUser(){
        return adminService.lookOverUser();
    }
}
