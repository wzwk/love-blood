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

    /**
     * 查询预约信息
     * @param token
     * @return
     */
    ResponseResult getAppointBloodInfo(String token);

    /**
     * 修改预约献血状态
     * @return
     */
    ResponseResult updateStatus(Integer id);

    /**
     * 删除预约信息
     * @param id
     * @return
     */
    ResponseResult deleteAppointBlood(Integer id);
}
