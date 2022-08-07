package com.wengzw.blood.poster.service;

import com.wengzw.blood.common.entity.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author wengzw
 * @date 2022/8/6 23:46
 */
public interface OssService {

    ResponseResult uploadOssFile(MultipartFile[] files, String posterId);

    ResponseResult downOssFile(Integer id);

    ResponseResult deletePosterImages(String posterId, String imgIndex);
}
