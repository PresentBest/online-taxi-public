package com.haohao.apipassenger.controller;

import com.haohao.apipassenger.service.IForecastPriceService;
import com.haohao.internalcommon.dto.ForecastPriceDto;
import com.haohao.internalcommon.result.ResultWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ForecastPriceController {

    @Autowired
    IForecastPriceService forecastPriceService;

    @PostMapping("/forecast-price")
    public ResultWrapper forecastPrice(@RequestBody ForecastPriceDto dto){
        log.info("出发地的经度：" + dto.getDepLongitude());
        log.info("出发地的纬度：" + dto.getDepLatitude());
        log.info("目的地的经度：" + dto.getDestLongitude());
        log.info("目的地的纬度：" + dto.getDestLatitude());
        return forecastPriceService.forecastPrice(dto.getDepLongitude(),dto.getDepLatitude()
                ,dto.getDestLongitude(),dto.getDestLatitude());
    }

}
