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
    public Queue apartmentQueueIn() {
        return new ActiveMQQueue("hostel-apartment-queue-in");
    }

    @Bean
    public Queue apartmentQueueOut() {
        return new ActiveMQQueue("hostel-apartment-queue-out");
    }

    @Bean
    public Topic roleQueueIn() {
        return new ActiveMQTopic("hostel-role-queue-in");
    }

    @Bean
    public Topic roleQueueOut() {
        return new ActiveMQTopic("hostel-role-queue-out");
    }

    @Bean
    public Topic guestBirthTopic() {
        return new ActiveMQTopic("hostel-guest-birth-topic");
    }

    @Bean
    public Topic newGuestTopic() {
        return new ActiveMQTopic("hostel-new-guest-topic");
    }

}