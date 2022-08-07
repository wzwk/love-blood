package com.wengzw.blood.poster.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 关注和帮助表
 *
 * @author wengzw
 * @date 2022/8/7 10:29
 */

@Data
public class Help implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer userId;
    private Integer posterId;
    private Integer status;
    private Integer hid;
}
