package adeo.leroymerlin.cdp.listing.fixtures;

import adeo.leroymerlin.cdp.listing.domain.model.Event;
import adeo.leroymerlin.cdp.listing.domain.model.EventId;

public class EventFixtures {

    public static Event EVENT_VIEILLES_CHARRUES = Event.builder()
            .id(new EventId(1L))
            .name("Les Vieilles Charrues")
            .pictureUrl("vieilles_charrues.png")
            .numberOfStars(5)
            .comment("good event")
            .build();
}
