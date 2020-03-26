package cn.xpbootcamp.gilded_rose;

import java.util.ArrayList;
import java.util.List;

public class LockerSystem {

    private List<Locker> lockers = new ArrayList<>();

    private int lockerCount;

    Ticket store() {
        Locker locker = lockers.stream()
                .filter(Locker::isEmpty)
                .findFirst()
                .orElse(null);
        if (locker != null) {
            lockerCount -= 1;
            locker.open();
            locker.occupy();
            return new Ticket(locker);
        } else {
            return null;
        }
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
