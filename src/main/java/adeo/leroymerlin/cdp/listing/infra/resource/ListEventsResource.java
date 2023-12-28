package adeo.leroymerlin.cdp.listing.infra.resource;

import adeo.leroymerlin.cdp.listing.domain.model.Event;
import adeo.leroymerlin.cdp.listing.domain.model.Member;
import adeo.leroymerlin.cdp.listing.domain.use_cases.list_events.ListEvents;
import adeo.leroymerlin.cdp.listing.domain.use_cases.list_events.ListEventsUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@RestController
@RequestMapping("/api/events")
public class ListEventsResource {

    private final ListEventsUseCase useCase;

    public ListEventsResource(ListEventsUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping
    public List<ListedEventDto> listEvents() {
        ListEventsUseCase.ListedEvents listedEvents = useCase.proceed(new ListEvents());

        return listedEvents.events().stream()
                .map(ListedEventDto::from)
                .toList();
    }

    public static class ListedEventDto {

        public Long id;
        public String title;
        public String comment;
        public String imgUrl;
        public Integer nbStars;
        public Set<BandDto> bands;

        public ListedEventDto(Long id, String title, String comment, String imgUrl, Integer nbStars, Set<BandDto> bands) {
            this.id = id;
            this.title = title;
            this.comment = comment;
            this.imgUrl = imgUrl;
            this.nbStars = nbStars;
            this.bands = bands;
        }

        public static ListedEventDto from(Event event) {
            Set<BandDto> bands = event.bands().stream()
                    .map(band -> new BandDto(
                                    band.id().value(),
                                    band.name(),
                                    from(band.members())
                            )
                    )
                    .collect(toSet());

            return new ListedEventDto(
                    event.id().value(),
                    event.name(),
                    event.comment(),
                    event.pictureUrl(),
                    event.numberOfStars(),
                    bands
            );
        }

        private static Set<MemberDto> from(Set<Member> members) {
            return members.stream()
                    .map(member -> new MemberDto(
                            member.id().value(),
                            member.name()
                    ))
                    .collect(toSet());
        }
    }

    public record BandDto(Long id, String name, Set<MemberDto> members) {

    }

    public record MemberDto(Long id, String name) {

    }
}
