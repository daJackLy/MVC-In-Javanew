package controllers;

import java.util.ArrayList;
import java.util.List;
import models.Guest;

public class GuestController {
    private List<Guest> guests;

    public GuestController() {
        guests = new ArrayList<>();
    }

    public void addGuest(Guest guest) {
        guests.add(guest);
    }

    public List<Guest> getGuests() {
        return guests;
    }

    public void removeGuest(int index) {
        if (index >= 0 && index < guests.size()) {
            guests.remove(index);
        }
    }
}
