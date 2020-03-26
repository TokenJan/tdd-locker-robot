package cn.xpbootcamp.gilded_rose;

public class Locker {

    private boolean open;

    private boolean free;

    private Ticket ticket;

    Locker() {
        this.open = false;
        this.free = true;
    }

    boolean isOpen() {
        return this.open;
    }

    boolean isFree() {
        return this.free;
    }

    public Ticket getTicket() {
        return ticket;
    }

    void store() {
        this.ticket = new Ticket(Ticket.Status.SUCCESS);
        this.free = false;
        this.open = true;
    }

    void take() {
        this.ticket = null;
        this.free = true;
        this.open = true;
    }
}
