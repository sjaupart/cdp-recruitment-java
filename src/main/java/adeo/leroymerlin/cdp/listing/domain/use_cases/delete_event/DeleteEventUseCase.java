package adeo.leroymerlin.cdp.listing.domain.use_cases.delete_event;

import adeo.leroymerlin.cdp.listing.domain.port.out.EventRepository;
import adeo.leroymerlin.cdp.listing.domain.use_cases.CommandHandler;

public class DeleteEventUseCase implements CommandHandler<DeleteEvent, DeleteEventUseCase.DeletedEvent> {

    private final EventRepository eventRepository;

    public DeleteEventUseCase(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public DeletedEvent proceed(DeleteEvent command) {
        eventRepository.delete(command.id());
        return new DeletedEvent();
    }

    public static class DeletedEvent {

    }
}
