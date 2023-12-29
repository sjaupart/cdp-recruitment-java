package adeo.leroymerlin.cdp.events.infra.config;

import adeo.leroymerlin.cdp.events.domain.port.out.EventRepository;
import adeo.leroymerlin.cdp.events.domain.use_cases.delete_event.DeleteEventUseCase;
import adeo.leroymerlin.cdp.events.domain.use_cases.list_events.ListEventsUseCase;
import adeo.leroymerlin.cdp.events.infra.adapter.InMemoryEventRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!test")
public class ListingConfiguration {

    @Bean
    public EventRepository eventsRepository(adeo.leroymerlin.cdp.events.infra.adapter.hsql.EventRepository eventRepository) {
        return new InMemoryEventRepository(eventRepository);
    }

    @Bean
    public ListEventsUseCase listEventsUseCase(EventRepository eventRepository) {
        return new ListEventsUseCase(eventRepository);
    }

    @Bean
    public DeleteEventUseCase deleteEventUseCase(EventRepository eventRepository) {
        return new DeleteEventUseCase(eventRepository);
    }
}
