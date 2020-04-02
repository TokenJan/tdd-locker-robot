package cn.xpbootcamp.gilded_rose;

import cn.xpbootcamp.gilded_rose.exception.InvalidTicketException;
import cn.xpbootcamp.gilded_rose.exception.LockerFullException;

import java.util.HashMap;
import java.util.Map;

public class Locker {

    private int capacity;
    private Map<Ticket, Bag> storedBags = new HashMap<>();

    Locker(int capacity) {
        this.capacity = capacity;
    }

    public Ticket store(Bag bag) {
        if (storedBags.size() == capacity) {
            throw new LockerFullException();
        }
        Ticket ticket = new Ticket();
        storedBags.put(ticket, bag);
        return ticket;
    }

    public Bag take(Ticket ticket) {
        Bag bag = storedBags.get(ticket);
        storedBags.remove(ticket);
        return bag;
    }

    public boolean isFree() {
        return this.capacity != this.storedBags.size();
    }
}
