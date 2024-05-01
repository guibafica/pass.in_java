package guilhermebafica.com.br.passin.services;

import guilhermebafica.com.br.passin.domain.event.Event;
import guilhermebafica.com.br.passin.repositories.EventRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    public void getEventDetail(String eventId) {
        Event event = this.eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found with this ID"));

        return;
    }
}
