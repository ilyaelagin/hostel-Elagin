package ru.elagin.hostel.dto;

import lombok.Data;
import ru.elagin.hostel.entities.Apartment;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ApartmentDTO {
    private String id;
    private String number;
    private String rooms;
    private List<GuestDTO> guestList;
    private String cleaning;
    private CategoryDTO category;

    public ApartmentDTO(Apartment apartment) {
        if (apartment == null) {
            throw new IllegalArgumentException("Apartment dos not exist");
        }
        if (apartment.getId() != null) {
            this.id = String.valueOf(apartment.getId());
        }
        if (apartment.getNumber() != null) {
            this.number = String.valueOf(apartment.getNumber());
        }
        if (apartment.getRooms() != null) {
            this.rooms = String.valueOf(apartment.getRooms());
        }
        if (apartment.getGuestList() != null) {
            this.guestList = apartment.getGuestList().stream().map(GuestDTO::new).collect(Collectors.toList());
        }
        if (apartment.getCleaning() != null) {
            this.cleaning = String.valueOf(apartment.getCleaning());
        }
        if (apartment.getCategory() != null) {
            this.category = new CategoryDTO(apartment.getCategory());
        }
    }
}
