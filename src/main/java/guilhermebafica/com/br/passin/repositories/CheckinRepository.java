package guilhermebafica.com.br.passin.repositories;

import guilhermebafica.com.br.passin.domain.checkin.CheckIn;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckinRepository extends JpaRepository<CheckIn, Integer> {
}
