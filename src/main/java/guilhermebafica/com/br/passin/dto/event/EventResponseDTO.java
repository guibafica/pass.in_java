package guilhermebafica.com.br.passin.dto.event;

import guilhermebafica.com.br.passin.domain.event.Event;

import lombok.Getter;

@Getter
public class EventResponseDTO {
    EventDetailDTO event;

    public EventResponseDTO(Event event, Integer numberOfAttendees) {
        // Must be in declared DTO order
        this.event = new EventDetailDTO(
            event.getId(),
            event.getTitle(),
            event.getDetails(),
            event.getSlug(),
            event.getMaximumAttendees(),
            numberOfAttendees
        );
    }
}
