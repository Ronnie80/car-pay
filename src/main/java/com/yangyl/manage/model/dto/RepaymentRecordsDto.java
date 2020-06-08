package com.yangyl.manage.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class RepaymentRecordsDto {

    @ApiModelProperty(value = "车辆编码")
    private Long vehicleCode;

    @ApiModelProperty(value = "还款日期")
    private Date repaymentDate;

}
