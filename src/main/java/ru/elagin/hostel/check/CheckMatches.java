package ru.elagin.hostel.check;

import java.util.HashMap;
import java.util.Map;

public class CheckMatches {

    private static final String INT_PATTERN = "\\d+";

    public static Map<String, Long> checkMatchesUserIdRoleId(Map<String, String> userIdRoleId) {
        Map<String, Long> map = new HashMap<>();
        if (!userIdRoleId.get("userId").matches(INT_PATTERN)) {
            throw new IllegalArgumentException("User id must be numeric!");
        }
        Long userId = Long.valueOf(userIdRoleId.get("userId"));

        if (!userIdRoleId.get("roleId").matches(INT_PATTERN)) {
            throw new IllegalArgumentException("Role id must be numeric!");
        }
        Long roleId = Long.valueOf(userIdRoleId.get("roleId"));

        map.put("userId", userId);
        map.put("roleId", roleId);

        return map;
    }

    public static Map<String, Long> checkMatchesGuestIdApartmentId(Map<String, String> guestIdApartmentId) {
        Map<String, Long> map = new HashMap<>();
        if (!guestIdApartmentId.get("guestId").matches(INT_PATTERN)) {
            throw new IllegalArgumentException("Guest id must be numeric!");
        }
        Long guestId = Long.valueOf(guestIdApartmentId.get("guestId"));

        if (!guestIdApartmentId.get("apartmentId").matches(INT_PATTERN)) {
            throw new IllegalArgumentException("Role id must be numeric!");
        }
        Long apartmentId = Long.valueOf(guestIdApartmentId.get("apartmentId"));

        map.put("guestId", guestId);
        map.put("apartmentId", apartmentId);

        return map;
    }

    public static Map<String, Long> checkMatchesApartmentIdCategoryId(Map<String, String> apartmentIdCategoryId) {
        Map<String, Long> map = new HashMap<>();
        if (!apartmentIdCategoryId.get("apartmentId").matches(INT_PATTERN)) {
            throw new IllegalArgumentException("Apartment id must be numeric!");
        }
        Long apartmentId = Long.valueOf(apartmentIdCategoryId.get("apartmentId"));

        if (!apartmentIdCategoryId.get("categoryId").matches(INT_PATTERN)) {
            throw new IllegalArgumentException("Category id must be numeric!");
        }
        Long categoryId = Long.valueOf(apartmentIdCategoryId.get("categoryId"));

        map.put("apartmentId", apartmentId);
        map.put("categoryId", categoryId);

        return map;
    }

    public static Map<String, String> checkMatchesUserIdStatus(Map<String, String> userIdStatus) {
        Map<String, String> map = new HashMap<>();
        if (!userIdStatus.get("userId").matches("\\d+")) {
            throw new IllegalArgumentException("User id must be numeric!");
        }
        String status = userIdStatus.get("status");
        if ((!"active".equals(status)) && (!"banned".equals(status))) {
            throw new IllegalArgumentException("The status must be: active or banned");
        }
        return map;
    }

}
