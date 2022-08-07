package com.wengzw.blood.poster.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 意见反馈表
 *
 * @author wengzw
 * @date 2022/8/7 16:49
 */
@Data
public class Opinion implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    public String id;
    public String content;
    public String name;
    public String phone;
    @TableField(fill = FieldFill.INSERT)
    public Date gmtCreate;

}
