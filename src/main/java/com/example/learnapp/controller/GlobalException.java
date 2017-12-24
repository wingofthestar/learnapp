package com.example.learnapp.controller;

import com.example.learnapp.exception.GradeSwitchException;
import com.example.learnapp.exception.LoginException;
import com.example.learnapp.exception.RegisterException;
import com.example.learnapp.pojo.ResponseInfo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常捕获处理
 */
@ControllerAdvice
public class GlobalException{

    /**
     * 全局处理注册异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = RegisterException.class)
    @ResponseBody
    public ResponseInfo registerExceptionHandle(RegisterException e){
        ResponseInfo r = new ResponseInfo(ResponseInfo.ERROR, e.getMessage());
        return r;
    }

    /**
     * 全局处理登录异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = LoginException.class)
    @ResponseBody
    public ResponseInfo loginExceptionHandle(LoginException e){
        ResponseInfo r = new ResponseInfo(ResponseInfo.ERROR, e.getMessage());
        return r;
    }

    /**
     * 全局处理年级比较异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = GradeSwitchException.class)
    @ResponseBody
    public ResponseInfo gradeSwitchExceptionHandle(GradeSwitchException e){
        ResponseInfo r = new ResponseInfo(ResponseInfo.ERROR, e.getMessage());
        return r;
    }
}
