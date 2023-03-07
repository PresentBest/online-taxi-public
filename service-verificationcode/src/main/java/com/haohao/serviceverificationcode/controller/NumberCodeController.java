package com.haohao.serviceverificationcode.controller;

import com.haohao.internalcommon.response.NumberCodeResponse;
import com.haohao.internalcommon.result.ResultWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class NumberCodeController {

    @GetMapping("/numberCode/{size}")
    public ResultWrapper numberCode(@PathVariable Integer size){
        double random = (Math.random()*9+1)*(Math.pow(10,size-1));
        Integer code = (int)random;
        log.info("生成验证码:" + code);
        NumberCodeResponse numberCodeResponse = new NumberCodeResponse();
        numberCodeResponse.setNumberCode(code);
        return ResultWrapper.success(numberCodeResponse);
    }

}
