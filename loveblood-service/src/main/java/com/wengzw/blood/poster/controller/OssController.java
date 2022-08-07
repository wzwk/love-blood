package com.wengzw.blood.poster.controller;

import com.wengzw.blood.common.entity.ResponseResult;
import com.wengzw.blood.poster.service.OssService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author wengzw
 * @date 2022/8/6 23:46
 */

@RestController
@RequestMapping(OssController.PREFIX)
@RequiredArgsConstructor
@Validated
@Slf4j
public class OssController {
    public static final String PREFIX = "/service";

    private final OssService ossService;

    /**
     * 上传图片
     *
     * @param files  文件
     * @param posterId 文章id
     * @return
     */
    @PostMapping("uploadOssFile")
    public ResponseResult uploadOssFile(@RequestParam("files") MultipartFile files[],
                                        String posterId) {
        return ossService.uploadOssFile(files,posterId);
    }

    /**
     * 文件下载
     * @param id
     * @return
     */
    @GetMapping("downOssFile/{id}")
    @ResponseBody
    public ResponseResult downOssFile(@PathVariable Integer id) {
        return ossService.downOssFile(id);
    }

    /**
     * 删除文件
     * @param posterId
     * @param imgIndex
     * @return
     */
    @DeleteMapping("deletePosterImages/{posterId}/{imgIndex}")
    @ResponseBody
    public ResponseResult deletePosterImages(@PathVariable String posterId,@PathVariable String imgIndex) {
        return ossService.deletePosterImages(posterId,imgIndex);
    }
}
