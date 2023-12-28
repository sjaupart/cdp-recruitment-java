package adeo.leroymerlin.cdp.listing.infra.config;

import adeo.leroymerlin.cdp.listing.MockRegistry;
import adeo.leroymerlin.cdp.listing.domain.port.out.EventRepository;
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
}
