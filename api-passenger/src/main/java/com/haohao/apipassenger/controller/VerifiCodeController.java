package com.haohao.apipassenger.controller;

import com.haohao.apipassenger.service.IVerifiCodeService;
import com.haohao.internalcommon.dto.VerifiCodeDto;
import com.haohao.internalcommon.result.ResultWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerifiCodeController {

    @Autowired
    private IVerifiCodeService verifiCodeService;

    /**
     * 发送验证码
     * @param dto
     * @return
     */
    @GetMapping("/verification-code")
    public ResultWrapper verificationCode(@RequestBody VerifiCodeDto dto){
        return verifiCodeService.verificationCode(dto.getPassengerPhone());
    }

    /**
     * 校验验证码
     * @param dto
     * @return
     */
    @PostMapping("/verification-code-check")
    public ResultWrapper verificationCodeCheck(@RequestBody VerifiCodeDto dto){
        return verifiCodeService.verificationCodeCheck(dto);
    }

}
