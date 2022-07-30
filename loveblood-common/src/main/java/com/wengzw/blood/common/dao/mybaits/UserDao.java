package com.wengzw.blood.common.dao.mybaits;

import com.wengzw.blood.common.entity.AuthUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author wengzw
 * @date 2022/7/30 10:33
 */
public interface UserDao {
    /**
     * 通过用户ID获取用户信息
     *
     * @param id 用户ID
     * @return 用户信息对象
     */
    @Select("SELECT * FROM user WHERE id = #{id}")
    AuthUser getUserById(Integer id);

    /**
     * 通过用户名获取用户信息
     *
     * @param username 用户名
     * @return 用户信息对象
     */
    @Select("SELECT * FROM user WHERE userName = #{username}")
    AuthUser getUserByUser(String username);

    /**
     * 添加用户
     *
     * @param username  用户名
     * @param password  密码
     * @return 受影响的表行数
     */
    @Insert("INSERT INTO user (userName,password) VALUE (#{username},#{password})")
    int addUser(@Param("username") String username,
                @Param("password") String password);
}

