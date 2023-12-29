package adeo.leroymerlin.cdp.events.infra.resource;

import adeo.leroymerlin.cdp.events.domain.model.Member;
import adeo.leroymerlin.cdp.events.domain.model.SearchCriteria;
import adeo.leroymerlin.cdp.events.domain.use_cases.list_events.ListEvents;
import adeo.leroymerlin.cdp.events.domain.use_cases.list_events.ListEventsUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/")
    public List<ListedEventDto> listEvents() {
        ListEventsUseCase.ListedEvents listedEvents = useCase.proceed(ListEvents.noCriteria());
        return toDto(listedEvents);
    }

    @GetMapping("/search/{query}")
    public List<ListedEventDto> searchEvents(@PathVariable String query) {
        ListEventsUseCase.ListedEvents listedEvents = useCase.proceed(new ListEvents(SearchCriteria.of(query)));
        return toDto(listedEvents);
    }

    private static List<ListedEventDto> toDto(ListEventsUseCase.ListedEvents listedEvents) {
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

        public static ListedEventDto from(ListEventsUseCase.ListedEvent event) {
            Set<BandDto> bands = event.bands().stream()
                    .map(BandDto::fromDomain)
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
    }

    public record BandDto(Long id, String name, Set<MemberDto> members) {
        public static BandDto fromDomain(ListEventsUseCase.ListedBand band) {
            return new BandDto(
                    band.id().value(),
                    band.name(),
                    band.members().stream()
                            .map(MemberDto::fromDomain)
                            .collect(toSet())
            );
        }
    }

    public record MemberDto(Long id, String name) {
        public static MemberDto fromDomain(Member member) {
            return new MemberDto(
                    member.id().value(),
                    member.name()
            );
        }
    }
}
