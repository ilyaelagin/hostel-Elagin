package ru.elagin.hostel.dto;

import lombok.Data;
import ru.elagin.hostel.entities.Apartment;

@Data
public class ApartmentDTO {
    private String id;
    private String number;
    private String rooms;
    private String cleaning;
    private Long categoryId;
    private String error;

    public ApartmentDTO() {
    }

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
        if (apartment.getCleaning() != null) {
            this.cleaning = String.valueOf(apartment.getCleaning());
        }
        if (apartment.getCategory() != null) {
            this.categoryId = apartment.getCategory().getId();
        }
    }
}
