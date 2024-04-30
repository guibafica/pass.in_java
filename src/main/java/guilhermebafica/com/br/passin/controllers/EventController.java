package guilhermebafica.com.br.passin.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Tell to Spring, that's a Controller
@RequestMapping("/events") // EndPoint config
public class EventController {
    @GetMapping
    public ResponseEntity<String> getTest() {
        return ResponseEntity.ok("Success (event)");
    }
}
