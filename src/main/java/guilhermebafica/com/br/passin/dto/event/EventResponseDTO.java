package guilhermebafica.com.br.passin.dto.event;

import guilhermebafica.com.br.passin.domain.event.Event;

public class EventResponseDTO {
    EventDetailDTO event;

    public EventResponseDTO(Event event, Integer numberOfAttendees) {
        // Must be in declared DTO order
        this.event = new EventDetailDTO(
            event.getId(),
            event.getTitle(),
            event.getDetail(),
            event.getSlug(),
            event.getMaximumAttendees(),
            numberOfAttendees
        );
    }
}
