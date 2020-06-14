package com.yangyl.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.yangyl.manage.entity.RepaymentRecords;
import com.yangyl.manage.entity.VehicleSaleInfo;
import com.yangyl.manage.mapper.VehicleSaleInfoMapper;
import com.yangyl.manage.model.dto.VehicleSaleInfoDto;
import com.yangyl.manage.service.ImageInfoService;
import com.yangyl.manage.service.RepaymentRecordsService;
import com.yangyl.manage.service.VehicleSaleInfoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 车辆信息 服务实现类
 * </p>
 *
 * @author yangyl
 * @since 2020-05-16
 */
@Service
public class VehicleSaleInfoServiceImpl extends ServiceImpl<VehicleSaleInfoMapper, VehicleSaleInfo> implements VehicleSaleInfoService {

    @Autowired
    @Qualifier("commonMapper")
    private ModelMapper modelMapper;
    @Autowired
    private RepaymentRecordsService repaymentRecordsService;
    @Autowired
    private ImageInfoService imageInfoService;


    @Override
    public IPage<VehicleSaleInfo> pageVehicleInfo(Integer page, Integer size, Integer settleType, Integer repaymentsType, String customerName, String customerPhone, String vehicleName, String licensePlate, String startTime, String endTime) {
        if (startTime == null) {
            startTime = "";
        }
        if (endTime == null) {
            endTime = "";
        }
        return this.baseMapper.pageVehicleInfo(new Page<>(page, size), settleType, repaymentsType, customerName,
                customerPhone, vehicleName, licensePlate, startTime, endTime);
    }

    @Transactional
    @Override
    public boolean save(VehicleSaleInfoDto info) {
        VehicleSaleInfo map = modelMapper.map(info, VehicleSaleInfo.class);
        if (info.getPeriodNumber() != null) {
            String vehicleCode = IdWorker.getIdStr();
            if (map.getPeriodNumber().intValue() > 0) {
                map.setCurrentPeriodPayStatus(0);
                map.setPhasingStatus(0);
            } else {
                map.setCurrentPeriodPayStatus(1);
                map.setPhasingStatus(1);
            }
            map.setVehicleCode(vehicleCode);
            this.save(map);
            List<RepaymentRecords> recordsList = Lists.newArrayList();
            for (int i = 0; i < info.getPeriodNumber().intValue(); i++) {
                RepaymentRecords records = new RepaymentRecords();
                records.setRepaymentStatus(0);
                records.setRepaymentDate(subMonth(info.getRepaymentDate(), i));
                records.setVehicleCode(vehicleCode);
                records.setRepaymentAmount(info.getMonthlyContribution());
                recordsList.add(records);
            }
            repaymentRecordsService.saveBatch(recordsList);
            if (info.getIdCard() != null && info.getIdCard().size() > 0) {
                imageInfoService.updateImageCode(info.getIdCard(), null, map.getVehicleCode());
            }
            if (info.getEnvironmentalBill() != null && info.getEnvironmentalBill().size() > 0) {
                imageInfoService.updateImageCode(info.getEnvironmentalBill(), null, map.getVehicleCode());
            }
            if (info.getInsurancePolicy() != null && info.getInsurancePolicy().size() > 0) {
                imageInfoService.updateImageCode(info.getInsurancePolicy(), null, map.getVehicleCode());
            }
            if (info.getTravelPass() != null && info.getTravelPass().size() > 0) {
                imageInfoService.updateImageCode(info.getTravelPass(), null, map.getVehicleCode());
            }
            return true;
        } else {
            map.setCurrentPeriodPayStatus(1);
            map.setPhasingStatus(1);
            this.save(map);
            if (info.getIdCard() != null && info.getIdCard().size() > 0) {
                imageInfoService.updateImageCode(info.getIdCard(), null, map.getVehicleCode());
            }
            if (info.getEnvironmentalBill() != null && info.getEnvironmentalBill().size() > 0) {
                imageInfoService.updateImageCode(info.getEnvironmentalBill(), null, map.getVehicleCode());
            }
            if (info.getInsurancePolicy() != null && info.getInsurancePolicy().size() > 0) {
                imageInfoService.updateImageCode(info.getInsurancePolicy(), null, map.getVehicleCode());
            }
            if (info.getTravelPass() != null && info.getTravelPass().size() > 0) {
                imageInfoService.updateImageCode(info.getTravelPass(), null, map.getVehicleCode());
            }
            return true;
        }
    }

    /****
     * 传入具体日期 ，返回具体日期增加一个月。
     * @param date 日期(2017-04-13)
     * @return 2020-5-30
     * @throws ParseException
     */
    private Date subMonth(Date date, int i) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar rightNow = Calendar.getInstance();
            rightNow.setTime(date);
            rightNow.add(Calendar.MONTH, i);
            Date dt1 = rightNow.getTime();
            return dt1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public VehicleSaleInfo getInfoByVehicleCode(String vehicleCode) {
        return this.baseMapper.getInfoByVehicleCode(vehicleCode);
    }

}
