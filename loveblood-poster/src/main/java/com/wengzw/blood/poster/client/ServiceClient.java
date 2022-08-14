package com.wengzw.blood.poster.client;

import com.wengzw.blood.common.entity.AuthUser;
import com.wengzw.blood.common.entity.ResponseResult;
import com.wengzw.blood.common.entity.vo.AuthUserVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;


/**
 * 调用第三方服务的客户端
 *
 * @author wengzw
 * @date 2022/8/13 18:58
 */
@Component
@FeignClient(name = "loveblood-service", fallback = ServiceClientFallback.class)
public interface ServiceClient {
    @PostMapping("/service/registerSuccess")
    ResponseResult registerSuccess(@RequestBody AuthUserVo user);
}
