package ru.elagin.hostel.akka;

import akka.actor.UntypedActor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import ru.elagin.hostel.entities.Guest;
import ru.elagin.hostel.service.GuestService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
@Slf4j
public class GuestBirthActor extends UntypedActor {
    private final GuestService guestService;
    private final JmsTemplate jmsTemplateTopic;

    @Override
    public void onReceive(Object message) {
        if (message.equals("birthday today")) {
            Optional<List<Guest>> guests = Optional.ofNullable(guestService.getAllGuest().getBody());
            if (guests.isPresent()) {
                int count = 0;
                for (Guest guest : guests.get()) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM");
                    if (LocalDate.now().format(formatter).equals(guest.getBirth().format(formatter))) {
                        count++;
                        jmsTemplateTopic.convertAndSend("hostel-guest-birth-topic",
                                "Today is the guest's birthday: " + guest.getName() + " " + guest.getSurname() + "!");
                        log.info((String) jmsTemplateTopic.receiveAndConvert("hostel-guest-birth-topic"));
                    }
                }
                if (count == 0) {
                    log.info("No guests birthdays today!");
                }
            } else {
                log.info("Guest list is empty!");
            }
        } else {
            unhandled(message);
        }
    }
}
