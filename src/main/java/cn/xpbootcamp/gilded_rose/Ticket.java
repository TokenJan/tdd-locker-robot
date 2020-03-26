package cn.xpbootcamp.gilded_rose;

import java.util.UUID;

public class Ticket {

    private String id;
    private Status status;

    public Ticket(Status status) {
        this.status = status;
        this.id =  Status.SUCCESS.equals(status)
                ? UUID.randomUUID().toString()
                : null;
    }

    public String getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    enum Status {
        SUCCESS("SUCCESS"),
        FALIED("NO FREE LOCKER AVAILABLE");

        private String message;

        Status(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
