package com.wengzw.blood.poster.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wengzw.blood.poster.entity.AppointBlood;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author wengzw
 * @date 2022/8/4 23:23
 */
public interface AppointBloodDao extends BaseMapper<AppointBlood> {

    @Select("SELECT a.id,a.place,a.`status`,a.user_id,a.gmt_create,\n" +
            "`auth_user`.userName,`auth_user`.bloodtype,`auth_user`.gender \n" +
            "FROM `appoint_blood` a\n" +
            "inner join `auth_user` \n" +
            "where `auth_user`.id=a.user_id and `auth_user`.id = #{userId}")
    List<AppointBlood> getAppointBloodInfo(Integer userId);
}
