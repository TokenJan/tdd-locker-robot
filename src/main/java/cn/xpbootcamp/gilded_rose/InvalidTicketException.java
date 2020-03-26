package cn.xpbootcamp.gilded_rose;

public class InvalidTicketException extends RuntimeException {
    public InvalidTicketException() {
        super("INVALID TICKET");
    }
}
