package com.yangyl.manage.service;

import com.yangyl.manage.common.dto.Response;
import com.yangyl.manage.entity.CustomerInfo;
import com.yangyl.manage.entity.ImageInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yangyl.manage.model.dto.CustomerInfoDto;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 图片信息 服务类
 * </p>
 *
 * @author yangyl
 * @since 2020-05-16
 */
public interface ImageInfoService extends IService<ImageInfo> {

    Response<ImageInfo> uploadFile(MultipartFile file, Integer type, String userCode, String fileName);

    Response<ImageInfo> queryByCode(String pictureCode);

    List<String> list(String customerCode, String vehicleCode, Integer type);

    List<ImageInfo> list(List<String> code, Integer type);

    void updateImageCode(List<String> code, String CustomerCode, String vehicleCode);

    void removeUpdateCode(List<String> code, String customerCode, String vehicleCode, Integer type);
}
