package cn.xpbootcamp.gilded_rose;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LockerSystem {

    private List<Locker> lockers = new ArrayList<>();

    private int lockerCount;

    Ticket store() {
        lockerCount -= 1;
        Locker locker = lockers.stream()
                .filter(Locker::isEmpty)
                .findFirst()
                .orElse(null);
        if (locker != null) {
            locker.open();
        }
        return new Ticket(locker);
    }

    public LockerSystem(int lockerCount) {
        this.lockerCount = lockerCount;
        for (int i = 0; i < lockerCount; i++) {
            lockers.add(new Locker());
        }
    }

    public int getLockerCount() {
        return lockerCount;
    }
}
