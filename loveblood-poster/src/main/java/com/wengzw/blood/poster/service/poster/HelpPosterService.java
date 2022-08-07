package com.wengzw.blood.poster.service.poster;

import com.wengzw.blood.common.entity.ResponseResult;
import com.wengzw.blood.poster.entity.HelpPoster;

import java.util.List;

/**
 * @author wengzw
 * @date 2022/8/2 22:43
 */
public interface HelpPosterService {
    ResponseResult insertHelpPoster(HelpPoster helpPoster);

    List<HelpPoster> getHelpPoster();

    ResponseResult getHelpPosterByPosterId(Integer posterId);
}
