package com.wengzw.blood.poster.service.poster.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wengzw.blood.common.entity.ResponseResult;
import com.wengzw.blood.common.enums.RespStatusEnum;
import com.wengzw.blood.common.utils.JwtUtils;
import com.wengzw.blood.poster.dao.HelpPosterDao;
import com.wengzw.blood.poster.entity.HelpPoster;
import com.wengzw.blood.poster.service.poster.HelpPosterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
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
     *
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
        return new ResponseResult(RespStatusEnum.SUCCESS, posterId);
    }

    /**
     * 获取寻求帮助文章
     *
     * @return
     */
    @Override
    public List<HelpPoster> getHelpPoster() {
        return helpPosterDao.getHelpPoster();
    }

    /**
     * 根据文章id 获取信息
     *
     * @param posterId
     * @return
     */
    @Override
    public ResponseResult getHelpPosterByPosterId(Integer posterId) {
        HelpPoster helpPosterByPosterId = baseMapper.getHelpPosterByPosterId(posterId);
        return new ResponseResult(RespStatusEnum.SUCCESS, helpPosterByPosterId);
    }

    /**
     * 根据用户id 获取用户自己发出的献血请求
     *
     * @param token
     * @return
     */
    @Override
    public ResponseResult getHelpPosterByUserId(String token) {
        String userId = JwtUtils.parse(token).get("id");
        QueryWrapper<HelpPoster> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", Integer.valueOf(userId));
        List<HelpPoster> helpPosters = baseMapper.selectList(wrapper);
        return new ResponseResult(RespStatusEnum.SUCCESS, helpPosters);
    }

    /**
     * 修改我的寻求文章信息
     *
     * @param helpPoster
     * @return
     */
    @Override
    public ResponseResult modifyHelpPosterInfo(HelpPoster helpPoster) {
        UpdateWrapper<HelpPoster> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", helpPoster.getId());
        helpPoster.setGmtCreate(new Date());
        helpPoster.setStatus("正常".equals(helpPoster.getStatus()) ? "1" : "0");
        int update = baseMapper.update(helpPoster, updateWrapper);
        if (update < 1) {
            return new ResponseResult(RespStatusEnum.FAIL);
        }
        return new ResponseResult(RespStatusEnum.SUCCESS);
    }

    /**
     * 删除我的寻求文章
     *
     * @param posterId
     * @return
     */
    @Override
    public ResponseResult deleteHelpPosterInfo(Integer posterId) {
        int deleteSuccess = baseMapper.deleteById(posterId);
        if (deleteSuccess < 1) {
            return new ResponseResult(RespStatusEnum.FAIL);
        }
        return new ResponseResult(RespStatusEnum.SUCCESS);
    }
}
