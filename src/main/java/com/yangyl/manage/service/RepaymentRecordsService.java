package com.yangyl.manage.service;

import com.yangyl.manage.common.dto.Response;
import com.yangyl.manage.entity.RepaymentRecords;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yangyl.manage.model.dto.RepaymentRecordsDto;

/**
 * <p>
 * 还款记录 服务类
 * </p>
 *
 * @author yangyl
 * @since 2020-05-16
 */
public interface RepaymentRecordsService extends IService<RepaymentRecords> {

    Response repayment(RepaymentRecordsDto records);

    Response getRepaymentRecord(String vehicleCode);

    Response repairPay(Integer id);

    RepaymentRecords getList(String vehicleCode);
}
