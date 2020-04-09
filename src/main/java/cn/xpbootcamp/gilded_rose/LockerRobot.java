package cn.xpbootcamp.gilded_rose;

import cn.xpbootcamp.gilded_rose.exception.InvalidTicketException;
import cn.xpbootcamp.gilded_rose.exception.LockerFullException;

import java.util.List;

public class LockerRobot {

    private List<Locker> lockers;

    public LockerRobot(List<Locker> lockers) {
        this.lockers = lockers;
    }

    public Ticket store(Bag bag) {
        Locker locker = this.lockers.stream()
                .filter(Locker::isAvailable)
                .findFirst()
                .orElseThrow(LockerFullException::new);
        return locker.store(bag);
    }

    public Bag take(Ticket ticket) {
        return this.lockers.stream()
                .filter(locker -> locker.isTicketValid(ticket))
                .findFirst()
                .map(locker -> locker.take(ticket))
                .orElseThrow(InvalidTicketException::new);
    }
}
