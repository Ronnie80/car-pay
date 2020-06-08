package com.yangyl.manage.controller;


import com.yangyl.manage.common.dto.Response;
import com.yangyl.manage.entity.RepaymentRecords;
import com.yangyl.manage.model.dto.RepaymentRecordsDto;
import com.yangyl.manage.service.RepaymentRecordsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 还款记录 前端控制器
 * </p>
 *
 * @author yangyl
 * @since 2020-05-16
 */
@RestController
public class RepaymentRecordsController {

    @Autowired
    private RepaymentRecordsService repaymentRecordsService;


    @ApiOperation(value = "还款操作")
    @PostMapping("/api/records/repayment")
    public Response repayment(@RequestBody RepaymentRecordsDto records) {
        return repaymentRecordsService.repayment(records);
    }

    @ApiOperation(value = "还款记录")
    @GetMapping("/api/records/getRepaymentRecord")
    public Response getRepaymentRecord(String vehicleCode) {
        return repaymentRecordsService.getRepaymentRecord(vehicleCode);
    }

    @ApiOperation(value = "补交应还款")
    @GetMapping("/api/records/repairPay")
    public Response repairPay(@RequestParam Integer id) {
        return repaymentRecordsService.repairPay(id);
    }


}

