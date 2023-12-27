package adeo.leroymerlin.cdp.listing.domain.port.out;

import adeo.leroymerlin.cdp.listing.domain.model.Event;

import java.util.Set;

public interface EventRepository {

    Set<Event> events();
}
