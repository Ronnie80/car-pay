package com.yangyl.manage.model.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class CustomerInfoDto {

    private Integer id;

    @ApiModelProperty(value = "公司名称")
    private String companyName;

    @ApiModelProperty(value = "联系人姓名")
    private String contactPerson;

    @ApiModelProperty(value = "联系人电话")
    private String contactPhone;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "营业执照图,上传获取图片编码code，保存客户信息需要")
    private List<String> operatingLicense;

}
