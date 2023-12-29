package adeo.leroymerlin.cdp.events.domain.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EventTest {

    @Test
    void name_is_mandatory() {
        Event.Builder eventBuilder = Event.builder()
                .name(null);

        assertThatThrownBy(eventBuilder::build)
                .isInstanceOf(InvalidEvent.class)
                .hasMessage("Name is mandatory");
    }
}