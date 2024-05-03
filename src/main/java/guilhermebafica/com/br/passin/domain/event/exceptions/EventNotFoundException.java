package guilhermebafica.com.br.passin.domain.event.exceptions;

public class EventNotFoundException extends RuntimeException {
    public EventNotFoundException(String message) {
        super(message); // Super calls the RuntimeException's constructor
    }
}
