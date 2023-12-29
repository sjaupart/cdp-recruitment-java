package adeo.leroymerlin.cdp.events.domain.use_cases.delete_event;

import adeo.leroymerlin.cdp.events.domain.model.EventId;
import adeo.leroymerlin.cdp.events.domain.use_cases.Command;

import java.util.Objects;

public class DeleteEvent implements Command {

    private EventId id;

    public DeleteEvent(EventId id) {
        this.id = id;
    }

    public EventId id() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeleteEvent that = (DeleteEvent) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
