package com.wengzw.blood.poster.client;

import com.wengzw.blood.common.entity.ResponseResult;
import com.wengzw.blood.common.entity.vo.AuthUserVo;
import com.wengzw.blood.common.enums.RespStatusEnum;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * 调用第三方出错回滚的方法
 *
 * @author wengzw
 * @date 2022/8/13 18:59
 */
@Component
public class ServiceClientFallback implements ServiceClient{

    @Override
    public ResponseResult registerSuccess(@RequestBody AuthUserVo user) {
        return new ResponseResult(RespStatusEnum.FAIL,"邮件服务发送有误");
    }
}
