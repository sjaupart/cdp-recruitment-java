package adeo.leroymerlin.cdp.listing.domain.use_cases;

import adeo.leroymerlin.cdp.listing.domain.model.Event;
import adeo.leroymerlin.cdp.listing.domain.port.out.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

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

        ListEventsUseCase.ListedEvents listedEvents = useCase.proceed(new ListEvents());

        assertThat(listedEvents.get()).hasSize(1);

        Event theEvent = listedEvents.get().stream().findFirst().get();
        assertThat(theEvent.name()).isEqualTo("Les Vieilles Charrues");
        assertThat(theEvent.comment()).isEqualTo("good event");
    }
}