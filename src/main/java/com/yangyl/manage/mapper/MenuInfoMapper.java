package com.yangyl.manage.mapper;

import com.yangyl.manage.common.dto.Menus;
import com.yangyl.manage.entity.MenuInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 菜单信息 Mapper 接口
 * </p>
 *
 * @author yangyl
 * @since 2020-05-16
 */
@Repository("menuInfoMapper")
public interface MenuInfoMapper extends BaseMapper<MenuInfo> {

}
