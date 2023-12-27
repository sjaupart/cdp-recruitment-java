package adeo.leroymerlin.cdp.listing.domain.use_cases;

import adeo.leroymerlin.cdp.listing.domain.model.Event;
import adeo.leroymerlin.cdp.listing.domain.port.out.EventRepository;

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

        public Set<Event> get() {
            return events;
        }
    }
}
