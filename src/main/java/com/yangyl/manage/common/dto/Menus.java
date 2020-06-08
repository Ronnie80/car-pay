package com.yangyl.manage.common.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Menus {

    private Integer id;

    @ApiModelProperty(value = "菜单编码")
    private Long menuCode;

    @ApiModelProperty(value = "菜单名称")
    private String menuName;

    @ApiModelProperty(value = "菜单父编码")
    private Long parentCode;

    @ApiModelProperty(value = "菜单类型 1 菜单 2 按钮")
    private Integer menuType;

    @ApiModelProperty(value = "菜单路由")
    private String menuUrl;

    @ApiModelProperty(value = "菜单功能")
    private List<Menus> children;

}
