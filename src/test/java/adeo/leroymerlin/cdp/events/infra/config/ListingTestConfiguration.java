package adeo.leroymerlin.cdp.events.infra.config;

import adeo.leroymerlin.cdp.events.MockRegistry;
import adeo.leroymerlin.cdp.events.domain.port.out.EventRepository;
import adeo.leroymerlin.cdp.events.domain.use_cases.delete_event.DeleteEventUseCase;
import adeo.leroymerlin.cdp.events.domain.use_cases.list_events.ListEventsUseCase;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.ActiveProfiles;

@TestConfiguration
@ActiveProfiles("test")
public class ListingTestConfiguration {

    @Bean
    public MockRegistry mockRegistry() {
        return new MockRegistry();
    }

    @Bean
    @Primary
    public EventRepository eventsRepository(MockRegistry registry) {
        return registry.mock(EventRepository.class);
    }

    @Bean
    @Primary
    public ListEventsUseCase listEventsUseCase(EventRepository eventRepository) {
        return new ListEventsUseCase(eventRepository);
    }

    @Bean
    @Primary
    public DeleteEventUseCase deleteEventUseCase(EventRepository eventRepository) {
        return new DeleteEventUseCase(eventRepository);
    }
}
