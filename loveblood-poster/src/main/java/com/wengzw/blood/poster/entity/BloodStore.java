package com.wengzw.blood.poster.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 血液库存表
 *
 * @author wengzw
 * @date 2022/8/6 23:03
 */

@Data
public class BloodStore implements Serializable {
    private static final long serialVersionUID = 1L;

    public Integer id;
    public Integer areaId;
    public Integer A;
    public Integer B;
    public Integer O;
    public Integer AB;

}
