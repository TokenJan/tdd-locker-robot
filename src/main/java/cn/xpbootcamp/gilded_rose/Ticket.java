package cn.xpbootcamp.gilded_rose;

import java.util.UUID;

public class Ticket {

    private Locker locker;

    private String id;

    public Ticket(Locker locker) {
        this.locker = locker;
        this.id = UUID.randomUUID().toString();
    }

    public Locker getLocker() {
        return locker;
    }

    public String getId() {
        return id;
    }
}
