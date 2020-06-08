package com.yangyl.manage.model.params;


import lombok.Data;

@Data
public class VehicleSourceParam {

    private Integer page;
    private Integer size;
    private String customerCode;
    private String vehicleName;
    private String licensePlate;
    private String purchasePrice;
    private String startTime;
    private String endTime;
    private Integer repaymentsType;
    private Integer settleType;
}
