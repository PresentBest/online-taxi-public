package com.haohao.servicemap.controller;

import com.haohao.internalcommon.dto.ForecastPriceDto;
import com.haohao.internalcommon.result.ResultWrapper;
import com.haohao.servicemap.service.DirectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/direction")
public class DirectionController {

    @Autowired
    DirectionService directionService;

    @GetMapping("/driving")
    public ResultWrapper driving(@RequestBody ForecastPriceDto dto){

        return directionService.driving(dto.getDepLongitude(),dto.getDepLatitude()
                ,dto.getDestLongitude(),dto.getDestLatitude());
    }


}
