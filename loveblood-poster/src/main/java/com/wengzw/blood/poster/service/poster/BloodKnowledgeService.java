package com.wengzw.blood.poster.service.poster;

import com.wengzw.blood.poster.entity.BloodKnowledge;

import java.util.List;

/**
 * @author wengzw
 * @date 2022/8/2 21:45
 */

public interface BloodKnowledgeService {
    List<BloodKnowledge> getBloodKnowledgeList(int bid);

    BloodKnowledge getBloodKnowledgeById(int id);
}
