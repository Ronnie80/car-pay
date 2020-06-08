package com.yangyl.manage.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


@Data
public class CustomerInfoVo {

    private Integer id;

    @ApiModelProperty(value = "客户编码")
    private String customerCode;

    @ApiModelProperty(value = "公司名称")
    private String companyName;

    @ApiModelProperty(value = "联系人姓名")
    private String contactPerson;

    @ApiModelProperty(value = "联系人电话")
    private String contactPhone;

    @ApiModelProperty(value = "销售数量")
    private Integer salesNumber;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "营业执照图")
    private List<String> operatingLicense;
}
