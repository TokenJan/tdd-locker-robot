package cn.xpbootcamp.gilded_rose;

public class Locker {

    private boolean open;

    private boolean empty;

    private Ticket ticket;

    Locker() {
        this.open = false;
        this.empty = true;
    }

    boolean isOpen() {
        return this.open;
    }

    boolean isEmpty() {
        return this.empty;
    }

    public Ticket getTicket() {
        return ticket;
    }

    void open() {
        this.open = true;
    }

    void occupy() {
        this.ticket = new Ticket();
        this.empty = false;
    }

    void take() {
        this.ticket = null;
        this.empty = true;
        this.open = true;
    }
}
