package com.yangyl.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.yangyl.manage.entity.CustomerInfo;
import com.yangyl.manage.entity.RepaymentRecords;
import com.yangyl.manage.entity.VehicleSaleInfo;
import com.yangyl.manage.entity.VehicleSource;
import com.yangyl.manage.mapper.VehicleSourceMapper;
import com.yangyl.manage.model.dto.VehicleSourceDto;
import com.yangyl.manage.model.params.VehicleSourceParam;
import com.yangyl.manage.model.vo.VehicleSourceVo;
import com.yangyl.manage.service.CustomerInfoService;
import com.yangyl.manage.service.ImageInfoService;
import com.yangyl.manage.service.RepaymentRecordsService;
import com.yangyl.manage.service.VehicleSourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 车辆信息来源 服务实现类
 * </p>
 *
 * @author yangyl
 * @since 2020-05-16
 */
@Service
public class VehicleSourceServiceImpl extends ServiceImpl<VehicleSourceMapper, VehicleSource> implements VehicleSourceService {

    @Autowired
    @Qualifier("commonMapper")
    private ModelMapper modelMapper;

    @Autowired
    private ImageInfoService imageInfoService;
    @Autowired
    private CustomerInfoService customerInfoService;
    @Autowired
    private RepaymentRecordsService repaymentRecordsService;

    @Transactional
    @Override
    public boolean save(VehicleSourceDto info) {
        VehicleSource map = modelMapper.map(info, VehicleSource.class);
        String vehicleCode = IdWorker.getIdStr();
        map.setVehicleCode(vehicleCode);
        if (map.getPeriodNumber().intValue() > 0) {
            map.setCurrentPeriodPayStatus(0);
            map.setPhasingStatus(0);
        } else {
            map.setCurrentPeriodPayStatus(1);
            map.setPhasingStatus(1);
        }
        if (this.save(map)) {
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
            if (info.getVehicleDisplay() != null && info.getVehicleDisplay().size() > 0) {
                imageInfoService.updateImageCode(info.getVehicleDisplay(), null, map.getVehicleCode());
            }
            CustomerInfo customerCode = customerInfoService.
                    getOne(new QueryWrapper<CustomerInfo>().eq("customer_code", info.getCustomerCode()));
            customerCode.setSalesNumber(customerCode.getSalesNumber().intValue() + 1);
            customerInfoService.updateById(customerCode);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public IPage<VehicleSourceVo> pages(VehicleSourceParam param) {
        return this.baseMapper.pages(new Page(param.getPage(), param.getSize()),
                param.getCustomerCode(),
                param.getVehicleName(),
                param.getLicensePlate(),
                param.getStartTime(),
                param.getEndTime(),
                param.getRepaymentsType(),
                param.getSettleType());
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

    public VehicleSource getInfoByVehicleCode(String vehicleCode) {
        return this.baseMapper.getInfoByVehicleCode(vehicleCode);
    }
}
