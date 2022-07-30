package com.wengzw.blood.gateway.dao;

import com.wengzw.blood.common.dao.redis.RedisDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Set;

/**
 * @author wengzw
 * @date 2022/7/30 10:49
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class RedisDaoImpl implements RedisDao {

    private final RedisTemplate<String, String> redisTemplate;

//    private final BlackIpDao blackIpDao;

    @Override
    public Set<String> scanKeys(String pattern) {
        return null;
    }

    @Override
    public Long decrementAndGet(String key, int step, int min) {
        return null;
    }

//    @Override
//    @PostConstruct
//    public void init() {
//        //每次生成之前需清除之前的黑名单
//        Boolean hasBlackIp = redisTemplate.hasKey("blackIp");
//        if (hasBlackIp) {
//            redisTemplate.delete("blackIp");
//        }
//        //查询数据库的黑名单
//        List<BlackIp> blackIps = blackIpDao.blackIpList();
//        for (BlackIp blackIp : blackIps) {
//            redisTemplate.opsForSet().add("blackIp", blackIp.getIp());
//        }
//    }

}

