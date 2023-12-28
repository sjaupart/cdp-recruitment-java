package adeo.leroymerlin.cdp.listing.domain.model;

import java.util.Optional;

public record EventId(Long value) {

    public EventId(Long value) {
        if (Optional.ofNullable(value).isEmpty()) {
            throw new InvalidEvent("Event identifier is mandatory");
        }

        this.value = value;
    }
}
