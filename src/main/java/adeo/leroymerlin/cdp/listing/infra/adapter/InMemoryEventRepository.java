package adeo.leroymerlin.cdp.listing.infra.adapter;

import adeo.leroymerlin.cdp.listing.domain.model.Event;
import adeo.leroymerlin.cdp.listing.domain.model.EventId;
import adeo.leroymerlin.cdp.listing.domain.port.out.EventRepository;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class InMemoryEventRepository implements EventRepository {

    private final adeo.leroymerlin.cdp.EventRepository repository;

    public InMemoryEventRepository(adeo.leroymerlin.cdp.EventRepository repository) {
        this.repository = repository;
    }

    @Override
    public Set<Event> events() {
        return repository.findAllBy().stream()
                .map(this::toDomain)
                .collect(toSet());
    }

    private Event toDomain(adeo.leroymerlin.cdp.Event persistedEvent) {
        return Event.builder()
                .id(new EventId(persistedEvent.getId()))
                .name(persistedEvent.getTitle())
                .comment(persistedEvent.getComment())
                .pictureUrl(persistedEvent.getImgUrl())
                .numberOfStars(persistedEvent.getNbStars())
                .build();
    }
}
