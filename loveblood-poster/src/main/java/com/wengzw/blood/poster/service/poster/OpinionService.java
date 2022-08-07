package com.wengzw.blood.poster.service.poster;

import com.wengzw.blood.common.entity.ResponseResult;

/**
 * @author wengzw
 * @date 2022/8/7 16:52
 */
public interface OpinionService {
    ResponseResult insertOpinionInfo(String name, String phone, String content);
}
