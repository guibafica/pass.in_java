package guilhermebafica.com.br.passin.controllers;

import guilhermebafica.com.br.passin.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Tell to Spring, that's a Controller
@RequestMapping("/events") // EndPoint config
@RequiredArgsConstructor
public class EventController {
    private final EventService service;
    @GetMapping("/{eventId}")
    public ResponseEntity<String> getEvent(@PathVariable String eventId) {
        this.service.getEventDetail(eventId);

        return ResponseEntity.ok("Success (event)");
    }
}
