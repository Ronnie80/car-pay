package com.yangyl.manage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yangyl.manage.entity.VehicleSource;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yangyl.manage.model.dto.VehicleSourceDto;
import com.yangyl.manage.model.params.VehicleSourceParam;
import com.yangyl.manage.model.vo.VehicleSourceVo;

/**
 * <p>
 * 车辆信息来源 服务类
 * </p>
 *
 * @author yangyl
 * @since 2020-05-16
 */
public interface VehicleSourceService extends IService<VehicleSource> {

    boolean save(VehicleSourceDto info);

    IPage<VehicleSourceVo> pages(VehicleSourceParam param);

}
