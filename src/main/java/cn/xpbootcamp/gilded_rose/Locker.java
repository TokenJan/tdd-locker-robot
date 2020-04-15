package cn.xpbootcamp.gilded_rose;

import cn.xpbootcamp.gilded_rose.exception.InvalidTicketException;
import cn.xpbootcamp.gilded_rose.exception.LockerFullException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Locker {

    private int capacity;
    private Map<Ticket, Bag> storedBags = new HashMap<>();

    Locker(int capacity) {
        this.capacity = capacity;
    }

    Ticket store(Bag bag) {
        if (!isAvailable()) {
            throw new LockerFullException();
        }
        Ticket ticket = new Ticket();
        storedBags.put(ticket, bag);
        return ticket;
    }

    Bag take(Ticket ticket) {
        Bag bag = storedBags.get(ticket);
        if (Objects.isNull(bag)) {
            throw new InvalidTicketException();
        }
        storedBags.remove(ticket);
        return bag;
    }

    boolean isTicketValid(Ticket ticket) {
        return this.storedBags.containsKey(ticket);
    }

    boolean isAvailable() {
        return this.storedBags.size() < this.capacity;
    }

    int getFreeSpace() {
        return this.capacity - this.storedBags.size();
    }

    double getVacancyRate() {
        return 1 - (double) this.storedBags.size() / this.capacity;
    }
}
