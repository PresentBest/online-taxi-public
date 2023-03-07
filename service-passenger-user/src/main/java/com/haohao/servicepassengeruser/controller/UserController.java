package com.haohao.servicepassengeruser.controller;

import com.haohao.internalcommon.dto.VerifiCodeDto;
import com.haohao.internalcommon.result.ResultWrapper;
import com.haohao.servicepassengeruser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/loginOrRegUser")
    public ResultWrapper loginOrReg(@RequestBody VerifiCodeDto dto){
        return userService.loginOrReg(dto.getPassengerPhone());
    }

    @PostMapping("/getUserByPhone")
    public ResultWrapper getUserByPhone(@RequestBody VerifiCodeDto dto){
        return userService.getUserByPhone(dto.getPassengerPhone());
    }

}
