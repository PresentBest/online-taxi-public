package com.haohao.apipassenger.remote;

import com.haohao.internalcommon.dto.PassengerUserDto;
import com.haohao.internalcommon.dto.VerifiCodeDto;
import com.haohao.internalcommon.result.ResultWrapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("service-passenger-user")
public interface ServicePassengerUserClient {

    @PostMapping("/loginOrRegUser")
    ResultWrapper loginOrReg(@RequestBody VerifiCodeDto dto);

    @PostMapping("/getUserByPhone")
    ResultWrapper<PassengerUserDto> getUserByPhone(@RequestBody VerifiCodeDto dto);

}
