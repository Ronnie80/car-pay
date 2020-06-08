package com.yangyl.manage.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yangyl.manage.common.dto.Response;
import com.yangyl.manage.entity.CustomerInfo;
import com.yangyl.manage.entity.ImageInfo;
import com.yangyl.manage.model.dto.CustomerInfoDto;
import com.yangyl.manage.model.params.CustomerInfoParam;
import com.yangyl.manage.model.vo.CustomerInfoVo;
import com.yangyl.manage.service.CustomerInfoService;
import com.yangyl.manage.service.ImageInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 客户信息 前端控制器
 * </p>
 *
 * @author yangyl
 * @since 2020-05-16
 */
@Api(value = "客户信息相关接口")
@RestController
public class CustomerInfoController {

    @Autowired
    @Qualifier("commonMapper")
    private ModelMapper modelMapper;

    @Autowired
    private ImageInfoService imageInfoService;

    @Autowired
    private CustomerInfoService customerInfoService;

    @ApiOperation(value = "客户信息分页")
    @PostMapping("/api/customer/pages")
    public Response pages(@RequestBody CustomerInfoParam param) {
        QueryWrapper<CustomerInfo> query = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(param.getCompanyName())) {
            query.like("company_name", param.getCompanyName());
        }
        if (StringUtils.isNotEmpty(param.getContactPerson())) {
            query.like("contact_person", param.getContactPerson());
        }
        if (StringUtils.isNotEmpty(param.getContactPhone())) {
            query.like("contact_phone", param.getContactPhone());
        }
        IPage<CustomerInfoVo> pages = customerInfoService
                .page(new Page<>(param.getPage(), param.getSize()), query)
                .convert(new Function<CustomerInfo, CustomerInfoVo>() {
                    @Override
                    public CustomerInfoVo apply(CustomerInfo info) {
                        return modelMapper.map(info, CustomerInfoVo.class);
                    }
                });
        return Response.ok(pages);
    }

    @ApiOperation(value = "保存客户信息")
    @PostMapping("/api/customer/save")
    public Response save(@RequestBody CustomerInfoDto info) {
        CustomerInfo map = modelMapper.map(info, CustomerInfo.class);
        map.setCustomerCode(IdWorker.getIdStr());
        if (customerInfoService.save(map)) {
            imageInfoService.updateImageCode(info.getOperatingLicense(), map.getCustomerCode(), null);
            return Response.ok();
        } else {
            return Response.err("保存客户信息失败");
        }
    }

    @ApiOperation(value = "获取客户信息")
    @GetMapping("/api/customer/get")
    public Response get(@RequestParam Integer id) {
        CustomerInfoVo info = modelMapper.map(customerInfoService.getById(id), CustomerInfoVo.class);
        List<String> collect = imageInfoService.list(info.getCustomerCode(), null, ImageInfo.ImgType.operatingLicense);
        info.setOperatingLicense(collect);
        return Response.ok(info);
    }

    @ApiOperation(value = "修改客户信息")
    @PostMapping("/api/customer/update")
    public Response update(@RequestBody CustomerInfoDto info) {
        CustomerInfo byId = customerInfoService.getById(info.getId());
        modelMapper.map(info, byId);
        if (customerInfoService.updateById(byId)) {
            if (info.getOperatingLicense() != null && info.getOperatingLicense().size() > 0) {
                imageInfoService.removeUpdateCode(info.getOperatingLicense(), byId.getCustomerCode()
                        , null, ImageInfo.ImgType.operatingLicense);
            }
            return Response.ok();
        } else {
            return Response.err("修改客户信息失败");
        }
    }

    @ApiOperation(value = "删除客户信息")
    @GetMapping("/api/customer/delete")
    public Response delete(@RequestParam Integer id) {
        if (customerInfoService.removeById(id)) {
            return Response.ok();
        } else {
            return Response.err("删除信息失败");
        }
    }

}

