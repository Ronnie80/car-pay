package com.yangyl.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yangyl.manage.common.dto.Menus;
import com.yangyl.manage.common.dto.Response;
import com.yangyl.manage.entity.MenuInfo;
import com.yangyl.manage.entity.UserMenu;
import com.yangyl.manage.mapper.MenuInfoMapper;
import com.yangyl.manage.service.MenuInfoService;
import com.yangyl.manage.service.UserMenuService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * 菜单信息 服务实现类
 * </p>
 *
 * @author yangyl
 * @since 2020-05-16
 */
@Service
public class MenuInfoServiceImpl extends ServiceImpl<MenuInfoMapper, MenuInfo> implements MenuInfoService {

    @Autowired
    private UserMenuService userMenuService;
    @Autowired
    @Qualifier("commonMapper")
    private ModelMapper modelMapper;


    @Override
    public Response getUserMenu(Long userCode) {
        List<Long> menuIds = userMenuService
                .list(new QueryWrapper<UserMenu>()
                        .eq("user_code", userCode))
                .stream().map(UserMenu::getMenuCode).collect(Collectors.toList());
        List<MenuInfo> menuList = this.list(new QueryWrapper<MenuInfo>().in("menu_code", menuIds));
        List<Menus> collect = menuList.stream()
                .filter(menu -> menu.getParentCode().longValue() == 0)
                .map(new Function<MenuInfo, Menus>() {
                    @Override
                    public Menus apply(MenuInfo menuInfo) {
                        return modelMapper.map(menuInfo, Menus.class);
                    }
                }).collect(Collectors.toList());
        List<Menus> result = collect.stream().map(new Function<Menus, Menus>() {
            @Override
            public Menus apply(Menus menu) {
                List<Menus> infos = menuList.stream()
                        .filter(menuInfo -> menuInfo.getParentCode().longValue()
                                == menu.getMenuCode().longValue())
                        .map(new Function<MenuInfo, Menus>() {
                            @Override
                            public Menus apply(MenuInfo menuInfo) {
                                return modelMapper.map(menuInfo, Menus.class);
                            }
                        }).collect(Collectors.toList());
                menu.setChildren(infos);
                return menu;
            }
        }).collect(Collectors.toList());
        return Response.ok(result);
    }

    @Override
    public Response settingMenusRole(List<UserMenu> list) {
        if (userMenuService.saveBatch(list)){
            return Response.ok();
        }else {
            return Response.err("权限设置失败");
        }
    }
}
