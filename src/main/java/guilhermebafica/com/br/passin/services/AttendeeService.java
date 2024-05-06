package guilhermebafica.com.br.passin.services;

import guilhermebafica.com.br.passin.domain.attendee.Attendee;
import guilhermebafica.com.br.passin.domain.attendee.exceptions.AttendeeAlreadyExistException;
import guilhermebafica.com.br.passin.domain.attendee.exceptions.AttendeeNotFoundException;
import guilhermebafica.com.br.passin.domain.checkin.CheckIn;
import guilhermebafica.com.br.passin.dto.attendee.AttendeeBadgeResponseDTO;
import guilhermebafica.com.br.passin.dto.attendee.AttendeeDetailsDTO;
import guilhermebafica.com.br.passin.dto.attendee.AttendeeListResponseDTO;
import guilhermebafica.com.br.passin.dto.attendee.AttendeeBadgeDTO;
import guilhermebafica.com.br.passin.repositories.AttendeeRepository;

import guilhermebafica.com.br.passin.repositories.CheckinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttendeeService {
    // "final" is used because there isn't a constructor in this class
    // or I can use "@AllArgsConstructor"
    private final AttendeeRepository attendeeRepository;
    private final CheckinRepository checkinRepository;

    public List<Attendee> getAllAttendeesFromEvent(String eventId) {
        return this.attendeeRepository.findByEventId(eventId);
    }

    public AttendeeListResponseDTO getEventsAttendee(String eventId) {
        List<Attendee> attendeeList = this.getAllAttendeesFromEvent(eventId);

        List<AttendeeDetailsDTO> attendeeDetailsList = attendeeList.stream().map(attendee -> {
            Optional<CheckIn> checkIn = this.checkinRepository.findByAttendeeId(attendee.getId());

            // Same as a ternary if
            LocalDateTime checkedInAt = checkIn.<LocalDateTime>map(CheckIn::getCreatedAt).orElse(null);

            return new AttendeeDetailsDTO(
                attendee.getId(),
                attendee.getName(),
                attendee.getEmail(),
                attendee.getCreatedAt(),
                checkedInAt
            );
        }).toList();

        return new AttendeeListResponseDTO(attendeeDetailsList);
    }

    public void verifyAttendeeSubscription(String email, String eventId) {
        Optional<Attendee> isAttendeeRegistered = this.attendeeRepository.findByEventIdAndEmail(eventId, email);

        if (isAttendeeRegistered.isPresent()) throw new AttendeeAlreadyExistException("Attendee is already registered");
    }

    public Attendee registerAttendee(Attendee newAttendee) {
        this.attendeeRepository.save(newAttendee);

        return newAttendee;
    }

    public AttendeeBadgeResponseDTO getAttendeeBadge(String attendeeId, UriComponentsBuilder uriComponentsBuilder) {
        Attendee attendee = this.attendeeRepository.findById(attendeeId).orElseThrow(
            () -> new AttendeeNotFoundException("Attendee not found with this ID")
        );

        var uri = uriComponentsBuilder
            .path("/attendees/{attendeeId}/check-in")
            .buildAndExpand(attendeeId)
            .toUri()
            .toString();

        AttendeeBadgeDTO attendeeBadgeDTO = new AttendeeBadgeDTO(
            attendee.getName(),
            attendee.getEmail(),
            uri,
            attendee.getEvent().getId()
        );

        return new AttendeeBadgeResponseDTO(attendeeBadgeDTO);
    }
}
