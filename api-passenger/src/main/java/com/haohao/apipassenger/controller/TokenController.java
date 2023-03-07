package com.haohao.apipassenger.controller;


import com.haohao.apipassenger.service.ITokenService;
import com.haohao.internalcommon.response.TokenResponse;
import com.haohao.internalcommon.result.ResultWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {

    @Autowired
    ITokenService tokenService;

    @PostMapping("/token-refresh")
    public ResultWrapper tokenRefresh(@RequestBody TokenResponse tokenResponse){
        String refreshTokenSrc = tokenResponse.getRefreshToken();
        System.out.println("原来的 refreshToken："+refreshTokenSrc);

        return tokenService.refreshToken(refreshTokenSrc);
    }

}
