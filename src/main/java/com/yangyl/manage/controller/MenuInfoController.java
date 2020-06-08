package com.yangyl.manage.controller;


import com.yangyl.manage.common.dto.Response;
import com.yangyl.manage.entity.UserMenu;
import com.yangyl.manage.service.MenuInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 菜单信息 前端控制器
 * </p>
 *
 * @author yangyl
 * @since 2020-05-16
 */
@Api(value = "菜单相关接口")
@RestController
public class MenuInfoController {

    @Autowired
    private MenuInfoService menuInfoService;

    @ApiOperation(value = "根据用户code获取左侧菜单列表")
    @GetMapping("/api/menu/getMenuUserRole")
    public Response getMenuUserRole(@RequestParam(required = true) Long userCode){
        return menuInfoService.getUserMenu(userCode);
    }

    @ApiOperation(value = "设置用户权限")
    @PostMapping("/api/menu/settingMenusRole")
    public Response settingMenusRole(List<UserMenu> list){
        return menuInfoService.settingMenusRole(list);
    }


}

