package com.wengzw.blood.poster.service.impl;

import com.wengzw.blood.common.utils.RandomUtil;
import com.wengzw.blood.poster.service.EmailService;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author wengzw
 * @date 2022/7/31 14:06
 */
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean emailSend(String email) {
        // 清除缓存，保证用户重复提交时得到最新验证码
        redisTemplate.delete(email);
        // 获取验证码
        String code = RandomUtil.getFourBitRandom();
        // 判断是否发送成功
        boolean send = true;
        try {
            HtmlEmail htmlEmail = new HtmlEmail();
            htmlEmail.setHostName("smtp.qq.com");
            htmlEmail.setCharset("UTF-8");
            // 收件地址
            htmlEmail.addTo(email);
            htmlEmail.setFrom("2441525760@qq.com", "love_blood");
            htmlEmail.setAuthentication("2441525760@qq.com", "vecuetzbcuqteahf");
            htmlEmail.setSubject("爱献血通讯");
            htmlEmail.setMsg("【爱献血】尊敬的用户您好，您本次申请的验证码是：" + code + "，验证码5分钟内有效。");
            htmlEmail.send();
        } catch (Exception e) {
            e.printStackTrace();
            send = false;
        }
        if (send) {
            redisTemplate.opsForValue().set(email, code, 60, TimeUnit.SECONDS);
            return true;
        }
        return false;
    }
}
