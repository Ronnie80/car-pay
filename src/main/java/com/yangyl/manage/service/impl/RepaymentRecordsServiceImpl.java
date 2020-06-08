package com.yangyl.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yangyl.manage.common.dto.Response;
import com.yangyl.manage.entity.RepaymentRecords;
import com.yangyl.manage.entity.VehicleSaleInfo;
import com.yangyl.manage.entity.VehicleSource;
import com.yangyl.manage.mapper.RepaymentRecordsMapper;
import com.yangyl.manage.model.dto.RepaymentRecordsDto;
import com.yangyl.manage.model.vo.RepaymentRecordsVo;
import com.yangyl.manage.service.RepaymentRecordsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yangyl.manage.service.VehicleSaleInfoService;
import com.yangyl.manage.service.VehicleSourceService;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 还款记录 服务实现类
 * </p>
 *
 * @author yangyl
 * @since 2020-05-16
 */
@Service
public class RepaymentRecordsServiceImpl extends ServiceImpl<RepaymentRecordsMapper, RepaymentRecords> implements RepaymentRecordsService {

    @Autowired
    private VehicleSaleInfoService vehicleSaleInfoService;
    @Autowired
    private VehicleSourceService vehicleSourceService;
    @Autowired
    @Qualifier("commonMapper")
    private ModelMapper modelMapper;


    @Override
    public Response repayment(RepaymentRecordsDto records) {
        QueryWrapper<RepaymentRecords> query = new QueryWrapper();
        query.eq("vehicle_code", records.getVehicleCode());
        query.eq("repayment_date", DateFormatUtils.format(records.getRepaymentDate(), "yyyy-MM-dd"));
        RepaymentRecords repaymentRecords = this.getOne(query);
        if (repaymentRecords != null) {
            repaymentRecords.setRepaymentStatus(1);
            if (this.updateById(repaymentRecords)) {
                VehicleSaleInfo vehicleSaleInfo = vehicleSaleInfoService.getOne(new QueryWrapper<VehicleSaleInfo>()
                        .eq("Vehicle_code", records.getVehicleCode()));
                if (vehicleSaleInfo != null) {
                    vehicleSaleInfo.setCurrentPeriodPayStatus(1);
                    vehicleSaleInfoService.updateById(vehicleSaleInfo);
                }
                VehicleSource vehicleCode = vehicleSourceService.getOne(new QueryWrapper<VehicleSource>()
                        .eq("Vehicle_code", records.getVehicleCode()));
                if (vehicleCode != null) {
                    vehicleCode.setCurrentPeriodPayStatus(1);
                    vehicleSourceService.updateById(vehicleCode);
                }
                return Response.ok();
            } else {
                return Response.err("还款异常");
            }
        } else {
            return Response.err("没有还款记录");
        }
    }

    @Override
    public Response getRepaymentRecord(String vehicleCode) {
        QueryWrapper<RepaymentRecords> query = new QueryWrapper();
        query.eq("vehicle_code", vehicleCode);
        query.orderByAsc("repayment_date");
        List<RepaymentRecords> code = this.list(query);

        List<RepaymentRecordsVo> collect = code.stream()
                .map(new Function<RepaymentRecords, RepaymentRecordsVo>() {
                    @Override
                    public RepaymentRecordsVo apply(RepaymentRecords records) {
                        return modelMapper.map(records, RepaymentRecordsVo.class);
                    }
                }).collect(Collectors.toList());
        return Response.ok(collect);
    }

    @Override
    public Response repairPay(Integer id) {
        RepaymentRecords byId = this.getById(id);
        byId.setRepaymentStatus(1);
        boolean flag = this.updateById(byId);
        if (flag) {
            return Response.ok();
        } else {
            return Response.err("补交失败");
        }
    }

    @Override
    public RepaymentRecords getList(String vehicleCode) {
        QueryWrapper<RepaymentRecords> query = new QueryWrapper();
        query.eq("vehicle_code", vehicleCode);
        query.orderByAsc("repayment_date");
        List<RepaymentRecords> list = this.list(query);
        if (list != null && list.size() >0){
            return list.get(0);
        }else {
            return null;
        }
    }
}
