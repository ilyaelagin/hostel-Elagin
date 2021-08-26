package ru.elagin.hostel.config;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import ru.elagin.hostel.akka.SpringExtension;

import javax.annotation.PostConstruct;
import java.time.Duration;

@Configuration
@RequiredArgsConstructor
public class GuestBirthConfig {
    private final ActorSystem actorSystem;
    private final SpringExtension springExtension;

    @PostConstruct
    public void wakeGuestBirth() {
        actorSystem.scheduler().schedule(
                Duration.ofSeconds(3),
                Duration.ofDays(1),
                actorSystem.actorOf(springExtension.props("guestBirthActor"), "guestBirth"),
                "birthday today", actorSystem.dispatcher(),
                ActorRef.noSender()
        );
    }
}
