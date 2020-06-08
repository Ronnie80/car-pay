package com.yangyl.manage.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yangyl.manage.entity.VehicleSource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yangyl.manage.model.vo.VehicleSourceVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 车辆信息来源 Mapper 接口
 * </p>
 *
 * @author yangyl
 * @since 2020-05-16
 */
public interface VehicleSourceMapper extends BaseMapper<VehicleSource> {

    IPage<VehicleSourceVo> pages(Page page, @Param("customerCode") String customerCode,
                                 @Param("vehicleName") String vehicleName,
                                 @Param("licensePlate") String licensePlate,
                                 @Param("startTime") String startTime,
                                 @Param("endTime") String endTime,
                                 @Param("repaymentsType") Integer repaymentsType,
                                 @Param("settleType") Integer settleType);
}
