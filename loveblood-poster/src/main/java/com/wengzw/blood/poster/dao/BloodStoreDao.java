package com.wengzw.blood.poster.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wengzw.blood.poster.entity.BloodStore;
import org.apache.ibatis.annotations.Select;

/**
 * @author wengzw
 * @date 2022/8/6 23:03
 */
public interface BloodStoreDao extends BaseMapper<BloodStore> {

    @Select("SELECT b.*,t.areaName \n" +
            "from blood_store b \n" +
            "INNER JOIN t_area t\n" +
            "where b.area_id = t.areaId \n" +
            "and t.areaName like concat('%',#{areaName}, '%')")
    BloodStore getBloodStore(String areaName);
}
