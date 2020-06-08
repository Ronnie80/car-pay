package com.yangyl.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yangyl.manage.common.dto.Response;
import com.yangyl.manage.entity.ImageInfo;
import com.yangyl.manage.mapper.ImageInfoMapper;
import com.yangyl.manage.service.ImageInfoService;
import com.yangyl.manage.utils.PathUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import xin.xihc.utils.common.FileUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 图片信息 服务实现类
 * </p>
 *
 * @author yangyl
 * @since 2020-05-16
 */
@Slf4j
@Service
public class ImageInfoServiceImpl extends ServiceImpl<ImageInfoMapper, ImageInfo> implements ImageInfoService {


    @Value("${spring.servlet.multipart.location}")
    private String majorPath;


    @Override
    public Response uploadFile(MultipartFile file, Integer type, String userCode, String fileName) {
        String path = PathUtil.generateSavePath(majorPath, String.valueOf(userCode), String.valueOf(type));
        File dictionary = new File(path);
        if (!dictionary.exists()) dictionary.mkdirs();
        String generateFileName = PathUtil.generateFileName(fileName);
        String realPath = PathUtil.generateFilePath(path, generateFileName);
        try {
            byte[] bytes = FileUtil.readInputStream(file.getInputStream());
            log.info("上传文件字节数  " + bytes.length);
            FileUtil.saveToFile(bytes, new File(realPath), false);
        } catch (IOException e) {
            log.error("internal eror, write file error.");
            return Response.err("");
        }
        String pictureCode = IdWorker.getIdStr();
        ImageInfo entity = ImageInfo.builder()
                .code(pictureCode)
                .fileName(fileName)
                .imgType(type)
                .imgUrl(realPath)
                .build();
        try {
            this.save(entity);
        } catch (Exception e) {
            log.error(e.getMessage());
            return Response.err("上传失败");
        }
        return Response.ok(String.valueOf(pictureCode));
    }

    @Override
    public Response queryByCode(String pictureCode) {
        ImageInfo picture = this.baseMapper.selectOne
                (new QueryWrapper<ImageInfo>().eq("code", pictureCode));
        if (picture == null) {
            log.error("internal eror, error picture_code.");
            return Response.err("图片不存在");
        }
        return Response.ok(picture);
    }

    @Override
    public List<String> list(String customerCode, String vehicleCode, Integer type) {
        QueryWrapper<ImageInfo> query = new QueryWrapper();
        if (customerCode != null) {
            query.eq("customer_code", customerCode);
        }
        if (vehicleCode != null) {
            query.eq("vehicle_code", vehicleCode);
        }
        if (type != null) {
            query.eq("img_type", type);
        }
        List<String> collect = this.list(query).stream().map(ImageInfo::getCode).collect(Collectors.toList());
        return collect;
    }

    @Override
    public List<ImageInfo> list(List<String> code, Integer type) {
        QueryWrapper<ImageInfo> query = new QueryWrapper();
        if (code != null && code.size() > 0) {
            query.in("code", code);
        }
        if (type != null) {
            query.eq("img_type", type);
        }
        return this.list(query);
    }

    @Override
    public void updateImageCode(List<String> code, String customerCode, String vehicleCode) {
        List<ImageInfo> images = this.list(new QueryWrapper<ImageInfo>()
                .in("code", code))
                .stream()
                .map(new Function<ImageInfo, ImageInfo>() {
                    @Override
                    public ImageInfo apply(ImageInfo imageInfo) {
                        if (customerCode != null) {
                            imageInfo.setCustomerCode(customerCode);
                        }
                        if (vehicleCode != null) {
                            imageInfo.setVehicleCode(vehicleCode);
                        }
                        return imageInfo;
                    }
                }).collect(Collectors.toList());
        this.updateBatchById(images);
    }

    @Override
    public void removeUpdateCode(List<String> code, String customerCode, String vehicleCode, Integer type) {
        QueryWrapper<ImageInfo> removeQuery = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(customerCode)) {
            removeQuery.eq("customer_code", customerCode);
        }
        if (StringUtils.isNotEmpty(vehicleCode)) {
            removeQuery.eq("vehicle_code", vehicleCode);
        }
        if (type != null) {
            removeQuery.eq("img_type", type);
        }
        if (code != null && code.size() > 0) {
            removeQuery.notIn("code", code);
        }
        this.remove(removeQuery);
        QueryWrapper<ImageInfo> query = new QueryWrapper<>();
        if (code != null && code.size() > 0) {
            query.in("code", code);
        }
        List<ImageInfo> list = this.list(query)
                .stream().map(new Function<ImageInfo, ImageInfo>() {
                    @Override
                    public ImageInfo apply(ImageInfo imageInfo) {
                        if (customerCode != null) {
                            imageInfo.setCustomerCode(customerCode);
                        }
                        if (vehicleCode != null) {
                            imageInfo.setVehicleCode(vehicleCode);
                        }
                        return imageInfo;
                    }
                }).collect(Collectors.toList());
        this.saveOrUpdateBatch(list);
    }
}
