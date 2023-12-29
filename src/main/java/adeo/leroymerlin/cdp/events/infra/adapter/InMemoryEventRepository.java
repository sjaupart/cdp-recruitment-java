package adeo.leroymerlin.cdp.events.infra.adapter;

import adeo.leroymerlin.cdp.events.domain.model.*;
import adeo.leroymerlin.cdp.events.domain.port.out.EventRepository;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class InMemoryEventRepository implements EventRepository {

    private final adeo.leroymerlin.cdp.events.infra.adapter.hsql.EventRepository repository;

    public InMemoryEventRepository(adeo.leroymerlin.cdp.events.infra.adapter.hsql.EventRepository repository) {
        this.repository = repository;
    }

    @Override
    public Set<Event> events() {
        return repository.findAllBy().stream()
                .map(this::toDomain)
                .collect(toSet());
    }

    @Override
    public void delete(EventId eventId) {
        repository.deleteById(eventId.value());
    }

    private Event toDomain(adeo.leroymerlin.cdp.events.infra.adapter.hsql.Event persistedEvent) {
        Set<Band> bands = persistedEvent.getBands().stream()
                .map(persistedBand -> new Band(
                        new BandId(persistedEvent.getId()),
                        persistedBand.getName(),
                        persistedBand.getMembers().stream()
                                .map(persistedMember -> Member.of(
                                        persistedMember.getId(),
                                        persistedMember.getName()
                                ))
                                .collect(toSet())
                ))
                .collect(toSet());

        return Event.builder()
                .id(new EventId(persistedEvent.getId()))
                .name(persistedEvent.getTitle())
                .comment(persistedEvent.getComment())
                .pictureUrl(persistedEvent.getImgUrl())
                .numberOfStars(persistedEvent.getNbStars())
                .bands(bands)
                .build();
    }
}
