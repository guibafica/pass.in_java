package guilhermebafica.com.br.passin.repositories;

import guilhermebafica.com.br.passin.domain.event.Event;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, String> {
}
