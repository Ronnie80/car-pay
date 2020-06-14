package com.yangyl.manage.service.impl;

import com.yangyl.manage.entity.CustomerInfo;
import com.yangyl.manage.mapper.CustomerInfoMapper;
import com.yangyl.manage.service.CustomerInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 客户信息 服务实现类
 * </p>
 *
 * @author yangyl
 * @since 2020-05-16
 */
@Service
public class CustomerInfoServiceImpl extends ServiceImpl<CustomerInfoMapper, CustomerInfo> implements CustomerInfoService {

    public CustomerInfo getByCustomerCode(String customerCode) {
        return this.baseMapper.getByCustomerCode(customerCode);
    }
}
