package com.wengzw.blood.poster.service.poster.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wengzw.blood.common.entity.ResponseResult;
import com.wengzw.blood.common.enums.RespStatusEnum;
import com.wengzw.blood.common.utils.JwtUtils;
import com.wengzw.blood.poster.dao.HelpDao;
import com.wengzw.blood.poster.entity.Help;
import com.wengzw.blood.poster.entity.vo.HelpVo;
import com.wengzw.blood.poster.service.poster.HelpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wengzw
 * @date 2022/8/7 10:32
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class HelpServiceImpl extends ServiceImpl<HelpDao, Help> implements HelpService {
    private final HelpDao helpDao;

    @Override
    public ResponseResult insertConcernInfo(String token, Integer posterId) {
        Integer userID = Integer.valueOf(JwtUtils.parse(token).get("id"));
        // 查询是否已经关注过这篇寻求文章
        QueryWrapper<Help> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userID);
        wrapper.eq("poster_id", posterId);
        wrapper.eq("hid", 0);
        Help concern = baseMapper.selectOne(wrapper);
        if (concern == null) {
            Help help = new Help();
            help.setUserId(userID);
            help.setPosterId(posterId);
            help.setStatus(1);
            help.setHid(0);
            baseMapper.insert(help);
            return new ResponseResult(RespStatusEnum.SUCCESS, help.getId());
        }
        return new ResponseResult(RespStatusEnum.FAIL, "您已关注，请在我的关注列表查询!");
    }

    /**
     * 获取我的关注信息
     *
     * @param token
     * @return
     */
    @Override
    public ResponseResult getMyConcernInfo(String token) {
        String userId = JwtUtils.parse(token).get("id");
        List<HelpVo> list = helpDao.getMyConcernInfo(Integer.valueOf(userId));
        return new ResponseResult(RespStatusEnum.SUCCESS,list);
    }

    @Override
    public ResponseResult deleteConcernInfo(Integer id) {
        QueryWrapper<Help> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        int delete = baseMapper.delete(wrapper);
        if (delete < 1) {
            return new ResponseResult(RespStatusEnum.FAIL);
        }
        return new ResponseResult(RespStatusEnum.SUCCESS);
    }


    /**
     * 加入帮助信息
     *
     * @param token
     * @param posterId
     * @return
     */
    @Override
    public ResponseResult insertHelpInfo(String token, Integer posterId) {
        Integer userID = Integer.valueOf(JwtUtils.parse(token).get("id"));
        // 查询是否已经关注过这篇寻求文章
        QueryWrapper<Help> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userID);
        wrapper.eq("poster_id", posterId);
        wrapper.eq("hid", 1);
        Help helpInfo = baseMapper.selectOne(wrapper);
        if (helpInfo == null) {
            Help help = new Help();
            help.setUserId(userID);
            help.setPosterId(posterId);
            help.setStatus(1);
            help.setHid(1);
            baseMapper.insert(help);
            return new ResponseResult(RespStatusEnum.SUCCESS, help.getId());
        }
        return new ResponseResult(RespStatusEnum.FAIL, "您已经成功关注，请及时到医疗站点献血！");
    }

    /**
     * 根据文章id查看谁帮助了我
     *
     * @param posterId
     * @return
     */
    @Override
    public ResponseResult getHelpInfoByPosterId(Integer posterId) {
        List<HelpVo> helpInfo = helpDao.getHelpInfoByPosterId(posterId);
        return new ResponseResult(RespStatusEnum.SUCCESS,helpInfo);
    }

    /**
     * 查看我帮助了谁
     *
     * @param token
     * @return
     */
    @Override
    public ResponseResult getMyHelp(String token) {
        String userID = JwtUtils.parse(token).get("id");
        List<HelpVo> myHelpInfo = baseMapper.getMyHelp(Integer.valueOf(userID));
        return new ResponseResult(RespStatusEnum.SUCCESS,myHelpInfo);
    }

    /**
     * 删除我的帮助文章
     *
     * @param token
     * @param posterId
     * @return
     */
    @Override
    public ResponseResult deleteMyHelp(String token, Integer posterId) {
        String userId = JwtUtils.parse(token).get("id");
        QueryWrapper<Help> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",Integer.valueOf(userId));
        wrapper.eq("poster_id",posterId);
        int delete = baseMapper.delete(wrapper);
        if (delete < 1) {
            return new ResponseResult(RespStatusEnum.FAIL);
        }
        return new ResponseResult(RespStatusEnum.SUCCESS);
    }

}
