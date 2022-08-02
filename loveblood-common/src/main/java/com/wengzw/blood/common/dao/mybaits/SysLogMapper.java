package com.wengzw.blood.common.dao.mybaits;

import com.wengzw.blood.common.entity.SysLog;
import com.wengzw.blood.common.entity.dto.SysLogDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wengzw
 * @date 2022/8/2 10:45
 */

@Mapper
@Component
public interface SysLogMapper {
    /**
     * 插入操作日志
     *
     * @param sysLogDO 操作日志
     */
    @Insert("insert into sys_log(user_id, operation_description, operation_type, operation_result, client_ip) " +
            " values (#{userId}, #{operationDescription}, #{operationType}, #{operationResult}, #{clientIp})")
    void insert(SysLog sysLogDO);

    /**
     * 获取操作日志列表
     *
     * @param sysLogDTO 查询参数
     * @return 操作日志列表
     */

    List<SysLog> list(SysLogDTO sysLogDTO);
}
