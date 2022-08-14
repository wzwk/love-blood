package com.wengzw.blood.poster.service.impl;

import com.wengzw.blood.common.entity.vo.AuthUserVo;
import com.wengzw.blood.common.utils.RandomUtil;
import com.wengzw.blood.poster.service.EmailService;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
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

    @Autowired
    private MailProperties mailProperties;

    @Override
    public boolean emailSend(String email) {
        // 清除缓存，保证用户重复提交时得到最新验证码
        redisTemplate.delete(email);
        // 获取验证码
        String code = RandomUtil.getFourBitRandom();
        // 判断是否发送成功
        boolean send = true;
        String msg = "【爱献血】尊敬的用户您好，您本次申请的验证码是：" + code + "，验证码5分钟内有效。";
        try {
            emailProperties(email, msg);
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

    /**
     * 用于给用户发送是否注册成功的邮件
     *
     * @param user
     * @return
     */
    @Override
    public boolean registerSuccess(AuthUserVo user) {
        // 判断是否发送成功
        boolean send = true;
        String msg = "【爱献血】尊敬的用户您好，您已注册成功 账号为"  + user.getUserName() + "-----" + "密码为" + user.getPassword() + "，请勿泄漏,祝您使用愉快!!";
        try {
            emailProperties(user.getEmail(), msg);
        } catch (Exception e) {
            e.printStackTrace();
            send = false;
        }
        return send;
    }

    /**
     * 邮件配置 发送邮件
     *
     * @param email 接收人
     * @param msg   接收信息
     * @throws EmailException
     */
    public void emailProperties(String email, String msg) throws EmailException {
        HtmlEmail htmlEmail = new HtmlEmail();
        htmlEmail.setHostName(mailProperties.getHost());
        htmlEmail.setCharset(mailProperties.getDefaultEncoding().toString());
        // 收件地址
        htmlEmail.addTo(email);
        htmlEmail.setFrom(mailProperties.getUsername(), "love_blood");
        htmlEmail.setAuthentication(mailProperties.getUsername(), mailProperties.getPassword());
        htmlEmail.setSubject("爱献血通讯");
        htmlEmail.setMsg(msg);
        htmlEmail.send();
    }

}
