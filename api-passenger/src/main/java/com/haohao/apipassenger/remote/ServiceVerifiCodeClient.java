package com.haohao.apipassenger.remote;

import com.haohao.internalcommon.response.NumberCodeResponse;
import com.haohao.internalcommon.result.ResultWrapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("service-verificationcode")
public interface ServiceVerifiCodeClient{

//    @RequestMapping(method = RequestMethod.GET, value = "/numberCode/{size}")
    @GetMapping("/numberCode/{size}")
    ResultWrapper<NumberCodeResponse> getNumberCode(@PathVariable("size") int size);

}
