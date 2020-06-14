package com.yangyl.manage.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yangyl.manage.entity.VehicleSaleInfo;
import com.yangyl.manage.model.dto.VehicleSaleInfoDto;

/**
 * <p>
 * 车辆信息 服务类
 * </p>
 *
 * @author yangyl
 * @since 2020-05-16
 */
public interface VehicleSaleInfoService extends IService<VehicleSaleInfo> {

    IPage<VehicleSaleInfo> pageVehicleInfo(Integer page, Integer size, Integer settleType, Integer repaymentsType, String customerName, String customerPhone, String vehicleName, String licensePlate, String startTime,String endTime);

    boolean save(VehicleSaleInfoDto info);

    VehicleSaleInfo getInfoByVehicleCode(String vehicleCode);

}
