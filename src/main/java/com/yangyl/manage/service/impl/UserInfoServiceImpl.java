package com.yangyl.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yangyl.manage.common.dto.Response;
import com.yangyl.manage.common.security.utils.JwtTokenUtil;
import com.yangyl.manage.entity.UserInfo;
import com.yangyl.manage.mapper.UserInfoMapper;
import com.yangyl.manage.service.UserInfoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Map;

/**
 * <p>
 * 用户信息 服务实现类
 * </p>
 *
 * @author yangyl
 * @since 2020-05-16
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    @Qualifier("commonMapper")
    private ModelMapper modelMapper;

    @Override
    public Response login(UserInfo userInfo) {
        UserInfo user = this.getOne(new QueryWrapper<UserInfo>().eq("user_name",userInfo.getUserName()));
        if (user != null) {
            String password = DigestUtils.md5DigestAsHex(userInfo.getPassword().getBytes());
            System.out.println(password);
            if (!password.equals(user.getPassword())) {
                return Response.err("密码错误，请重新输入！~");
            }
            Map<String, Object> result = jwtTokenUtil.getToken(user.getUserName(), user);
            result.put("userInfo", user);
            return Response.ok(result);
        }
        return Response.err("用户不存在，请联系管理员");
    }

    @Override
    public UserInfo getUserInfo(Object principal) {
        UserInfo user = this.getOne(new QueryWrapper<UserInfo>().eq("user_name",principal));
        return user;
    }

    @Override
    public Response updatePassWord(UserInfo user) {
        UserInfo code = this.getOne(new QueryWrapper<UserInfo>().eq("user_code", user.getUserCode()));
        if (code != null){
            String password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
            code.setPassword(password);
            this.updateById(code);
            return Response.ok();
        }
        return Response.err("用户不存在");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo user = this.getOne(new QueryWrapper<UserInfo>().eq("user_name",username));
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new UserDetailsImpl(user);
    }
}
