package com.wengzw.blood.common.dao.mybaits;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wengzw.blood.common.entity.AuthUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author wengzw
 * @date 2022/7/30 10:33
 */
public interface UserDao extends BaseMapper<AuthUser> {
    /**
     * 通过用户ID获取用户信息
     *
     * @param id 用户ID
     * @return 用户信息对象
     */
    @Select("SELECT * FROM auth_user WHERE id = #{id}")
    AuthUser getUserById(Integer id);

    /**
     * 通过用户名获取用户信息
     *
     * @param username 用户电话
     * @return 用户信息对象
     */
    @Select("SELECT * FROM auth_user WHERE mobile = #{username}")
    AuthUser getUserByUser(String username);

    /**
     * 添加用户
     *
     * @param username
     * @param password
     * @param mobile
     * @param email
     * @param avatar  用户头像
     * @param disabled
     */
    @Insert("INSERT INTO auth_user (userName,password,mobile,email,avatar,is_disabled) " +
            "VALUE (#{username},#{password},#{mobile},#{email},#{avatar},#{disabled})")
    int addUser(@Param("username") String username,
                 @Param("password") String password,
                 @Param("mobile") String mobile,
                 @Param("email") String email,
                 @Param("avatar") String avatar,
                 @Param("disabled") boolean disabled);
}

