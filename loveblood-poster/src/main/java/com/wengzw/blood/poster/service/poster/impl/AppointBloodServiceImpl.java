package com.wengzw.blood.poster.service.poster.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wengzw.blood.common.entity.ResponseResult;
import com.wengzw.blood.common.enums.RespStatusEnum;
import com.wengzw.blood.common.exception.JsonException;
import com.wengzw.blood.common.utils.JwtUtils;
import com.wengzw.blood.poster.dao.AppointBloodDao;
import com.wengzw.blood.poster.entity.AppointBlood;
import com.wengzw.blood.poster.service.poster.AppointBloodService;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.units.qual.A;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wengzw
 * @date 2022/8/4 23:24
 */

@Service
@RequiredArgsConstructor
public class AppointBloodServiceImpl extends ServiceImpl<AppointBloodDao, AppointBlood> implements AppointBloodService {

    private final AppointBloodDao appointBloodDao;

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
        if (appoint > 0) {
            return new ResponseResult(RespStatusEnum.FAIL,"您已预约，请勿重复预约(⊙o⊙)");
        }
        int insert = baseMapper.insert(appointblood);
        if (insert < 1) {
            throw new JsonException(ResponseResult.fail());
        }
        return new ResponseResult(RespStatusEnum.SUCCESS,"您已预约成功，请准时前往医疗站点献血");
    }

    @Override
    public ResponseResult getAppointBloodInfo(String token) {
        String userId = JwtUtils.parse(token).get("id");
        List<AppointBlood> appointBloodInfo = appointBloodDao.getAppointBloodInfo(Integer.valueOf(userId));
        return new ResponseResult(RespStatusEnum.SUCCESS,appointBloodInfo);
    }

    /**
     * 修改预约献血状态
     * @return
     */
    @Override
    public ResponseResult updateStatus(Integer id) {
        UpdateWrapper<AppointBlood> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",id);
        updateWrapper.set("status",1);
        AppointBlood appointBlood = new AppointBlood();
        int update = baseMapper.update(appointBlood, updateWrapper);
        if (update < 1) {
            return new ResponseResult(RespStatusEnum.FAIL);
        }
        return new ResponseResult(RespStatusEnum.SUCCESS);
    }

    @Override
    public ResponseResult deleteAppointBlood(Integer id) {
        Map<String,Object> map = new HashMap<String,Object>(){{
            put("id",id);
        }};
        int delete = baseMapper.deleteByMap(map);
        if (delete < 1) {
            return new ResponseResult(RespStatusEnum.FAIL);
        }
        return new ResponseResult(RespStatusEnum.SUCCESS);
    }


}
















