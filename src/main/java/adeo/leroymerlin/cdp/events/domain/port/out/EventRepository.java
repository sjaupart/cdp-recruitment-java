package adeo.leroymerlin.cdp.events.domain.port.out;

import adeo.leroymerlin.cdp.events.domain.model.Event;
import adeo.leroymerlin.cdp.events.domain.model.EventId;

import java.util.Set;

public interface EventRepository {

    Set<Event> events();

    void delete(EventId eventId);
}
