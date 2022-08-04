package com.wengzw.blood.poster.service.poster;

import com.wengzw.blood.common.entity.ResponseResult;
import com.wengzw.blood.poster.entity.AppointBlood;

/**
 * @author wengzw
 * @date 2022/8/4 23:24
 */
public interface AppointBloodService {
    /**
     * 预约献血
     * @param appointblood
     * @return
     */
    ResponseResult appointBlood(AppointBlood appointblood);
}
