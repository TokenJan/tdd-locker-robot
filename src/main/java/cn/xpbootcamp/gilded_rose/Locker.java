package cn.xpbootcamp.gilded_rose;

public class Locker {

    private boolean open;

    private boolean empty;

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

    void open() {
        this.open = true;
    }
}
