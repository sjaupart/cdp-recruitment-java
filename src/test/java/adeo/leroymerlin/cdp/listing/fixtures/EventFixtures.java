package adeo.leroymerlin.cdp.listing.fixtures;

import adeo.leroymerlin.cdp.listing.domain.model.Event;

public class EventFixtures {

    public static Event EVENT_VIEILLES_CHARRUES = Event.builder()
            .name("Les Vieilles Charrues")
            .comment("good event")
            .build();
}
