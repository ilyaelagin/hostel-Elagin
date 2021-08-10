package ru.elagin.hostel.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

import javax.jms.Queue;

@Configuration
@EnableJms
public class ActiveMQConfig {

    @Bean
    public Queue guestQueueOut() {
        return new ActiveMQQueue("hostel-guest-queue-out");
    }

    @Bean
    public Queue guestQueueIn() {
        return new ActiveMQQueue("hostel-guest-queue-in");
    }

}
