package guilhermebafica.com.br.passin.services;

import guilhermebafica.com.br.passin.domain.attendee.Attendee;
import guilhermebafica.com.br.passin.domain.event.Event;
import guilhermebafica.com.br.passin.domain.event.exceptions.EventNotFoundException;
import guilhermebafica.com.br.passin.dto.event.EventIdDTO;
import guilhermebafica.com.br.passin.dto.event.EventRequestDTO;
import guilhermebafica.com.br.passin.dto.event.EventResponseDTO;
import guilhermebafica.com.br.passin.repositories.EventRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final AttendeeService attendeeService;

    public EventResponseDTO getEventDetail(String eventId) {
        Event event = this.eventRepository.findById(eventId).orElseThrow(
            () -> new EventNotFoundException("Event not found with this ID")
        );
        List<Attendee> attendeeList = this.attendeeService.getAllAttendeesFromEvent(eventId);

        return new EventResponseDTO(event, attendeeList.size());
    }

    public EventIdDTO createEvent(EventRequestDTO eventDTO) {
        Event newEvent = new Event();

        newEvent.setTitle(eventDTO.title());
        newEvent.setDetails(eventDTO.details());
        newEvent.setMaximumAttendees(eventDTO.maximumAttendees());
        newEvent.setSlug(this.createSlug(eventDTO.title()));

        this.eventRepository.save(newEvent);

        return new EventIdDTO(newEvent.getId());
    }

    private String createSlug(String text) {
        String normalized = Normalizer.normalize(text, Normalizer.Form.NFD);

        return normalized
            .replaceAll("[\\p{InCOMBINING_DIACRITICAL_MARKS}]", "") // Remove accents
            .replaceAll("[^\\w\\s]", "") // Remove everything that isn't letters and numbers
            .replaceAll("\\s+", "-") // Replace all empty spaces with hyphen
            .toLowerCase();
    }
}
