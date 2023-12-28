package adeo.leroymerlin.cdp;

import adeo.leroymerlin.cdp.listing.infra.adapter.hsql.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteEvent(@PathVariable Long id) {
        eventService.delete(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void updateEvent(@PathVariable Long id, @RequestBody Event event) {
    }
}
