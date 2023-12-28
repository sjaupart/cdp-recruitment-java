package adeo.leroymerlin.cdp.listing.domain.port.out;

import adeo.leroymerlin.cdp.listing.domain.model.Event;
import adeo.leroymerlin.cdp.listing.domain.model.EventId;

import java.util.Set;

public interface EventRepository {

    Set<Event> events();

    void delete(EventId eventId);
}
