package com.haohao.internalcommon.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class VerifiCodeDto implements Serializable {

    private String passengerPhone;

    private String verificationCode;

}
