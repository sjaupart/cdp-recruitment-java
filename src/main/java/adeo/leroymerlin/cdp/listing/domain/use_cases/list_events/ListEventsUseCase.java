package adeo.leroymerlin.cdp.listing.domain.use_cases.list_events;

import adeo.leroymerlin.cdp.listing.domain.model.Event;
import adeo.leroymerlin.cdp.listing.domain.model.SearchCriteria;
import adeo.leroymerlin.cdp.listing.domain.port.out.EventRepository;
import adeo.leroymerlin.cdp.listing.domain.use_cases.QueryHandler;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class ListEventsUseCase implements QueryHandler<ListEvents, ListEventsUseCase.ListedEvents> {

    private final EventRepository eventRepository;

    public ListEventsUseCase(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public ListedEvents proceed(ListEvents query) {
        Set<Event> events = eventRepository.events();

        return query.criteria()
                .map(c -> eventsWithMembersMatching(c, events))
                .map(ListedEvents::new)
                .orElse(new ListedEvents(events));
    }

    private Set<Event> eventsWithMembersMatching(SearchCriteria criteria, Set<Event> events) {
        return events.stream()
                .filter(event -> event.hasMemberNameMatching(criteria.pattern()))
                .collect(toSet());
    }

    public static class ListedEvents {

        private final Set<Event> events;

        public ListedEvents(Set<Event> events) {
            this.events = events;
        }

        public Set<Event> events() {
            return events;
        }

        public Set<String> names() {
            return events.stream()
                    .map(Event::name)
                    .collect(toSet());
        }
    }
}
