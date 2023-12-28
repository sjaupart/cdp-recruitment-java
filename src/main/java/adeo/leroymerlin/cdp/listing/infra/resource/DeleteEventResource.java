package adeo.leroymerlin.cdp.listing.infra.resource;

import adeo.leroymerlin.cdp.listing.domain.model.EventId;
import adeo.leroymerlin.cdp.listing.domain.use_cases.delete_event.DeleteEvent;
import adeo.leroymerlin.cdp.listing.domain.use_cases.delete_event.DeleteEventUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events")
public class DeleteEventResource {

    private final DeleteEventUseCase useCase;

    public DeleteEventResource(DeleteEventUseCase useCase) {
        this.useCase = useCase;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAnEvent(@PathVariable Long id) {
        useCase.proceed(new DeleteEvent(new EventId(id)));
    }
}
