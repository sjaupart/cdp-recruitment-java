package adeo.leroymerlin.cdp.events.domain.use_cases.list_events;

import adeo.leroymerlin.cdp.events.domain.model.*;
import adeo.leroymerlin.cdp.events.domain.port.out.EventRepository;
import adeo.leroymerlin.cdp.events.domain.use_cases.QueryHandler;

import java.util.Objects;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class ListEventsUseCase implements QueryHandler<ListEvents, ListEventsUseCase.ListedEvents> {

    private final EventRepository eventRepository;

    public ListEventsUseCase(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public ListedEvents proceed(ListEvents query) {
        Set<Event> events = query.criteria()
                .map(this::eventsWithMembersMatching)
                .orElse(eventRepository.events());

        return toListedEvents(events);
    }

    private ListedEvents toListedEvents(Set<Event> events) {
        Set<ListedEvent> listedEvents = events.stream().map(ListedEvent::fromDomain).collect(toSet());
        return new ListedEvents(listedEvents);
    }

    private Set<Event> eventsWithMembersMatching(SearchCriteria criteria) {
        Set<Event> events = eventRepository.events();

        return events.stream()
                .filter(event -> event.hasMemberNameMatching(criteria.pattern()))
                .collect(toSet());
    }

    public static class ListedEvents {

        private final Set<ListedEvent> events;

        public ListedEvents(Set<ListedEvent> events) {
            this.events = events;
        }

        public Set<ListedEvent> events() {
            return events;
        }

        public Set<String> names() {
            return events.stream()
                    .map(ListedEvent::name)
                    .collect(toSet());
        }
    }

    public static class ListedEvent {
        private final EventId id;
        private final String name;
        private final String pictureUrl;
        private final Integer numberOfStars;
        private final String comment;
        private final Set<ListedBand> bands;

        public ListedEvent(EventId id, String name, String pictureUrl, Integer numberOfStars, String comment, Set<ListedBand> bands) {
            this.id = id;
            this.name = formattedName(name, bands);
            this.pictureUrl = pictureUrl;
            this.numberOfStars = numberOfStars;
            this.comment = comment;
            this.bands = bands;
        }

        private String formattedName(String name, Set<ListedBand> children) {
            return "%s [%s]".formatted(name, children.size());
        }

        public static ListedEvent fromDomain(Event event) {
            return new ListedEvent(
                    event.id(),
                    event.name(),
                    event.pictureUrl(),
                    event.numberOfStars(),
                    event.comment(),
                    event.bands().stream().map(ListedBand::fromDomain).collect(toSet())
            );
        }

        public EventId id() {
            return id;
        }

        public String name() {
            return name;
        }

        public String pictureUrl() {
            return pictureUrl;
        }

        public Integer numberOfStars() {
            return numberOfStars;
        }

        public String comment() {
            return comment;
        }

        public Set<ListedBand> bands() {
            return bands;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ListedEvent that = (ListedEvent) o;
            return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(pictureUrl, that.pictureUrl) && Objects.equals(numberOfStars, that.numberOfStars) && Objects.equals(comment, that.comment) && Objects.equals(bands, that.bands);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name, pictureUrl, numberOfStars, comment, bands);
        }

        @Override
        public String toString() {
            return "ListedEvent{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", pictureUrl='" + pictureUrl + '\'' +
                    ", numberOfStars=" + numberOfStars +
                    ", comment='" + comment + '\'' +
                    ", bands=" + bands +
                    '}';
        }
    }

    public static class ListedBand {
        private final BandId id;
        private final String name;
        private final Set<Member> members;

        public ListedBand(BandId id, String name, Set<Member> members) {
            this.id = id;
            this.name = toFormattedName(name, members);
            this.members = members;
        }

        public static ListedBand fromDomain(Band band) {
            return new ListedBand(
                    band.id(),
                    band.name(),
                    band.members()
            );
        }

        private String toFormattedName(String name, Set<Member> children) {
            return "%s [%s]".formatted(name, children.size());
        }

        public BandId id() {
            return id;
        }

        public String name() {
            return name;
        }

        public Set<Member> members() {
            return members;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ListedBand that = (ListedBand) o;
            return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(members, that.members);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name, members);
        }

        @Override
        public String toString() {
            return "ListedBand{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", members=" + members +
                    '}';
        }
    }
}
