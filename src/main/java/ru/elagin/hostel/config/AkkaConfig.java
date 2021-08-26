package ru.elagin.hostel.config;

import akka.actor.ActorSystem;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.elagin.hostel.akka.SpringExtension;

@Configuration
@RequiredArgsConstructor
public class AkkaConfig {

    private final ApplicationContext applicationContext;
    private final SpringExtension springExtension;

    @Bean
    public ActorSystem actorSystem() {
        ActorSystem system = ActorSystem.create("hostel-guest-birth");
        springExtension.initialize(applicationContext);
        return system;
    }
}
