package adeo.leroymerlin.cdp.events.domain.use_cases.delete_event;

import adeo.leroymerlin.cdp.events.domain.port.out.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static adeo.leroymerlin.cdp.events.fixtures.EventFixtures.EVENT_ID_VIEILLES_CHARRUES;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeleteEventUseCaseTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private DeleteEventUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new DeleteEventUseCase(eventRepository);
    }

    @Test
    void delete_an_event() {
        useCase.proceed(new DeleteEvent(EVENT_ID_VIEILLES_CHARRUES));
        verify(eventRepository).delete(EVENT_ID_VIEILLES_CHARRUES);
    }
}