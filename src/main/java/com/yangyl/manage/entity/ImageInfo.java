package com.yangyl.manage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 图片信息
 * </p>
 *
 * @author yangyl
 * @since 2020-05-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Builder
@ApiModel(value = "ImageInfo对象", description = "图片信息")
public class ImageInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "图片编码")
    private String code;

    @ApiModelProperty(value = "图片名称")
    private String fileName;

    @ApiModelProperty(value = "车辆编码")
    private String vehicleCode;

    @ApiModelProperty(value = "客户编码")
    private String customerCode;

    @ApiModelProperty(value = "图片类型 1 身份证 2 行车本 3 保单 4 环保合同 5 营业执照")
    private Integer imgType;

    @ApiModelProperty(value = "图片地址")
    private String imgUrl;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "创建人编码")
    private String createBy;

    @ApiModelProperty(value = "是否删除 0 否  1 是")
    private Integer isDelete;

    public static final class ImgType {
        public static final Integer idcar = 1;
        public static final Integer travelPass = 2;
        public static final Integer insurancePolicy = 3;
        public static final Integer environmentalBill = 4;
        public static final Integer operatingLicense = 5;
        public static final Integer VehicleDisplay = 6;
    }
}
