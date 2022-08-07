package com.wengzw.blood.poster.service.poster.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wengzw.blood.common.entity.ResponseResult;
import com.wengzw.blood.common.enums.RespStatusEnum;
import com.wengzw.blood.poster.dao.HelpDao;
import com.wengzw.blood.poster.dao.OpinionDao;
import com.wengzw.blood.poster.entity.Help;
import com.wengzw.blood.poster.entity.Opinion;
import com.wengzw.blood.poster.service.poster.HelpService;
import com.wengzw.blood.poster.service.poster.OpinionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author wengzw
 * @date 2022/8/7 16:53
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class OpinionServiceImpl extends ServiceImpl<OpinionDao, Opinion> implements OpinionService {

    @Override
    public ResponseResult insertOpinionInfo(String name, String phone, String content) {
        Opinion opinion = new Opinion();
        opinion.setContent(content);
        opinion.setName(name);
        opinion.setPhone(phone);
        opinion.setGmtCreate(new Date());
        int insert = baseMapper.insert(opinion);
        if (insert < 1) {
            return new ResponseResult(RespStatusEnum.FAIL);
        }
        return new ResponseResult(RespStatusEnum.SUCCESS);
    }
}
