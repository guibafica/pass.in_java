package guilhermebafica.com.br.passin.services;

import guilhermebafica.com.br.passin.domain.attendee.Attendee;
import guilhermebafica.com.br.passin.domain.event.Event;
import guilhermebafica.com.br.passin.dto.event.EventResponseDTO;
import guilhermebafica.com.br.passin.repositories.AttendeeRepository;
import guilhermebafica.com.br.passin.repositories.EventRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final AttendeeRepository attendeeRepository;

    public EventResponseDTO getEventDetail(String eventId) {
        Event event = this.eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found with this ID"));
        List<Attendee> attendeeList = this.attendeeRepository.findByEventId(eventId);

        return new EventResponseDTO(event, attendeeList.size());
    }
}
