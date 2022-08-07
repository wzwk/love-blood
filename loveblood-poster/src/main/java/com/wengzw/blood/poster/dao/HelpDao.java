package com.wengzw.blood.poster.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wengzw.blood.poster.entity.Help;
import com.wengzw.blood.poster.entity.vo.HelpVo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author wengzw
 * @date 2022/8/7 10:31
 */


public interface HelpDao extends BaseMapper<Help> {

    @Select("SELECT h.* ,u.userName,gender,mobile,idcard,bloodtype,integral FROM `help` h\n" +
            "INNER JOIN auth_user u WHERE u.id = h.user_id AND h.hid = 1\n" +
            "and h.poster_id = #{posterId}")
    List<HelpVo> getHelpInfoByPosterId(Integer posterId);


    @Select("select h.*,u.userName,p.title,need,content,p.gmt_create,place \n" +
            "from `help` h INNER JOIN help_poster p \n" +
            "INNER JOIN auth_user u on h.poster_id = p.id and p.user_id = u.id and\n" +
            "h.hid = 1 AND h.user_id = #{userId}")
    List<HelpVo> getMyHelp(Integer userId);

    @Select("SELECT h.id,h.user_id,poster_id,u.userName,p.title,need,\n" +
            "p.`status`,content,place,p.gmt_create FROM `help` h \n" +
            "left JOIN help_poster p on p.id = h.poster_id\n" +
            "left JOIN auth_user u   on h.hid = 0 and\n" +
            "h.user_id = u.id and h.user_id = #{userID}")
    List<HelpVo> getMyConcernInfo(Integer userId);
}
