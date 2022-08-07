package com.wengzw.blood.poster.service.poster.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wengzw.blood.common.entity.ResponseResult;
import com.wengzw.blood.common.enums.RespStatusEnum;
import com.wengzw.blood.poster.dao.AppointBloodDao;
import com.wengzw.blood.poster.dao.BloodStoreDao;
import com.wengzw.blood.poster.entity.AppointBlood;
import com.wengzw.blood.poster.entity.BloodStore;
import com.wengzw.blood.poster.service.poster.AppointBloodService;
import com.wengzw.blood.poster.service.poster.BloodStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author wengzw
 * @date 2022/8/6 23:05
 */

@Service
@RequiredArgsConstructor
public class BloodStoreServiceImpl extends ServiceImpl<BloodStoreDao, BloodStore> implements BloodStoreService {

    private final BloodStoreDao bloodStoreDao;

    @Override
    public ResponseResult getBloodStore(String areaName) {
        BloodStore bloodStore =  bloodStoreDao.getBloodStore(areaName);
        return new ResponseResult(RespStatusEnum.SUCCESS,bloodStore);
    }
}
