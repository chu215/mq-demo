package cn.itcast.mq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// 基于Bean声明队列和交换机
@Configuration
public class FanoutConfig {
    // chu.fanout
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("chu.fanout");
    }

    // fanout.queue
    @Bean
    public Queue fanoutQueue1() {
        return new Queue("fanout.queue1");
    }

    // 绑定队列1到交换机
    @Bean
    public Binding fanoutBinding1(FanoutExchange fanoutExchange, Queue fanoutQueue1) {
        return BindingBuilder.bind(fanoutQueue1).to(fanoutExchange);
    }

    // fanout.queue
    @Bean
    public Queue fanoutQueue2() {
        return new Queue("fanout.queue2");
    }

    // 绑定队列2到交换机
    @Bean
    public Binding fanoutBinding2(FanoutExchange fanoutExchange, Queue fanoutQueue2) {
        return BindingBuilder.bind(fanoutQueue2).to(fanoutExchange);
    }

    @Bean
    public Queue objectQueue() {
        return new Queue("object.queue");
    }
}
