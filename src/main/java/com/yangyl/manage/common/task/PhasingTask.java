package com.yangyl.manage.common.task;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yangyl.manage.entity.RepaymentRecords;
import com.yangyl.manage.entity.VehicleSaleInfo;
import com.yangyl.manage.service.RepaymentRecordsService;
import com.yangyl.manage.service.VehicleSaleInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class PhasingTask {


    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private VehicleSaleInfoService vehicleSaleInfoService;
    @Autowired
    private RepaymentRecordsService repaymentRecordsService;

    /**
     * @Description 每小时一次，检查当月下月账单
     * @Date 2020/5/23 20:35
     * @Created by yangyl
     */
    @Scheduled(cron = "0 0 0/1 * * ?")
    public void reportCurrentTime() {
//        QueryWrapper<VehicleSaleInfo> saleQuery = new QueryWrapper<>();
//        saleQuery.eq("phasing_status", 0);
//        List<VehicleSaleInfo> sales = vehicleSaleInfoService.list(saleQuery);
//        QueryWrapper<RepaymentRecords> payQuery = new QueryWrapper<>();
//        payQuery.eq("repayment_status", 0);
//        List<RepaymentRecords> records = repaymentRecordsService.list(payQuery);
//        for (VehicleSaleInfo sale : sales) {
//            List<RepaymentRecords> collect = records.stream()
//                    .filter(rd -> rd.getVehicleCode().equals(sale.getVehicleCode()))
//                    .filter(rd1 -> DateFormatUtils.format(rd1
//                            .getRepaymentDate(), "yyyy-MM")
//                            .equals(DateFormatUtils.format(new Date(), "yyyy-MM")))
//                    .collect(Collectors.toList());
//            log.info(String.valueOf(collect.size()));
//
//        }


        log.info("NOW：" + sdf.format(new Date()));
    }


}
