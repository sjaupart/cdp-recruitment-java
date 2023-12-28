package adeo.leroymerlin.cdp.listing.infra.resource;

import adeo.leroymerlin.cdp.listing.domain.model.Event;
import adeo.leroymerlin.cdp.listing.domain.use_cases.list_events.ListEvents;
import adeo.leroymerlin.cdp.listing.domain.use_cases.list_events.ListEventsUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

        public ListedEventDto(Long id, String title, String comment, String imgUrl, Integer nbStars) {
            this.id = id;
            this.title = title;
            this.comment = comment;
            this.imgUrl = imgUrl;
            this.nbStars = nbStars;
        }

        public static ListedEventDto from(Event event) {
            return new ListedEventDto(
                    event.id().value(),
                    event.name(),
                    event.comment(),
                    event.pictureUrl(),
                    event.numberOfStars()
            );
        }
    }
}
