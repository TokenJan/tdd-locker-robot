package cn.xpbootcamp.gilded_rose;

import cn.xpbootcamp.gilded_rose.exception.InvalidTicketException;
import cn.xpbootcamp.gilded_rose.exception.LockerFullException;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class SuperLockerRobot {

    private List<Locker> lockers;

    public SuperLockerRobot(List<Locker> lockers) {
        this.lockers = lockers;
    }

    Ticket store(Bag bag) {
        Locker locker = getMaxVacancyRateLocker().orElseThrow(LockerFullException::new);
        return locker.store(bag);
    }

    private Optional<Locker> getMaxVacancyRateLocker() {
        return this.lockers.stream()
                .filter(Locker::isAvailable)
                .max(Comparator.comparingDouble(Locker::getVacancyRate));
    }

    Bag take(Ticket ticket) {
        return this.lockers.stream()
                .filter(locker -> locker.isTicketValid(ticket))
                .findFirst()
                .map(locker -> locker.take(ticket))
                .orElseThrow(InvalidTicketException::new);
    }
}
