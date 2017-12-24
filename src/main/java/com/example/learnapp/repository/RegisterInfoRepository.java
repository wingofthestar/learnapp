package com.example.learnapp.repository;

import com.example.learnapp.entity.RegisterInfo;
import com.example.learnapp.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisterInfoRepository extends JpaRepository<RegisterInfo, String> {

    RegisterInfo findByPhoneNumberAndPassword(Long phoneNumber, String password);
    RegisterInfo findByPhoneNumber(Long phoneNumber);
    RegisterInfo findByRegisterInfoId(String register_info_id);
    RegisterInfo findByUserInfo(UserInfo userInfo);
}
