package cn.xpbootcamp.gilded_rose;

import cn.xpbootcamp.gilded_rose.exception.LockerFullException;

import java.util.HashMap;
import java.util.Map;

public class Locker {

    private int capacity;
    private int emptyCapacity;
    private Map<Ticket, Bag> storedBags = new HashMap<>();

    Locker(int capacity, int emptyCapacity) {
        this.capacity = capacity;
        this.emptyCapacity = emptyCapacity;
    }

    Ticket store(Bag bag) {
        if (!isFree()) {
            throw new LockerFullException();
        }
        Ticket ticket = new Ticket();
        this.emptyCapacity -= 1;
        storedBags.put(ticket, bag);
        return ticket;
    }

    Bag take(Ticket ticket) {
        Bag bag = storedBags.get(ticket);
        storedBags.remove(ticket);
        this.emptyCapacity += 1;
        return bag;
    }

    boolean isFree() {
        return this.emptyCapacity > 0;
    }
}
