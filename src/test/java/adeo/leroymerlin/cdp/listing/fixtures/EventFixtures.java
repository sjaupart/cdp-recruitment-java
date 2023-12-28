package adeo.leroymerlin.cdp.listing.fixtures;

import adeo.leroymerlin.cdp.listing.domain.model.*;

import java.util.Set;

public class EventFixtures {

    public static Band BAND_ACDC = new Band(
            new BandId(1006L),
            "AC/DC",
            Set.of(
                    new Member(new MemberId(1020L), "Queen Abigail Cardenas"),
                    new Member(new MemberId(1021L), "Queen Kimberly Jacobs"),
                    new Member(new MemberId(1022L), "Queen Crystal Lynn"),
                    new Member(new MemberId(1023L), "Queen Felix Nichols")
            )
    );

    public static Event EVENT_VIEILLES_CHARRUES = Event.builder()
            .id(new EventId(1L))
            .name("Les Vieilles Charrues")
            .pictureUrl("vieilles_charrues.png")
            .numberOfStars(5)
            .comment("good event")
            .bands(Set.of(BAND_ACDC))
            .build();
}
