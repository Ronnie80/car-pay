package com.yangyl.manage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yangyl.manage.common.dto.Response;
import com.yangyl.manage.entity.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * <p>
 * 用户信息 服务类
 * </p>
 *
 * @author yangyl
 * @since 2020-05-16
 */
public interface UserInfoService extends IService<UserInfo>, UserDetailsService {

    Response login(UserInfo userInfo);

    UserInfo getUserInfo(Object principal);

    Response updatePassWord(UserInfo user);
}
