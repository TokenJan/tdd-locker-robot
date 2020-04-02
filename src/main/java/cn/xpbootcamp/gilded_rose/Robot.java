package cn.xpbootcamp.gilded_rose;

import cn.xpbootcamp.gilded_rose.exception.LockerFullException;

import java.util.ArrayList;
import java.util.List;

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
}
