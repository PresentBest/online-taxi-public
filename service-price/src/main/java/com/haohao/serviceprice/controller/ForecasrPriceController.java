package com.haohao.serviceprice.controller;

import com.haohao.internalcommon.dto.ForecastPriceDto;
import com.haohao.internalcommon.result.ResultWrapper;
import com.haohao.serviceprice.service.ForecastPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForecasrPriceController {

    @Autowired
    ForecastPriceService forecastPriceService;

    @PostMapping("/forecast-price")
    public ResultWrapper forecastPrice(@RequestBody ForecastPriceDto dto){
        return forecastPriceService.forecastPrice(dto);
    }
}
