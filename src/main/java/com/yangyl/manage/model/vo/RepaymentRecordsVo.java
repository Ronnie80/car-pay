package com.yangyl.manage.model.vo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class RepaymentRecordsVo {

    private Integer id;

    @ApiModelProperty(value = "车辆编码")
    private Long vehicleCode;

    @ApiModelProperty(value = "还款金额")
    private BigDecimal repaymentAmount;

    @ApiModelProperty(value = "还款状态 1 已还款 0 未还款")
    private Integer repaymentStatus;

    @ApiModelProperty(value = "还款日期")
    private Date repaymentDate;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

}
