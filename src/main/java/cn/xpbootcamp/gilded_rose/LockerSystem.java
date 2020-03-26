package cn.xpbootcamp.gilded_rose;

import java.util.ArrayList;
import java.util.List;

public class LockerSystem {

    private List<Locker> lockers = new ArrayList<>();

    Ticket store() {
        Locker locker = lockers.stream()
                .filter(Locker::isFree)
                .findFirst()
                .orElse(null);
        if (locker != null) {
            locker.store();
            return locker.getTicket();
        } else {
            throw new LockerFullException();
        }
    }

    LockerSystem(int lockerCount) {
        for (int i = 0; i < lockerCount; i++) {
            lockers.add(new Locker());
        }
    }

    long getFreeLockerCount() {
        return this.lockers.stream()
                .filter(Locker::isFree)
                .count();
    }

    Locker take(Ticket ticket) {
        Locker locker = findLockerByTicker(ticket);
        locker.take();
        return locker;
    }

    Locker findLockerByTicker(Ticket ticket) {
        return lockers.stream()
                .filter(locker -> !locker.isFree())
                .filter(locker -> locker.getTicket().equals(ticket))
                .findAny()
                .orElseThrow(InvalidTicketException::new);
    }
}
