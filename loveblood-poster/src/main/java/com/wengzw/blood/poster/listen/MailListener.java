package com.wengzw.blood.poster.listen;

import com.wengzw.blood.common.entity.AuthUser;
import com.wengzw.blood.common.entity.ResponseResult;
import com.wengzw.blood.common.entity.vo.AuthUserVo;
import com.wengzw.blood.poster.client.ServiceClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;


/**
 * 消费者队列
 * 只要有消息过来，就马上被消费 所以在界面上看不到消息信息
 * 除非把这个监听器进行关闭
 *
 * @author wengzw
 * @date 2022/8/13 18:39
 */

@Service
@Slf4j
@RequiredArgsConstructor
public class MailListener {

    private final ServiceClient serviceClient;

    /**
     * 消费者队列
     *
     * @param user 生产者的消息
     */
    @RabbitListener(queues = "MAIL_REGISTER_QUEUE")
    public void sendRegisterMail(AuthUserVo user) {
        ResponseResult responseResult = serviceClient.registerSuccess(user);
        if (responseResult.getCode() == -1) {
            log.error("邮件发送失败");
        } else {
            log.info("邮件发送成功！！！！！");
        }
    }
}
