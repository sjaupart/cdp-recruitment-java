package adeo.leroymerlin.cdp.listing.domain.use_cases.list_events;

import adeo.leroymerlin.cdp.listing.domain.model.*;
import adeo.leroymerlin.cdp.listing.domain.port.out.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static adeo.leroymerlin.cdp.listing.fixtures.EventFixtures.EVENT_DOWNLOAD_FESTIVAL;
import static adeo.leroymerlin.cdp.listing.fixtures.EventFixtures.EVENT_VIEILLES_CHARRUES;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListEventsUseCaseTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private ListEventsUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new ListEventsUseCase(eventRepository);
    }

    @Test
    void list_events() {
        when(eventRepository.events())
                .thenReturn(Set.of(EVENT_VIEILLES_CHARRUES));

        ListEventsUseCase.ListedEvents listedEvents = useCase.proceed(ListEvents.noCriteria());

        Event expectedResult = Event.builder()
                .id(new EventId(1L))
                .name("Les Vieilles Charrues")
                .pictureUrl("vieilles_charrues.png")
                .numberOfStars(5)
                .comment("good event")
                .bands(Set.of(
                        new Band(
                                new BandId(1006L),
                                "AC/DC",
                                Set.of(
                                        new Member(new MemberId(1020L), "Queen Abigail Cardenas"),
                                        new Member(new MemberId(1021L), "Queen Kimberly Jacobs"),
                                        new Member(new MemberId(1022L), "Queen Crystal Lynn"),
                                        new Member(new MemberId(1023L), "Queen Felix Nichols")
                                )
                        )
                ))
                .build();

        assertThat(listedEvents.events()).containsExactly(expectedResult);
    }

    @Test
    void list_events_matching_criteria() {
        when(eventRepository.events())
                .thenReturn(Set.of(EVENT_VIEILLES_CHARRUES, EVENT_DOWNLOAD_FESTIVAL));

        ListEventsUseCase.ListedEvents listedEvents = useCase.proceed(new ListEvents(SearchCriteria.of("o")));
        assertThat(listedEvents.names()).containsExactlyInAnyOrder("Les Vieilles Charrues", "Download Festival");

        ListEventsUseCase.ListedEvents listedEventsWithYn = useCase.proceed(new ListEvents(SearchCriteria.of("yn")));
        assertThat(listedEventsWithYn.names()).containsExactlyInAnyOrder("Les Vieilles Charrues");

        ListEventsUseCase.ListedEvents listedEventsWithCoo = useCase.proceed(new ListEvents(SearchCriteria.of("Coo")));
        assertThat(listedEventsWithCoo.names()).containsExactlyInAnyOrder("Download Festival");

        ListEventsUseCase.ListedEvents listedEventsWithLowercasedCoo = useCase.proceed(new ListEvents(SearchCriteria.of("coo")));
        assertThat(listedEventsWithLowercasedCoo.names()).containsExactlyInAnyOrder();
    }
}