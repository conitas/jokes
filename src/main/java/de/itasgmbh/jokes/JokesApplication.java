package de.itasgmbh.jokes;

import de.itasgmbh.jokes.model.Joke;
import de.itasgmbh.jokes.repository.JokeReactiveRepository;
import de.itasgmbh.jokes.repository.JokeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Example;

import java.nio.file.Files;

@SpringBootApplication
public class JokesApplication implements CommandLineRunner {
    private final JokeRepository repository;
    private final JokeReactiveRepository reactiveRepository;

    @Autowired
    public JokesApplication(JokeRepository repository, JokeReactiveRepository reactiveRepository) {
        this.repository = repository;
        this.reactiveRepository = reactiveRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(JokesApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        if (!repository.findOne(Example.of(new Joke())).isPresent()) {
            Files.readAllLines(new ClassPathResource("jokes.txt").getFile().toPath())
                    .forEach(jokeLine -> {
                        // save a couple of jokes
                        final String[] parts = jokeLine.split("###");
                        switch (parts.length) {
                            case 1:
                                repository.save(new Joke(parts[0]));
                                return;
                            case 2:
                                repository.save(new Joke(parts[0], parts[1]));
                                return;
                            default:
                        }

                    });
        }

        System.out.println("-------------------------------");
        System.out.format(" Found jokes with database: %d", repository.count());

    }
}
