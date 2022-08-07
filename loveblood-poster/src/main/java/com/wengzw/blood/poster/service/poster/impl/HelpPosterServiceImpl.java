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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wengzw
 * @date 2022/8/2 22:43
 */

@Service
@RequiredArgsConstructor
@Slf4j
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
        Integer posterId = baseMapper.selectByUserId(helpPoster.getUserId());
        return new ResponseResult(RespStatusEnum.SUCCESS,posterId);
    }

    /**
     * 获取寻求帮助文章
     * @return
     */
    @Override
    public List<HelpPoster> getHelpPoster() {
        return helpPosterDao.getHelpPoster();
    }

    /**
     * 根据文章id 获取信息
     * @param posterId
     * @return
     */
    @Override
    public ResponseResult getHelpPosterByPosterId(Integer posterId) {
        HelpPoster helpPosterByPosterId = baseMapper.getHelpPosterByPosterId(posterId);
        return new ResponseResult(RespStatusEnum.SUCCESS,helpPosterByPosterId);
    }
}
