package com.wengzw.blood.poster.service.poster;

import com.wengzw.blood.common.entity.ResponseResult;

/**
 * @author wengzw
 * @date 2022/8/7 10:32
 */
public interface HelpService {
    /**
     * 加入关注文章
     * @param token
     * @param posterId
     * @return
     */
    ResponseResult insertConcernInfo(String token,Integer posterId);

    ResponseResult insertHelpInfo(String token, Integer posterId);

    ResponseResult getHelpInfoByPosterId(Integer posterId);

    ResponseResult getMyHelp(String token);

    ResponseResult deleteMyHelp(String token, Integer posterId);

    ResponseResult getMyConcernInfo(String token);

    ResponseResult deleteConcernInfo(Integer posterId);
}
