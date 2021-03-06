package com.yangyl.manage.model.vo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class VehicleSaleInfoVo {

    private Integer id;

    @ApiModelProperty(value = "车辆编码")
    private Long vehicleCode;

    @ApiModelProperty(value = "客户名称")
    private String customerName;

    @ApiModelProperty(value = "客户手机号")
    private String customerPhone;

    @ApiModelProperty(value = "身份证编码")
    private String idCardNumber;

    @ApiModelProperty(value = "车辆名称")
    private String vehicleName;

    @ApiModelProperty(value = "车牌号码")
    private String licensePlate;

    @ApiModelProperty(value = "总金额")
    private BigDecimal totalAmount;

    @ApiModelProperty(value = "实付金额")
    private BigDecimal actualPaymentAmount;

    @ApiModelProperty(value = "首付金额")
    private BigDecimal downPayment;

    @ApiModelProperty(value = "期数")
    private Integer periodNumber;

    @ApiModelProperty(value = "采购日期")
    private Date purchaseDate;

    @ApiModelProperty(value = "月供金额")
    private BigDecimal monthlyContribution;

    @ApiModelProperty(value = "利率")
    private Double interestRates;

    @ApiModelProperty(value = "当期还款状态 0 待还款 1 已还款 ")
    private Integer currentPeriodPayStatus;

    @ApiModelProperty(value = "分期状态 0 未结清 1 已结清")
    private Integer phasingStatus;

    @ApiModelProperty(value = "还款日期")
    private Date repaymentDate;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "备注信息")
    private String remark;

    @ApiModelProperty(value = "身份证号码图片集合")
    @TableField(exist = false)
    private List<String> idCard;

    @ApiModelProperty(value = "保险单图片集合")
    @TableField(exist = false)
    private List<String> insurancePolicy;

    @ApiModelProperty(value = "行车证图片集合")
    @TableField(exist = false)
    private List<String> travelPass;

    @ApiModelProperty(value = "环保单图片集合")
    @TableField(exist = false)
    private List<String> environmentalBill;

}
