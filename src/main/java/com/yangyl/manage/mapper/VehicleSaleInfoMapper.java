package com.yangyl.manage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yangyl.manage.entity.VehicleSaleInfo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 车辆信息 Mapper 接口
 * </p>
 *
 * @author yangyl
 * @since 2020-05-16
 */
public interface VehicleSaleInfoMapper extends BaseMapper<VehicleSaleInfo> {

    IPage<VehicleSaleInfo> pageVehicleInfo(Page page,
                                           @Param("settleType") Integer settleType,
                                           @Param("repaymentsType") Integer repaymentsType,
                                           @Param("customerName") String customerName,
                                           @Param("customerPhone") String customerPhone,
                                           @Param("vehicleName") String vehicleName,
                                           @Param("licensePlate") String licensePlate,
                                           @Param("startTime") String startTime,
                                           @Param("endTime") String endTime);

    VehicleSaleInfo getInfoByVehicleCode(String vehicleCode);

}
