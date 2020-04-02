package cn.xpbootcamp.gilded_rose;

import cn.xpbootcamp.gilded_rose.exception.InvalidTicketException;
import cn.xpbootcamp.gilded_rose.exception.LockerFullException;

import java.util.List;
import java.util.Objects;

public class LockerRobot {

    private List<Locker> lockers;

    public LockerRobot(List<Locker> lockers) {
        this.lockers = lockers;
    }

    public Ticket store(Bag bag) {
        Locker locker = this.lockers.stream()
                .filter(Locker::isFree)
                .findFirst()
                .orElseThrow(LockerFullException::new);
        return locker.store(bag);
    }

    public Bag take(Ticket ticket) {
        return this.lockers.stream()
                .map(locker -> locker.take(ticket))
                .filter(Objects::nonNull)
                .findFirst()
                .orElseThrow(InvalidTicketException::new);
    }

    public List<Locker> getLockers() {
        return lockers;
    }
}
