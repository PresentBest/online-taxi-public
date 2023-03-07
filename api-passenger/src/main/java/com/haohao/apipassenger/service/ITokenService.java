package com.haohao.apipassenger.service;

import com.haohao.internalcommon.result.ResultWrapper;

public interface ITokenService {

    ResultWrapper refreshToken(String refreshTokenSrc);
}
