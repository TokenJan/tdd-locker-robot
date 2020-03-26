package cn.xpbootcamp.gilded_rose;

import java.util.UUID;

public class Ticket {

    private String id;

    public Ticket() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }
}
