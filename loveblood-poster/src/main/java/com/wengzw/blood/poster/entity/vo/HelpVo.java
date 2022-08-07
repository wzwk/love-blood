package com.wengzw.blood.poster.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * @author wengzw
 * @date 2022/8/7 15:26
 */

@Data
public class HelpVo implements Serializable {
    private static final long serialVersionUID = 1L;

    public String id;
    public Integer userId;
    public Integer posterId;
    public Integer status;

    public String userName;

    public String title;

    public String bloodtype;

    public int integral;

    public String content;

    public String need;

    public String place;
}
