package cn.xpbootcamp.gilded_rose.exception;

public class LockerFullException extends RuntimeException {
    public LockerFullException() {
        super("LOCKER IS FULL");
    }
}
