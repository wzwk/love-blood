package com.wengzw.blood.poster.service.poster.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wengzw.blood.common.entity.ResponseResult;
import com.wengzw.blood.common.enums.RespStatusEnum;
import com.wengzw.blood.common.exception.JsonException;
import com.wengzw.blood.poster.dao.AppointBloodDao;
import com.wengzw.blood.poster.entity.AppointBlood;
import com.wengzw.blood.poster.service.poster.AppointBloodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author wengzw
 * @date 2022/8/4 23:24
 */

@Service
@RequiredArgsConstructor
public class AppointBloodServiceImpl extends ServiceImpl<AppointBloodDao, AppointBlood> implements AppointBloodService {

    /**
     * 预约献血
     *
     * @param appointblood
     * @return
     */
    @Override
    public ResponseResult appointBlood(AppointBlood appointblood) {
        // 查询是否预约过
        QueryWrapper<AppointBlood> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",appointblood.getUserId());
        Integer appoint = baseMapper.selectCount(wrapper);
        if (appoint < 1) {
            return new ResponseResult(RespStatusEnum.FAIL,"您已预约，请勿重复预约(⊙o⊙)");
        }
        int insert = baseMapper.insert(appointblood);
        if (insert < 1) {
            throw new JsonException(ResponseResult.fail());
        }
        return new ResponseResult(RespStatusEnum.SUCCESS,"您已预约成功，请准时前往医疗站点献血");
    }


}
















