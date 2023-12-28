package adeo.leroymerlin.cdp.listing.infra.adapter;

import adeo.leroymerlin.cdp.listing.domain.model.*;
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
        Set<Band> bands = persistedEvent.getBands().stream()
                .map(persistedBand -> new Band(
                        new BandId(persistedEvent.getId()),
                        persistedBand.getName(),
                        persistedBand.getMembers().stream()
                                .map(persistedMember -> new Member(
                                        new MemberId(persistedMember.getId()),
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
