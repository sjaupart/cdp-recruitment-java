package adeo.leroymerlin.cdp.listing.domain.model;

import java.util.Optional;

public record EventId(Long value) {

    public EventId {
        if (Optional.ofNullable(value).isEmpty()) {
            throw new InvalidEvent("Event identifier is mandatory");
        }
    }
}
