package adeo.leroymerlin.cdp.events.infra.resource;

import adeo.leroymerlin.cdp.events.domain.model.EventId;
import adeo.leroymerlin.cdp.events.domain.use_cases.delete_event.DeleteEvent;
import adeo.leroymerlin.cdp.events.domain.use_cases.delete_event.DeleteEventUseCase;
import adeo.leroymerlin.cdp.events.infra.config.ListingTestConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ListingTestConfiguration.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class DeleteEventResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DeleteEventUseCase useCase;

    @Test
    void delete_an_event() throws Exception {
        when(useCase.proceed(new DeleteEvent(new EventId(1000L))))
                .thenReturn(new DeleteEventUseCase.DeletedEvent());

        mockMvc.perform(delete("/api/events/1000"))
                .andExpect(status().isNoContent());
    }
}