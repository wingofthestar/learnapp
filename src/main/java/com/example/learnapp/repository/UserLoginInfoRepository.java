package com.example.learnapp.repository;

import com.example.learnapp.entity.UserLoginInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLoginInfoRepository extends JpaRepository<UserLoginInfo, String> {
}
