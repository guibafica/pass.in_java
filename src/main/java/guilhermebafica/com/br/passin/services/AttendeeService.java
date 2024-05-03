package guilhermebafica.com.br.passin.services;

import guilhermebafica.com.br.passin.domain.attendee.Attendee;
import guilhermebafica.com.br.passin.domain.checkin.CheckIn;
import guilhermebafica.com.br.passin.dto.attendee.AttendeeDetailsDTO;
import guilhermebafica.com.br.passin.dto.attendee.AttendeeListResponseDTO;
import guilhermebafica.com.br.passin.repositories.AttendeeRepository;

import guilhermebafica.com.br.passin.repositories.CheckinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttendeeService {
    private AttendeeRepository attendeeRepository;
    private CheckinRepository checkinRepository;

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
}
