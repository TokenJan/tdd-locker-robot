package cn.xpbootcamp.gilded_rose;

import cn.xpbootcamp.gilded_rose.exception.InvalidTicketException;
import cn.xpbootcamp.gilded_rose.exception.LockerFullException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Robot {

    private List<Locker> lockers = new ArrayList<>();

    public void addLocker(Locker locker) {
        this.lockers.add(locker);
    }

    public void addLockers(List<Locker> lockers) {
        this.lockers.addAll(lockers);
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
}
