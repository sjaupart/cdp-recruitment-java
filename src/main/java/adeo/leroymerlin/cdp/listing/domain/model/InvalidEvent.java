package adeo.leroymerlin.cdp.listing.domain.model;

public class InvalidEvent extends RuntimeException {

    public InvalidEvent(String message) {
        super(message);
    }
}
