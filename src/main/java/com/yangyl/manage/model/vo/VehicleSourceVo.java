package com.yangyl.manage.model.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class VehicleSourceVo {

    private Integer id;

    @ApiModelProperty(value = "客户编码")
    private String customerCode;

    @ApiModelProperty(value = "车辆编码")
    private String vehicleCode;

    @ApiModelProperty(value = "车型名称")
    private String vehicleName;

    @ApiModelProperty(value = "车牌号")
    private String licensePlate;

    @ApiModelProperty(value = "采购价格")
    private BigDecimal purchasePrice;

    @ApiModelProperty(value = "采购日期")
    private Date purchaseDate;

    @ApiModelProperty(value = "实付金额")
    private BigDecimal actualPaymentAmount;

    @ApiModelProperty(value = "首付金额")
    private BigDecimal downPayment;

    @ApiModelProperty(value = "期数")
    private Integer periodNumber;

    @ApiModelProperty(value = "月供金额")
    private BigDecimal monthlyContribution;

    @ApiModelProperty(value = "还款日期")
    private Date repaymentDate;

    @ApiModelProperty(value = "当期还款状态 0 待还款 1 已还款 ")
    private Integer currentPeriodPayStatus;

    @ApiModelProperty(value = "分期状态 0 未结清 1 已结清")
    private Integer phasingStatus;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "车辆展示图片集合")
    private List<String> VehicleDisplay;


}
