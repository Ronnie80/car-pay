package com.yangyl.manage.common.security.filter;

import com.alibaba.fastjson.JSONObject;
import com.yangyl.manage.common.security.utils.JwtTokenUtil;
import com.yangyl.manage.entity.UserInfo;
import com.yangyl.manage.service.UserInfoService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 验证用户名密码正确后，生成一个token，并将token返回给客户端
 * 该类继承自UsernamePasswordAuthenticationFilter，重写了其中的2个方法
 * attemptAuthentication ：接收并解析用户凭证。
 * successfulAuthentication ：用户成功登录后，这个方法会被调用，我们在这个方法里生成token。
 *
 * @author zhaoxinguo on 2017/9/12.
 */
public class JWTLoginFilter extends UsernamePasswordAuthenticationFilter {

    private JwtTokenUtil jwtTokenUtil;

    private AuthenticationManager authenticationManager;

    private UserInfoService userInfoService;

    public JWTLoginFilter(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, UserInfoService userInfoService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userInfoService = userInfoService;
    }

    // 接收并解析用户凭证
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            String result = new BufferedReader(new InputStreamReader(req.getInputStream()))
                    .lines().collect(Collectors.joining(System.lineSeparator()));
            JSONObject user = JSONObject.parseObject(result);
            UserDetails details = userInfoService.loadUserByUsername(user.getString("username"));
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            details.getUsername(),
                            details.getPassword(),
                            details.getAuthorities())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 用户成功登录后，这个方法会被调用，我们在这个方法里生成token
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication auth) throws IOException {
        UserInfo userInfo = userInfoService.getUserInfo(auth.getPrincipal());
        Map<String,Object> result = jwtTokenUtil.getToken(auth.getPrincipal().toString(),userInfo);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(JSONObject.toJSONString(result));
    }

}
