package com.wengzw.blood.poster.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 预约献血表
 *
 * @author wengzw
 * @date 2022/8/4 23:21
 */

@Data
public class AppointBlood implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String place;
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;
    private Integer userId;
    private Integer status;

    @TableField(exist = false)
    private String userName;
    @TableField(exist = false)
    private Integer gender;
    @TableField(exist = false)
    private String bloodType;
}
