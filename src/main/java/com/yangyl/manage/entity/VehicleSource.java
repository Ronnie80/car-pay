package com.yangyl.manage.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.security.core.Transient;

/**
 * <p>
 * 车辆信息来源
 * </p>
 *
 * @author yangyl
 * @since 2020-05-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="VehicleSource对象", description="车辆信息来源")
public class VehicleSource implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
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

    @ApiModelProperty(value = "当期还款状态 0 待还款 1 已还款 ")
    private Integer currentPeriodPayStatus;

    @ApiModelProperty(value = "分期状态 0 未结清 1 已结清")
    private Integer phasingStatus;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "创建人")
    private String createBy;

    @TableLogic
    @ApiModelProperty(value = "是否删除 0 否 1 是")
    private Integer isDelete;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "还款日期")
    private Date repaymentDate;

}
