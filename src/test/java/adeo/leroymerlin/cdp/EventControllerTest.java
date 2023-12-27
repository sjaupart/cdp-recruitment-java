package adeo.leroymerlin.cdp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventService service;

    @Test
    void find_all_events() throws Exception {
        Event event = anEvent();

        when(service.getEvents()).thenReturn(List.of(event));

        mockMvc.perform(get("/api/events/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("Les Vieilles Charrues")))
                .andExpect(jsonPath("$[0].comment", is("good event")))
                .andExpect(jsonPath("$[0].imgUrl", is("event_url.png")))
                .andExpect(jsonPath("$[0].nbStars", is(5)))
                .andExpect(jsonPath("$[0].bands", hasSize(1)))
                .andExpect(jsonPath("$[0].bands[0].name", is("Megadeth")))
                .andExpect(jsonPath("$[0].bands[0].members", hasSize(1)))
                .andExpect(jsonPath("$[0].bands[0].members[0].name", is("Queen Gertrude Hudson")))
        ;
    }

    private static Event anEvent() {
        Member member = new Member();
        member.setName("Queen Gertrude Hudson");

        Band band = new Band();
        band.setName("Megadeth");
        band.setMembers(Set.of(member));

        Event event = new Event();
        event.setTitle("Les Vieilles Charrues");
        event.setId(1L);
        event.setNbStars(5);
        event.setComment("good event");
        event.setImgUrl("event_url.png");
        event.setBands(Set.of(band));

        return event;
    }
}