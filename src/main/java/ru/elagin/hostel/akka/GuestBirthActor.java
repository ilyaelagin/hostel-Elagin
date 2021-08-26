package ru.elagin.hostel.akka;

import akka.actor.UntypedActor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
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
public class GuestBirthActor extends UntypedActor {

    private final GuestService guestService;

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
                        System.out.println("Today is the birthday of the " + guest.getName() + " " + guest.getSurname() + "!");
                        ResponseEntity.ok(guest);
                    }
                }
                if (count == 0) {
                    System.out.println("No guests birthdays today!");
                }
            } else {
                System.out.println("Guest list is empty!");
            }
        } else {
            unhandled(message);
        }
    }
}
