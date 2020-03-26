package cn.xpbootcamp.gilded_rose.exception;

public class InitializeLockerSystemException extends RuntimeException {
    public InitializeLockerSystemException() {
        super("INVALID LOCKER COUNT");
    }
}
