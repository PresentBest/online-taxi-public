package com.haohao.serviceprice.remote;

import com.haohao.internalcommon.dto.ForecastPriceDto;
import com.haohao.internalcommon.result.ResultWrapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("service-map")
public interface ServiceMapClient {

    @PostMapping("/direction/driving")
    ResultWrapper driving(@RequestBody ForecastPriceDto dto);

}
