package com.haohao.apipassenger.controller;

import com.haohao.apipassenger.service.IUserService;
import com.haohao.internalcommon.result.ResultWrapper;
import com.haohao.internalcommon.result.TokenResult;
import com.haohao.internalcommon.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
public class UserController {

    @Autowired
    IUserService userService;

    @GetMapping("/users")
    public ResultWrapper getUser(HttpServletRequest request){
        String accessToken = request.getHeader("Authorization");
        log.info("查询用户信息接口-accessToken：",accessToken);
        TokenResult tokenResult = JwtUtils.checkToken(accessToken);
        String phone = tokenResult.getPhone();
        log.info("查询用户信息接口-解析用户手机号：",phone);
        return userService.getUser(phone);
    }
}
