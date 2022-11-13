package cn.itcast.mq.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringAmqpTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSendMessage2SimpleQueue() {
        String queueName = "simple.queue";
        String message = "hello, spring amqp!";
        rabbitTemplate.convertAndSend(queueName, message);
    }

    @Test
    public void testWorkQueue() throws InterruptedException {
        String queueName = "simple.queue";
        String message = "hello, message_";

        for (int i = 0; i < 50; i++) {
            rabbitTemplate.convertAndSend(queueName, message + i);
            Thread.sleep(20);
        }
    }

    @Test
    public void testSendFanoutExchange() {
        String exchangeName = "chu.fanout";
        String message = "hello, everyone!";

        rabbitTemplate.convertAndSend(exchangeName, "", message);
    }

    @Test
    public void testSendDirectExchange() {
        String exchangeName = "chu.direct";

        String message = "hello, blue";

        rabbitTemplate.convertAndSend(exchangeName, "blue", message);
    }

    @Test
    public void testSendTopicExchange() {
        String exchangeName = "chu.topic";

        String message = "哈哈哈哈哈";

        rabbitTemplate.convertAndSend(exchangeName, "china.news", message);
    }

    @Test
    public void testSendObjectQueue() {
        Map<String, Object> msg = new HashMap<>();
        msg.put("name", "艾瑞利亚");
        msg.put("age", 21);
        rabbitTemplate.convertAndSend("object.queue", msg);
    }
}
