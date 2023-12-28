package adeo.leroymerlin.cdp.listing.infra.resource;

import adeo.leroymerlin.cdp.listing.domain.model.SearchCriteria;
import adeo.leroymerlin.cdp.listing.domain.use_cases.list_events.ListEvents;
import adeo.leroymerlin.cdp.listing.domain.use_cases.list_events.ListEventsUseCase;
import adeo.leroymerlin.cdp.listing.infra.config.ListingTestConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;

import static adeo.leroymerlin.cdp.listing.fixtures.EventFixtures.LISTED_EVENT_VIEILLES_CHARRUES;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = ListingTestConfiguration.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ListEventsResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ListEventsUseCase useCase;

    @Test
    void find_all_events() throws Exception {
        Set<ListEventsUseCase.ListedEvent> events = Set.of(LISTED_EVENT_VIEILLES_CHARRUES);

        when(useCase.proceed(ListEvents.noCriteria())).thenReturn(new ListEventsUseCase.ListedEvents(events));

        mockMvc.perform(get("/api/events/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("Les Vieilles Charrues")))
                .andExpect(jsonPath("$[0].comment", is("good event")))
                .andExpect(jsonPath("$[0].imgUrl", is("vieilles_charrues.png")))
                .andExpect(jsonPath("$[0].nbStars", is(5)))
                .andExpect(jsonPath("$[0].bands", hasSize(1)))
                .andExpect(jsonPath("$[0].bands[0].name", is("AC/DC")))
                .andExpect(jsonPath("$[0].bands[0].members", hasSize(4)))
                .andExpect(jsonPath("$[0].bands[0].members[*].name", containsInAnyOrder(
                        "Queen Abigail Cardenas",
                        "Queen Kimberly Jacobs",
                        "Queen Crystal Lynn",
                        "Queen Felix Nichols"
                )));
    }

    @Test
    void list_events_according_to_a_given_criteria() throws Exception {
        Set<ListEventsUseCase.ListedEvent> events = Set.of(LISTED_EVENT_VIEILLES_CHARRUES);

        when(useCase.proceed(new ListEvents(SearchCriteria.of("cob")))).thenReturn(new ListEventsUseCase.ListedEvents(events));

        mockMvc.perform(get("/api/events/search/cob"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("Les Vieilles Charrues")))
                .andExpect(jsonPath("$[0].comment", is("good event")))
                .andExpect(jsonPath("$[0].imgUrl", is("vieilles_charrues.png")))
                .andExpect(jsonPath("$[0].nbStars", is(5)))
                .andExpect(jsonPath("$[0].bands", hasSize(1)))
                .andExpect(jsonPath("$[0].bands[0].name", is("AC/DC")))
                .andExpect(jsonPath("$[0].bands[0].members", hasSize(4)))
                .andExpect(jsonPath("$[0].bands[0].members[*].name", containsInAnyOrder(
                        "Queen Abigail Cardenas",
                        "Queen Kimberly Jacobs",
                        "Queen Crystal Lynn",
                        "Queen Felix Nichols"
                )));
    }

    @Test
    void no_event_according_to_the_given_criteria() throws Exception {
        when(useCase.proceed(new ListEvents(SearchCriteria.of("azerty")))).thenReturn(new ListEventsUseCase.ListedEvents(Set.of()));

        mockMvc.perform(get("/api/events/search/azerty"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(0)));
    }
}