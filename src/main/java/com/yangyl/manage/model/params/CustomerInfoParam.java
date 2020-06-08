package com.yangyl.manage.model.params;


import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

@Data
public class CustomerInfoParam {

    private Integer page;
    private Integer size;
    private String companyName;
    private String contactPerson;
    private String contactPhone;

}
