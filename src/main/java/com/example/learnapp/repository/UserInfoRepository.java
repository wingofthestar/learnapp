package com.example.learnapp.repository;

import com.example.learnapp.entity.RegisterInfo;
import com.example.learnapp.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, String> {

    UserInfo findByUserInfoId(String userInfoId);
}
