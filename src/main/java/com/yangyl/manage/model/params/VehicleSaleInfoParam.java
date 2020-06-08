package com.yangyl.manage.model.params;


import lombok.Data;

@Data
public class VehicleSaleInfoParam {

    private Integer page;
    private Integer size;
    private Integer settleType;
    private Integer repaymentsType;
    private String customerName;
    private String customerPhone;
    private String vehicleName;
    private String licensePlate;
    private String startTime;
    private String endTime;

}
