package adeo.leroymerlin.cdp.events.fixtures;

import adeo.leroymerlin.cdp.events.domain.model.*;
import adeo.leroymerlin.cdp.events.domain.use_cases.list_events.ListEventsUseCase;

import java.util.Set;

public class EventFixtures {

    public static Band BAND_ACDC = new Band(
            new BandId(1006L),
            "AC/DC",
            Set.of(
                    Member.of(1020L, "Queen Abigail Cardenas"),
                    Member.of(1021L, "Queen Kimberly Jacobs"),
                    Member.of(1022L, "Queen Crystal Lynn"),
                    Member.of(1023L, "Queen Felix Nichols")
            )
    );

    public static Band BAND_DEEP_PURPLE = new Band(
            new BandId(1007L),
            "Deep Purple",
            Set.of(Member.of(1024L, "Queen Victoria Cooper"))
    );

    public static final EventId EVENT_ID_VIEILLES_CHARRUES = new EventId(1L);
    public static final EventId EVENT_ID_DOWNLOAD_FESTIVAL = new EventId(1003L);

    public static Event EVENT_VIEILLES_CHARRUES = Event.builder()
            .id(EVENT_ID_VIEILLES_CHARRUES)
            .name("Les Vieilles Charrues")
            .pictureUrl("vieilles_charrues.png")
            .numberOfStars(5)
            .comment("good event")
            .bands(Set.of(BAND_ACDC))
            .build();

    public static Event EVENT_DOWNLOAD_FESTIVAL = Event.builder()
            .id(EVENT_ID_DOWNLOAD_FESTIVAL)
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
                    Member.of(1020L, "Queen Abigail Cardenas"),
                    Member.of(1021L, "Queen Kimberly Jacobs"),
                    Member.of(1022L, "Queen Crystal Lynn"),
                    Member.of(1023L, "Queen Felix Nichols")
            )
    );

    public static ListEventsUseCase.ListedEvent LISTED_EVENT_VIEILLES_CHARRUES = new ListEventsUseCase.ListedEvent(
            EVENT_ID_VIEILLES_CHARRUES,
            "Les Vieilles Charrues",
            "vieilles_charrues.png",
            5,
            "good event",
            Set.of(LISTED_BAND_ACDC)
    );
}
