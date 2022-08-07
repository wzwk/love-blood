package com.wengzw.blood.poster.service.poster;

import com.wengzw.blood.common.entity.ResponseResult;

/**
 * @author wengzw
 * @date 2022/8/6 23:04
 */
public interface BloodStoreService {
    /**
     * 获取血液库存信息
     * @param areaName
     * @return
     */
    ResponseResult getBloodStore(String areaName);
}
