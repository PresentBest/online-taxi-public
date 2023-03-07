package com.haohao.apipassenger.controller;

import com.haohao.internalcommon.result.ResultWrapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/noauthTest")
    public ResultWrapper test(){
        return ResultWrapper.success("noauthTest");
    }

    @GetMapping("/authTest")
    public ResultWrapper authTest(){
        return ResultWrapper.success("authTest");
    }

}
