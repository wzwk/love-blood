package com.wengzw.blood.poster.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 求助文章
 *
 * @author wengzw
 * @date 2022/8/2 22:39
 */
@Data
@ToString
public class HelpPoster implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 标题
     */
    private String title;
    /**
     * 所需血型
     */
    private String need;
    /**
     * 所需血型状态
     */
    private String status;
    /**
     * 内容
     */
    private String content;
    /**
     * 位置
     */
    private String place;
    /**
     * 注册时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 用户名字
     */
    @TableField(exist = false)
    private String userName;
}
