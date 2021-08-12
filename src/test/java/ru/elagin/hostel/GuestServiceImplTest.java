package ru.elagin.hostel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.elagin.hostel.dto.ApartmentDTO;
import ru.elagin.hostel.dto.GuestDTO;
import ru.elagin.hostel.entities.Apartment;
import ru.elagin.hostel.entities.Guest;
import ru.elagin.hostel.service.impl.ApartmentServiceImpl;
import ru.elagin.hostel.service.impl.GuestServiceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@SpringBootTest
@TestPropertySource("/application-test.properties")
public class GuestServiceImplTest {

    @Autowired
    private GuestServiceImpl guestService;

    @Autowired
    private ApartmentServiceImpl apartmentService;

    @Test
    public void createGuestTest() {
        GuestDTO guestDTO = new GuestDTO();
        guestDTO.setName("Bob");
        guestDTO.setSurname("Marley");
        guestDTO.setPassport("7777 777777");
        guestDTO.setFoto("3e");
        guestDTO.setBirth("1945-02-06");
        guestDTO.setCheckIn("1967-07-01");
        guestDTO.setCheckOut("1967-08-01");
        guestDTO.setApartmentId(1L);
        guestService.createGuest(guestDTO);

        Guest guestById = guestService.getGuestById(1L).getBody();

        Assertions.assertEquals(1L, guestById.getId());
        Assertions.assertEquals("Bob", guestById.getName());
        Assertions.assertEquals("Marley", guestById.getSurname());
        Assertions.assertEquals("7777 777777", guestById.getPassport());
        Assertions.assertArrayEquals("3e".getBytes(), guestById.getFoto());
        Assertions.assertEquals(LocalDate.parse("1945-02-06"), guestById.getBirth());
        Assertions.assertEquals(LocalDate.parse("1967-07-01"), guestById.getCheckIn());
        Assertions.assertEquals(LocalDate.parse("1967-08-01"), guestById.getCheckOut());
        Assertions.assertEquals(1L, guestById.getApartment().getId());
    }

    @Test
    public void createApartmentTest() {
        ApartmentDTO apartmentDTO = new ApartmentDTO();
        apartmentDTO.setNumber("40");
        apartmentDTO.setRooms("2");
        apartmentDTO.setCleaning("2021-07-03T11:30:00");
        apartmentDTO.setCategoryId(1L);
        apartmentService.createApartment(apartmentDTO);

        Apartment apartmentById = apartmentService.getApartmentById(3L).getBody();

        Assertions.assertEquals(3L, apartmentById.getId());
        Assertions.assertEquals(40, apartmentById.getNumber());
        Assertions.assertEquals(2, apartmentById.getRooms());
        Assertions.assertEquals(Collections.emptySet(), apartmentById.getGuestSet());
        Assertions.assertEquals(LocalDateTime.parse("2021-07-03T11:30:00"), apartmentById.getCleaning());
        Assertions.assertEquals(1L, apartmentById.getCategory().getId());
    }

    @Test
    public void setGuestApartmentTest() {
        Map<String, String> guestIdApartmentId = new HashMap<>();
        guestIdApartmentId.put("guestId", "1");
        guestIdApartmentId.put("apartmentId", "3");

        Guest guestToUpdate = guestService.setGuestApartment(guestIdApartmentId).getBody();
        Apartment apartmentById = apartmentService.getApartmentById(3L).getBody();

        Assertions.assertEquals(apartmentById.getId(), guestToUpdate.getApartment().getId());
        Assertions.assertEquals(apartmentById.getNumber(), guestToUpdate.getApartment().getNumber());
        Assertions.assertEquals(apartmentById.getCleaning(), guestToUpdate.getApartment().getCleaning());

        Set<Guest> guestList1 = apartmentById.getGuestSet();
        Set<Guest> guestList2 = guestToUpdate.getApartment().getGuestSet();
        Assertions.assertEquals(guestList1.size(), guestList2.size());
        Guest guest1 = guestList1.stream().filter(guest -> guest.getId()==1L).collect(Collectors.toList()).get(0);
        Guest guest2 = guestList2.stream().filter(guest -> guest.getId()==1L).collect(Collectors.toList()).get(0);
        Assertions.assertEquals(guest1.getId(),guest2.getId());
        Assertions.assertEquals(guest1.getName(), guest2.getName());
        Assertions.assertEquals(guest1.getSurname(), guest2.getSurname());
        Assertions.assertEquals(guest1.getPassport(), guest2.getPassport());
        Assertions.assertArrayEquals(guest1.getFoto(), guest2.getFoto());
        Assertions.assertEquals(guest1.getBirth(), guest2.getBirth());
        Assertions.assertEquals(guest1.getCheckIn(), guest1.getCheckIn());
        Assertions.assertEquals(guest1.getCheckOut(), guest2.getCheckOut());
        Assertions.assertEquals(guest1.getApartment().getId(), guest2.getApartment().getId());
    }
}
