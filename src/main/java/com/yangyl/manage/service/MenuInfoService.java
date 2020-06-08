package com.yangyl.manage.service;

import com.yangyl.manage.common.dto.Response;
import com.yangyl.manage.entity.MenuInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yangyl.manage.entity.UserMenu;

import java.util.List;

/**
 * <p>
 * 菜单信息 服务类
 * </p>
 *
 * @author yangyl
 * @since 2020-05-16
 */
public interface MenuInfoService extends IService<MenuInfo> {

    Response getUserMenu(Long userCode);

    Response settingMenusRole(List<UserMenu> list);
}
