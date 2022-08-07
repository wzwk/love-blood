package com.wengzw.blood.poster.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wengzw.blood.poster.entity.HelpPoster;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author wengzw
 * @date 2022/8/2 22:43
 */
public interface HelpPosterDao extends BaseMapper<HelpPoster> {

    @Select("select p.*,u.userName FROM help_poster p INNER JOIN auth_user u\n" +
            "where p.user_id = u.id ORDER BY p.gmt_create DESC")
    List<HelpPoster> getHelpPoster();

    @Select("SELECT id from help_poster where user_id = #{userId} ORDER BY gmt_create DESC LIMIT 1")
    Integer selectByUserId(Integer userId);

    @Select("select p.*,u.userName from help_poster p INNER JOIN auth_user u \n" +
            "where p.user_id = u.id  AND p.id = #{posterId}")
    HelpPoster getHelpPosterByPosterId(Integer posterId);

}
