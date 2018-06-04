package de.itasgmbh.jokes.rest;

import de.itasgmbh.jokes.repository.JokeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/jokes")
public class JokeRestController {
    private static final Logger log = LoggerFactory.getLogger(JokeRestController.class);

    private final JokeRepository repository;

    @Autowired
    public JokeRestController(JokeRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(path = "/random/{quantity}", method = GET)
    public ResponseEntity<?> getRandomJoke(@PathVariable("quantity") int quantity) {
        log.info("Rest call: GET random joke with quantity = {}", quantity);
        return new ResponseEntity<>(new JokeRestResponse(repository.findRandom( quantity, "de")), HttpStatus.OK);
    }
}
