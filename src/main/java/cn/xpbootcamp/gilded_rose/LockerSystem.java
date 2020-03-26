package cn.xpbootcamp.gilded_rose;

import cn.xpbootcamp.gilded_rose.exception.InitializeLockerSystemException;
import cn.xpbootcamp.gilded_rose.exception.InvalidTicketException;
import cn.xpbootcamp.gilded_rose.exception.LockerFullException;

import java.util.ArrayList;
import java.util.List;

public class LockerSystem {

    private List<Locker> lockers = new ArrayList<>();

    LockerSystem(int lockerCount) {
        if (lockerCount < 0) {
            throw new InitializeLockerSystemException();
        }

        for (int i = 0; i < lockerCount; i++) {
            lockers.add(new Locker());
        }
    }

    Ticket store() {
        Locker locker = lockers.stream()
                .filter(Locker::isFree)
                .findFirst()
                .orElseThrow(LockerFullException::new);
        locker.store();
        return locker.getTicket();
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

    long getFreeLockerCount() {
        return this.lockers.stream()
                .filter(Locker::isFree)
                .count();
    }
}
