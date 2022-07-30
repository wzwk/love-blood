package com.wengzw.blood.common.dao.redis;

import java.util.Set;

/**
 * @author wengzw
 * @date 2022/7/30 10:32
 */
public interface RedisDao {
    /**
     * 通过表达式使用scan方法扫描匹配的key（而不是keys）
     *
     * @param pattern key匹配表达式
     * @return 匹配的key集合
     */
    Set<String> scanKeys(String pattern);

    /**
     * 在限定范围内进行原子安全自减操作，自减成功时返回自减后的值，自减失败返回null
     *
     * @param key  要操作的key
     * @param step 自减不长
     * @param min  允许自减后的最小值
     * @return 自减结果或失败后的null
     */
    Long decrementAndGet(String key, int step, int min);

    /**
     * todo 将数据库的黑名单初始化到缓存
     */
//    public void init();
}

