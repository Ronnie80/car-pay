package com.yangyl.manage.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yangyl.manage.common.dto.Response;
import com.yangyl.manage.entity.CustomerInfo;
import com.yangyl.manage.entity.ImageInfo;
import com.yangyl.manage.entity.RepaymentRecords;
import com.yangyl.manage.entity.VehicleSource;
import com.yangyl.manage.model.dto.VehicleSourceDto;
import com.yangyl.manage.model.params.VehicleSourceParam;
import com.yangyl.manage.model.vo.VehicleSourceVo;
import com.yangyl.manage.service.ImageInfoService;
import com.yangyl.manage.service.RepaymentRecordsService;
import com.yangyl.manage.service.VehicleSourceService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Function;

/**
 * <p>
 * 车辆信息来源 前端控制器
 * </p>
 *
 * @author yangyl
 * @since 2020-05-16
 */
@RestController
public class VehicleSourceController {

    @Autowired
    private VehicleSourceService vehicleSourceService;
    @Autowired
    private ImageInfoService imageInfoService;
    @Autowired
    private RepaymentRecordsService repaymentRecordsService;


    @Autowired
    @Qualifier("commonMapper")
    private ModelMapper modelMapper;


    @ApiOperation(value = "车辆信息来源分页")
    @PostMapping("/api/vehicleSource/pages")
    public Response pages(@RequestBody VehicleSourceParam param) {
        IPage<VehicleSourceVo> pages = vehicleSourceService.pages(param);
        return Response.ok(pages);
    }

    @ApiOperation(value = "保存车辆信息来源")
    @PostMapping("/api/vehicleSource/save")
    public Response save(@RequestBody VehicleSourceDto info) {
        if (vehicleSourceService.save(info)) {
            return Response.ok();
        } else {
            return Response.err("保存车辆来源信息失败");
        }
    }

    @ApiOperation(value = "获取车辆信息来源")
    @GetMapping("/api/vehicleSource/get")
    public Response get(@RequestParam Integer id) {
        VehicleSource info = vehicleSourceService.getById(id);
        if (info == null) {
            return Response.err("车辆信息不存在");
        }
        VehicleSourceVo map = modelMapper.map(info, VehicleSourceVo.class);
        List<String> vehicleDisplay = imageInfoService.list(null,
                info.getVehicleCode(), ImageInfo.ImgType.VehicleDisplay);
        map.setVehicleDisplay(vehicleDisplay);
        return Response.ok(map);
    }

    @ApiOperation(value = "修改车辆信息来源")
    @PostMapping("/api/vehicleSource/update")
    public Response update(@RequestBody VehicleSourceDto info) {
        VehicleSource map = modelMapper.map(info, VehicleSource.class);
        if (vehicleSourceService.update(map, new UpdateWrapper<VehicleSource>().eq("vehicle_code", info.getVehicleCode()))) {
            if (info.getVehicleDisplay() != null && info.getVehicleDisplay().size() > 0) {
                imageInfoService.removeUpdateCode(info.getVehicleDisplay(), null,
                        map.getVehicleCode(), ImageInfo.ImgType.VehicleDisplay);
            }
            return Response.ok();
        } else {
            return Response.err("修改车辆来源信息失败");
        }
    }

    @ApiOperation(value = "删除车辆信息来源")
    @GetMapping("/api/vehicleSource/delete")
    public Response delete(@RequestParam Integer id) {
        if (vehicleSourceService.removeSource(id)) {
            return Response.ok();
        } else {
            return Response.err("删除信息失败");
        }
    }


}

