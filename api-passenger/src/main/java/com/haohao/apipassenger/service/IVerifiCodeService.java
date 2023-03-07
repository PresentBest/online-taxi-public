package com.haohao.apipassenger.service;

import com.haohao.internalcommon.dto.VerifiCodeDto;
import com.haohao.internalcommon.result.ResultWrapper;

public interface IVerifiCodeService {

    ResultWrapper verificationCode(String passengerPhone);

    ResultWrapper verificationCodeCheck(VerifiCodeDto dto);

}
