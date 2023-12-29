package adeo.leroymerlin.cdp.events.domain.model;

public class InvalidEvent extends RuntimeException {

    public InvalidEvent(String message) {
        super(message);
    }
}
