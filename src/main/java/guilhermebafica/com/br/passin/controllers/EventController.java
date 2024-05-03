package guilhermebafica.com.br.passin.controllers;

import guilhermebafica.com.br.passin.dto.attendee.AttendeeListResponseDTO;
import guilhermebafica.com.br.passin.dto.event.EventIdDTO;
import guilhermebafica.com.br.passin.dto.event.EventRequestDTO;
import guilhermebafica.com.br.passin.dto.event.EventResponseDTO;
import guilhermebafica.com.br.passin.services.AttendeeService;
import guilhermebafica.com.br.passin.services.EventService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController // Tell to Spring, that's a Controller
@RequestMapping("/events") // EndPoint config
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;
    private final AttendeeService attendeeService;

    @GetMapping("/{eventId}")
    public ResponseEntity<EventResponseDTO> getEvent(@PathVariable String eventId) {
        EventResponseDTO event = this.eventService.getEventDetail(eventId);

        return ResponseEntity.ok(event);
    }

    @PostMapping
    public ResponseEntity<EventIdDTO> createEvent(@RequestBody EventRequestDTO body, UriComponentsBuilder uriComponentsBuilder) {
        EventIdDTO eventIdDTO = this.eventService.createEvent(body);

        var uri = uriComponentsBuilder.path("/events/{id}").buildAndExpand(eventIdDTO.eventId()).toUri();

        return ResponseEntity.created(uri).body(eventIdDTO);
    }

    @GetMapping("/attendees/{eventId}")
    public ResponseEntity<AttendeeListResponseDTO> getEventAttendees(@PathVariable String eventId) {
        AttendeeListResponseDTO attendeeListResponseDTO = this.attendeeService.getEventsAttendee(eventId);

        return ResponseEntity.ok(attendeeListResponseDTO);
    }
}
