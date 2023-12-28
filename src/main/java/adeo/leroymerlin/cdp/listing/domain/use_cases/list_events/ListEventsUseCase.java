package adeo.leroymerlin.cdp.listing.domain.use_cases.list_events;

import adeo.leroymerlin.cdp.listing.domain.model.Event;
import adeo.leroymerlin.cdp.listing.domain.port.out.EventRepository;
import adeo.leroymerlin.cdp.listing.domain.use_cases.QueryHandler;

import java.util.Set;

public class ListEventsUseCase implements QueryHandler<ListEvents, ListEventsUseCase.ListedEvents> {

    private final EventRepository eventRepository;

    public ListEventsUseCase(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public ListedEvents proceed(ListEvents query) {
        Set<Event> events = eventRepository.events();

        return new ListedEvents(events);
    }

    public static class ListedEvents {

        private final Set<Event> events;

        public ListedEvents(Set<Event> events) {
            this.events = events;
        }

        public Set<Event> events() {
            return events;
        }
    }
}
