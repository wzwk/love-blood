package com.wengzw.blood.poster.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.ListObjectsRequest;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.wengzw.blood.common.entity.ResponseResult;
import com.wengzw.blood.common.enums.RespStatusEnum;
import com.wengzw.blood.poster.service.OssService;
import com.wengzw.blood.poster.utils.ConstantPropertiesUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wengzw
 * @date 2022/8/6 23:47
 */

@Service
public class OssServiceImpl implements OssService {
    @Override
    public ResponseResult uploadOssFile(MultipartFile[] files, String posterId) {
        boolean uploadSuccess = uploadImg(files, posterId);
        if (uploadSuccess) {
            return new ResponseResult(RespStatusEnum.SUCCESS, "上传成功!!");
        }
        return new ResponseResult(RespStatusEnum.SUCCESS, "上传失败!!!!");
    }


    @Override
    public ResponseResult downOssFile(Integer id) {
        List<String> list = downLoadImg(id);
        if (CollectionUtil.isNotEmpty(list)) {
            return new ResponseResult(RespStatusEnum.SUCCESS,list);
        }
        return new ResponseResult(RespStatusEnum.FAIL,"下载失败");
    }

    @Override
    public ResponseResult deletePosterImages(String posterId, String imgIndex) {
        boolean deleteSuccess = deleteImg(posterId, imgIndex);
        if (deleteSuccess) {
            return new ResponseResult(RespStatusEnum.SUCCESS,"图片成功删除");
        }
        return new ResponseResult(RespStatusEnum.FAIL);
    }

    /**
     * 删除文件
     *
     * @param id
     * @param imgIndex
     * @return
     */
    public boolean deleteImg(String id,String imgIndex) {
        try {
            //工具栏获取值
            String endpoint = ConstantPropertiesUtils.END_POIND;
            String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
            String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
            String bucketName = ConstantPropertiesUtils.BUCKET_NAME;
            String objectName = "imgs/"+ id + "/" + imgIndex;

            // 创建OSSClient实例
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            // 删除文件或目录。如果要删除目录，目录必须为空。
            ossClient.deleteObject(bucketName, objectName);
            // 关闭client
            ossClient.shutdown();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 下载文件
     *
     * @param poster_id
     * @return
     */
    public List<String> downLoadImg(Integer poster_id) {
        ArrayList<String> imgs = new ArrayList<>();
        //工具栏获取值
        String endpoint = ConstantPropertiesUtils.END_POIND;
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;

        // 创建OSSClient实例
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 构造ListObjectsRequest请求
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest(bucketName);
        //Delimiter 设置为 “/” 时，罗列该文件夹下的文件
        listObjectsRequest.setDelimiter("/");
        //Prefix 设为某个文件夹名，罗列以此 Prefix 开头的文件
        listObjectsRequest.setPrefix("imgs/" + poster_id + "/");

        ObjectListing listing = ossClient.listObjects(listObjectsRequest);

        // 遍历所有Object:目录下的文件
        for (OSSObjectSummary objectSummary : listing.getObjectSummaries()) {
            //key：imgs/postid/001.avi等，即：Bucket中存储文件的路径
            String key = objectSummary.getKey();
            String url = "https://edu-8887.oss-cn-beijing.aliyuncs.com";
            imgs.add(url + "/" + key);
        }
        // 关闭client
        ossClient.shutdown();
        return imgs;
    }

    /**
     * 上传文件
     *
     * @param files
     * @param posterId
     * @return
     */
    public boolean uploadImg(MultipartFile[] files, String posterId) {
        String uploadFilePath = "imgs";
        String endpoint = ConstantPropertiesUtils.END_POIND;
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            for (int i = 0; i < files.length; i++) {
                // 获取文件的上传输入流
                InputStream inputStream = files[i].getInputStream();
                String fileName = files[i].getOriginalFilename();  // 文件名
                fileName = uploadFilePath + "/" + posterId + "/" + fileName;
                System.out.println(fileName);
                // 调用oss实现文件上传
                ossClient.putObject(bucketName, fileName, inputStream);
            }
            // 关闭OSSClient
            ossClient.shutdown();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
