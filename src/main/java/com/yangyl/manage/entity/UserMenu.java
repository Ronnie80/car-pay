package com.yangyl.manage.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 用户权限信息

 * </p>
 *
 * @author yangyl
 * @since 2020-05-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="UserMenu对象", description="用户权限信息 ")
public class UserMenu implements Serializable {

    private static final long serialVersionUID=1L;

    private Long userCode;

    private Long menuCode;


}
