package adeo.leroymerlin.cdp.listing.fixtures;

import adeo.leroymerlin.cdp.listing.domain.model.*;
import adeo.leroymerlin.cdp.listing.domain.use_cases.list_events.ListEventsUseCase;

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

    public static Band BAND_DEEP_PURPLE = new Band(
            new BandId(1007L),
            "Deep Purple",
            Set.of(new Member(new MemberId(1024L), "Queen Victoria Cooper"))
    );

    public static Event EVENT_VIEILLES_CHARRUES = Event.builder()
            .id(new EventId(1L))
            .name("Les Vieilles Charrues")
            .pictureUrl("vieilles_charrues.png")
            .numberOfStars(5)
            .comment("good event")
            .bands(Set.of(BAND_ACDC))
            .build();

    public static Event EVENT_DOWNLOAD_FESTIVAL = Event.builder()
            .id(new EventId(1003L))
            .name("Download Festival")
            .pictureUrl("download_festival.png")
            .numberOfStars(4)
            .comment("no comment")
            .bands(Set.of(BAND_DEEP_PURPLE))
            .build();

    public static ListEventsUseCase.ListedBand LISTED_BAND_ACDC = new ListEventsUseCase.ListedBand(
            new BandId(1006L),
            "AC/DC",
            Set.of(
                    new Member(new MemberId(1020L), "Queen Abigail Cardenas"),
                    new Member(new MemberId(1021L), "Queen Kimberly Jacobs"),
                    new Member(new MemberId(1022L), "Queen Crystal Lynn"),
                    new Member(new MemberId(1023L), "Queen Felix Nichols")
            )
    );

    public static ListEventsUseCase.ListedEvent LISTED_EVENT_VIEILLES_CHARRUES = new ListEventsUseCase.ListedEvent(
            new EventId(1L),
            "Les Vieilles Charrues",
            "vieilles_charrues.png",
            5,
            "good event",
            Set.of(LISTED_BAND_ACDC)
    );
}
