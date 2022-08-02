package com.wengzw.blood.common.entity;

import lombok.Data;

/**
 * @author wengzw
 * @date 2022/8/2 10:41
 */
@Data
public class SysLog {
    /**
     * 操作人
     */
    private String userId;
    /**
     * 操作描述
     */
    private String operationDescription;
    /**
     * 操作类型
     */
    private String operationType;
    /**
     * 操作结果（0-失败，1-成功）
     */
    private Integer operationResult;
    /**
     * 客户端IP
     */
    private String clientIp;
    /**
     * 创建时间
     */
    private String createTime;
}
