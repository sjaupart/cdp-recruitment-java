package adeo.leroymerlin.cdp.listing.domain.use_cases.list_events;

import adeo.leroymerlin.cdp.listing.domain.model.*;
import adeo.leroymerlin.cdp.listing.domain.port.out.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;
import java.util.stream.Stream;

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

        ListEventsUseCase.ListedEvent expectedResult = new ListEventsUseCase.ListedEvent(
                new EventId(1L),
                "Les Vieilles Charrues",
                "vieilles_charrues.png",
                5,
                "good event",
                Set.of(
                        new ListEventsUseCase.ListedBand(
                                new BandId(1006L),
                                "AC/DC",
                                Set.of(
                                        new Member(new MemberId(1020L), "Queen Abigail Cardenas"),
                                        new Member(new MemberId(1021L), "Queen Kimberly Jacobs"),
                                        new Member(new MemberId(1022L), "Queen Crystal Lynn"),
                                        new Member(new MemberId(1023L), "Queen Felix Nichols")
                                )
                        )
                )
        );

        assertThat(listedEvents.events()).containsExactly(expectedResult);
    }

    private static Stream<Arguments> eventsMatchingPattern() {
        return Stream.of(
                Arguments.of("o", Set.of("Les Vieilles Charrues", "Download Festival")),
                Arguments.of("yn", Set.of("Les Vieilles Charrues")),
                Arguments.of("Coo", Set.of("Download Festival")),
                Arguments.of("coo", Set.of())
        );
    }

    @MethodSource("eventsMatchingPattern")
    @ParameterizedTest
    void list_events_matching_criteria(String pattern, Set<String> eventNames) {
        when(eventRepository.events())
                .thenReturn(Set.of(EVENT_VIEILLES_CHARRUES, EVENT_DOWNLOAD_FESTIVAL));

        ListEventsUseCase.ListedEvents listedEvents = useCase.proceed(new ListEvents(SearchCriteria.of(pattern)));

        assertThat(listedEvents.names()).containsExactlyInAnyOrderElementsOf(eventNames);
    }
}