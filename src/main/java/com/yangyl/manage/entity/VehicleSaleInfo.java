package com.yangyl.manage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 车辆信息
 * </p>
 *
 * @author yangyl
 * @since 2020-05-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="VehicleInfo对象", description="车辆信息")
public class VehicleSaleInfo implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "车辆编码")
    private String vehicleCode;

    @ApiModelProperty(value = "客户名称")
    private String customerName;

    @ApiModelProperty(value = "客户手机号")
    private String customerPhone;

    @ApiModelProperty(value = "身份证号码")
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

    @TableLogic
    @ApiModelProperty(value = "是否删除 0 否  1 是")
    private Integer isDelete;

}
