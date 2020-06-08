package com.yangyl.manage.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.yangyl.manage.common.dto.Response;
import com.yangyl.manage.entity.ImageInfo;
import com.yangyl.manage.entity.VehicleSaleInfo;
import com.yangyl.manage.model.dto.VehicleSaleInfoDto;
import com.yangyl.manage.model.params.VehicleSaleInfoParam;
import com.yangyl.manage.model.vo.VehicleSaleInfoVo;
import com.yangyl.manage.service.ImageInfoService;
import com.yangyl.manage.service.VehicleSaleInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 车辆信息 前端控制器
 * </p>
 *
 * @author yangyl
 * @since 2020-05-16
 */
@Api(value = "车辆销售信息相关接口")
@RestController
public class VehicleSaleInfoController {

    @Autowired
    private VehicleSaleInfoService vehicleInfoService;
    @Autowired
    private ImageInfoService imageInfoService;

    @Autowired
    @Qualifier("commonMapper")
    private ModelMapper modelMapper;


    @ApiOperation(value = "车辆销售信息分页")
    @PostMapping("/api/vehicleInfo/pages")
    public Response pages(@RequestBody VehicleSaleInfoParam param) {
        IPage<VehicleSaleInfo> pages = vehicleInfoService
                .pageVehicleInfo(param.getPage(), param.getSize(), param.getSettleType(), param.getRepaymentsType(), param.getCustomerName(),
                        param.getCustomerPhone(), param.getVehicleName(), param.getLicensePlate(), param.getStartTime(), param.getEndTime());
        return Response.ok(pages);
    }

    @ApiOperation(value = "保存销售车辆信息")
    @PostMapping("/api/vehicleInfo/save")
    public Response save(@RequestBody VehicleSaleInfoDto info) {
        if (vehicleInfoService.save(info)) {
            return Response.ok();
        } else {
            return Response.err("保存车辆信息失败");
        }
    }

    @ApiOperation(value = "获取销售车辆信息")
    @GetMapping("/api/vehicleInfo/get")
    public Response get(@RequestParam Integer id) {
        VehicleSaleInfo info = vehicleInfoService.getById(id);
        if (info == null) {
            return Response.err("车辆不存在");
        }
        VehicleSaleInfoVo map = modelMapper.map(info, VehicleSaleInfoVo.class);
        List<String> idcard = imageInfoService.list(null, info.getVehicleCode(), ImageInfo.ImgType.idcar);
        map.setIdCard(idcard);
        List<String> pass = imageInfoService.list(null, info.getVehicleCode(), ImageInfo.ImgType.travelPass);
        map.setTravelPass(pass);
        List<String> bill = imageInfoService.list(null, info.getVehicleCode(), ImageInfo.ImgType.environmentalBill);
        map.setEnvironmentalBill(bill);
        List<String> policy = imageInfoService.list(null, info.getVehicleCode(), ImageInfo.ImgType.insurancePolicy);
        map.setInsurancePolicy(policy);
        return Response.ok(map);
    }

    @ApiOperation(value = "修改销售车辆信息")
    @PostMapping("/api/vehicleInfo/update")
    public Response update(@RequestBody VehicleSaleInfoDto info) {
        VehicleSaleInfo map = modelMapper.map(info, VehicleSaleInfo.class);
        if (vehicleInfoService.updateById(map)) {
            if (info.getTravelPass() != null && info.getTravelPass().size() > 0) {
                imageInfoService.removeUpdateCode(info.getTravelPass(), null,
                        map.getVehicleCode(), ImageInfo.ImgType.travelPass);
            }
            if (info.getInsurancePolicy() != null && info.getInsurancePolicy().size() > 0) {
                imageInfoService.removeUpdateCode(info.getInsurancePolicy(), null,
                        map.getVehicleCode(), ImageInfo.ImgType.insurancePolicy);
            }
            if (info.getEnvironmentalBill() != null && info.getEnvironmentalBill().size() > 0) {
                imageInfoService.removeUpdateCode(info.getEnvironmentalBill(), null,
                        map.getVehicleCode(), ImageInfo.ImgType.environmentalBill);
            }
            if (info.getIdCard() != null && info.getIdCard().size() > 0) {
                imageInfoService.removeUpdateCode(info.getIdCard(), null,
                        map.getVehicleCode(), ImageInfo.ImgType.idcar);
            }
            return Response.ok();
        } else {
            return Response.err("修改车辆信息失败");
        }
    }

    @ApiOperation(value = "删除销售车辆信息")
    @GetMapping("/api/vehicleInfo/delete")
    public Response delete(@RequestParam Integer id) {
        if (vehicleInfoService.removeById(id)) {
            return Response.ok();
        } else {
            return Response.err("删除信息失败");
        }
    }

    @ApiOperation(value = "车辆销售信息列表")
    @PostMapping("/api/vehicleInfo/list")
    public Response list() {
        List<VehicleSaleInfo> list = vehicleInfoService.list();
        return Response.ok(list);
    }

}

