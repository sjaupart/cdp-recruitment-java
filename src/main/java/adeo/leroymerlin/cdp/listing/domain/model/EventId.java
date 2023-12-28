package adeo.leroymerlin.cdp.listing.domain.model;

import java.util.Optional;

public record EventId(Long id) {

    public EventId(Long id) {
        if (Optional.ofNullable(id).isEmpty()) {
            throw new InvalidEvent("Event identifier is mandatory");
        }

        this.id = id;
    }
}
