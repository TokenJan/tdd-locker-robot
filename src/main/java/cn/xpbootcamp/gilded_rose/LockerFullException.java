package cn.xpbootcamp.gilded_rose;

public class LockerFullException extends RuntimeException {
    public LockerFullException() {
        super("LOCKER IS FULL");
    }
}
