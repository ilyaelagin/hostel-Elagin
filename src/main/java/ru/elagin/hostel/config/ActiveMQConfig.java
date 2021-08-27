package ru.elagin.hostel.config;


import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

import javax.jms.Queue;
import javax.jms.Topic;

@Configuration
@EnableJms
public class ActiveMQConfig {

    @Bean
    public Queue guestQueueIn() {
        return new ActiveMQQueue("hostel-apartment-queue-in");
    }

    @Bean
    public Queue guestQueueOut() {
        return new ActiveMQQueue("hostel-apartment-queue-out");
    }

    @Bean
    public Topic roleTopicQueueIn() {
        return new ActiveMQTopic("hostel-role-topic-in");
    }

    @Bean
    public Topic roleTopicQueueOut() {
        return new ActiveMQTopic("hostel-role-topic-out");
    }

    @Bean
    public Topic guestBirthTopicQueue() {
        return new ActiveMQTopic("hostel-guest-birth-topic");
    }

}
