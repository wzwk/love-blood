package com.wengzw.blood.poster.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 献血知识库
 *
 * @author wengzw
 * @date 2022/8/2 21:37
 */

@Data
public class BloodKnowledge implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private Integer id;
    /**
     * 标题
     */
    private String title;
    /**
     * 误区解答
     */
    private String reason;
    /**
     * 详情
     */
    private String description;
    /**
     * 图片地址
     */
    private String imgurl;
    /**
     * 用于区分 1 献血流程  2献血误区 3血液百科 4关于我们
     */
    private Integer bid;
}
