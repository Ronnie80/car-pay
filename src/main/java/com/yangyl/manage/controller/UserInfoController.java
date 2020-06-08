package com.yangyl.manage.controller;


import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.yangyl.manage.common.dto.Response;
import com.yangyl.manage.entity.UserInfo;
import com.yangyl.manage.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 用户信息 前端控制器
 * </p>
 *
 * @author yangyl
 * @since 2020-05-16
 */
@Api(value = "用户信息相关接口")
@RestController
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;


    @ApiOperation(value = "用户登录接口", httpMethod = "POST", notes = "用户登录接口")
    @PostMapping("/api/user/login")
    public Response login(@RequestBody UserInfo user) {
        return userInfoService.login(user);
    }

    @ApiOperation(value = "移除token接口", httpMethod = "POST", notes = "移除token接口")
    @PostMapping("/api/user/removeToken")
    public Response<Boolean> removeToken(@RequestParam String accesstoken, HttpServletRequest request, HttpServletResponse response) {
        return Response.ok(Boolean.TRUE);
    }

    @ApiOperation(value = "修改密码接口", httpMethod = "POST", notes = "修改密码接口")
    @PostMapping("/api/user/updatePassWord")
    public Response updatePassWord(@RequestBody UserInfo user) {
        return userInfoService.updatePassWord(user);
    }

    @ApiOperation(value = "获取用户信息", httpMethod = "GET", notes = "获取用户信息,只穿请求头就可以")
    @GetMapping("/api/user/info")
    public Response info(Authentication authorization) throws AuthenticationException {
        return Response.ok(userInfoService.getUserInfo(authorization.getPrincipal()));
    }

    @ApiOperation(value = "获取一个全新的编码")
    @GetMapping("/api/code/getCode")
    public Long getCode(){
        return IdWorker.getId();
    }


}

