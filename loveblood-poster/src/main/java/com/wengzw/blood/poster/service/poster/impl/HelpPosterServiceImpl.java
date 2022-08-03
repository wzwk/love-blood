package com.wengzw.blood.poster.service.poster.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wengzw.blood.common.entity.ResponseResult;
import com.wengzw.blood.common.enums.RespStatusEnum;
import com.wengzw.blood.poster.dao.BloodKnowledgeDao;
import com.wengzw.blood.poster.dao.HelpPosterDao;
import com.wengzw.blood.poster.entity.BloodKnowledge;
import com.wengzw.blood.poster.entity.HelpPoster;
import com.wengzw.blood.poster.service.poster.BloodKnowledgeService;
import com.wengzw.blood.poster.service.poster.HelpPosterService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wengzw
 * @date 2022/8/2 22:43
 */

@Service
@RequiredArgsConstructor
public class HelpPosterServiceImpl extends ServiceImpl<HelpPosterDao, HelpPoster> implements HelpPosterService {

    private final HelpPosterDao helpPosterDao;

    /**
     * 插入寻求文章
     * @param helpPoster
     * @return
     */
    @Override
    public ResponseResult insertHelpPoster(HelpPoster helpPoster) {
        int insert = baseMapper.insert(helpPoster);
        if (insert < 1) {
            return new ResponseResult(RespStatusEnum.FAIL);
        }
        return new ResponseResult(RespStatusEnum.SUCCESS);
    }
}
