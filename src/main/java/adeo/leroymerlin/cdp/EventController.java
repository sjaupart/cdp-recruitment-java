package adeo.leroymerlin.cdp;

import adeo.leroymerlin.cdp.listing.infra.adapter.hsql.Event;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void updateEvent(@PathVariable Long id, @RequestBody Event event) {
    }
}
