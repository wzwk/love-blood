package com.wengzw.blood.poster.service.poster.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wengzw.blood.poster.entity.BloodKnowledge;
import com.wengzw.blood.poster.dao.BloodKnowledgeDao;
import com.wengzw.blood.poster.service.poster.BloodKnowledgeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wengzw
 * @date 2022/8/2 21:46
 */

@Service
@RequiredArgsConstructor
public class BloodKnowledgeServiceImpl extends ServiceImpl<BloodKnowledgeDao, BloodKnowledge> implements BloodKnowledgeService {

    /**
     * 根据条件获取献血知识库信息
     * @return
     */
    @Override
    public List<BloodKnowledge> getBloodKnowledgeList(int bid) {
        QueryWrapper<BloodKnowledge> wrapper = new QueryWrapper<>();
        wrapper.eq("bid",bid);
        return baseMapper.selectList(wrapper);
    }

    /**
     * 根据献血流程id获取详情
     * @param id
     * @return
     */
    @Override
    public BloodKnowledge getBloodKnowledgeById(int id) {
        return baseMapper.selectById(id);
    }


}
