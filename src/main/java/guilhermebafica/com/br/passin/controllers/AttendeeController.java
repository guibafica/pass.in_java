package guilhermebafica.com.br.passin.controllers;

import guilhermebafica.com.br.passin.dto.attendee.AttendeeBadgeResponseDTO;
import guilhermebafica.com.br.passin.services.AttendeeService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController // Tell to Spring, that's a Controller
@RequestMapping("/attendees") // EndPoint config
@RequiredArgsConstructor // The Lombok will auto create a constructor with the "Final" dependency
public class AttendeeController {
    private final AttendeeService attendeeService;

    @GetMapping("/{attendeeId}/badge")
    public ResponseEntity<AttendeeBadgeResponseDTO> getAttendeeBadge(@PathVariable String attendeeId, UriComponentsBuilder uriComponentsBuilder) {
        AttendeeBadgeResponseDTO response = this.attendeeService.getAttendeeBadge(attendeeId, uriComponentsBuilder);

        return ResponseEntity.ok(response);
    }
}
