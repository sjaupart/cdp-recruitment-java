package adeo.leroymerlin.cdp.events.domain.use_cases.list_events;

import adeo.leroymerlin.cdp.events.domain.model.BandId;
import adeo.leroymerlin.cdp.events.domain.model.Member;
import adeo.leroymerlin.cdp.events.domain.model.SearchCriteria;
import adeo.leroymerlin.cdp.events.domain.port.out.EventRepository;
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
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static adeo.leroymerlin.cdp.events.fixtures.EventFixtures.*;
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
                EVENT_ID_VIEILLES_CHARRUES,
                "Les Vieilles Charrues",
                "vieilles_charrues.png",
                5,
                "good event",
                Set.of(
                        new ListEventsUseCase.ListedBand(
                                new BandId(1006L),
                                "AC/DC",
                                Set.of(
                                        Member.of(1020L, "Queen Abigail Cardenas"),
                                        Member.of(1021L, "Queen Kimberly Jacobs"),
                                        Member.of(1022L, "Queen Crystal Lynn"),
                                        Member.of(1023L, "Queen Felix Nichols")
                                )
                        )
                )
        );

        Set<ListEventsUseCase.ListedEvent> events = listedEvents.events();
        Set<ListEventsUseCase.ListedBand> bands = events.stream().flatMap(e -> e.bands().stream()).collect(Collectors.toSet());

        assertThat(events).containsExactly(expectedResult);
        assertThat(events).extracting("name")
                .containsExactlyInAnyOrder("Les Vieilles Charrues [1]");
        assertThat(bands).extracting("name")
                .containsExactlyInAnyOrder("AC/DC [4]");
    }

    private static Stream<Arguments> eventsMatchingPattern() {
        return Stream.of(
                Arguments.of("o", Set.of("Les Vieilles Charrues [1]", "Download Festival [1]")),
                Arguments.of("yn", Set.of("Les Vieilles Charrues [1]")),
                Arguments.of("Coo", Set.of("Download Festival [1]")),
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